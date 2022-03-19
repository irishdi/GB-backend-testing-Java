package Lesson_5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "title",
        "products"
})
@Data
public class CategoryResponse {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("products")
    public List<Product> products = new ArrayList<>();

}
