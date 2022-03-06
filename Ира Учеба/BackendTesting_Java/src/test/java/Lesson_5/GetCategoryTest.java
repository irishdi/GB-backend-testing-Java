package Lesson_5;

import Lesson_5.dto.CategoryResponse;
import Lesson_5.enums.CategoryType;
import Lesson_5.service.CategoryService;
import Lesson_5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetCategoryTest {
    static CategoryService categoryService;

    @BeforeAll
    static void beforeAll(){
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }

    @SneakyThrows
    @Test
    void getCategoryByIdPositiveTest(){
        Response<CategoryResponse> response = categoryService.getCategory(CategoryType.FOOD.getId()).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(CategoryType.FOOD.getId()));
        assertThat(response.body().getTitle(), equalTo(CategoryType.FOOD.getTitle()));
        response.body().getProducts().forEach(product -> assertThat(product.getCategoryTitle(),
                equalTo(CategoryType.FOOD.getTitle())));
        }


    @SneakyThrows
    @Test
    void getCategoryByIdNegativeTest(){
        Response<CategoryResponse> response = categoryService.getCategory(CategoryType.NONEXISTENT.getId()).execute();
        assertThat(response.code(), equalTo(404));
    }
}
