package Lesson_6;

import Lesson_6.dto.Product;
import Lesson_6.enums.CategoryType;
import Lesson_6.service.ProductService;
import Lesson_6.utils.DBUtils;
import Lesson_6.utils.RetrofitUtils;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateProductTest {
    static ProductService productService;
    static db.dao.ProductsMapper productsMapper;
    Product product = null;

    Faker faker = new Faker();

    Integer productid;

    @BeforeAll
    static void beforeAll() {
        productsMapper = DBUtils.getProductsMapper();
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
        Response<Product> response = productService.createProduct(product).execute();

        productid =  response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(productsMapper.selectByPrimaryKey((long) productid).getTitle(), equalTo(product.getTitle()));


    }

    @AfterEach
    @SneakyThrows
    void tearDown() {
        if(productid != null)
            DBUtils.getCategoriesMapper().deleteByPrimaryKey(productid);
    }
}
