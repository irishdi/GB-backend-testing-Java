package Lesson_5;

import Lesson_5.dto.Product;
import Lesson_5.enums.CategoryType;
import Lesson_5.service.ProductService;
import Lesson_5.utils.RetrofitUtils;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModifyProductTest {
    static ProductService productService;
    Product product;
    Product productModified;
    Faker faker = new Faker();

    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @SneakyThrows
    @BeforeEach
    void setUp(){
        product = new Product()
                .withTitle(faker.harryPotter().location())
                .withCategoryTitle(CategoryType.FURNITURE.getTitle())
                .withPrice((int) (Math.random() *100));
        Response<Product> response = productService.createProduct(product)
                .execute();
        id =  response.body().getId();
    }

    @Test
    @DisplayName("Modify product by title, price, category test")
    @SneakyThrows
    void modifyProductTitlePriceCategoryTest(){
        productModified = new Product()
                .withId(id)
                .withTitle("Updated")
                .withPrice(22222)
                .withCategoryTitle(CategoryType.ELECTRONIC.getTitle());
        Response<Product> responseModified = productService.modifyProduct(productModified).execute();
        assertThat(responseModified.isSuccessful(), CoreMatchers.is(true));
        assertThat(responseModified.body().getTitle(), equalTo(productModified.getTitle()));
        assertThat(responseModified.body().getId(), equalTo(productModified.getId()));
        assertThat(responseModified.body().getCategoryTitle(), equalTo(productModified.getCategoryTitle()));
        tearDown();
    }

    @Test
    @DisplayName("Modify product with unknown ID test")
    @SneakyThrows
    void modifyProductWithUnknownId(){
        productModified = new Product()
                .withTitle(faker.ancient().titan())
                .withPrice(((int) ((Math.random() *100))))
                .withCategoryTitle(CategoryType.ELECTRONIC.getTitle());
        Response<Product> responseModified = productService.modifyProduct(productModified).execute();
        assertThat(responseModified.code(), equalTo(400));

    }

    @Test
    @DisplayName("Modify product with null data test")
    @SneakyThrows
    void modifyProductWithNullData(){
        productModified = new Product()
                .withId(id)
                .withTitle(null)
                .withPrice(null)
                .withCategoryTitle(null);
        Response<Product> responseModified = productService.modifyProduct(productModified).execute();
        assertThat(responseModified.code(), equalTo(500));
    }

    @Test
    @DisplayName("Modify deleted product test")
    @SneakyThrows
    void modifyDeletedProductTest(){
        Response<ResponseBody> responseDelete = productService.deleteProductById(id).execute();
        productModified = new Product()
                .withId(id)
                .withTitle(faker.food().ingredient())
                .withPrice(333333)
                .withCategoryTitle(CategoryType.ELECTRONIC.getTitle());
        Response<Product> responseModified = productService.modifyProduct(productModified).execute();
        assertThat(responseModified.code(), equalTo(400));

    }

    @SneakyThrows
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProductById(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

    }



}
