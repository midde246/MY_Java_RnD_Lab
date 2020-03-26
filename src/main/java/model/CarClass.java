
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
    "car_class_id",
    "title",
    "models",
    "photo",
    "capacity"
})
public class CarClass {

    @JsonProperty("car_class_id")
    private String carClassId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("models")
    private Models models;
    @JsonProperty("photo")
    private String photo;
    @JsonProperty("capacity")
    private String capacity;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("car_class_id")
    public String getCarClassId() {
        return carClassId;
    }

    @JsonProperty("car_class_id")
    public void setCarClassId(String carClassId) {
        this.carClassId = carClassId;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("models")
    public Models getModels() {
        return models;
    }

    @JsonProperty("models")
    public void setModels(Models models) {
        this.models = models;
    }

    @JsonProperty("photo")
    public String getPhoto() {
        return photo;
    }

    @JsonProperty("photo")
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @JsonProperty("capacity")
    public String getCapacity() {
        return capacity;
    }

    @JsonProperty("capacity")
    public void setCapacity(String capacity) {
        this.capacity = capacity;
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
