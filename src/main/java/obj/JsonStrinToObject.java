/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.PickupSchedule;
import org.codehaus.jackson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author saradam
 */
public class JsonStrinToObject {

    public static void main(String[] args) throws IOException {
//        List<PickupScheduleBean> pickupScheduleList = new ArrayList<>();
        String jsonString = "[\n"
                + "              {\n"
                + "                \"day_name\": \"Monday\",\n"
                + "                \"is_open\": \"true\",\n"
                + "                \"start\": \"8:00\",\n"
                + "                \"end\": \"13:00\"\n"
                + "              },\n"
                + "              {\n"
                + "                \"day_name\": \"Monday\",\n"
                + "                \"is_open\": \"true\",\n"
                + "                \"start\": \"14:30\",\n"
                + "                \"end\": \"18:00\"\n"
                + "              }\n"
                + "            ]";

             
        JSONArray jsonArray = new JSONArray(jsonString); 
        System.out.println("jsonArray :: "+jsonArray);
        List<PickupSchedule> pickScheduleList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            PickupSchedule pickSchedule = new PickupSchedule();
            pickSchedule.setDayName(jsonArray.getJSONObject(i).get("day_name").toString());
            pickSchedule.setIsOpen(jsonArray.getJSONObject(i).get("is_open").toString());
            pickSchedule.setDayName(jsonArray.getJSONObject(i).get("day_name").toString());
            pickSchedule.setIsOpen(jsonArray.getJSONObject(i).get("is_open").toString());
            pickScheduleList.add(pickSchedule);
        }
        
        
        
    }

}