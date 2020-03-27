/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.erev.ta.common.dao.LevelInfoDAO;
import com.erev.ta.common.dao.LoginValidationDAO;
import com.erev.ta.common.exception.BadRequestException;
import com.erev.ta.common.exception.NoContentException;
import com.erev.ta.model.CancellationPolicies;
import com.erev.ta.model.CancellationPoliciesBean;
import com.erev.ta.model.Candidate;
import com.erev.ta.model.GooglePlaceLatLngBean;
import com.erev.ta.model.Offers;
import com.erev.ta.performance.dao.HotelPerformanceDAO;
import com.erev.ta.search.dao.CommissionDAO;
import com.erev.ta.search.dao.HotelSearchDAO;
import com.erev.ta.settings.dao.PartnerAccessDAO;
import com.erev.ta.wallet.dao.WalletServiceDAO;
import com.erevmax.security.DataSecurity;
import com.github.rkpunjal.sqlsafe.SqlSafeUtil;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jettison.json.JSONException;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author AnupG
 */
@Component
public class CommonUtil {

    @Autowired
    private PartnerAccessDAO partnerAccessDAO;

    @Autowired
    LoginValidationDAO loginValidationDAO;

    @Autowired
    LevelInfoDAO levelInfoDAO;

    @Autowired
    WalletServiceDAO walletServiceDAO;

    @Autowired
    HotelPerformanceDAO hotelPerformanceDAO;

    @Autowired
    CommissionDAO commissionDAO;

    @Autowired
    private HotelSearchDAO hotelSearchDAO;

    @Value("${WALLET_CURRENCY}")
    private String walletCurrency;

    @Value("${COMMISSION_HOTELBEDS}")
    private String hotelbedsCommission;

    @Value("${COMMISSION_LOTSOFHOTELS}")
    private String lotsofhotelsCommission;

    @Value("${COMMISSION_MIKITRAVEL}")
    private String mikitravelCommission;

    @Value("${COMMISSION_HOTELBEDS_CAR}")
    private String hotelbedsCarCommission;

    @Value("${COMMISSION_CITYRIDE}")
    private String cityrideCommission;

    @Value("${GOOGLE_SEARCH_API_URL}")
    private String googleSearchApiUrl;

    @Value("${GOOGLE_SEARCH_API_KEY}")
    private String googleSearchApiKey;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);

    private static Map<String, String> usernameClientSuperMap = new HashMap<>();

    public static void setUsernameClientSuperMap(String clientName, String superUserName) {
        CommonUtil.usernameClientSuperMap.put(clientName, superUserName);
    }

    public static String getSupreUserNameByClientName(String clientName) {
        String superUserName = "";
        if (CommonUtil.usernameClientSuperMap.containsKey(clientName)) {
            superUserName = CommonUtil.usernameClientSuperMap.get(clientName);
        }
        return superUserName;
    }

    public static void clearUsernameClientSuperMap() {
        CommonUtil.usernameClientSuperMap.clear();
    }

    public static String convertDecimalNumToString(Number num) {
        DecimalFormat formatter = new DecimalFormat("0.00");
        return formatter.format(num);
    }

    public static String convertDecimalNumToString(Number num, String decimalPlaces) {
        DecimalFormat formatter = new DecimalFormat(decimalPlaces);
        return formatter.format(num);
    }

    /**
     *
     * @param num
     * @return
     */
    public static String convertDecimalNumberToString(Double number) {
        BigDecimal bd = new BigDecimal(number);
        return (String) bd.setScale(ServerConstants.FRACTIONAL_ROUNDOFF, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * Method to round off number upto two decimal places
     *
     * @param source
     * @return
     * @author Priyanka D
     */
    public static String convertDecimalNumToString(String source) {
        Double tmpDouble = Double.parseDouble(source);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(tmpDouble);
    }

    public static String getQueryIntegerFromList(List<String> elementList) {
        StringBuilder sbQueryParam = new StringBuilder();
        for (String strElem : elementList) {
            sbQueryParam.append(strElem);
            sbQueryParam.append(",");
        }
        String strQueryParam = sbQueryParam.toString();
        if (strQueryParam.length() > 0) {
            strQueryParam = strQueryParam.substring(0, strQueryParam.length() - 1);
        }
        return strQueryParam;
    }

    public static String getQueryStringFromSet(Set<String> elementSet) {
        StringBuilder sbQueryParam = new StringBuilder();
        for (String strElem : elementSet) {
            strElem = replaceSingleQuoteByDoubleQuote(strElem);
            sbQueryParam.append("'");
            sbQueryParam.append(strElem);
            sbQueryParam.append("'");
            sbQueryParam.append(",");
        }
        String strQueryParam = sbQueryParam.toString();
        if (strQueryParam.length() > 0) {
            strQueryParam = strQueryParam.substring(0, strQueryParam.length() - 1);
        }
        return strQueryParam;
    }

    public static String replaceSingleQuoteByDoubleQuote(String str) {
        String replacedStr = str;
        if (str != null && str.contains("'")) {
            replacedStr = str.replaceAll("'", "''");
        }
        return replacedStr;
    }

    public static String getQueryStringFromList(List<String> elementList) {
        StringBuilder sbQueryParam = new StringBuilder();
        for (String strElem : elementList) {
            strElem = replaceSingleQuoteByDoubleQuote(strElem);
            sbQueryParam.append("'");
            sbQueryParam.append(strElem);
            sbQueryParam.append("'");
            sbQueryParam.append(",");
        }
        String strQueryParam = sbQueryParam.toString();
        if (strQueryParam.length() > 0) {
            strQueryParam = strQueryParam.substring(0, strQueryParam.length() - 1);
        }
        return strQueryParam;
    }

    public static String getQueryStringFromIntegerSet(Set<Integer> elementSet) {
        StringBuilder sbQueryParam = new StringBuilder();
        for (Integer intElem : elementSet) {
            sbQueryParam.append("'");
            sbQueryParam.append(intElem);
            sbQueryParam.append("'");
            sbQueryParam.append(",");
        }
        String strQueryParam = sbQueryParam.toString();
        if (strQueryParam.length() > 0) {
            strQueryParam = strQueryParam.substring(0, strQueryParam.length() - 1);
        }
        return strQueryParam;
    }

    public static String getQueryStringFromIntegerList(List<Integer> elementList) {
        StringBuilder sbQueryParam = new StringBuilder();
        for (Integer intElem : elementList) {
            sbQueryParam.append("'");
            sbQueryParam.append(intElem);
            sbQueryParam.append("'");
            sbQueryParam.append(",");
        }
        String strQueryParam = sbQueryParam.toString();
        if (strQueryParam.length() > 0) {
            strQueryParam = strQueryParam.substring(0, strQueryParam.length() - 1);
        }
        return strQueryParam;
    }

    /**
     * This method is use to get currency Factor According to user currency
     *
     * @param agentCurrency
     * @return currencyFactor
     */
    public static double getCurrencyFactor(String agentCurrency) {
        if (agentCurrency.equalsIgnoreCase(ServerConstants.CURRENCY)) {
            return 1;
        }
        if (CacheUtil.currencyFactorMap.get(agentCurrency) == null) {
            return 1;
        }
        return CacheUtil.currencyFactorMap.get(agentCurrency);
    }

    /**
     * This method is use to get currency conversion Factor From one currency to
     * another currency
     *
     * @param fromCurrency
     * @param toCurrency
     * @return currencyFactor
     */
    public static double getCurrencyConversionFactor(String fromCurrency, String toCurrency) {
        double dblConvFactor = -1;
        if (toCurrency.equalsIgnoreCase(ServerConstants.EREV_CURRENCY)) {
            toCurrency = ServerConstants.CURRENCY;
        }
        Double toFactorVal = CacheUtil.currencyFactorMap.get(toCurrency);
        if (toFactorVal != null) {
            if (fromCurrency.equalsIgnoreCase(ServerConstants.EREV_CURRENCY)) {
                fromCurrency = ServerConstants.CURRENCY;
            }
            if (!fromCurrency.equalsIgnoreCase(ServerConstants.CURRENCY)) {
                Double fromFactorVal = CacheUtil.currencyFactorMap.get(fromCurrency);

                if (fromFactorVal != null) {
                    double dblFromCurrConvFactor = fromFactorVal;
                    double dblToCurrConvFactor = toFactorVal;
                    dblConvFactor = dblToCurrConvFactor / dblFromCurrConvFactor;
                } else {
                    LOGGER.info("No conversion factor found for fromFactorVal ::" + fromFactorVal + ":: toFactorVal ::" + toFactorVal);
                }
            } else {
                dblConvFactor = toFactorVal;
            }
        }
        return dblConvFactor;
    }

    /**
     * This method is use to get Month name by month number
     *
     * @param month number
     * @return month
     */
    public static String getMonthNameByMonthNumber(String monthNum) {
        String monthName = "";
        switch (Integer.valueOf(monthNum)) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }
        return monthName;
    }

    /**
     * This method is use to get Month Number by month Name
     *
     * @param month Name
     * @return Number
     */
    public static String getMonthNumberByMonthName(String monthName) {
        String monthnum = "";

        switch (monthName) {
            case "January":
                monthnum = "1";
                break;
            case "February":
                monthnum = "2";
                break;
            case "March":
                monthnum = "3";
                break;
            case "April":
                monthnum = "4";
                break;
            case "May":
                monthnum = "5";
                break;
            case "June":
                monthnum = "6";
                break;
            case "July":
                monthnum = "7";
                break;
            case "August":
                monthnum = "8";
                break;
            case "September":
                monthnum = "9";
                break;
            case "October":
                monthnum = "10";
                break;
            case "November":
                monthnum = "11";
                break;
            case "December":
                monthnum = "12";
                break;
        }

        return monthnum;
    }

    /**
     *
     * @param starCategoryList
     * @param starRating
     * @return isStartRatingMatch
     */
    public static boolean getIsStartRateMatch(List<String> starCategoryList, String starRating) {
        boolean isStartRatingMatch = false;
        boolean isFiveStartRating = false;
        boolean isOneStartRating = false;
        for (String ratting : starCategoryList) {
            switch (ratting.trim()) {
                case "1":
                    isOneStartRating = true;
                    if (starRating.equalsIgnoreCase("1")) {
                        isStartRatingMatch = true;
                    }
                    break;
                case "2":
                    if (starRating.equalsIgnoreCase("2")) {
                        isStartRatingMatch = true;
                    }
                    break;
                case "3":
                    if (starRating.equalsIgnoreCase("3")) {
                        isStartRatingMatch = true;
                    }
                    break;
                case "4":
                    if (starRating.equalsIgnoreCase("4")) {
                        isStartRatingMatch = true;
                    }
                    break;
                case "5":
                    isFiveStartRating = true;
                    if (starRating.equalsIgnoreCase("5")) {
                        isStartRatingMatch = true;
                    }
                    break;
                default:
//                    isStartRatingMatch = true;
            }
        }
        try {
            if (starRating.isEmpty()) {
                isStartRatingMatch = true;
            } else {
                int rating = Integer.parseInt(starRating);
                if (rating > 5 && isFiveStartRating) {
                    isStartRatingMatch = true;
                } else if (rating < 1 && isOneStartRating) {
                    isStartRatingMatch = true;
                } else if (starCategoryList.size() == 5 && rating >= 1) {
                    isStartRatingMatch = true;
                }
            }

        } catch (Exception e) {
            isStartRatingMatch = true;
//            LOGGER.error("Exception : " + e.getMessage());
        }
        return isStartRatingMatch;
    }

    /**
     * This method is use to GMT date format
     *
     * @param requestedDate
     * @return date
     */
    public static String getFormatedDateFromGMT(String requestedDate) {
        DateFormat inputFormat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss 'GMT'z");
        Date date = null;
        try {
            date = inputFormat.parse(requestedDate);
        } catch (ParseException e) {
            LOGGER.info("parse error in getDateFrom GMT ", e);
        }
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        outputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String strDay = outputFormat.format(date);
        return strDay;
    }

    public static String getFormatedDateTimeFromGMT(String requestedDate) {
        LOGGER.info("Request Date Time :: " + requestedDate);
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
     * This method is use to get today GMT date format
     *
     * @param requestedDate
     * @return date
     */
    public static String getTodayFormatedDateFromGMT() {
        Date date = new Date();
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        outputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String strDay = outputFormat.format(date);
        return strDay;
    }

    /**
     * Get Current date and time as per REQUEST_DATE_TIME_FORMAT_WITH_TIMEZONE
     * (EEE MMM dd yyyy HH:mm:ss zzz).
     */
    public static String getCurrentDateTimeRQ() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(ServerConstants.REQUEST_DATE_TIME_FORMAT_WITH_TIMEZONE);
        return sdf.format(cal.getTime());
    }

    /**
     * To get dateDifference between DBdateTime and currentDateTime
     *
     * @param dateStr1
     * @param dateStr2
     * @return
     * @throws Exception
     */
    public static int daysDiffFromDate(String dateStr1, String dateStr2) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(ServerConstants.DATE_FORMAT);

        Date date1 = sdf.parse(dateStr1.substring(0, 10));
        Date date2 = sdf.parse(dateStr2.substring(0, 10));

        return Days.daysBetween(new LocalDate(date1.getTime()), new LocalDate(date2.getTime())).getDays();
    }

    /**
     * Validates SQL injections in query string.
     *
     * @param strQueryWord
     * @return isValid
     */
    public static boolean isValidQueryString(String strQueryWord) {
        boolean isValid = true;
        if (strQueryWord.contains("--") || strQueryWord.contains("'") || strQueryWord.contains(";")
                || strQueryWord.contains("show") || strQueryWord.contains("tables") || strQueryWord.contains("UPDATE")
                || strQueryWord.contains("DROP") || strQueryWord.contains("table") || strQueryWord.contains("=")
                || strQueryWord.contains("\\") || strQueryWord.contains(",") || strQueryWord.contains("/")
                || strQueryWord.contains("`") || strQueryWord.contains("LIKE'")
                || strQueryWord.contains("+")) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Validates a user with its agency.
     *
     * @param userName, agencyId
     * @return isValid
     */
    public boolean userAgencyValidation(String userName, String agencyId) {
        boolean isValid = false;
        List<Map<String, Object>> userInfoList = partnerAccessDAO.getUserInfo(userName);
        String userLevel = "";
        String userId = "";
        if (userInfoList != null && !userInfoList.isEmpty()) {
            userLevel = userInfoList.get(0).get("User_Level").toString().trim();
            userId = userInfoList.get(0).get("User_Id").toString();
        } else {
            return isValid;
        }
        String dbAgencyId = loginValidationDAO.getAgencyId(userId, userLevel);
        if (dbAgencyId.trim().equalsIgnoreCase(agencyId.trim())) {
            isValid = true;
        }
        return isValid;
    }

    /**
     *
     * @param cancelPolicyList
     * @param currency
     * @return
     */
    @Deprecated
    public static String getCancellationPoliciesString(List<CancellationPolicies> cancelPolicyList, String currency) {
        String policyText = "";
        for (CancellationPolicies policy : cancelPolicyList) {
            String text = "";
            String isPercentage = policy.getIsPercentage() != null ? policy.getIsPercentage().trim() : ServerConstants.FALSE;
            String amount = policy.getAmount() != null ? policy.getAmount().trim() : "";
            String toDate = policy.getToDate() != null ? policy.getToDate().trim() : "";
            String fromDate = policy.getFromDate() != null ? policy.getFromDate().trim() : "";
            toDate = toDate.replaceAll("T", " ");
            fromDate = fromDate.replaceAll("T", " ");
            if (amount.equalsIgnoreCase("null")) {
                amount = "";
            }
            if (toDate.equalsIgnoreCase("null")) {
                toDate = "";
            }
            if (fromDate.equalsIgnoreCase("null")) {
                fromDate = "";
            }

            if (amount.equalsIgnoreCase("0") || amount.equalsIgnoreCase("0.00") || amount.equalsIgnoreCase("0.0")) {
                if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                    text = "Free Cancellation, If cancelled between " + fromDate + " and " + toDate;
                } else {
                    text = "Free Cancellation";
                }
            } else if (amount.equalsIgnoreCase("100") && isPercentage.equalsIgnoreCase("true")) {
                if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                    text = "Non refundable, If cancelled between " + fromDate + " and " + toDate;
                } else {
                    text = "Non refundable";
                }
            } else if (!amount.isEmpty()) {

                if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                    text = isPercentage.equalsIgnoreCase("false") ? "Cancellation charge of " + amount + " " + currency + " will apply if booking is cancelled between " + fromDate + " and " + toDate : "Cancellation charge of " + amount + " % will apply if booking is cancelled between " + fromDate + " and " + toDate;
                } else if (toDate.isEmpty()) {
                    text = isPercentage.equalsIgnoreCase("false") ? "Cancellation charge of " + amount + " " + currency + " will apply if booking is cancelled on or after " + fromDate : "Cancellation charge of " + amount + " % will apply if booking is cancelled on or after " + fromDate;
                } else {
                    text = isPercentage.equalsIgnoreCase("false") ? "Cancellation charge of " + amount + " " + currency + " will apply if booking is cancelled on or before " + toDate : "Cancellation charge of " + amount + " % will apply if booking is cancelled on or before " + toDate;
                }
//                String deadline = policy.getToDate();
//                if (amount != "" && deadline != "") {
//                    text = policy.getIsPercentage().equalsIgnoreCase("false") ? "Cancellation charge of " + amount + " will apply if booking is cancelled before " + deadline : "Cancellation charge of " + amount + " % will apply if booking is cancelled before " + deadline ;
//                } else if (amount != "" && deadline == "") {
//                    text = policy.getIsPercentage().equalsIgnoreCase("false") ? "Cancellation charge of " + amount + " will apply if booking is cancelled" : "Cancellation charge of " + amount + " % will apply if booking is cancelled";
//                }
            }
            if (policyText.isEmpty()) {
                policyText = text;
            } else if (!text.isEmpty()) {
                policyText = policyText + " , " + text;
            }
        }

        if (policyText.length() > 0) {
            policyText = policyText + ".";
        }

        return policyText;
    }

    /**
     *
     * @param cancelPolicyList
     * @param currency
     * @param pAmount
     * @return
     */
    public static String getCancellationPoliciesString(List<CancellationPolicies> cancelPolicyList, String currency, String pAmount) {
        String policyText = "";
        try {
            for (CancellationPolicies policy : cancelPolicyList) {
                String text = "";
                String isPercentage = policy.getIsPercentage() != null ? policy.getIsPercentage().trim() : ServerConstants.FALSE;
                String amount = policy.getAmount() != null ? policy.getAmount().trim() : "";
                String toDate = policy.getToDate() != null ? policy.getToDate().trim() : "";
                String fromDate = policy.getFromDate() != null ? policy.getFromDate().trim() : "";
                String partnerAmount = policy.getAmountPartnerCurrency() != null ? policy.getAmountPartnerCurrency().trim() : "";
                String chargeDescription = policy.getChargeDescription() == null ? "" : policy.getChargeDescription();
                toDate = toDate.replaceAll("T", " ");
                fromDate = fromDate.replaceAll("T", " ");
                if (amount.equalsIgnoreCase("null")) {
                    amount = "";
                }
                if (toDate.equalsIgnoreCase("null")) {
                    toDate = "";
                }
                if (fromDate.equalsIgnoreCase("null")) {
                    fromDate = "";
                }
                int partnerAmountval = 0;
                if (!partnerAmount.isEmpty()) {
                    partnerAmountval = (int) Double.parseDouble(partnerAmount);
                }
                int pAmountValue = 0;
                if (pAmount != null && !pAmount.isEmpty()) {
                    pAmountValue = (int) Double.parseDouble(pAmount);
                }
                if (partnerAmountval != 0 && partnerAmountval == pAmountValue) {
                    if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                        text = "Non refundable, If cancelled between " + fromDate + " and " + toDate;
                    } else if (toDate.isEmpty()) {
                        text = "Non refundable, If cancelled on or after " + fromDate;
                    } else {
                        text = "Non refundable";
                    }

                    if (!chargeDescription.isEmpty()) {
                        if (chargeDescription.contains(ServerConstants.NO_SHOW_TEXT)) {
                            text = text + ". " + ServerConstants.NO_SHOW_TEXT;
                        }
                    }
                    if (policyText.isEmpty()) {
                        policyText = text;
                    } else if (!text.isEmpty()) {
                        policyText = policyText + " , <br> " + text;
                    }
                } else {
                    if (amount.equalsIgnoreCase("0") || amount.equalsIgnoreCase("0.00") || amount.equalsIgnoreCase("0.0")) {
                        if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                            text = "Free Cancellation, If cancelled between " + fromDate + " and " + toDate;
                        } else {
                            text = "Free Cancellation";
                        }
                    } else if (amount.equalsIgnoreCase("100") && isPercentage.equalsIgnoreCase("true")) {
                        if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                            text = "Non refundable, If cancelled between " + fromDate + " and " + toDate;
                        } else {
                            text = "Non refundable";
                        }
                    } else if (!amount.isEmpty()) {

                        if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                            text = isPercentage.equalsIgnoreCase("false") ? "Cancellation charge of " + amount + " " + currency + " will apply if booking is cancelled between " + fromDate + " and " + toDate : "Cancellation charge of " + amount + " % will apply if booking is cancelled between " + fromDate + " and " + toDate;
                        } else if (toDate.isEmpty()) {
                            text = isPercentage.equalsIgnoreCase("false") ? "Cancellation charge of " + amount + " " + currency + " will apply if booking is cancelled on or after " + fromDate : "Cancellation charge of " + amount + " % will apply if booking is cancelled on or after " + fromDate;
                        } else {
                            text = isPercentage.equalsIgnoreCase("false") ? "Cancellation charge of " + amount + " " + currency + " will apply if booking is cancelled on or before " + toDate : "Cancellation charge of " + amount + " % will apply if booking is cancelled on or before " + toDate;
                        }
                        //                String deadline = policy.getToDate();
                        //                if (amount != "" && deadline != "") {
                        //                    text = policy.getIsPercentage().equalsIgnoreCase("false") ? "Cancellation charge of " + amount + " will apply if booking is cancelled before " + deadline : "Cancellation charge of " + amount + " % will apply if booking is cancelled before " + deadline ;
                        //                } else if (amount != "" && deadline == "") {
                        //                    text = policy.getIsPercentage().equalsIgnoreCase("false") ? "Cancellation charge of " + amount + " will apply if booking is cancelled" : "Cancellation charge of " + amount + " % will apply if booking is cancelled";
                        //                }
                    }

                    if (!chargeDescription.isEmpty()) {
                        if (chargeDescription.contains(ServerConstants.NO_SHOW_TEXT)) {
                            text = text + ". " + ServerConstants.NO_SHOW_TEXT;
                        }
                    }

                    if (policyText.isEmpty()) {
                        policyText = text;
                    } else if (!text.isEmpty()) {
                        policyText = policyText + " , <br> " + text;
                    }
                }
            }
            if (policyText.length() > 0) {
                policyText = policyText + ".";
            }
        } catch (Exception e) {
            LOGGER.error("Exception in generate CancellatioPolicies String" + e.getMessage());
        }

        return policyText;
    }

    /**
     *
     * @param policy
     * @param currency
     * @return
     */
    @Deprecated
    public static String getCancellationPoliciesString(CancellationPolicies policy, String currency) {
        String text = "";
        String isPercentage = policy.getIsPercentage() != null ? policy.getIsPercentage().trim() : ServerConstants.FALSE;
        String amount = policy.getAmount() != null ? policy.getAmount().trim() : "";
        String toDate = policy.getToDate() != null ? policy.getToDate().trim() : "";
        String fromDate = policy.getFromDate() != null ? policy.getFromDate().trim() : "";
        toDate = toDate.replaceAll("T", " ");
        fromDate = fromDate.replaceAll("T", " ");
        if (amount.equalsIgnoreCase("null")) {
            amount = "";
        }
        if (toDate.equalsIgnoreCase("null")) {
            toDate = "";
        }
        if (fromDate.equalsIgnoreCase("null")) {
            fromDate = "";
        }

        if (amount.equalsIgnoreCase("0") || amount.equalsIgnoreCase("0.00") || amount.equalsIgnoreCase("0.0")) {
            if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                text = "Free Cancellation, If cancelled between " + fromDate + " and " + toDate;
            } else if (!fromDate.isEmpty()) {
                text = "Free Cancellation, If cancelled on or after " + fromDate;
            } else {
                text = "Free Cancellation";
            }
        } else if (amount.equalsIgnoreCase("100") && isPercentage.equalsIgnoreCase("true")) {
            if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                text = "Non refundable, If cancelled between " + fromDate + " and " + toDate;
            } else if (toDate.isEmpty()) {
                text = "Non refundable, If cancelled on or after " + fromDate;
            } else {
                text = "Non refundable";
            }
        } else if (!amount.isEmpty()) {

            if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                text = isPercentage.equalsIgnoreCase("false") ? "Cancellation charge of " + amount + " " + currency + " will apply if booking is cancelled between " + fromDate + " and " + toDate : "Cancellation charge of " + amount + " % will apply if booking is cancelled between " + fromDate + " and " + toDate;
            } else if (toDate.isEmpty()) {
                text = isPercentage.equalsIgnoreCase("false") ? "Cancellation charge of " + amount + " " + currency + " will apply if booking is cancelled on or after " + fromDate : "Cancellation charge of " + amount + " % will apply if booking is cancelled on or after " + fromDate;
            } else {
                text = isPercentage.equalsIgnoreCase("false") ? "Cancellation charge of " + amount + " " + currency + " will apply if booking is cancelled on or before " + toDate : "Cancellation charge of " + amount + " % will apply if booking is cancelled on or before " + toDate;
            }
        }

        if (text.length() > 0) {
            text = text + ".";
        }

        return text;
    }

    /**
     *
     * @param policy
     * @param currency
     * @param pAmount
     * @return
     */
    public static String getCancellationPoliciesString(CancellationPolicies policy, String currency, String pAmount) {
        String text = "";
        String isPercentage = policy.getIsPercentage() != null ? policy.getIsPercentage().trim() : ServerConstants.FALSE;
        String amount = policy.getAmount() != null ? policy.getAmount().trim() : "";
        String toDate = policy.getToDate() != null ? policy.getToDate().trim() : "";
        String fromDate = policy.getFromDate() != null ? policy.getFromDate().trim() : "";
        String partnerAmount = policy.getAmountPartnerCurrency() != null ? policy.getAmountPartnerCurrency().trim() : "";
        toDate = toDate.replaceAll("T", " ");
        fromDate = fromDate.replaceAll("T", " ");
        if (amount.equalsIgnoreCase("null")) {
            amount = "";
        }
        if (toDate.equalsIgnoreCase("null")) {
            toDate = "";
        }
        if (fromDate.equalsIgnoreCase("null")) {
            fromDate = "";
        }
        try {
            int partnerAmountval = 0;
            if (!partnerAmount.isEmpty()) {
                partnerAmountval = (int) Double.parseDouble(partnerAmount);
            }
            int pAmountValue = 0;
            if (pAmount != null && !pAmount.isEmpty()) {
                pAmountValue = (int) Double.parseDouble(pAmount);
            }
            if (partnerAmountval != 0 && partnerAmountval == pAmountValue) {
                if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                    text = "Non refundable, If cancelled between " + fromDate + " and " + toDate;
                } else if (toDate.isEmpty()) {
                    text = "Non refundable, If cancelled on or after " + fromDate;
                } else {
                    text = "Non refundable";
                }
            } else {
                if (amount.equalsIgnoreCase("0") || amount.equalsIgnoreCase("0.00") || amount.equalsIgnoreCase("0.0")) {
                    if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                        text = "Free Cancellation, If cancelled between " + fromDate + " and " + toDate;
                    } else if (!fromDate.isEmpty()) {
                        text = "Free Cancellation, If cancelled on or after " + fromDate;
                    } else {
                        text = "Free Cancellation";
                    }
                } else if (amount.equalsIgnoreCase("100") && isPercentage.equalsIgnoreCase("true")) {
                    if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                        text = "Non refundable, If cancelled between " + fromDate + " and " + toDate;
                    } else if (toDate.isEmpty()) {
                        text = "Non refundable, If cancelled on or after " + fromDate;
                    } else {
                        text = "Non refundable";
                    }
                } else if (!amount.isEmpty()) {

                    if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                        text = isPercentage.equalsIgnoreCase("false") ? "Cancellation charge of " + amount + " " + currency + " will apply if booking is cancelled between " + fromDate + " and " + toDate : "Cancellation charge of " + amount + " % will apply if booking is cancelled between " + fromDate + " and " + toDate;
                    } else if (toDate.isEmpty()) {
                        text = isPercentage.equalsIgnoreCase("false") ? "Cancellation charge of " + amount + " " + currency + " will apply if booking is cancelled on or after " + fromDate : "Cancellation charge of " + amount + " % will apply if booking is cancelled on or after " + fromDate;
                    } else {
                        text = isPercentage.equalsIgnoreCase("false") ? "Cancellation charge of " + amount + " " + currency + " will apply if booking is cancelled on or before " + toDate : "Cancellation charge of " + amount + " % will apply if booking is cancelled on or before " + toDate;
                    }
                }
            }
            if (text.length() > 0) {
                text = text + ".";
            }
        } catch (Exception e) {
            LOGGER.error("Exception in generate CancellatioPolicies String" + e.getMessage());
        }
        return text;
    }

    /**
     *
     * @param offer
     * @param currency
     * @return
     */
    public static String getOfferString(Offers offer, String currency) {
        String text = "";
        boolean isPercentage = offer.getIsPercentage() != null ? Boolean.valueOf(offer.getIsPercentage().trim()) : false;
        String amount = offer.getAmount() != null ? offer.getAmount().trim() : "";
        String toDate = offer.getToDate() != null ? offer.getToDate().trim() : "";
        String fromDate = offer.getFromDate() != null ? offer.getFromDate().trim() : "";
        toDate = toDate.replaceAll("T", " ");
        fromDate = fromDate.replaceAll("T", " ");
        if (amount.equalsIgnoreCase("null")) {
            amount = "";
        }
        if (toDate.equalsIgnoreCase("null")) {
            toDate = "";
        }
        if (fromDate.equalsIgnoreCase("null")) {
            fromDate = "";
        }

        if (amount.equalsIgnoreCase("100") && isPercentage) {
            if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                text = offer.getName() + " between " + fromDate + " and " + toDate;
            } else {
                text = offer.getName();
            }
        } else if (!amount.isEmpty()) {

            if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                text = !isPercentage ? offer.getName() + " , Booked charge of " + amount + " " + currency + " will be apply between " + fromDate + " and " + toDate : offer.getName() + " , Booked charge of " + amount + " % will be apply between " + fromDate + " and " + toDate;
            } else if (toDate.isEmpty() && fromDate.isEmpty()) {
                text = !isPercentage ? offer.getName() + " , Booked charge of " + amount + " " + currency + " will be apply " : offer.getName() + " , Booked charge of " + amount + " % will be apply ";
            } else if (toDate.isEmpty()) {
                text = !isPercentage ? offer.getName() + " , Booked charge of " + amount + " " + currency + " will be apply on or after " + fromDate : offer.getName() + " , Booked charge of " + amount + " % will be apply on or after " + fromDate;
            } else {
                text = !isPercentage ? offer.getName() + " , Booked charge of " + amount + " " + currency + " will be apply on or before " + toDate : offer.getName() + " , Booked charge of " + amount + " " + currency + " will be apply on or before ";
            }
        } else {
            if (!toDate.isEmpty() && !fromDate.isEmpty()) {
                text = offer.getName() + " , If Booked between " + fromDate + " and " + toDate;
            } else {
                text = offer.getName();
            }
        }

        if (text.length() > 0) {
            text = text + ".";
        }
        return text;
    }

    /**
     * This method is use to parse Date
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date getDateInstance(final String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(ServerConstants.DATE_FORMAT);
        sdf.setLenient(false);
        return sdf.parse(strDate);
    }

    /**
     * SB Algorithm to search a string within a text.
     *
     * @param pattern
     * @param text
     * @return
     */
    public static List<Integer> match(String pattern, String text) {
        List<Integer> matches = new ArrayList<>();
        int m = text.length();
        int n = pattern.length();
        Map<String, Integer> rightMostIndexes = preprocessForBadCharacterShift(pattern);
        int alignedAt = 0;
        while (alignedAt + (n - 1) < m) {
            for (int indexInPattern = n - 1; indexInPattern >= 0; indexInPattern--) {
                int indexInText = alignedAt + indexInPattern;
                char x = text.charAt(indexInText);
                char y = pattern.charAt(indexInPattern);
                String XX = x + "";
                String YY = y + "";
                if (indexInText >= m) {
                    break;
                }
                if (!XX.equalsIgnoreCase(YY)) {
                    Integer r = rightMostIndexes.get(XX.toLowerCase());
                    if (r == null) {
                        alignedAt = indexInText + 1;
                    } else {
                        int shift = indexInText - (alignedAt + r);
                        alignedAt += shift > 0 ? shift : 1;
                    }
                    break;
                } else if (indexInPattern == 0) {
                    if (alignedAt != 0 && text.substring(alignedAt - 1, alignedAt).equals(" ")) {
                        matches.add(-1);
                    }
                    matches.add(alignedAt);
                    alignedAt++;
                }
            }
        }
        return matches;
    }

    private static Map<String, Integer> preprocessForBadCharacterShift(
            String pattern) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = pattern.length() - 1; i >= 0; i--) {
            char c = pattern.charAt(i);
            String CC = (c + "").toLowerCase();
            if (!map.containsKey(CC)) {
                map.put(CC, i);
            }
        }
        return map;
    }

    public static String removeBad(String badText) {
        String goodText = "";
        goodText = badText.replaceAll("^\\s+", "");
//        goodText = goodText.replaceAll("[,#@]", " ");
        return goodText;
    }

    public static String createClonedSearchKey(String searchString) {
        String clonedKey = "";
        if (searchString.contains("'")) {
            clonedKey = searchString.replace("'", "’");
        } else if (searchString.contains("’")) {
            clonedKey = searchString.replace("’", "'");
        }
        return clonedKey;
    }

    /**
     * This method is use to get branch info according to any level type
     *
     * @param userName
     * @param agencyId
     * @param userId
     * @param dataType
     * @param levelType
     * @param levelIds
     * @return
     * @throws com.erev.ta.common.exception.BadRequestException
     */
    public Map<String, Set<String>> getBranchIdMap(String userName, String agencyId, String userId, String dataType, LevelType levelType, List<String> levelIds) throws BadRequestException {

        Map<String, Set<String>> branchRowMap = new HashMap<>();
        Set<String> regionIdSet = new HashSet<>();
        Set<String> countryCodeSet = new HashSet<>();
        Set<String> cityIdSet = new HashSet<>();
        switch (levelType) {
//            case NullType:
//                for (String levelValue : levelIds) {
//                    String strRegionId = levelValue.substring(levelValue.indexOf("-")+1);
//                    regionIdSet.add(strRegionId);
//                }
//                break;
//            case Global:
//                for (String levelValue : levelIds) {
//                    String strRegionId = levelValue.substring(levelValue.indexOf("-")+1);
//                    regionIdSet.add(strRegionId);
//                }
//                break;
            case Region:
                for (String levelValue : levelIds) {
                    String regionId = levelValue.substring(levelValue.indexOf("-") + 1);
                    regionIdSet.add(regionId);
                }
                break;
            case Country:
                for (String levelValue : levelIds) {
                    String regionId = levelValue.substring(0, levelValue.indexOf("-"));
                    String country = levelValue.substring(levelValue.indexOf("-") + 1);
                    regionIdSet.add(regionId);
                    countryCodeSet.add(country);
                }
                break;
            case City:
                for (String levelValue : levelIds) {
                    String regionId = levelValue.substring(0, levelValue.indexOf("-"));
                    String city = levelValue.substring(levelValue.indexOf("-") + 1);
                    regionIdSet.add(regionId);
                    cityIdSet.add(city);
                }
                break;
            case Branch:
                for (String levelValue : levelIds) {
                    Set<String> levelIdSet = new HashSet<>();
                    levelIdSet.add(levelValue);
                    branchRowMap.put(levelValue, levelIdSet);
                }
                return branchRowMap;
        }

        String regionIdStr = "''";
        if (!regionIdSet.isEmpty()) {
            regionIdStr = getQueryStringFromSet(regionIdSet);
        }

        String countryCodeStr = "''";
        if (!countryCodeSet.isEmpty()) {
            countryCodeStr = getQueryStringFromSet(countryCodeSet);
        }

        String cityIdStr = "''";
        if (!cityIdSet.isEmpty()) {
            cityIdStr = getQueryStringFromSet(cityIdSet);
        }

        LOGGER.info("regionIdStr :: " + regionIdStr + "  ::  countryCodeStr :: " + countryCodeStr + "  :: cityIdStr :: " + cityIdStr);

        List<Map<String, Object>> resultSetRows = loginValidationDAO.validateUserNameAndPassword(userName, "");

        if (!resultSetRows.isEmpty()) {
            String userLevel = "";
            Map resultRowMap = (Map) resultSetRows.get(0);
            boolean userStatus = ((Boolean) resultRowMap.get("Status"));
            if (userStatus) {
                userLevel = ((String) (resultRowMap.get("User_Level") == null ? "" : resultRowMap.get("User_Level"))).trim();
            }
            if (userId != null && !userId.isEmpty()) {
                List<Map<String, Object>> agencyInfoList = levelInfoDAO.getUserAgencyInfo(userId, userLevel);
                String parentTAId = "";
                if (!agencyInfoList.isEmpty()) {
                    Map<String, Object> agencyInfo = agencyInfoList.get(0);
                    agencyId = agencyInfo.get("Travel_Agency_Id").toString();
                    parentTAId = agencyInfo.get("Parent_TA_Id").toString();
                }
                if (agencyId.equalsIgnoreCase(parentTAId)) {
                    List<String> subAgencyInfo = levelInfoDAO.getSubAgencyInfo(agencyId);
                    if (subAgencyInfo.isEmpty() || subAgencyInfo.size() == 1) {
                        branchRowMap = levelInfoDAO.getBranchIdInfoMap(agencyId, userId, dataType, levelType, regionIdStr, countryCodeStr, cityIdStr);
                    } else {
                        subAgencyInfo.remove(agencyId);
                        branchRowMap = levelInfoDAO.getBranchIdWithSubAgencyInfoMap(agencyId, userId, dataType, levelType, subAgencyInfo, regionIdStr, countryCodeStr, cityIdStr);
                    }
                } else {
                    branchRowMap = levelInfoDAO.getBranchIdInfoMap(agencyId, userId, dataType, levelType, regionIdStr, countryCodeStr, cityIdStr);
                }
            }
        }
        return branchRowMap;
    }

    /**
     * This method is use to get next level of datatype
     *
     * @param requestedLevelType
     * @return
     */
    public static LevelType getNextLevelType(LevelType requestedLevelType) {
        LevelType nextLevelType = LevelType.Branch;
        switch (requestedLevelType) {
            case Global:
                nextLevelType = LevelType.Region;
                break;
            case Region:
                nextLevelType = LevelType.Country;
                break;
            case Country:
                nextLevelType = LevelType.City;
                break;
            case City:
                nextLevelType = LevelType.Branch;
                break;
        }
        return nextLevelType;
    }

    /**
     * This method is for replacing Space in a string with under score.
     *
     * @param fileName
     * @return
     */
    public static String replaceSpaceToUnderScore(String fileName) {
        fileName = fileName.replaceAll(" ", "_");
        return fileName;
    }

    /**
     * This method is use to get Db Storing Error Message
     *
     * @param message
     * @param code
     * @return
     */
    public static String getDbErrorMessage(String message, String code) {
        if (message == null) {
            message = "";
        }
        String errorMessage = message.toUpperCase().trim();
        String dbMessage = null;
        switch (errorMessage) {

            case ServerConstants.INVALID_REQUEST:
                dbMessage = ServerConstants.INVALID_REQUEST_MESSAGE;
                break;
            case ServerConstants.INVALID_DATA:
                if (code.equalsIgnoreCase("400")) {
                    dbMessage = ServerConstants.INVALID_DATA_400_MESSAGE;
                } else {
                    dbMessage = ServerConstants.INVALID_DATA_410_MESSAGE;
                }
                break;
            case ServerConstants.DEVELOPER_INACTIVE:
                dbMessage = ServerConstants.DEVELOPER_INACTIVE_MESSAGE;
                break;
            case ServerConstants.NOT_AUTHORIZED:
                dbMessage = ServerConstants.NOT_AUTHORIZED_MESSAGE;
                break;
            case ServerConstants.FORBIDDEN:
                dbMessage = ServerConstants.FORBIDDEN_MESSAGE;
                break;
            case ServerConstants.DEVELOPER_OVER_QPS:
                dbMessage = ServerConstants.DEVELOPER_OVER_QPS_MESSAGE;
                break;
            case ServerConstants.DEVELOPER_OVER_RATE:
                dbMessage = ServerConstants.DEVELOPER_OVER_RATE_MESSAGE;
                break;
            case ServerConstants.NOT_FOUND:
                dbMessage = ServerConstants.NOT_FOUND_MESSAGE;
                break;
            case ServerConstants.SYSTEM_ERROR:
                dbMessage = ServerConstants.SYSTEM_ERROR_MESSAGE;
                break;
            case ServerConstants.PRODUCT_ERROR:
                dbMessage = ServerConstants.PRODUCT_ERROR_MESSAGE;
                break;
            case ServerConstants.CONFIGURATION_ERROR:
                dbMessage = ServerConstants.CONFIGURATION_ERROR_MESSAGE;
                break;
            case ServerConstants.SERVICE_OVERLOAD:
                dbMessage = ServerConstants.SERVICE_OVERLOAD_MESSAGE;
                break;
            case ServerConstants.TIMEOUT_ERROR:
                dbMessage = ServerConstants.TIMEOUT_ERROR_MESSAGE;
                break;
            default:
                dbMessage = ServerConstants.NOT_PROPER_MESSAGE;
        }

        return dbMessage;
    }

    /**
     * This method is use to get Interface Error Message
     *
     * @param code
     * @return message
     */
    public static String getInterfaceErrorMessage(String code) {
        if (code == null) {
            code = "";
        }
        String errorMessage = code.trim();
        String message = null;
        switch (errorMessage) {
            case "400":
                message = ServerConstants.ERROR_CODE_400;
                break;
            case "403":
                message = ServerConstants.ERROR_CODE_403;
                break;
            case "404":
                message = ServerConstants.ERROR_CODE_404;
                break;
            case "410":
                message = ServerConstants.ERROR_CODE_410;
                break;
            case "500":
                message = ServerConstants.ERROR_CODE_500;
                break;
            case "503":
                message = ServerConstants.ERROR_CODE_503;
                break;
            case "504":
                message = ServerConstants.ERROR_CODE_504;
                break;
            default:
                message = ServerConstants.ERROR_CODE_GENERAL;
        }
        return message;
    }

    /**
     *
     * @param hotelItineraryList
     * @param checkDate
     * @param ischeckInDate
     * @return
     * @throws ParseException
     */
    public static Date getTripStartEndDate(List<Map<String, Object>> hotelItineraryList, Date checkDate, boolean ischeckInDate) throws ParseException {
        Date returnDate = new Date();
        if (ischeckInDate == true) {
            List<Date> checkInDateList = new ArrayList<>();
            for (Map<String, Object> hotelItinerary : hotelItineraryList) {
                checkInDateList.add(CommonUtil.getDateInstance(hotelItinerary.get("Checkin_Date").toString()));
            }
            checkInDateList.remove(checkDate);
            if (checkInDateList.isEmpty()) {
                return checkDate;
            }
            returnDate = checkInDateList.get(0);
            for (Date inDate : checkInDateList) {
                if (inDate.before(returnDate)) {
                    returnDate = inDate;
                }
            }
        } else {
            List<Date> checkOutDateList = new ArrayList<>();
            for (Map<String, Object> hotelItinerary : hotelItineraryList) {
                String checkoutDate = hotelItinerary.get("Checkout_Date") != null ? hotelItinerary.get("Checkout_Date").toString() : hotelItinerary.get("Checkin_Date").toString();
                checkOutDateList.add(CommonUtil.getDateInstance(checkoutDate));
            }
            checkOutDateList.remove(checkDate);
            if (checkOutDateList.isEmpty()) {
                return checkDate;
            }
            returnDate = checkOutDateList.get(0);
            for (Date outDate : checkOutDateList) {
                if (outDate.after(returnDate)) {
                    returnDate = outDate;
                }
            }
        }
        return returnDate;
    }

    /**
     * This method is for getting Empty string if the parameter Object is NULL
     *
     * @param get
     */
    public static String getEmptyIfNull(Object obj) {
        if (obj != null) {
            return obj.toString();
        }
        return "";
    }

    /**
     * This method is for getting Empty string if the parameter Object is NULL
     *
     * @param get
     */
    public static String getEmptyIfNull(Object obj, String retrnValue) {
        if (obj != null) {
            return obj.toString();
        }
        return retrnValue;
    }

    /**
     *
     * @param userId
     * @return
     */
    public String getUserCurrency(String userId) {
        List<String> currencyInfo = partnerAccessDAO.getUserCurrencyInfo(userId);
        if (!currencyInfo.isEmpty()) {
            return currencyInfo.get(0);
        }
        return ServerConstants.CURRENCY;
    }

    /**
     *
     * @param userName
     * @return
     */
    public String getUserNameCurrency(String userName) {
        String userCurrency = hotelPerformanceDAO.getCurrency(userName);
        return userCurrency;
    }

    /**
     * Get Wallet Currency agency id wise
     *
     * @param agencyId
     * @return
     */
    public String getWalletCurrency(String agencyId) {
        String userCurrency = hotelPerformanceDAO.getWalletCurrency(agencyId);
        return userCurrency;
    }

    /**
     *
     * @param type
     * @return
     */
    public static String getTransactionType(String type) {
        String message = "";
        switch (type) {
            case ServerConstants.BOOKING:
                message = ServerConstants.PAYMENT_MADE;
                break;
            case ServerConstants.REFUND_CANCELLATION:
                message = ServerConstants.REFUND_RECEIVED;
                break;
        }
        return message;
    }

    /**
     *
     * @param dateTime
     * @return
     */
    public static String getDateFromDate_Time(String dateTime) {
        String dateInstance = "";
        int spacePos = dateTime.indexOf(" ");
//        LOGGER.info("Space Position :: "+spacePos);
        dateInstance = dateTime.substring(0, spacePos);
        return dateInstance;
    }

    /**
     * This method is use to get in String format from the list value
     *
     * @param stringList
     * @return
     */
    public static String getListOfStringToJsonString(List<String> stringList) {
        String jsonValue = "";
        if (stringList != null && !stringList.isEmpty()) {
            for (String str : stringList) {
                jsonValue = jsonValue + "\"" + str + "\",";
            }
            if (jsonValue.length() > 0) {
                jsonValue = jsonValue.substring(0, jsonValue.length() - 1);
            }
        }
        return jsonValue;
    }

    public static String getItineraryStatus(String itineraryStatus) {
        String message = "";
        switch (itineraryStatus) {
            case ServerConstants.ITINERARY_CREATED:
                message = ServerConstants.ITINERARY_CREATED;
                break;
            case ServerConstants.BOOKING_INITIATED:
                message = ServerConstants.ITINERARY_CREATED;
                break;

            case ServerConstants.BOOKING_CONFIRMED:
                message = ServerConstants.CONFIRMED;
                break;
            case ServerConstants.BOOKING_FAILED:
                message = ServerConstants.BOOKING_FAILED;
                break;
            case ServerConstants.BOOKING_REJECTED:
                message = ServerConstants.BOOKING_FAILED;
                break;
            case ServerConstants.CANCELLATION_INITIATED:
                message = ServerConstants.CONFIRMED;
                break;
            case ServerConstants.CANCELLATION_CONFIRMED:
                message = ServerConstants.CANCELLED;
                break;
            case ServerConstants.CANCELLATION_FAILED:
                message = ServerConstants.CONFIRMED;
                break;
            case ServerConstants.CONFIRMED_AND_PAID:
                message = ServerConstants.CONFIRMED;
                break;
            default:
                message = ServerConstants.CANCELLED;
                break;

        }
        return message;
    }

    public boolean insertTransactionChargeDetails(String serviceId, String agencyId, String userName, String itineraryStatus, String moduleName, boolean isModified, String prevServiceId) throws BadRequestException, NoContentException {
        boolean isTransactionSuccess = false;
        String isCurrencyServiceDown = ServerConstants.FALSE;
        Map<String, String> chargeTypeMap = new HashMap<>();
        String agencyCurrency = "";
        List<String> queryList = new ArrayList<>();
        String tripItineraryId = "";
        List<Map<String, Object>> serviceDetailsList = new ArrayList<>();

        List<Map<String, Object>> chargeTypeList = walletServiceDAO.getChargeTypeList();
        Map<String, Object> agencyDetails = walletServiceDAO.getAgencyDetails(agencyId);

        /**
         * Creating a Map for charge Name with charge ID.
         */
        for (Map<String, Object> chargeType : chargeTypeList) {
            String chargeID = chargeType.get("TA_Charge_Type_ID").toString();
            String chargeName = chargeType.get("Charge_Type_Name").toString();
            chargeTypeMap.put(chargeName, chargeID);
        }

        /**
         * Agency Details.
         */
        if (!agencyDetails.isEmpty()) {
            agencyCurrency = agencyDetails.get("Currency").toString();
        } else {
            throw new BadRequestException("Invalid Agency ID");
        }

        /**
         * Transaction data based on Module.
         */
        if (ServerConstants.HOTEL_MODULE.equalsIgnoreCase(moduleName)) {
            serviceDetailsList = walletServiceDAO.getHotelServiceDetails(serviceId);
        } else if (ServerConstants.CAR_MODULE.equalsIgnoreCase(moduleName)) {
            serviceDetailsList = walletServiceDAO.getCarServiceDetails(serviceId);
        } else if (moduleName.equalsIgnoreCase(ServerConstants.ATTRACTION_MODULE)) {
            serviceDetailsList = walletServiceDAO.getAttractionServiceDetails(serviceId);
        } else if (moduleName.equalsIgnoreCase(ServerConstants.FLIGHT_MODULE)) {
            serviceDetailsList = walletServiceDAO.getFlightServiceDetails(serviceId);
        } else if (moduleName.equalsIgnoreCase(ServerConstants.CAR_RENTAL_MODULE)) {
            serviceDetailsList = walletServiceDAO.getCarRentalServiceDetails(serviceId);
        }
        Map<String, String> transactionQueryMap = generateTransactionQury(serviceDetailsList, queryList, chargeTypeMap, itineraryStatus, agencyId, agencyCurrency, moduleName, serviceId, userName, isModified, prevServiceId);
        String nonTxnRequired = CommonUtil.getEmptyIfNull(transactionQueryMap.get(ServerConstants.NO_TXN_REQUIRED), ServerConstants.FALSE);
        if (nonTxnRequired.equals(ServerConstants.FALSE) && !queryList.isEmpty()) {
            try {
                isCurrencyServiceDown = CommonUtil.getEmptyIfNull(transactionQueryMap.get(ServerConstants.CURRENCY_SERVER_DOWN), ServerConstants.FALSE);

                if (isCurrencyServiceDown.equalsIgnoreCase(ServerConstants.FALSE)) {
                    int[] rowsEffected = walletServiceDAO.insertDataInBatch(queryList);
                    if (rowsEffected != null && rowsEffected.length > 0) {
                        LOGGER.info(" Transaction Completed successfully ! ");
                        isTransactionSuccess = true;
                    }
                } else {
                    LOGGER.info(" Transaction Faild due to currency service down, please check in the Transaction Issue Table ! ");
                    String errorMessage = "Currency Service Down";
                    tripItineraryId = CommonUtil.getEmptyIfNull(transactionQueryMap.get(ServerConstants.TRIP_ITINERARY_ID));
                    String errorQuery = insertTransactionsIssues(agencyId, tripItineraryId, moduleName, serviceId, errorMessage, userName);
                    int rowsEffectedError = walletServiceDAO.insertChargeDetails(errorQuery);
                }
            } catch (Exception ex) {
                LOGGER.info(" Transaction Faild, please check in the Transaction Issue Table ! ");
                String errorMessage = "Error on executing INSERT_TRAVEL_AGENCY_CHARGE query";
                tripItineraryId = CommonUtil.getEmptyIfNull(transactionQueryMap.get(ServerConstants.TRIP_ITINERARY_ID));
                String errorQuery = insertTransactionsIssues(agencyId, tripItineraryId, moduleName, serviceId, errorMessage, userName);
                int rowsEffectedError = walletServiceDAO.insertChargeDetails(errorQuery);
            }
        }

        return isTransactionSuccess;
    }

    public Map<String, String> insertTransactionChargeDetailsForBooking(String serviceId, String agencyId, String userName, String itineraryStatus, String moduleName, boolean isModified, String prevServiceId, List<String> queryList) throws BadRequestException, NoContentException {

        Map<String, String> chargeTypeMap = new HashMap<>();
        String agencyCurrency = "";

        List<Map<String, Object>> serviceDetailsList = new ArrayList<>();

        List<Map<String, Object>> chargeTypeList = walletServiceDAO.getChargeTypeList();
        Map<String, Object> agencyDetails = walletServiceDAO.getAgencyDetails(agencyId);

        /**
         * Creating a Map for charge Name with charge ID.
         */
        for (Map<String, Object> chargeType : chargeTypeList) {
            String chargeID = chargeType.get("TA_Charge_Type_ID").toString();
            String chargeName = chargeType.get("Charge_Type_Name").toString();
            chargeTypeMap.put(chargeName, chargeID);
        }

        /**
         * Agency Details.
         */
        if (!agencyDetails.isEmpty()) {
            agencyCurrency = agencyDetails.get("Currency").toString();
        } else {
            throw new BadRequestException("Invalid Agency ID");
        }

        /**
         * Transaction data based on Module.
         */
        if (ServerConstants.HOTEL_MODULE.equalsIgnoreCase(moduleName)) {
            serviceDetailsList = walletServiceDAO.getHotelServiceDetails(serviceId);
        } else if (ServerConstants.CAR_MODULE.equalsIgnoreCase(moduleName)) {
            serviceDetailsList = walletServiceDAO.getCarServiceDetails(serviceId);
        } else if (moduleName.equalsIgnoreCase(ServerConstants.ATTRACTION_MODULE)) {
            serviceDetailsList = walletServiceDAO.getAttractionServiceDetails(serviceId);
        } else if (moduleName.equalsIgnoreCase(ServerConstants.FLIGHT_MODULE)) {
            serviceDetailsList = walletServiceDAO.getFlightServiceDetails(serviceId);
        } else if (moduleName.equalsIgnoreCase(ServerConstants.CAR_RENTAL_MODULE)) {
            serviceDetailsList = walletServiceDAO.getCarRentalServiceDetails(serviceId);
        }
        Map<String, String> transactionQueryMap = generateTransactionQury(serviceDetailsList, queryList, chargeTypeMap, itineraryStatus, agencyId, agencyCurrency, moduleName, serviceId, userName, isModified, prevServiceId);

        return transactionQueryMap;
    }

    
    boolean insertRefundChargeAmount(String tripItineraryId, String serviceId){
        boolean isRefundedSuccessfully = false;
        
//        List<Map<String, O>>
        
        
        
        return isRefundedSuccessfully;
    }
    
    private String insertQueryforChargeDetails(String agencyId, String chargeId, String chargeValue, String chargeCurrency, String walletValue, String walletCurrency, String agencyWalletValue, String agencyCurrency, String walletCurrencyFactor, String agencyCurrencyFactor, String confirmationNo, String sourceName, String tripItineraryId, String userName, String moduleName, String serviceId, String netRateFromPartnerInpartnerCur, String netRateFromPartnerInWalletCur, String txnValueInPartnerCur, String txnValueInWalletCur, String transactionStatus) {
        StringBuilder query = new StringBuilder(QueryUtil.INSERT_TRAVEL_AGENCY_CHARGE);
        query.append("'" + agencyId + "',");
        query.append("'" + chargeId + "',");
        query.append("'" + chargeValue + "',");
        query.append("'" + chargeCurrency + "',");
        query.append("'" + walletValue + "',");
        query.append("'" + walletCurrency + "',");
        query.append("'" + agencyWalletValue + "',");
        query.append("'" + agencyCurrency + "',");
        query.append("'" + walletCurrencyFactor + "',");
        query.append("'" + agencyCurrencyFactor + "',");
        query.append("GETDATE(),");
        query.append("'" + tripItineraryId + "',");
        if (chargeValue.equalsIgnoreCase("0")) {
            query.append("'0',");
        } else {
            query.append("'1',");
        }
        if (!sourceName.isEmpty()) {
            query.append("'" + sourceName + "',");
        } else {
            query.append("NULL,");
        }
        if (!confirmationNo.isEmpty()) {
            query.append("'" + confirmationNo + "',");
        } else {
            query.append("NULL,");
        }
        query.append("GETDATE(),");
        query.append("'" + userName + "',");
        if (!moduleName.isEmpty()) {
            query.append("'" + moduleName + "',");
        } else {
            query.append("NULL,");
        }
        if (!serviceId.isEmpty()) {
            query.append("'" + serviceId + "',");
        } else {
            query.append("NULL,");
        }
        query.append("'" + netRateFromPartnerInpartnerCur + "',");
        query.append("'" + netRateFromPartnerInWalletCur + "',");
        query.append("'" + txnValueInPartnerCur + "',");
        query.append("'" + txnValueInWalletCur + "',");
        query.append("'" + transactionStatus + "')");
        LOGGER.info(" Query for INSERT_TRAVEL_AGENCY_CHARGE :: " + query.toString());

        return query.toString();
    }

    private String insertTransactionsIssues(String agencyId, String tripItineraryId, String moduleName, String serviceId, String errorMessage, String userName) {
        StringBuilder query = new StringBuilder(QueryUtil.INSERT_TRAVEL_AGENCY_TXN_ISSUES);
        query.append("'" + agencyId + "',");
        query.append("'" + tripItineraryId + "',");
        query.append("'" + moduleName + "',");
        query.append("'" + serviceId + "',");
        query.append("'1',");
        query.append("'0',");
        query.append("'0',");
        if (errorMessage == null || errorMessage.isEmpty()) {
            query.append("NULL,");
        } else {
            query.append("'" + errorMessage + "',");
        }
        query.append("GETDATE(),");
        query.append("'" + userName + "')");

        LOGGER.info(" Query for INSERT_TRAVEL_AGENCY_TXN_ISSUES :: " + query.toString());

        return query.toString();
    }

    /**
     *
     * @param itineraryStatus
     * @return
     */
    public static String getServiceStatus(String itineraryStatus) {
        String message = "";
        switch (itineraryStatus) {
            case ServerConstants.ITINERARY_CREATED:
                message = ServerConstants.ON_HOLD;
                break;
            case ServerConstants.PRE_BOOKED:
                message = ServerConstants.ON_HOLD;
                break;
            case ServerConstants.BOOKING_INITIATED:
                message = ServerConstants.STATUS_INPROGRESS;
                break;
            case ServerConstants.BOOKING_REQUESTED:
                message = ServerConstants.PENDING;
                break;
            case ServerConstants.BOOKING_CONFIRMED:
                message = ServerConstants.CONFIRMED;
                break;
            case ServerConstants.BOOKING_FAILED:
                message = ServerConstants.FAILED;
                break;
            case ServerConstants.BOOKING_REJECTED:
                message = ServerConstants.FAILED;
                break;
            case ServerConstants.CANCELLATION_INITIATED:
                message = ServerConstants.CONFIRMED;
                break;
            case ServerConstants.CANCELLATION_CONFIRMED:
                message = ServerConstants.CANCELLED;
                break;
            case ServerConstants.CONFIRMED_AND_PAID:
                message = ServerConstants.CONFIRMED;
                break;
            case ServerConstants.CANCELLATION_FAILED:
                message = ServerConstants.CONFIRMED;
                break;
            default:
                message = ServerConstants.ON_HOLD;
                break;
        }
        return message;
    }
    
    /**
     * 
     * @param statusList
     * @return 
     */
    public static String getResStatus(Set<String> statusList) {
        String status = "";
        for (String statusPrev : statusList) {
            if (statusPrev.equalsIgnoreCase(ServerConstants.CONFIRMED)) {
                if (status.equalsIgnoreCase(ServerConstants.FAILED) || status.equalsIgnoreCase(ServerConstants.PENDING) || status.equalsIgnoreCase(ServerConstants.STATUS_INPROGRESS)) {
                    status = ServerConstants.PARTIAL_CONFIRMED;
                    break;
                } else if (status.equalsIgnoreCase(ServerConstants.CANCELLED)) {
                    status = ServerConstants.PARTIAL_CANCELLED;
                    break;
                } else {
                    status = ServerConstants.CONFIRMED;
                }

            } else {
                
                if (status.equalsIgnoreCase(ServerConstants.CONFIRMED)) {
                    status = ServerConstants.PARTIAL_CONFIRMED;
                } else if (status.equalsIgnoreCase(ServerConstants.PARTIAL_CONFIRMED)){
                    status = ServerConstants.PARTIAL_CONFIRMED;
                }else if (status.equalsIgnoreCase(ServerConstants.PARTIAL_CANCELLED)){
                    status = ServerConstants.PARTIAL_CANCELLED;
                }else if (status.equalsIgnoreCase(ServerConstants.PENDING)){
                    status = ServerConstants.PARTIAL_CONFIRMED;                	
                } else if (statusPrev.equalsIgnoreCase(ServerConstants.PENDING)){
                    status = ServerConstants.PARTIAL_CONFIRMED;
                }else if (statusPrev.equalsIgnoreCase(ServerConstants.FAILED)) {
                    status = ServerConstants.FAILED;
                }else if (statusPrev.equalsIgnoreCase(ServerConstants.CANCELLED)){
                    status = ServerConstants.CANCELLED;
                }else if (statusPrev.equalsIgnoreCase(ServerConstants.PRE_BOOKED)){
                    status = ServerConstants.STATUS_INPROGRESS;
                }else {
                    status = ServerConstants.ON_HOLD;
                }
            }
        }
        return status;
    } 
      

    /**
     *
     * @param partnerName
     * @param moduleName
     * @return commission factor
     */
    @Deprecated
    public double getAgentX101PartnerCommisionFactor(String partnerName, String moduleName) {
        double commissionfactor = 0.00;
        partnerName = partnerName.toLowerCase();
        moduleName = moduleName.toLowerCase();
        switch (moduleName) {
            case "hotel": {
                switch (partnerName) {
                    case "hotelbeds":
                        double commission = Double.parseDouble(hotelbedsCommission);
//                            commissionfactor = (double) (100+commission)/100;
//                            commissionfactor = 1.03;
                        commissionfactor = (double) commission / 100;
                        break;
                    case "lotsofhotels":
                        double commission1 = Double.parseDouble(lotsofhotelsCommission);
//                            commissionfactor = (double) (100+commission1)/100;
//                            commissionfactor = 1.03;
                        commissionfactor = (double) commission1 / 100;
                        break;
                    case "mikitravel":
                        double commission2 = Double.parseDouble(mikitravelCommission);
//                            commissionfactor = (double) (100+commission2)/100;
//                            commissionfactor = 1.03;
                        commissionfactor = (double) commission2 / 100;
                        break;
//                        case "gta" :
//                            commissionfactor = 1.03;
//                            break;           
                    }
                break;
            }
            case "car transfer": {
                switch (partnerName) {
                    case "hotelbeds":
                        double commission = Double.parseDouble(hotelbedsCarCommission);
//                                commissionfactor = (double) (100+commission)/100;
//                                commissionfactor = 1.02;
                        commissionfactor = (double) commission / 100;
                        break;
                    case "cityride":
                        double commission1 = Double.parseDouble(cityrideCommission);
//                                commissionfactor = (double) (100+commission1)/100;
//                                commissionfactor = 1.02;
                        commissionfactor = (double) commission1 / 100;
                        break;
                }
                break;
            }

        }
        return commissionfactor;
    }

    /**
     *
     * @param agencyId
     * @param partnerId
     * @param moduleName
     * @return commission factor
     */
    public double getAgentX101PartnerCommisionFactor(String agencyId, String partnerId, String moduleName) {
        double commissionfactor = 0.00;
        String commission = commissionDAO.getCommisionInfo(agencyId, partnerId, moduleName);
        try {
            double commissionValue = Double.parseDouble(commission);
            if (commissionValue != 0) {
                commissionfactor = (double) commissionValue / 100;
            }
        } catch (NumberFormatException e) {
            LOGGER.error("Exception in calculating COmmission value :: " + e.getMessage() + " :: commission :: " + commission);
        }
        return commissionfactor;
    }

    /**
     *
     * @param agencyId
     * @param partnerId
     * @param moduleName
     * @return commission factor
     */
    public List<Map<String, Object>> getMarkupInfoDetails(String agencyId, String partnerId, String moduleName) {
        return commissionDAO.getMarkupInfoDetails(agencyId, partnerId, moduleName);
    }

    /**
     * This method is use to getTwodigiteNunmer
     *
     * @param value
     * @return
     */
    public static double getTwodigiteNunmer(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /**
     * This method is use to remove un used character from rate comment
     *
     * @param message
     * @return
     */
    public static String removeUnUseCharacter(String message) {
        if (message != null) {
            message = message.replaceAll("\n", "");
        }
        return message;
    }

    /**
     * This method is use to get currency factor
     *
     * @param fromCurrency
     * @param toCurrency
     * @return
     */
    public static double getCurrencyFactorInfo(String fromCurrency, String toCurrency) {
        double factor = 1;
        if (fromCurrency != null && !fromCurrency.isEmpty() && !fromCurrency.equalsIgnoreCase(toCurrency)) {
            factor = getCurrencyConversionFactor(fromCurrency, toCurrency);
        }
        return factor;
    }

    /**
     *
     * @param str
     * @return
     */
    public static String removeQuoteCode(String str) {
        String replacedStr = str;
        if (str != null && str.contains("'")) {
            replacedStr = str.replaceAll("'", "");
        }
        return replacedStr;
    }

    /**
     * This method is used to get the Lat-Lng of any Address by using Google
     * API.
     *
     * @param pickLocation
     * @return
     */
    public String getLatLngFromPlaceByGoogleAPI(String searchkeyword) throws NoContentException, JSONException, IOException {
        StringBuilder finalSearchUrl = new StringBuilder(googleSearchApiUrl);
        String latLngNameAddress = "";
        searchkeyword = searchkeyword.replaceAll(" ", "%20"); // Space should be replaced by '%20', as per Google API Doc.

        finalSearchUrl.append("?input=" + searchkeyword);
        finalSearchUrl.append("&inputtype=" + ServerConstants.TEXT_QUERY);
        finalSearchUrl.append("&fields=formatted_address,name,geometry");
        finalSearchUrl.append("&key=" + googleSearchApiKey);

        String responseJson = ServiceCaller.callServiceGET(finalSearchUrl.toString());

        if (responseJson != null && !responseJson.isEmpty()) {
            Map<String, String> countryMap = new HashMap<>();
            countryMap.put("UAE", "AE");
            countryMap.put("UK", "GB");
            countryMap.put("USA", "US");
            GooglePlaceLatLngBean googlePlaceLatLngBean = new GsonBuilder().create().fromJson(responseJson, GooglePlaceLatLngBean.class);
            String status = googlePlaceLatLngBean.getStatus();
            if (status.trim().equalsIgnoreCase(ServerConstants.OK.trim())) {
                String name = googlePlaceLatLngBean.getCandidates().get(0).getName();
                String lat = googlePlaceLatLngBean.getCandidates().get(0).getGeometry().getLocation().getLat();
                String lng = googlePlaceLatLngBean.getCandidates().get(0).getGeometry().getLocation().getLng();
                String address = googlePlaceLatLngBean.getCandidates().get(0).getFormattedAddress();
                String countryName = "";
                String countryCode = "";
                for (Map.Entry<String, String> mapData : countryMap.entrySet()) {
                    if (address.contains(mapData.getKey())) {
                        countryName = mapData.getKey();
                        countryCode = mapData.getValue();
                        break;
                    }
                }
                if (countryName.isEmpty() && countryCode.isEmpty()) {
                    for (String countryArray : CacheUtil.getCountryInfo()) {
                        String countryNameTemp = countryArray.split("#@#")[0];
                        if (address.contains(countryNameTemp)) {
                            countryName = countryNameTemp;
                            countryCode = countryArray.split("#@#")[1];
                            break;
                        }
                    }
                }
                latLngNameAddress = lat + "#@#" + lng + "#@#" + name + "#@#" + address + "#@#" + countryName + "#@#" + countryCode;
                LOGGER.info("Lat-Lng-name-address" + latLngNameAddress);
            } else {
                return ServerConstants.LOCATION_NOT_FOUND;
            }

        } else {
            return ServerConstants.LOCATION_NOT_FOUND;
        }

        return latLngNameAddress;
    }

    public static String getIP(HttpServletRequest servletRequest) {

        String ipAddress = servletRequest.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = servletRequest.getRemoteAddr();
        }
        LOGGER.info("Clent ipAddress :: " + ipAddress);

        return ipAddress;
        // return ((HttpServletRequest)
        // facesContext.getExternalContext().getRequest()).getRemoteAddr();
    }

    /**
     * This method will convert input date string of any given format to another
     * format specified in its parameters.
     *
     * @param strDate - Date string in <origFormat> format
     * @param origFormat - The format in which the original string is in.
     * @param destFormat - The destination format to which the original string
     * must be converted to.
     * @return formattedDate - Date string in <destFormat> format
     */
    public static String getFormattedDate(String strDate, String origFormat, String destFormat) {
        String formattedDate = null;
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat(origFormat, Locale.US);
            Date inputDate = sdf1.parse(strDate);
            SimpleDateFormat sdf2 = new SimpleDateFormat(destFormat, Locale.US);
            formattedDate = sdf2.format(inputDate);
        } catch (java.text.ParseException ex) {
            LOGGER.error("Exception :: ", ex);
        }
        return formattedDate;
    }

    /**
     * This method is use to get client time to GMT server format
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
            LOGGER.info("parse error in getDateFrom GMT ");
            e.printStackTrace();
        }
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        outputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String strDay = outputFormat.format(date);
        return strDay;
    }

    /**
     * This method is use to validate email
     *
     * @param email
     * @return
     */
    public static boolean isValidEmailAddress(String email) {
        Pattern pattern = Pattern.compile(ServerConstants.EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private Map<String, String> generateTransactionQury(List<Map<String, Object>> serviceDetailsList, List<String> queryList, Map<String, String> chargeTypeMap, String itineraryStatus, String agencyId, String agencyCurrency, String moduleName, String serviceId, String userName, boolean isModified, String prevServiceId) throws NoContentException {
        Map<String, String> transactionQueryMap = new HashMap<>();
        String tripItineraryId = "";
        if (isModified == true) {
            if (moduleName.equalsIgnoreCase(ServerConstants.HOTEL_MODULE)) {
                double walletValue = 0.00, agencyWalletValue = 0.00, walletCurrencyFactor = 0.00, agencyCurrencyFactor = 0.00,
                        netRateFromPartnerInWalletCur = 0.00, txnValueInWalletCur = 0.00;
                String chargeId = "";
                boolean isCurrencyServiceDown = false;
                String prevChargeValue = "", prevChargeCurrency = "", prevNetRateFromPartnerInpartnerCur = "",
                        prevTxnValueInPartnerCur = "", prevItineraryStatus = "", chargeType = "";
                List<Map<String, Object>> prevServiceDetailsList = walletServiceDAO.getHotelServiceDetails(serviceId);
                if (prevServiceDetailsList.isEmpty()) {
                    Map<String, Object> prevServiceDetailsMap = prevServiceDetailsList.get(0);
                    prevChargeValue = CommonUtil.getEmptyIfNull(prevServiceDetailsMap.get("Cost_To_Agent_Partner_Currency"), "0.00");
                    prevChargeCurrency = CommonUtil.getEmptyIfNull(prevServiceDetailsMap.get("Partner_Currency"));
                    prevTxnValueInPartnerCur = CommonUtil.getEmptyIfNull(prevServiceDetailsMap.get("Transaction_Fee_Partner_Currency"), "0.00");
                    prevNetRateFromPartnerInpartnerCur = CommonUtil.getEmptyIfNull(prevServiceDetailsMap.get("Total_Net_Rate_Partner_Currency"), "0.00");
                    prevItineraryStatus = CommonUtil.getEmptyIfNull(prevServiceDetailsMap.get("Itinerary_Status"));
                } else {
                    throw new NoContentException("No previous Booking details found");
                }

                Map<String, Object> serviceDetailsMap = serviceDetailsList.get(0);
                tripItineraryId = serviceDetailsMap.get("Trip_Itinerary_Id").toString();
                String chargeValue = CommonUtil.getEmptyIfNull(serviceDetailsMap.get("Cost_To_Agent_Partner_Currency"), "0.00");
                String txnValueInPartnerCur = CommonUtil.getEmptyIfNull(serviceDetailsMap.get("Transaction_Fee_Partner_Currency"), "0.00");
                String chargeCurrency = CommonUtil.getEmptyIfNull(serviceDetailsMap.get("Partner_Currency"));
                String sourceName = CommonUtil.getEmptyIfNull(serviceDetailsMap.get("SOURCE_NAME"));
                String confirmationNo = CommonUtil.getEmptyIfNull(serviceDetailsMap.get("Booking_Confirmation_No"));
                String netRateFromPartnerInpartnerCur = CommonUtil.getEmptyIfNull(serviceDetailsMap.get("Total_Net_Rate_Partner_Currency"), "0.00");

                if (prevItineraryStatus.equalsIgnoreCase(ServerConstants.BOOKING_CONFIRMED) || prevItineraryStatus.equalsIgnoreCase(ServerConstants.CANCELLATION_INITIATED) || prevItineraryStatus.equalsIgnoreCase(ServerConstants.CANCELLATION_FAILED)) {
                    if (Double.parseDouble(prevChargeValue) > Double.parseDouble(chargeCurrency)) {
                        chargeCurrency = "" + (Double.parseDouble(prevChargeValue) - Double.parseDouble(chargeCurrency));
                        netRateFromPartnerInpartnerCur = "" + (Double.parseDouble(prevNetRateFromPartnerInpartnerCur) - Double.parseDouble(netRateFromPartnerInpartnerCur));
                        txnValueInPartnerCur = prevTxnValueInPartnerCur;
                        chargeType = ServerConstants.REFUND_MODIFY;
                    } else if (Double.parseDouble(prevChargeValue) < Double.parseDouble(chargeCurrency)) {
                        chargeCurrency = "" + (Double.parseDouble(chargeCurrency) - Double.parseDouble(prevChargeCurrency));
                        chargeType = ServerConstants.BOOKING_MODIFY;
                    } else {
                        LOGGER.info("No rate Change, Hence no transaction requred !!");
                        transactionQueryMap.put(ServerConstants.NO_TXN_REQUIRED, ServerConstants.TRUE);
                        return transactionQueryMap;
                    }
                }
                chargeId = chargeTypeMap.get(chargeType);
                if (chargeId == null) {
                    chargeId = "0";
                }

                if (!walletCurrency.equalsIgnoreCase(chargeCurrency.trim())) {
                    walletCurrencyFactor = getCurrencyConversionFactor(chargeCurrency.trim(), walletCurrency);
                    if (walletCurrencyFactor == -1) {
                        isCurrencyServiceDown = true;
                    }
                    walletValue = Double.parseDouble(chargeValue) * walletCurrencyFactor;
                    netRateFromPartnerInWalletCur = Double.parseDouble(netRateFromPartnerInpartnerCur) * walletCurrencyFactor;
                    txnValueInWalletCur = Double.parseDouble(txnValueInPartnerCur) * walletCurrencyFactor;
                } else {
                    walletValue = Double.parseDouble(chargeValue);
                    walletCurrencyFactor = 1.00;
                }

                if (!chargeCurrency.equalsIgnoreCase(agencyCurrency.trim())) {
                    agencyCurrencyFactor = getCurrencyConversionFactor(chargeCurrency, agencyCurrency.trim());
                    if (agencyCurrencyFactor == -1) {
                        isCurrencyServiceDown = true;
                    }
                    agencyWalletValue = Double.parseDouble(chargeValue) * agencyCurrencyFactor;
                } else {
                    agencyWalletValue = Double.parseDouble(chargeValue);
                    agencyCurrencyFactor = 1.00;
                }
                if (!isCurrencyServiceDown) {
//                   String query = insertQueryforChargeDetails(agencyId, chargeId, chargeValue, chargeCurrency, ""+walletValue, walletCurrency, ""+agencyWalletValue, agencyCurrency, ""+walletCurrencyFactor, ""+agencyCurrencyFactor, confirmationNo, sourceName, tripItineraryId, userName, moduleName, serviceId, netRateFromPartnerInpartnerCur, ""+netRateFromPartnerInWalletCur, txnValueInPartnerCur, ""+txnValueInWalletCur, transactionStatus);
//                   queryList.add(query);
                } else {
                    transactionQueryMap.put(ServerConstants.CURRENCY_SERVER_DOWN, ServerConstants.TRUE);
                }

            }

        } else {
            int i = 0;
            for (Map<String, Object> serviceDetails : serviceDetailsList) {
                String transactionStatus = ServerConstants.PENDING;
                double walletValue = 0.00, agencyWalletValue = 0.00, walletCurrencyFactor = 0.00, agencyCurrencyFactor = 0.00,
                        netRateFromPartnerInWalletCur = 0.00, txnValueInWalletCur = 0.00;
                boolean isCurrencyServiceDown = false;
                boolean isCancellationRefund = false;
                double chargeWalletBookingCurrencyfact = 0.00, chargeAgencyBookingCurrencyfact = 0.00;
                tripItineraryId = serviceDetails.get("Trip_Itinerary_Id").toString();
                String chargeValue = CommonUtil.getEmptyIfNull(serviceDetails.get("Cost_To_Agent_Partner_Currency"), "0.00");
                String txnValueInPartnerCur = CommonUtil.getEmptyIfNull(serviceDetails.get("Transaction_Fee_Partner_Currency"), "0.00");
                String chargeCurrency = CommonUtil.getEmptyIfNull(serviceDetails.get("Partner_Currency"));
                String cancelAmount = CommonUtil.getEmptyIfNull(serviceDetails.get("Cancel_Amount_Partner_Currency"), "0.00");
                String cancelTXNAmountInPartnerCur = CommonUtil.getEmptyIfNull(serviceDetails.get("Cancel_Txn_Fee_Partner_Currency"), "0.00");
                String sourceName = CommonUtil.getEmptyIfNull(serviceDetails.get("SOURCE_NAME"));
                String confirmationNo = CommonUtil.getEmptyIfNull(serviceDetails.get("Booking_Confirmation_No"));
                String netRateFromPartnerInpartnerCur = CommonUtil.getEmptyIfNull(serviceDetails.get("Total_Net_Rate_Partner_Currency"), "0.00");

                transactionQueryMap.put(ServerConstants.TRIP_ITINERARY_ID, tripItineraryId);

                /**
                 * Getting the Charge ID.
                 */
                String chargeId = chargeTypeMap.get(itineraryStatus);
                if (chargeId == null) {
                    chargeId = "0";
                }

                /**
                 * For Cancellation refund, Calculating The Cancel Amount.
                 */
                if (itineraryStatus.equalsIgnoreCase(ServerConstants.REFUND_CANCELLATION)) {
                    int cancelAmountInt = (int) Double.parseDouble(cancelAmount);
                    int netRateFromPartnerInt = (int) Double.parseDouble(netRateFromPartnerInpartnerCur);
                    if (cancelAmountInt == netRateFromPartnerInt) {
                        /**
                         * If the cancellation is non refundable, nothing will
                         * be refunded.
                         */

                        if (moduleName.equalsIgnoreCase(ServerConstants.CAR_MODULE)) {
                            i++;
                            continue;
                        } else {
                            LOGGER.info("This is a case of NON refundable !!");
                            transactionQueryMap.put(ServerConstants.NO_TXN_REQUIRED, ServerConstants.TRUE);
                            return transactionQueryMap;
                        }
                    }
                    double refundValue = Double.parseDouble(chargeValue) - Double.parseDouble(cancelAmount);
                    if (refundValue == 0 || refundValue == 0.00) {
                        LOGGER.info("This is a case of NON refundable !!");
                        transactionQueryMap.put(ServerConstants.NO_TXN_REQUIRED, ServerConstants.TRUE);
                        return transactionQueryMap;
                    }
                    chargeValue = "" + refundValue;
                    txnValueInPartnerCur = cancelTXNAmountInPartnerCur;
                    if (!walletCurrency.equalsIgnoreCase(chargeCurrency.trim()) || !chargeCurrency.equalsIgnoreCase(agencyCurrency.trim())) {
                        List<Map<String, Object>> bookingChargeDetailList = walletServiceDAO.getBookingChargeDetailList(agencyId, tripItineraryId, serviceId, moduleName);
                        for (Map<String, Object> bookingChargeDetail : bookingChargeDetailList) {
                            chargeWalletBookingCurrencyfact = Double.parseDouble(bookingChargeDetail.get("Wallet_Currency_Conv_Factor").toString());
                            chargeAgencyBookingCurrencyfact = Double.parseDouble(bookingChargeDetail.get("Agency_Currency_Conv_Factor").toString());
                        }
                        isCancellationRefund = true;
                    }
                    transactionStatus = ServerConstants.SUCCESS;
                }

                if (!walletCurrency.equalsIgnoreCase(chargeCurrency.trim())) {
                    if (!isCancellationRefund) {
                        walletCurrencyFactor = getCurrencyConversionFactor(chargeCurrency.trim(), walletCurrency);
                    } else {
                        walletCurrencyFactor = chargeWalletBookingCurrencyfact;
                    }

                    if (walletCurrencyFactor == -1) {
                        isCurrencyServiceDown = true;
                    }

                    walletValue = Double.parseDouble(chargeValue) * walletCurrencyFactor;
                    netRateFromPartnerInWalletCur = Double.parseDouble(netRateFromPartnerInpartnerCur) * walletCurrencyFactor;
                    txnValueInWalletCur = Double.parseDouble(txnValueInPartnerCur) * walletCurrencyFactor;
                } else {
                    walletValue = Double.parseDouble(chargeValue);
                    walletCurrencyFactor = 1.00;
                }

                if (!chargeCurrency.equalsIgnoreCase(agencyCurrency.trim())) {
                    if (!isCancellationRefund) {
                        agencyCurrencyFactor = getCurrencyConversionFactor(chargeCurrency, agencyCurrency.trim());
                    } else {
                        agencyCurrencyFactor = chargeAgencyBookingCurrencyfact;
                    }

                    if (agencyCurrencyFactor == -1) {
                        isCurrencyServiceDown = true;
                    }

                    agencyWalletValue = Double.parseDouble(chargeValue) * agencyCurrencyFactor;
                } else {
                    agencyWalletValue = Double.parseDouble(chargeValue);
                    agencyCurrencyFactor = 1.00;
                }

                if (!isCurrencyServiceDown) {
                    String query = insertQueryforChargeDetails(agencyId, chargeId, chargeValue, chargeCurrency, "" + walletValue, walletCurrency, "" + agencyWalletValue, agencyCurrency, "" + walletCurrencyFactor, "" + agencyCurrencyFactor, confirmationNo, sourceName, tripItineraryId, userName, moduleName, serviceId, netRateFromPartnerInpartnerCur, "" + netRateFromPartnerInWalletCur, txnValueInPartnerCur, "" + txnValueInWalletCur, transactionStatus);
                    queryList.add(query);
                } else {
                    transactionQueryMap.put(ServerConstants.CURRENCY_SERVER_DOWN, ServerConstants.TRUE);
                }
            }
        }

        return transactionQueryMap;
    }

    public static void getListFromCommaSeperateString(List<String> ticketNumberList, String ticketNumbers) {
        String[] split = ticketNumbers.split(ServerConstants.UNIQUE_KEY_SEPARATOR);
        ticketNumberList = Arrays.asList(split);

    }

    public static boolean cancellationPlocyDuplicateChecking(CancellationPoliciesBean cancelBean, List<CancellationPolicies> cancellationPlicyList) {
        boolean isAlreadyPresent = false;
        for (CancellationPolicies cancellationPlicy : cancellationPlicyList) {

        }
        return isAlreadyPresent;
    }

    /**
     * Validates SQL injections in query string.
     *
     * @param strQueryWord
     * @return isValid
     */
    public static boolean isValidQueryJsonString(String strQueryWord) {
        boolean isValid = true;
        if (!SqlSafeUtil.isSqlInjectionSafe(strQueryWord)) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Get AgencyId by userName
     *
     * @param userName
     * @return
     */
    public String getAgencyIdByAgentName(String userName) {
        List<Map<String, Object>> userInfoList = partnerAccessDAO.getUserInfo(userName);
        String userLevel = "";
        String userId = "";
        if (userInfoList != null && !userInfoList.isEmpty()) {
            userLevel = userInfoList.get(0).get("User_Level").toString().trim();
            userId = userInfoList.get(0).get("User_Id").toString();
        }
        return loginValidationDAO.getAgencyId(userId, userLevel);
    }
    
    /**
     * Get AgencyId by userName
     *
     * @param userName
     * @return
     */
    public String getAgentIdByAgentName(String userName) {
        List<Map<String, Object>> userInfoList = partnerAccessDAO.getUserInfo(userName);
        String userId = "";
        if (userInfoList != null && !userInfoList.isEmpty()) {
            userId = userInfoList.get(0).get("User_Id").toString();
        }
        return userId;
    }

    public String getAgencyNameByAgencyId(String agencyId) {
        Map<String, Object> userInfoMap = walletServiceDAO.getAgencyDetails(agencyId);
        String name = "";
        if (!userInfoMap.isEmpty()) {
            name = userInfoMap.get("Travel_Agency_Name") != null ? userInfoMap.get("Travel_Agency_Name").toString() :"";
        }
        return name;
    }
    
    public void updateWalletTransactionStatus(String transactionStatus, String serviceId, String agencyId, String username, String moduleName) {

        walletServiceDAO.updateWalletTransactionStatus(transactionStatus, serviceId, agencyId, username, moduleName);

    }

    public String getBranchIdByAgentName(String userName) {
        List<Map<String, Object>> userInfoList = loginValidationDAO.validateUserNameAndPassword(userName, null);
        String userLevel = "";
        String branchId = "";
        String userId = "";
        if (userInfoList != null && !userInfoList.isEmpty()) {
            userLevel = userInfoList.get(0).get("User_Level").toString().trim();
            userId = userInfoList.get(0).get("User_Id").toString().trim();
            branchId = userInfoList.get(0).get("Branch_Id") != null ? userInfoList.get(0).get("Branch_Id").toString() : "";
        }
        if (branchId == null || branchId.isEmpty()) {
            if (userLevel != null && userLevel.equalsIgnoreCase(ServerConstants.BRANCH)) {
                branchId = loginValidationDAO.getBranchId(userId);
            }
        }
        return branchId;
    }

    public List<String> getPartnerIdListByLocation(String locationLat, String locationLng, String partnerId, String cityName, String searchRadius) throws NoContentException, JSONException, IOException {
        List<String> propertyIdList = new ArrayList<>();
        double locationLatDbl = 0.0, locationLngDbl = 0.0;
        int maxDistanceInKm = 0;
        try {
            locationLatDbl = Double.parseDouble(locationLat);
            locationLngDbl = Double.parseDouble(locationLng);
            maxDistanceInKm = Integer.parseInt(searchRadius) / 1000; //Request data is in Meter, Changing to KM
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return propertyIdList;
        }

        List<Map<String, Object>> extraHotelList = hotelSearchDAO.getExtraHotelDataByCityPartnerId(cityName, partnerId);
        for (Map<String, Object> extraHotel : extraHotelList) {
            double hotelLatDbl = 0.0, hotelLngDbl = 0.0;
            String hotelLat = extraHotel.get("Latitude") == null ? "" : extraHotel.get("Latitude").toString();
            String hotelLng = extraHotel.get("Longitude") == null ? "" : extraHotel.get("Longitude").toString();
            if (hotelLat.isEmpty() || hotelLng.isEmpty()) {
                continue;
            }
            try {
                hotelLatDbl = Double.parseDouble(hotelLat);
                hotelLngDbl = Double.parseDouble(hotelLng);
            } catch (Exception ex) {
                continue;
            }
            double distanceFromLatLonInKm = getDistanceFromLatLonInKm(locationLatDbl, locationLngDbl, hotelLatDbl, hotelLngDbl);
            if (distanceFromLatLonInKm <= maxDistanceInKm) {
                propertyIdList.add(extraHotel.get("Hotel_Code").toString());
            }

        }

        return propertyIdList;
    }

    /**
     * Calculates great-circle distances between the two points – that is, the
     * shortest distance over the earth’s surface – using the ‘Haversine’
     * formula.
     *
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return
     */
    public static double getDistanceFromLatLonInKm(double lat1, double lon1, double lat2, double lon2) {

        double earthRedius = 6371.0; // Radius of the earth in km
        double dLat = deg2rad(lat2 - lat1);  // deg2rad below
        double dLon = deg2rad(lon2 - lon1);
        double a
                = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = earthRedius * c; // Distance in km

        return d;
    }

    /**
     * Converts degree to radius.
     *
     * @param deg
     * @return
     */
    public static double deg2rad(double deg) {
        return deg * (Math.PI / 180.0);
    }
    
    public static String getEncrypted(String rowData) throws Exception{
        DataSecurity ds = new DataSecurity();
        String myStringEnc = ds.encryptData(rowData.trim());
        return myStringEnc.trim();
    }
    public static String getDecrypted(String rowData) throws Exception{
        DataSecurity ds = new DataSecurity();
        String myStringDec = ds.decryptData(rowData.trim());
        return myStringDec.trim();
    }
  
    /* password checker logic for strong password
    *@return isStrong
     */
    public static boolean isPasswordStrong(String password) {
        boolean isStrong = false;
        isStrong = password.matches("^(?=.*[A-Z])(?=.*[!@#$&*^%_~?])(?=.*[0-9])(?=.*[a-z]).{8,15}$");
        return isStrong;
    } 
}
