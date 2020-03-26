/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package population.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author saradam
 */

public class StaticHotelData {
    
//    @XmlElement(name = "code", required = true)
    private String code;
    
//    @XmlElement(name = "content", required = true)
    private Content content;
   
//    @XmlElement(name = "country_code", required = true)
    private String countryCode;
     
//    @XmlElement(name = "coordinates", required = true) 
    private Coordinates coordinates;
    
//    @XmlElement(name = "language", required = true) 
    private String language;
    
    private String name;
    
    private String category;
    
    private String description;
    
    private String destinationCode;
    
    private String city;
    
    private String chainCode;
    
    private String address;
    
    private String postalCode;
    
    private List<Terminals> terminals;
    
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the content
     */
    public Content getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(Content content) {
        this.content = content;
    }

    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates the coordinates to set
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the destinationCode
     */
    public String getDestinationCode() {
        return destinationCode;
    }

    /**
     * @param destinationCode the destinationCode to set
     */
    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the chainCode
     */
    public String getChainCode() {
        return chainCode;
    }

    /**
     * @param chainCode the chainCode to set
     */
    public void setChainCode(String chainCode) {
        this.chainCode = chainCode;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the terminals
     */
    public List<Terminals> getTerminals() {
        return terminals;
    }

    /**
     * @param terminals the terminals to set
     */
    public void setTerminals(List<Terminals> terminals) {
        this.terminals = terminals;
    }
    
}
