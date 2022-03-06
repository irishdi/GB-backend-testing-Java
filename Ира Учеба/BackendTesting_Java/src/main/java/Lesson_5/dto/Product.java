package Lesson_5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "title",
        "price",
        "categoryTitle"
})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@With

public class Product {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("price")
    public Integer price;
    @JsonProperty("categoryTitle")
    public String categoryTitle;

   }