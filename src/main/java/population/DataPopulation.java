/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package population;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import population.model.CountryData;
import population.model.HotelData;
import population.model.StaticCountryData;
import population.model.StaticHotelData;

/**
 *
 * @author saradam
 */
public class DataPopulation {
    public static void main(String[] args) throws IOException {
        List<String> countryList = new ArrayList<>();
        List<StaticHotelData> staticHotelDataList = new ArrayList<>();       
//        getData(countryList, staticHotelDataList);
        getDataAsPerHotelBedsCountryData(countryList);
        System.out.println("Total Hotel List :: "+staticHotelDataList.size());
        System.out.println(" Data Dumped Successfully !! ");
    }

    private static void getData(List<String> countryList, List<StaticHotelData> staticHotelDataList) {
        
        String dbUrl = "jdbc:sqlserver://159.100.134.215:1433;DatabaseName=TRAVELAGENT;ssl=request;useLOBs=false"; 
        String userName = "travelagent"; 
        String dbPassword = "travelagent123"; 
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(dbUrl, userName, dbPassword);
            
            Statement statement = connection.createStatement(); 
            String query = "SELECT Country_CodeA, Country_Name from RATETIGER.dbo.RT_COUNTRY_CODE with(nolock)";
            ResultSet rs = statement.executeQuery(query);  
            if(rs != null){
                int i = 0;
                while(rs.next()){  
                    countryList.add(rs.getString(1));  
                }  
            }
            
            int i = 1;
            System.out.println("Country list Size :: "+countryList.size());
            for(String code : countryList){
                if(i > 154){
                    String url = "https://api.test.hotelbeds.com/transfer-cache-api/1.0/hotels?fields=ALL&language=en&countryCodes=:countryCode";
    //                String url = "https://api.test.hotelbeds.com/transfer-cache-api/1.0/hotels?fields=ALL&language=en&countryCodes=ES&destinationCodes=PMI&codes=1523";
                    url = url.replaceAll(":countryCode", code.trim());
                    String jsonResponse = ServiceCaller.callServiceGET(url);
                    if(jsonResponse != null && !jsonResponse.isEmpty()){
                        HotelData hotelData = new HotelData();
                        ObjectMapper mapper = new ObjectMapper();
                        staticHotelDataList.addAll(mapper.readValue(jsonResponse, new TypeReference<List<StaticHotelData>>(){}));
                        hotelData.setStaticHotelData(staticHotelDataList);
                        writeTheDatas(hotelData, code.trim());
                    }
                }
                System.out.println("Country Number :: "+(i++));
            }
            
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
    }

 

    private static void getDataAsPerHotelBedsCountryData(List<String> countryList) throws IOException {
        String countryUrl = "https://api.test.hotelbeds.com/transfer-cache-api/1.0/locations/countries?fields=ALL&language=en";
//        String hotelUrl = "https://api.test.hotelbeds.com/transfer-cache-api/1.0/hotels?fields=ALL&language=en&countryCodes=:countryCode";
        
        String jsonResponseCountry = ServiceCaller.callServiceGET(countryUrl);
        CountryData countryData = new CountryData();
        List<StaticCountryData> countryDataList = new ArrayList<>();
        countryData.setStaticCountryData(countryDataList);
                
        if(jsonResponseCountry != null && !jsonResponseCountry.isEmpty()){
            ObjectMapper mapper = new ObjectMapper();
            countryDataList.addAll(mapper.readValue(jsonResponseCountry, new TypeReference<List<StaticCountryData>>(){}));
        }
        
        System.out.println("Country List :: "+countryDataList.size());
        writeTheDatas(countryData); // Write the Country Data.
        System.out.println("Country List Dumpped Successfully !! ");
        
        for(StaticCountryData listData : countryDataList){
            countryList.add(listData.getCode().trim());
        }
        
        int i = 1;
        int count = 0;
        for(String code : countryList){
            if(code.equalsIgnoreCase("FR")){
                String url = "https://api.test.hotelbeds.com/transfer-cache-api/1.0/hotels?fields=ALL&language=en&countryCodes=:countryCode";
                url = url.replaceAll(":countryCode", code.trim());
                String jsonResponse = ServiceCaller.callServiceGET(url);
                if(jsonResponse != null && !jsonResponse.isEmpty()){
                    HotelData hotelData = new HotelData();
                    ObjectMapper mapper = new ObjectMapper();
                    hotelData.setStaticHotelData(mapper.readValue(jsonResponse, new TypeReference<List<StaticHotelData>>(){}));
                    writeTheDatas(hotelData, code.trim());
                    count += hotelData.getStaticHotelData().size();
                }
            }
            System.out.println("Country Number :: "+(i++));
        }
         System.out.println("Total Number of Hotel :: "+count);
    }
    
    private static void writeTheDatas(HotelData hotelData, String countryCode) {
        try{

            JAXBContext jContext = JAXBContext.newInstance(HotelData.class);
            Marshaller marshallObj = jContext.createMarshaller();
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);     
   //            marshallObj.marshal(hotelData, System.out);  
            marshallObj.marshal(hotelData, new FileOutputStream("D:\\Projects\\"+countryCode+"_HoteData.xml"));

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
     private static void writeTheDatas(CountryData countryData) {
        try{

            JAXBContext jContext = JAXBContext.newInstance(CountryData.class);
            Marshaller marshallObj = jContext.createMarshaller();
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);     
            marshallObj.marshal(countryData, new FileOutputStream("D:\\Projects\\StaticDataPopulation\\HotelBeds\\Car\\Country\\CountryData.xml"));

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
