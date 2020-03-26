/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dating;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author saradam
 */
public class SomeTimeBforeCurrentDateTime {

    public static void main(String[] args) throws ParseException {

        String finalDateTime = "2020-01-13 08:23:27";
                
        System.out.println("current time : " + finalDateTime);

        finalDateTime = getPreviousDateTime(finalDateTime, "5");

        System.out.println("5 min before time was : " + finalDateTime);
    }

//    private static String getBforeSomeTime(String _082327, String string) {
//        Calendar cal = new GregorianCalendar();
//        Date date = new Date();
//        date
//        cal.setTime(date);
//    }
    public static String getPreviousDateTime(String myDate, String clxTimeInMin) throws ParseException {
        Date dateInstance = getDateInstance(myDate, "yyyy-MM-dd HH:mm:ss");
        Calendar cal = new GregorianCalendar();
        cal.setTime(dateInstance);
        cal.add(Calendar.MINUTE, (0-Integer.parseInt(clxTimeInMin)));
        return getFormattedDate(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public static Date getDateInstance(final String strDate, final String dateFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        return sdf.parse(strDate);
    }

    public static String getFormattedDate(final Date date, final String dateFormat) {
        String formattedDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            formattedDate = sdf.format(date);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return formattedDate;
    }
}
