/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package population;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author saradam
 */
public class ServiceCaller {
    public static String callService(String serviceUrl, JSONObject jsonObj) throws IOException {
    	System.out.println("serviceUrl inside ServiceCaller.callService :: "+serviceUrl +" :: Request :: "+jsonObj);
        StringBuilder response = new StringBuilder();
        URL url = new URL(serviceUrl);
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        huc.setRequestMethod("GET");
        huc.setRequestProperty("Host", url.getHost());
        huc.setRequestProperty("Content-Type", "application/json");
//        huc.setRequestProperty("Accept-Charset", "UTF-8");
//        huc.setRequestProperty("Accept-Encoding", "gzip");
//        huc.setRequestProperty("Content-Language", "en-US");
//        huc.setRequestProperty("accept", "application/json");
        huc.setDoOutput(true);
        huc.setConnectTimeout(1000000);
        huc.setReadTimeout(100000000);
        PrintWriter writer = new PrintWriter(huc.getOutputStream());
        writer.write(jsonObj.toString());
        writer.flush();
        writer.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
        String line = null;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();
        return response.toString();
    }
    
    public static String callServiceGET(String url) throws ProtocolException, IOException{
		
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("Api-Key", "pbqzak34avwdyx4ew8a5vk25");
        con.setConnectTimeout(100000000);
        con.setReadTimeout(100000000);
        int responseCode = con.getResponseCode();
        System.out.println("Sending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();

        //Return result
        return response.toString();
    }
}
