/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dating.rnd;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 *
 * @author saradam
 */
public class GMTdate {

    public static void main(String[] args) throws ParseException {
//        String date = getTodayDateFromGMT();
//        System.out.println("To day is :: "+date);
//        
//        String extDate = addHoursInDate(date, "2");
//        System.out.println("New Date :: "+extDate);

//        String gmtDate = getDateFromGMT("Tue Nov 26 2019 13:03:20 GMT+0530 (India Standard Time)");
//        String fromDate = "2019-11-26 14:03:20";
//        String toDate = "2019-11-27 13:03:20";
//        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar cal = new GregorianCalendar();
//        Calendar calFrom = new GregorianCalendar();
//        Calendar calTo = new GregorianCalendar();
//        cal.setTime(outputFormat.parse(gmtDate));
//        calFrom.setTime(outputFormat.parse(fromDate));
//        calTo.setTime(outputFormat.parse(toDate));
//
//        if (cal.after(calFrom)) {
//            System.out.println("I am After Date");
//        }
//        if (cal.before(calTo)) {
//            System.out.println("I am Before Date");
//        }
//        System.out.println(gmtDate);

        System.out.println(getCurrentDateTimeRQ());
    }

    public static String getTodayDateFromGMT() {
        Date date = new Date();
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        outputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String strDay = outputFormat.format(date);
        return strDay;
    }

    /**
     *
     * @param date
     * @param hours
     * @return
     * @throws ParseException
     */
    public static String addHoursInDate(String requestDate, String hours) throws ParseException {
//        String dateFromGMT = getDateFromGMT(requestDate);
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(requestDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
//        System.out.println("Request Time :: "+calendar.getTime().toString());
        calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hours));
        String addedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
//        System.out.println("Generated Time :: "+calendar.getTime().toString());
        return addedDate;
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
        outputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String strDay = outputFormat.format(date);

        return strDay;
    }
   
   public static String getCurrentDateInGMT(){
       Calendar cal = new GregorianCalendar();
     
       return getDateFromGMT(getCurrentDateTimeRQ());
   }
   
    public static String getCurrentDateTimeRQ() {
        
         DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        outputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String strDay = outputFormat.format(new Date());
        return strDay;
 
    }

}
