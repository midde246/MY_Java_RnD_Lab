/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latlng;

/**
 *
 * @author saradam
 */
public class DistanceBetweenTwoLatLng {

    public static void main(String[] args) {
        
        double distanceFromLatLonInKm = getDistanceFromLatLonInKm(22.6520429, 88.44632989999999, 22.5753931, 88.47979029999999);
        System.out.println("distance : "+distanceFromLatLonInKm);
    }

    /**
     * Calculates great-circle distances between the two points – that is, the
     * shortest distance over the earth’s surface – using the ‘Haversine’
     * formula.
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return
     */
    private static double getDistanceFromLatLonInKm(double lat1, double lon1, double lat2, double lon2) {

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
     * @param deg
     * @return
     */
    private static double deg2rad(double deg) {
        return deg * (Math.PI / 180.0);
    }

}
