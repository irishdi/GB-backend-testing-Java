package Lesson_5;

import Lesson_5.dto.Product;
import Lesson_5.enums.CategoryType;
import Lesson_5.service.ProductService;
import Lesson_5.utils.RetrofitUtils;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetProductByIdTest {
    static ProductService productService;
    Product product;
    Faker faker = new Faker();

    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @BeforeEach
    @SneakyThrows
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle(CategoryType.FOOD.getTitle())
                .withPrice((int) (Math.random() * 10000));
        Response<Product> response = productService.createProduct(product).execute();
        id =  response.body().getId();
    }

    @Test
    @DisplayName("Get product by id positive test")
    @SneakyThrows
    void getProductsByIdTest(){
    Response<Product> response = productService.getProductById(id).execute();
    assertThat(response.isSuccessful(), CoreMatchers.is(true));
    assertThat(response.body().getId(), equalTo(id));
    assertThat(response.code(), equalTo(200));
    }

    @Test
    @DisplayName("Get product by nonexistent ID test")
    @SneakyThrows
    void getProductByNonExistentId(){
        Response<Product> response = productService.getProductById(id+100).execute();
        assertThat(response.code(), equalTo(404));
    }



}
