
package model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "place_id",
    "title",
    "type",
    "type_title",
    "declension_titles"
})
public class FinishPlace {

    @JsonProperty("place_id")
    private String placeId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("type")
    private String type;
    @JsonProperty("type_title")
    private String typeTitle;
    @JsonProperty("declension_titles")
    private DeclensionTitles_ declensionTitles;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("place_id")
    public String getPlaceId() {
        return placeId;
    }

    @JsonProperty("place_id")
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("type_title")
    public String getTypeTitle() {
        return typeTitle;
    }

    @JsonProperty("type_title")
    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    @JsonProperty("declension_titles")
    public DeclensionTitles_ getDeclensionTitles() {
        return declensionTitles;
    }

    @JsonProperty("declension_titles")
    public void setDeclensionTitles(DeclensionTitles_ declensionTitles) {
        this.declensionTitles = declensionTitles;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
