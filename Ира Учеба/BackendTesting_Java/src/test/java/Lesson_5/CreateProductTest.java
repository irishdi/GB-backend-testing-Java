package Lesson_5;

import Lesson_5.dto.Product;
import Lesson_5.enums.CategoryType;
import Lesson_5.service.ProductService;
import Lesson_5.utils.RetrofitUtils;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CreateProductTest {
    static ProductService productService;
    Product product = null;
    Product productNew;
    Faker faker = new Faker();

    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp(){
        product = new Product()
                .withTitle(faker.space().agency())
                .withCategoryTitle(CategoryType.ELECTRONIC.getTitle())
                .withPrice((int) (Math.random() *10000));
    }
    @Test
    @DisplayName("Create product in Electronics Category Positive test")
    @SneakyThrows
    void createProductInElectronicCategoryTest() {
        Response<Product> response = productService.createProduct(product)
                .execute();

        id =  response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        tearDown();
    }

    @Test
    @DisplayName("Create product with null title and price")
    @SneakyThrows
    void createProductWithNullTitleAndPrice(){
        product.setTitle(null);
        product.setPrice(null);
        Response<Product> response = productService.createProduct(product).execute();
        id = response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(201));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
        assertThat(response.body().getTitle(), equalTo(null));
        assertThat(response.body().getPrice(), equalTo(0));
        tearDown();
    }

    @Test
    @DisplayName("Create product with preset Id test")
    @SneakyThrows
    void createProductWithPresetIdTest(){
        product.setId(123456);
        Response<Product> response = productService.createProduct(product).execute();
        assertThat(response.code(), equalTo(400));
        //assertThat(response.message(), CoreMatchers.containsString("Id must be null for new entity"));
    }

    @Test
    @DisplayName("Create product with new Category test")
    @SneakyThrows
    void createProductWithNewCategoryTest() {
        product.setCategoryTitle("New");
        Response<Product> response = productService.createProduct(product).execute();
        assertThat(response.code(), equalTo(500));

    }

    @Test
    @DisplayName("Create product with no category")
    @SneakyThrows
    void createProductWithNoCategory(){
        productNew = new Product()
                .withTitle(faker.space().agency())
                .withPrice((int) (Math.random() *100));

        Response<Product> response = productService.createProduct(productNew).execute();
        assertThat(response.code(), equalTo(500));
    }

    @Test
    @DisplayName("Create product with negative price")
    @SneakyThrows
    void createProductWithNegativePrice(){
        product.setPrice(-1000);
        Response<Product> response = productService.createProduct(product).execute();
        id = response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(201));
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
        tearDown();
    }


    @SneakyThrows
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProductById(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
