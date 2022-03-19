package Lesson_5.service;

import Lesson_5.dto.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ProductService {
    @GET("products")
    Call<ResponseBody> getAllProducts();

    @POST("products")
    Call<Product> createProduct(@Body Product createProductRequest);

    @PUT("products")
    Call<Product> modifyProduct(@Body Product modifyProductRequest);

    @GET("products/{id}")
    Call<Product> getProductById(@Path("id") int id);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProductById(@Path("id") int id);

}
