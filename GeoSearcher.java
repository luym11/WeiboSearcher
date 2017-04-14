/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _geosearch;

import java.io.UnsupportedEncodingException; 
import static java.lang.Thread.sleep;
import java.net.URLEncoder;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;

/**
 *
 * @author luyimeng
 */
public class GeoSearcher {
    public static void main(String[] args) throws JSONException, WeiboException, UnsupportedEncodingException{
        weibo4j.Place wp = new weibo4j.Place();
		//wp.client.setToken(args[1]);
                wp.client.setToken("2.00IatpcDHJIpLD539d7e3848zYztqD");
		try {
                    
                    String GeoInfo = wp.poiidSearch("清华大学综合体育馆");
                    
                    System.out.println("Hajime masu yo~");
                    System.out.println(GeoInfo);
                    PoiidConverter PC = new PoiidConverter();
                    PC.converter(GeoInfo, PC.Shiki_poiid);
                    String UTFSearchResult = wp.placeWeiboSearch((PC.POIIDs).get(0));
                    UnicodeConverter UC = new UnicodeConverter();
                    String KanjiResult = UC.convert(UTFSearchResult);
                    System.out.println(KanjiResult);
                    
                    System.out.println("split");
                    String[] KanjiResultArray = KanjiResult.split("poiid");
                    int KanjiLength = KanjiResultArray.length;
                    for(int j = 0;j<KanjiLength;j++){
                        System.out.println(KanjiResultArray[j]);
                    }
                    System.out.println(KanjiLength);
                    
                    //用正则表达式读取分割好的KanjiResultArray[]里的screen_name字段
                    Vector<String> ResultName = new Vector<>();
                    System.out.println("name");
                    for(int j = 0;j<KanjiLength;j++){
                        PoiidConverter PC_name = new PoiidConverter();
                        PC_name.converter(KanjiResultArray[j], PC_name.Shiki_name);
                        ResultName.add(PC_name.Kekka);
                    }
                    
                    
                    //用正则表达式读取分割好的KanjiResultArray[]里的text字段
                    Vector<String> ResultText = new Vector<>();
                    System.out.println("text");
                    for(int k = 0;k<KanjiLength;k++){
                        PoiidConverter PC_text = new PoiidConverter();
                        PC_text.converter(KanjiResultArray[k], PC_text.Shiki_text);
                        ResultText.add(PC_text.Kekka);
                    }
                    
                    //用正则表达式读取分割好的KanjiResultArray[]里的时间字段
                    Vector<String> ResultTime = new Vector<>();
                    System.out.println("time");
                    for(int k = 0;k<KanjiLength;k++){
                        PoiidConverter PC_time = new PoiidConverter();
                        PC_time.converter(KanjiResultArray[k], PC_time.Shiki_time);
                        ResultTime.add(PC_time.Kekka);
                    }
                  
                    //去掉多余的符号
                    WordFilter WF = new WordFilter();
                    Vector<String> RealName = WF.wordfilter(ResultName, WF.FilterName,1,0);
                    WF = new WordFilter();
                    Vector<String> RealTime = WF.wordfilter(ResultTime, WF.FilterName,1,0);
                    WF = new WordFilter();
                    Vector<String> RealText = WF.wordfilter(ResultText, WF.FilterName,1,0);
                    
                    for(int j = 0;j<RealName.size();j++){
                        System.out.println(RealName.get(j)+"  "+RealTime.get(j)+"  "+RealText.get(j));
                    }
                    
		} catch (WeiboException e) {
			e.printStackTrace();
		}
        

        //画个地图吧
               // String MapURL = wp.drawMeMap(null);
                //System.out.println(MapURL);
    }
}
