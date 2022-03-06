package Lesson_5;

import Lesson_5.service.ProductService;
import Lesson_5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetProductsTest {

    static ProductService productService;

    @BeforeAll
    static void beforeAll(){
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @SneakyThrows
    @Test
    @DisplayName("Get all products test")
    void getAllProductsTest(){
        Response<ResponseBody> response = productService.getAllProducts().execute();
        assertThat(response.code(), equalTo(500));

       }
}
