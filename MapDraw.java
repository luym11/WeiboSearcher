/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _geosearch;

import weibo4j.model.WeiboException;

/**
 *
 * @author luyimeng
 */
public class MapDraw {
    //画个地图吧
    ////weibo4j.Place wp2 = new weibo4j.Place(); 
    ////String MapURL = wp.drawMeMap(FloatLong.get(0)+","+FloatLat.get(0), "ResultNewPlaceName", "116.343048,40.005424");
    static String MapURL;
    public static String getMapURL(){
        return MapURL;
    }
    public void urlMap(String FloatLong, String FloatLat) throws WeiboException{
        weibo4j.Place wp = new weibo4j.Place();
        wp.client.setToken("2.00IatpcDHJIpLD539d7e3848zYztqD");
        try{
            MapURL = wp.drawMeMap(FloatLong+","+FloatLat);
            MapURL = MapURL.split("\"",7)[5];
            System.out.println(MapURL);
        }
        catch (Exception e){
                MapURL = "http://i.imgur.com/hWYi8uE.png";
        }
    }
    
}
