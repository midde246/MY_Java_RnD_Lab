/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author AnupG
 */
public class TimeUtil {
   
    private TimeUtil(){}
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeUtil.class);
    /**
     *
     * @return
     */
    public static java.util.Calendar getTimeInUTC(int min) {
        Calendar cal = Calendar.getInstance();//this is timezone of this system
        TimeZone tm = cal.getTimeZone();
        int offset = tm.getRawOffset();
        int daylightSavingsMS = tm.getDSTSavings();
        if ((tm.useDaylightTime()) && (daylightSavingsMS != 0) && (tm.inDaylightTime(cal.getTime()))) {
            offset = offset + daylightSavingsMS;
        }

        cal.add(Calendar.MILLISECOND, -offset);//this is GMT
        cal.add(Calendar.MINUTE, min);
        return cal;
    }

    /**
     * 
     * @param date
     * @return 
     */
    public static String getFormattedDate(Date date) {
        String formattedDate = null;
        try {
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formattedDate = sdf2.format(date);
        } catch (Exception ex) {
            LOGGER.error("Exception :: ", ex);
        }
        return formattedDate;
    }

    /**
     * 
     * @param date
     * @param dateFormat
     * @return 
     */
    public static String getFormattedDate(final Date date, final String dateFormat) {
        String formattedDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            formattedDate = sdf.format(date);
        } catch (Exception ex) {
            LOGGER.error("Exception :: ", ex);
        }
        return formattedDate;
    }

    /**
     * 
     * @param strDate
     * @return 
     */
    public static Date getDateInstance(String strDate) {
        Date date = null;
        try {
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf2.parse(strDate);
        } catch (Exception ex) {
            LOGGER.error("Exception :: ", ex);
        }
        return date;
    }

    /**
     * 
     * @param strDate
     * @param dateFormat
     * @return
     * @throws ParseException 
     */
    public static Date getDateInstance(final String strDate, final String dateFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        return sdf.parse(strDate);
    }

    /**
     * 
     * @param date
     * @param field
     * @param value
     * @return 
     */
    public static Date modifyDate(Date date, int field, int value) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(field, value);
        return calendar.getTime();
    }

    /**
     * 
     * @param strDate
     * @param dateFormat
     * @return 
     */
    public static String modifyDate(final String strDate, final String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = modifyDate(sdf.parse(strDate), Calendar.YEAR, -1);
        } catch (ParseException ex) {
            LOGGER.error("Exception :: ", ex);
        }
        return sdf.format(date);
    }

    /**
     * 
     * @param startDate
     * @param endDate
     * @return 
     */
    public static int calculateLength(Date startDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0); // Crucial.
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long startTime = calendar.getTimeInMillis();
        calendar.setTime(endDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23); // Crucial.
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long endTime = calendar.getTimeInMillis();
        long diff = endTime - startTime;
        int diffDays = (int) Math.ceil(diff / (double) (24 * 60 * 60 * 1000));
        return diffDays;
    }

    /**
     * 
     * @param strStartDate
     * @param strEndDate
     * @return
     * @throws ParseException 
     */
    public static int calculateLength(String strStartDate, String strEndDate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(ServerConstants.DATE_FORMAT);
        Date startDate = sdf.parse(strStartDate);
        return calculateLength(startDate, sdf.parse(strEndDate));
    }

    /**
     * 
     * @param date
     * @return 
     */
    public static String getQueryStringFromDate(Date date) {
        String formattedDate = null;
        try {
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            formattedDate = sdf2.format(date);
        } catch (Exception ex) {
            LOGGER.error("Exception :: ", ex);
        }
        formattedDate = "'" + formattedDate + "'";
        return formattedDate;
    }

    /**
     * 
     * @param dates
     * @return 
     */
    public static String getQueryStringFromDateSet(Set<Date> dates) {
        if (dates == null || dates.isEmpty()) {
            return "";
        }
        StringBuilder sbDate = new StringBuilder("");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        for (Date date : dates) {
            try {
                if (sbDate.length() > 0) {
                    sbDate.append(",");
                }
                sbDate.append("'").append(sdf2.format(date)).append("'");
            } catch (Exception ex) {
                LOGGER.error("Exception :: ", ex);
            }
        }        
        return sbDate.toString();
    }
    
    /**
     * 
     * @param date
     * @return 
     */
    public static String getCallDateString(Date date) {
    	SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return df.format(cal.getTime());
    }
    
    /**
     * 
     * @param date
     * @param field
     * @param value
     * @return 
     */
    public static Date modifyTime(Date date, int field, int value) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(field, value);
        return calendar.getTime();
    }
    
    /**
     * 
     * @param dob
     * @return
     * @throws ParseException 
     */
    public static String getAgeByDOB(String dob) throws ParseException {
        Calendar calendar = new GregorianCalendar();
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(dob);
        calendar.setTime(startDate);
        int dboYear = calendar.get(Calendar.YEAR);
        calendar.setTime(today);
        int currentYear = calendar.get(Calendar.YEAR);
        int age = (currentYear - dboYear);
        LOGGER.info("Age"+age);
        return age+"";
    }
    
    /**
     * This method is used to get previous date of particular date with respect to a certain no of days.
     * @param get
     * @param clxDays
     * @return 
     */ 
    public static String getPreviousDate(String myDate, String clxDays) throws ParseException {
        Date dateInstance = getDateInstance(myDate, ServerConstants.DATE_FORMAT);
        Calendar cal = new GregorianCalendar();
        cal.setTime(dateInstance);
        cal.add(Calendar.DATE, (0-Integer.parseInt(clxDays)));
        return getFormattedDate(cal.getTime(), ServerConstants.DATE_FORMAT);
    }   
    
    /**
     * This method is used for getting month number from a particular Date.
     * @param dateStr
     * @return 
     */
    public static String getMonthNumberFromDate(String dateStr){
        Date formatedDate = getDateInstance(dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(formatedDate);
        int month = cal.get(Calendar.MONTH);
        month++; // Here index of January is '0'
        return ""+month;
    }
    
    /**
     * This method is used for getting month number from a particular Date.
     * @param dateStr
     * @return 
     */
    public static String getYearFromDate(String dateStr) throws ParseException{
        Date formatedDate = getDateInstance(dateStr, ServerConstants.DATE_FORMAT);
        Calendar cal = Calendar.getInstance();
        cal.setTime(formatedDate);
        int year = cal.get(Calendar.YEAR);
        return ""+year;
    }
    
   /**
    * 
    * @param requestedDate
    * @return 
    */ 
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
    
   /**
    * This method is use to to date GMT
    * @return 
    */ 
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
     * pass hours with "-", to get previous date.
     */
    public static String addHoursInDate(String requestDate, String hours) throws ParseException {
        String dateFromGMT = getDateFromGMT(requestDate);
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateFromGMT);  
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        LOGGER.info("Request Time :: "+calendar.getTime().toString());
        calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hours));
        String addedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());  
        LOGGER.info("Generated Time :: "+calendar.getTime().toString());
        return addedDate;
    }
    
    /**
     * 
     * @param date
     * @param hours
     * @return
     * @throws ParseException 
     */
    public static String addHoursInDateLocal(String requestDate, String hours) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(requestDate);  
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        LOGGER.info("Request Time :: "+calendar.getTime().toString());
        calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hours));
        String addedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());  
        LOGGER.info("Generated Time :: "+calendar.getTime().toString());
        return addedDate;
    }
    
    /**
     * 
     * @param date
     * @param hours
     * @return
     * @throws ParseException 
     */
    public static String calculateDateFromHour(String requestDate, String hours) throws ParseException {
       
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(requestDate);  
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        LOGGER.info("Request Time :: "+calendar.getTime().toString());
        calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hours));
        String addedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());  
        LOGGER.info("Generated Time :: "+calendar.getTime().toString());
        return addedDate;
    }
}
