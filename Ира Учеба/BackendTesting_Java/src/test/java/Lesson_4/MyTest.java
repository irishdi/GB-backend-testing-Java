package Lesson_4;

import Lesson_4.dto.response.AccountInfoResponse;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MyTest{

    private static String API_KEY = "0fe8dd3ed41646c4aa1fe227be0d07b1";
    private static String SEARCH_URL = "recipes/complexSearch";
    private static String CUISINE_URL = "recipes/cuisine";

    static Properties properties = new Properties();

    ResponseSpecification responseSpecification = null;
    RequestSpecification requestSpecification = null;

    @BeforeAll
    static void setup() throws IOException {
        //подключение AllureReports
        RestAssured.filters(new AllureRestAssured());

        RestAssured.baseURI = "https://api.spoonacular.com";
        //генерация логов в случае падения тестов:
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        //чтение properties из файла
        FileInputStream fileInputStream;
        fileInputStream = new FileInputStream("src/test/resources/my.properties");
        properties.load(fileInputStream);
    }

    @BeforeEach
    void beforeTest(){
        //основные проверки для Response
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectContentType(ContentType.JSON)
                .build();
        //спецификация для Request
        requestSpecification = new RequestSpecBuilder()
                .addParam("apiKey", API_KEY)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.ANY)
                .build();
    }
    @Test
    @DisplayName("RecipeComplexSearch")
    void getRecipeComplexSearch() {
        given()
                .spec(requestSpecification)
                .param("query", "pasta")
                .param("number", 3)
                .log()
                .parameters()
                .expect()
                //использование спецификации Response
                .spec(responseSpecification)
                //расширение спецификации
                .body(containsString("Pasta With Tuna"))
                .body("offset", is(0))
                .body("number", is(3))
                .log()
                .body()
                .when()
                .get(SEARCH_URL);
    }

    @Test
    @DisplayName("Search Recipes NoParams")
    void getRecipeSearchNoParams(){
        AccountInfoResponse response1 = given()
                .spec(requestSpecification)
                .log()
                .parameters()
                .expect()
                .spec(responseSpecification)
                .when()
                .get(SEARCH_URL)
                .prettyPeek()
                .then()
                .extract()
                .body()
                .as(AccountInfoResponse.class);
        assertThat(response1.getNumber(), equalTo(10));
        assertThat(response1.getOffset(), equalTo(0));
        assertThat(response1.getData().get(0).getTitle(),
                containsString("Cauliflower, Brown Rice, and Vegetable Fried Rice"));
        assertThat(response1.getData().get(1).getImage(), notNullValue());
        assertThat(response1.getData().get(2).getImageType(), containsString("jpg"));
    }

    @Test
    @DisplayName("SearchRecipesSomeParams")
    void getRecipeSearchSomeParams(){
        AccountInfoResponse response = given().spec(requestSpecification)
                .param("cuisine", properties.get("cuisine"))
                .param("type", "main course")
                .param("query", "pasta")
                .param("number", 2)
                .param("offset", 0)
                .log().parameters()
                .when().get(SEARCH_URL)
                .prettyPeek()
                .then()
                .spec(responseSpecification)
                //распарсить JSON
                .extract()
                .body()
                .as(AccountInfoResponse.class);
        assertThat(response.getTotalResults(), equalTo(3));
    }

    @Test
    @DisplayName("BodyHasGreekRecipes")
    void getGreekRecipes(){
        String result1 = given().spec(requestSpecification)//настройки
                .param("cuisine", properties.get("cuisine"))
                .param("type", "main course")
                .param("query", "pasta")
                .param("number", 2)
                .param("offset", 2)
                .when().get(SEARCH_URL) //шаги
                .then()
                .spec(responseSpecification)//проверки
                .extract().response().jsonPath().getString("results.title");
        assertThat(result1, containsString("Greek"));
    }

    @Test
    @DisplayName("SearchNotAuthorized")
    void getNotAuthorizedSearch(){
        //Rest-assured проверки в блоке given
        given().param("cuisine", properties.get("cuisine"))
                .param("type", "main course")
                .param("query", "pasta")
                .param("number", 2)
                .log().parameters()
                .expect().statusCode(401)
                .when().get(SEARCH_URL)
                .prettyPeek();
    }

    @Test
    @DisplayName("ClassifyCuisineByParams")
    void postCuisineClassifyByParams(){
        //Использовать queryParam вместо param для авторизации POST
        //Как в этом случае составить RequestSpec для POST запроса?
        given().queryParam("apiKey", API_KEY)
                .param("language", "en")
                .param("title", "sushi")
                .param("ingredientList", "cucumber")
                .log().parameters()
                .expect()
                .spec(responseSpecification)
                .body("cuisine", is("Japanese"))
                .body("confidence", Matchers.greaterThanOrEqualTo(0.85F))
                .when().post(CUISINE_URL)
                .prettyPeek();
    }

    @Test
    @DisplayName("ClassifyCuisineNoParams")
    void postCuisineNoParams(){
                given()
                        .queryParam("apiKey", API_KEY)
                        .log().parameters()
                        .expect()
                        .spec(responseSpecification)
                        .body("cuisine", is ("Mediterranean"))
                        .body("confidence", Matchers.lessThan(0.1F))
                        .when()
                        .post(CUISINE_URL)
                        .prettyPeek();
    }

}
