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
import static org.hamcrest.Matchers.equalTo;

public class DeleteProductTest {
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
    @DisplayName("Delete product by ID positive test")
    @SneakyThrows
    void deleteProductByIdPositiveTest(){
        Response<ResponseBody> responseDel = productService.deleteProductById(id).execute();
        assertThat(responseDel.isSuccessful(), CoreMatchers.is(true));
        assertThat(responseDel.code(), equalTo(200));
    }


    @Test
    @DisplayName("Delete product by nonexistent ID")
    @SneakyThrows
    void deleteProductWithoutIdTest() {
        Response<ResponseBody> responseDel = productService.deleteProductById(id + 100).execute();
        assertThat(responseDel.code(), equalTo(500));
    }
}
