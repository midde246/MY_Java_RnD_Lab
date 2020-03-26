
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
    "price",
    "price_rub",
    "reverse_price",
    "currency",
    "service_id",
    "car_class",
    "allowable_subaddress",
    "price_subaddress",
    "cancellation_time",
    "type",
    "payed_road",
    "allowable_time",
    "class_services",
    "price_id",
    "minimum_duration",
    "start_place",
    "finish_place"
})
public class Item {

    @JsonProperty("price")
    private String price;
    @JsonProperty("price_rub")
    private String priceRub;
    @JsonProperty("reverse_price")
    private ReversePrice reversePrice;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("service_id")
    private String serviceId;
    @JsonProperty("car_class")
    private CarClass carClass;
    @JsonProperty("allowable_subaddress")
    private String allowableSubaddress;
    @JsonProperty("price_subaddress")
    private String priceSubaddress;
    @JsonProperty("cancellation_time")
    private String cancellationTime;
    @JsonProperty("type")
    private String type;
    @JsonProperty("payed_road")
    private String payedRoad;
    @JsonProperty("allowable_time")
    private String allowableTime;
    @JsonProperty("class_services")
    private ClassServices classServices;
    @JsonProperty("price_id")
    private String priceId;
    @JsonProperty("minimum_duration")
    private String minimumDuration;
    @JsonProperty("start_place")
    private StartPlace startPlace;
    @JsonProperty("finish_place")
    private FinishPlace finishPlace;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("price_rub")
    public String getPriceRub() {
        return priceRub;
    }

    @JsonProperty("price_rub")
    public void setPriceRub(String priceRub) {
        this.priceRub = priceRub;
    }

    @JsonProperty("reverse_price")
    public ReversePrice getReversePrice() {
        return reversePrice;
    }

    @JsonProperty("reverse_price")
    public void setReversePrice(ReversePrice reversePrice) {
        this.reversePrice = reversePrice;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("service_id")
    public String getServiceId() {
        return serviceId;
    }

    @JsonProperty("service_id")
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @JsonProperty("car_class")
    public CarClass getCarClass() {
        return carClass;
    }

    @JsonProperty("car_class")
    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }

    @JsonProperty("allowable_subaddress")
    public String getAllowableSubaddress() {
        return allowableSubaddress;
    }

    @JsonProperty("allowable_subaddress")
    public void setAllowableSubaddress(String allowableSubaddress) {
        this.allowableSubaddress = allowableSubaddress;
    }

    @JsonProperty("price_subaddress")
    public String getPriceSubaddress() {
        return priceSubaddress;
    }

    @JsonProperty("price_subaddress")
    public void setPriceSubaddress(String priceSubaddress) {
        this.priceSubaddress = priceSubaddress;
    }

    @JsonProperty("cancellation_time")
    public String getCancellationTime() {
        return cancellationTime;
    }

    @JsonProperty("cancellation_time")
    public void setCancellationTime(String cancellationTime) {
        this.cancellationTime = cancellationTime;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("payed_road")
    public String getPayedRoad() {
        return payedRoad;
    }

    @JsonProperty("payed_road")
    public void setPayedRoad(String payedRoad) {
        this.payedRoad = payedRoad;
    }

    @JsonProperty("allowable_time")
    public String getAllowableTime() {
        return allowableTime;
    }

    @JsonProperty("allowable_time")
    public void setAllowableTime(String allowableTime) {
        this.allowableTime = allowableTime;
    }

    @JsonProperty("class_services")
    public ClassServices getClassServices() {
        return classServices;
    }

    @JsonProperty("class_services")
    public void setClassServices(ClassServices classServices) {
        this.classServices = classServices;
    }

    @JsonProperty("price_id")
    public String getPriceId() {
        return priceId;
    }

    @JsonProperty("price_id")
    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    @JsonProperty("minimum_duration")
    public String getMinimumDuration() {
        return minimumDuration;
    }

    @JsonProperty("minimum_duration")
    public void setMinimumDuration(String minimumDuration) {
        this.minimumDuration = minimumDuration;
    }

    @JsonProperty("start_place")
    public StartPlace getStartPlace() {
        return startPlace;
    }

    @JsonProperty("start_place")
    public void setStartPlace(StartPlace startPlace) {
        this.startPlace = startPlace;
    }

    @JsonProperty("finish_place")
    public FinishPlace getFinishPlace() {
        return finishPlace;
    }

    @JsonProperty("finish_place")
    public void setFinishPlace(FinishPlace finishPlace) {
        this.finishPlace = finishPlace;
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
