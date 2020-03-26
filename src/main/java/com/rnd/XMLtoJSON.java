/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnd;

import org.json.JSONObject;
import org.json.XML;

/**
 *
 * @author saradam
 */
public class XMLtoJSON {
    public static void main(String[] args) {
        String xml_data = "<student><name>Sarada Prasad Midde</name><age>25</age></student>";
 
        String jsonResponse = convertToJson(xml_data);
        System.out.println(jsonResponse);
    }

    private static String convertToJson(String xml_data) {
        JSONObject obj = XML.toJSONObject(xml_data);
        return obj.toString();
    }
}
