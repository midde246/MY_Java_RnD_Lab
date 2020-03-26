/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package population.xmltoexcel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import population.model.CountryData;
import population.model.HotelData;
import population.model.StaticCountryData;
import population.model.StaticHotelData;

/**
 *
 * @author saradam
 */
public class XMLtoExcel {
    
    private static final ConcurrentMap<Class<?>, JAXBContext> CONTEXT = new ConcurrentHashMap<>();
    
    public static void main(String[] args) throws JAXBException, IOException {
        Map<String, String> countryCodeNameMap = new HashMap<>();
        populateCountryMap(countryCodeNameMap);
        int count = 0;
        Map<String, String> avilableCountryCodeMap = new HashMap<>();
        for(Map.Entry<String, String> mapData : countryCodeNameMap.entrySet()){
            String hotelFileName = "D:\\Projects\\StaticDataPopulation\\HotelBeds\\Car\\Hotel\\:countryCode_HoteData.xml";
            String countryCode = mapData.getKey();
            hotelFileName = hotelFileName.replaceAll(":countryCode", countryCode);
            try{
               HotelData hotelData = getObjectFromXML(hotelFileName, HotelData.class);
               count += hotelData.getStaticHotelData().size();
               avilableCountryCodeMap.put(countryCode, mapData.getValue());
//              writeDataToExcel(hotelData, mapData.getValue());
            } finally {
                continue;
            } 
        }
        writeTheDatas(avilableCountryCodeMap);
        System.out.println("Hotel Count :: "+count);
        System.out.println("Available Country Count :: "+avilableCountryCodeMap.size());
    }


    private static void populateCountryMap(Map<String, String> countryCodeNameMap) throws JAXBException {
        String countryFileName = "D:\\Projects\\StaticDataPopulation\\HotelBeds\\Car\\Country\\CountryData.xml";
        CountryData countryData = getObjectFromXML(countryFileName, CountryData.class);
        for(StaticCountryData staticCountryData : countryData.getStaticCountryData()){
            String code = staticCountryData.getCode();
            String name = staticCountryData.getName();
            countryCodeNameMap.put(code, name);
        }
        System.out.println("Country List :: "+countryCodeNameMap.size());
    }

    public static <T> T getObjectFromXML(String fileName, Class<T> objectClass) throws JAXBException {
        Object objResponse = null;
        System.out.println("FileName :: "+fileName);
        try{
            JAXBContext jc = CONTEXT.get(objectClass);
            if (jc == null){
                jc = JAXBContext.newInstance(objectClass);
                CONTEXT.putIfAbsent(objectClass, jc);
            }
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            objResponse =  (Object)unmarshaller.unmarshal(new File(fileName));
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return objectClass.cast(objResponse);
    }      
    
    private static void writeTheDatas(Map<String, String> countryDataMap) {
        try{
            List<StaticCountryData> staticCountrydata = new ArrayList<>();
            CountryData countryData = new CountryData();
            for(Map.Entry<String, String> mapData : countryDataMap.entrySet()){
                StaticCountryData staticCountryData = new StaticCountryData();
                staticCountryData.setCode(mapData.getKey());
                staticCountryData.setName(mapData.getValue());
                staticCountrydata.add(staticCountryData);
            }
            countryData.setStaticCountryData(staticCountrydata);
            
            JAXBContext jContext = JAXBContext.newInstance(CountryData.class);
            Marshaller marshallObj = jContext.createMarshaller();
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);     
   //            marshallObj.marshal(hotelData, System.out);  
            marshallObj.marshal(countryData, new FileOutputStream("D:\\Projects\\StaticDataPopulation\\HotelBeds\\Car\\Country\\availableCountryData.xml"));

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeDataToExcel(HotelData hotelData, String countryName) throws FileNotFoundException, IOException {

        String outputExcelFile = "D:\\Projects\\StaticDataPopulation\\HotelBeds\\Car\\Hotel\\Excel\\"+countryName+"_Unmapped_Hotels.xlsx";
                
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        CellStyle styleR = workbook.createCellStyle();
        styleR.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        styleR.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styleR.setBottomBorderColor(IndexedColors.BLACK.getIndex());       
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIME.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND); 
        
        int rowCount = 0;
        
        Row rowHeader = sheet.createRow(rowCount++);
        
        int columnCountHeader = 0;
        for(int i=0; i<8; i++) {
            Cell cell = rowHeader.createCell(columnCountHeader++);           
            switch(i) {
                case 0: 
                    cell.setCellValue("Hotel_Code");                        
                    break;
                case 1:
                    cell.setCellValue("Hotel_Name");                        
                    break;
                case 2:
                    cell.setCellValue("Latitude");                        
                    break;
                case 3:
                    cell.setCellValue("Longitude");
                    break;
                case 4:
                    cell.setCellValue("City_Name");
                    break;
                case 5:
                    cell.setCellValue("Country_Name");
                    break;
                case 6:
                    cell.setCellValue("Address");
                    break;
                case 7:
                    cell.setCellValue("Partner_Id");
                    break;    
                default:
                    break;
            }                
        }   
        
        for(StaticHotelData staticHotelInfo : hotelData.getStaticHotelData()) {
            Row row = sheet.createRow(rowCount++);
            
            int columnCount = 0;
            for(int i=0; i<7; i++) {
                Cell cell = row.createCell(columnCount++);           
                switch(i) {
                    case 0: 
                        cell.setCellValue(staticHotelInfo.getCode());                        
                        break;
                    case 1:
                        cell.setCellValue(staticHotelInfo.getName());                        
                        break;
                    case 2:
                        cell.setCellValue(staticHotelInfo.getCoordinates().getLatitude());                        
                        break;
                    case 3:
                        cell.setCellValue(staticHotelInfo.getCoordinates().getLongitude());
                        break;
                    case 4:
                        cell.setCellValue(staticHotelInfo.getCity());
                        break;
                    case 5:
                        cell.setCellValue(countryName);
                        break;
                    case 6:
                        cell.setCellValue(staticHotelInfo.getAddress());
                        break;
                    default:
                        break;
                }                
            }   
        }
       
        FileOutputStream outputStream = new FileOutputStream(outputExcelFile);
        workbook.write(outputStream);
    }  

}
