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
@XmlRootElement
public class CountryData {

    private List<StaticCountryData> staticCountryData;

    /**
     * @return the staticCountryData
     */
    public List<StaticCountryData> getStaticCountryData() {
        return staticCountryData;
    }

    /**
     * @param staticCountryData the staticCountryData to set
     */
    @XmlElement
    public void setStaticCountryData(List<StaticCountryData> staticCountryData) {
        this.staticCountryData = staticCountryData;
    }

}
