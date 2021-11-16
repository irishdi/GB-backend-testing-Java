package Lesson_7.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)

public class TemperatureUnit {
    @JsonProperty("Value")
    private Double value;
    @JsonProperty("Unit")
    private String unit;

    @Override
    public String toString(){
        return "Min t = " + value + unit + "Max t = " + value + unit;
    }
    @JsonProperty("Value")
    public Double getValue() {
        return value;
    }

    @JsonProperty("Value")
    public void setValue(Double value) {
        this.value = value;
    }

    @JsonProperty("Unit")
    public String getUnit() {
        return unit;
    }

    @JsonProperty("Unit")
    public void setUnit(String unit) {
        this.unit = unit;
    }

}
