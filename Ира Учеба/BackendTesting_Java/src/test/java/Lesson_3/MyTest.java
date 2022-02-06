package Lesson_3;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MyTest {

    private static String API_KEY = "0fe8dd3ed41646c4aa1fe227be0d07b1";
    private static String SEARCH_URL = "recipes/complexSearch";
    private static String CUISINE_URL = "recipes/cuisine";

    static Properties properties = new Properties();

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

    @Test
    @DisplayName("RecipeComplexSearch")
    void getRecipeComplexSearch() {
        String actual = given()
                .param("apiKey", API_KEY)
                .param("query", "pasta")
                .param("number", 3)
                .log()
                .parameters()
                .expect()
                .statusCode(200)
                .time(Matchers.lessThan(3000L))
                .body("offset", is(0))
                .body("number", is(3))
                .body("results", Matchers.arrayWithSize(3))
                .log()
                .body()
                .when()
                .get(SEARCH_URL)
                .body()
                .asPrettyString();
//// не работает у меня игнорирование полей в JSON. Можете понять почему?
//        String expected = getResourceAsString("expected.json");
//        JsonAssert.assertJsonEquals(
//                expected,
//                actual,
//                JsonAssert.when(IGNORING_ARRAY_ORDER)
//        );

    }

    @Test
    @DisplayName("Search Recipes NoParams")
    void getRecipeSearchNoParams(){
        given()
                .param("apiKey", API_KEY)
                .log().parameters()
                .expect().body("offset",is(0))
                .when().get(SEARCH_URL)
                .then().statusCode(200);

    }

    @Test
    @DisplayName("SearchRecipesSomeParams")
    void getRecipeSearchSomeParams(){
        Integer result = given().param("apiKey", API_KEY)
                .param("cuisine", properties.get("cuisine"))
                .param("type", "main course")
                .param("query", "pasta")
                .param("number", 2)
                .param("offset", 0)
                .log().parameters()
                .when().get(SEARCH_URL)
                .prettyPeek()
                .then().statusCode(200)
                .contentType(MyProperties.applicationJson)
                //распарсить JSON
                .extract().response().jsonPath()
                //получить значение из поля totalResults
                .getInt("totalResults");
        assertThat(result, equalTo(3));
    }

    @Test
    @DisplayName("BodyHasGreekRecipes")
    void getGreekRecipes(){
        String result1 = given().param("apiKey", API_KEY)//настройки
                .param("cuisine", properties.get("cuisine"))
                .param("type", "main course")
                .param("query", "pasta")
                .param("number", 2)
                .param("offset", 2)
                .when().get(SEARCH_URL) //шаги
                .then().statusCode(200)//проверки
                .extract().response().jsonPath().getString("results.title");
        assertThat(result1, containsString("Greek"));
    }

//    @ParameterizedTest(name = "{index}. + {Meal Types}")
//    @ValueSource()
//    @DisplayName("GetRecipeTypesFromFile")
//    void getRecipeTypesFromFile(){
//        given().param("apiKey", API_KEY)
//                .param("type", from csv file)
//                .when().get(SEARCH_URL)
//                .then().statusCode(200);
//    }

    @Test
    @DisplayName("SearchNotAuthorized")
    void getNotAuthorizedSearch(){
        //Rest-assured проверки в блоке given
        given().param("cuisine", properties.get("cuisine"))
                .param("type", "main course")
                .param("query", "pasta")
                .param("number", 2)
                .expect().statusCode(401)
                .when().get(SEARCH_URL);
    }

    @Test
    @DisplayName("ClassifyCuisineByParams")
    void postCuisineClassifyByParams(){
        //Использовать queryParam вместо param для авторизации POST
        given().queryParam("apiKey", API_KEY)
                .param("language", "en")
                .param("title", "sushi")
                .param("ingredientList", "cucumber")
                .expect().body("cuisine", is("Japanese"))
                .when().post(CUISINE_URL)
                .then().statusCode(200);
    }

    @Test
    @DisplayName("ClassifyCuisineNoParams")
    void postCuisineNotSupportedLang(){
                given()
                        .queryParam("apiKey", API_KEY)
                        .expect()
                        .body("cuisine", is ("Mediterranean"))
                        .when()
                        .post(CUISINE_URL)
                        .then()
                        .statusCode(200);
    }
}
