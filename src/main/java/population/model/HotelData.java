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
public class HotelData {
    
    private List<StaticHotelData> staticHotelData;

//    @XmlElement
    public List<StaticHotelData> getStaticHotelData() {
        return staticHotelData;
    }
    
    @XmlElement
    public void setStaticHotelData(List<StaticHotelData> staticHotelData) {
        this.staticHotelData = staticHotelData;
    }
    
}
