/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aws.sns;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author saradam
 */
public class Sms {
    public static void main(String[] args) {
        AWSCredentials awsCredentials = new BasicAWSCredentials("AKIAJCDMM63G2PXO7U7A", "LH3UvOHSm24O8/Jkef/z9rrPaOTuRZQ5zYZWtopw");
        final AmazonSNSClient snsClient = new AmazonSNSClient(awsCredentials);
        snsClient.withRegion(Region.getRegion(Regions.AP_SOUTH_1));
        String message = "Your booking has been confirmed By AgentX101 with Itinerary ID 41045";
        String phoneNumber = "+917699533487";         
        sendSMSMessage(snsClient, message, phoneNumber);
    }

    public static void sendSMSMessage(AmazonSNSClient snsClient, String message, 
                    String phoneNumber) {
            PublishResult result = snsClient.publish(new PublishRequest()
                            .withSubject("AgentX101")
                            .withMessage(message)
                            .withPhoneNumber(phoneNumber));
            System.out.println(result); // Prints the message ID.
    }
}
