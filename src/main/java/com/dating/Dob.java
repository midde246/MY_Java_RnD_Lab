/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dating;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author saradam
 */
public class Dob {
    public static void main(String args[]) throws ParseException {
//        Calendar calendar = new GregorianCalendar();
//        String strStartDate = "2017-10-23";
//        Date today = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date startDate = sdf.parse(strStartDate);
//        calendar.setTime(startDate);
//        int dboYear = calendar.get(Calendar.YEAR);
//        calendar.setTime(today);
//        int currentYear = calendar.get(Calendar.YEAR);
//        System.out.println(currentYear - dboYear);

        Set<String> test = new HashSet<>();
        test.add("test");
        test.add("test");
        test.add("test1");
        System.out.println(test.size());
        System.out.println(new ArrayList<>(test).get(1));
    }
}
