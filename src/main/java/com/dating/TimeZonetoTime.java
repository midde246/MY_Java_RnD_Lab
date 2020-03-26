/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dating;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author saradam
 */
public class TimeZonetoTime {
    public static void main(String[] args) throws ParseException {
        String timeGMT = "Fri May 24 2019 15:55:28 GMT+0530 (India Standard Time)";
        System.out.println("Existing Formated Date Is :: "+timeGMT);
//        String date = getFormattedDate(timeGMT);
//        String date = getMyDate(timeGMT);
//        String date = getDateFromGMT(timeGMT);
        String showDate = addHoursInDate(timeGMT, 13);
//        System.out.println("New Generated Date Is :: "+date);
        System.out.println("New Generated Date With Added TIme Is :: "+showDate);
    }
    
  
    public static String getDateFromGMT(String requestedDate) {
        DateFormat inputFormat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss 'GMT'z");
        Date date = null;
        try {
            date = inputFormat.parse(requestedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        outputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String strDay = outputFormat.format(date);
        
        
        
        
        return strDay;
    }

    private static String addHoursInDate(String requestDate, int hours) throws ParseException {
        String dateFromGMT = getDateFromGMT(requestDate);
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateFromGMT);  
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        String addedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());  
        return addedDate;
    }
    
}
