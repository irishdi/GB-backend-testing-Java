package Lesson_4.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "results",
        "offset",
        "number",
        "totalResults"
})
@lombok.Data
public class AccountInfoResponse {

    @JsonProperty("results")
    private ArrayList<Result> data;
    private Integer offset;
    private Integer number;
    private Integer totalResults;


}
