package Lesson_7HW;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Headline {
    @JsonProperty(value = "EffectiveDate")
    private String date;
    @JsonProperty(value = "Severity")
    private float Severity;
    @JsonProperty(value = "Text")
    private String Text;
    private String Category;
    private String EndDate;

    public Headline() {
    }

    @Override
    public String toString() {
        return "Headline{" +
                "EffectiveDate='" + date + '\'' +
                ", Severity=" + Severity +
                ", Text='" + Text + '\'' +
                ", Category='" + Category + '\'' +
                ", EndDate='" + EndDate;
    }

    @JsonGetter("EffectiveDate")
    public String getEffectiveDate() {
        return date;
    }

    @JsonSetter("EffectiveDate")
    public void setEffectiveDate(String effectiveDate) {
        date = effectiveDate;
    }


    @JsonGetter("Severity")
    public float getSeverity() {
        return Severity;
    }

    @JsonSetter("Severity")
    public void setSeverity(float severity) {
        Severity = severity;
    }

    @JsonGetter("Text")
    public String getText() {
        return Text;
    }

    @JsonSetter("Text")
    public void setText(String text) {
        Text = text;
    }

    @JsonGetter("Category")
    public String getCategory() {
        return Category;
    }

    @JsonSetter("Category")
    public void setCategory(String category) {
        Category = category;
    }

    @JsonGetter("EndDate")
    public String getEndDate() {
        return EndDate;
    }

    @JsonSetter("EndDate")
    public void setEndDate(String endDate) {
        EndDate = endDate;
    }


}
