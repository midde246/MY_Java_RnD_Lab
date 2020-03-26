/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dating;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author saradam
 */
public class GetMonth {
    public static void main(String[] args) {
//        String myDateStr = "2019-04-19";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        System.out.println("MY DATE :: "+myDate);
        System.out.println("Month Number :: "+(month+1));
         System.out.println("Year :: "+year);
    }
}
