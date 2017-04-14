/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _geosearch;

import static java.lang.Float.parseFloat;
import java.util.Vector;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;

/**
 *
 * @author luyimeng
 */
public class nearSearcher {
    public static void main(String[] args) throws JSONException{
        weibo4j.Place wp = new weibo4j.Place();
        wp.client.setToken("2.00IatpcDHJIpLD539d7e3848zYztqD");
        try {
                    
                    String GeoInfo = wp.poiidSearch("清华大学综合体育馆");
                    
                    System.out.println("Hajime masu yo~");
                    System.out.println(GeoInfo);
                    PoiidConverter PC = new PoiidConverter();
                    PC.converter(GeoInfo, PC.Shiki_poiid);
                    for(int j = 0;j<PC.POIIDs.size();j++){
                        System.out.println(j);
                        System.out.println(PC.POIIDs.get(j));
                        
                    }
                    System.out.println("Owariyo~");
                    String UTFSearchResult = wp.placeWeiboSearch((PC.POIIDs).get(0));
                    UnicodeConverter UC = new UnicodeConverter();
                    String KanjiResult = UC.convert(UTFSearchResult);
                    //System.out.println(KanjiResult);
                    
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
                    
                    //nearSearcher新加段
                    //用正则表达式读取分割好的KanjiResultArray[]里的lat，long字段
                    Vector<String> ResultLong = new Vector<>();
                    Vector<String> ResultLat = new Vector<>();
                    System.out.println("CooCOoCooCooCooCooCooCooCooCooCOoCooCooCooCooCooCooCooCooCOoCooCooCooCooCooCooCoo= =");
                    for(int j = 0;j<KanjiLength;j++){
                        PoiidConverter PC_long = new PoiidConverter();
                        PoiidConverter PC_lat = new PoiidConverter();
                        PC_long.converter(KanjiResultArray[j], PC_long.Shiki_long);
                        ResultLong.add(PC_long.Kekka);
                        PC_lat.converter(KanjiResultArray[j], PC_lat.Shiki_lat);
                        ResultLat.add(PC_lat.Kekka);
                    }
                    System.out.println("Starto! ");
                    for(int j = 0; j<ResultLong.size();j++){
                        System.out.println(ResultLat.get(j)+ResultLong.get(j));
                    }
                    //不过这么搞出来的第一个的坐标是不在lat，lon关键字后面的，不要了~！上一个for从1开始就好了
                    
                    //取出lat，lon的纯数组部分
                    Vector<String> FloatLong = new Vector<>();
                    Vector<String> FloatLat = new Vector<>();
                    System.out.println("FloatFloatFloatFloatFloatFloatFloatFloatFloatFloatFloatFloatFloatFloatFloatFloatFloat IN COMING= =");
                    WF = new WordFilter();
                    FloatLong = WF.wordfilter(ResultLong, WF.FilterLon,1,1);
                    WF = new WordFilter();
                    FloatLat = WF.wordfilter(ResultLat, WF.FilterLat,1,1);
                    for(int j = 0;j<FloatLong.size();j++){
                        System.out.println("LONG:"+FloatLong.get(j)+", LAT:"+FloatLat.get(j));
                    }
                    
                    System.out.println("Frikin' start");
                    //画个地图吧
                    MapDraw MD = new MapDraw();
                    MD.urlMap(FloatLong.get(2), FloatLat.get(2));
                    
                    
                    //////////////////////////
                    //需要分开弄，下面这一半代码要是一起运行的话会超时
                    
                    //开始搜索第一项微博所在经纬度的nearWeibo
                    GeoInfo =wp.nearbyWeiboSearch(parseFloat(FloatLat.get(2)), parseFloat(FloatLong.get(2)));
                    
                    System.out.println("Hajime masu yo~");
                    System.out.println(GeoInfo);
                    PC = new PoiidConverter();
                    PC.converter(GeoInfo, PC.Shiki_poiid);
                    UTFSearchResult = wp.placeWeiboSearch((PC.POIIDs).get(0));
                    UC = new UnicodeConverter();
                    KanjiResult = UC.convert(UTFSearchResult);
                    //System.out.println(KanjiResult);
                    
                    System.out.println("split");
                    KanjiResultArray = KanjiResult.split("poiid");
                    KanjiLength = KanjiResultArray.length;
                    for(int j = 0;j<KanjiLength;j++){
                        System.out.println(KanjiResultArray[j]);
                    }
                    System.out.println(KanjiLength);
                    
                    //用正则表达式读取分割好的KanjiResultArray[]里的screen_name字段
                    ResultName = new Vector<>();
                    System.out.println("name");
                    for(int j = 0;j<KanjiLength;j++){
                        PoiidConverter PC_name = new PoiidConverter();
                        PC_name.converter(KanjiResultArray[j], PC_name.Shiki_name);
                        ResultName.add(PC_name.Kekka);
                    }
                    
                    
                    //用正则表达式读取分割好的KanjiResultArray[]里的text字段
                    ResultText = new Vector<>();
                    System.out.println("text");
                    for(int k = 0;k<KanjiLength;k++){
                        PoiidConverter PC_text = new PoiidConverter();
                        PC_text.converter(KanjiResultArray[k], PC_text.Shiki_text);
                        ResultText.add(PC_text.Kekka);
                    }
                    
                    //用正则表达式读取分割好的KanjiResultArray[]里的时间字段
                    ResultTime = new Vector<>();
                    System.out.println("time");
                    for(int k = 0;k<KanjiLength;k++){
                        PoiidConverter PC_time = new PoiidConverter();
                        PC_time.converter(KanjiResultArray[k], PC_time.Shiki_time);
                        ResultTime.add(PC_time.Kekka);
                    }
                  
                    //去掉多余的符号
                    WF = new WordFilter();
                    RealName = WF.wordfilter(ResultName, WF.FilterName,1,0);
                    WF = new WordFilter();
                    RealTime = WF.wordfilter(ResultTime, WF.FilterName,1,0);
                    WF = new WordFilter();
                    RealText = WF.wordfilter(ResultText, WF.FilterName,1,0);
                    
                    for(int j = 0;j<RealName.size();j++){
                        System.out.println(RealName.get(j)+"  "+RealTime.get(j)+"  "+RealText.get(j));
                    }
                    
                    //测试地理位置
                    //nearSearcher新加段
                    //用正则表达式读取分割好的KanjiResultArray[]里的lat，long字段
                    ResultLong = new Vector<>();
                    ResultLat = new Vector<>();
                    System.out.println("CooCOoCooCooCooCooCooCooCooCooCOoCooCooCooCooCooCooCooCooCOoCooCooCooCooCooCooCoo= =");
                    for(int j = 0;j<KanjiLength;j++){
                        PoiidConverter PC_long = new PoiidConverter();
                        PoiidConverter PC_lat = new PoiidConverter();
                        PC_long.converter(KanjiResultArray[j], PC_long.Shiki_long);
                        ResultLong.add(PC_long.Kekka);
                        PC_lat.converter(KanjiResultArray[j], PC_lat.Shiki_lat);
                        ResultLat.add(PC_lat.Kekka);
                    }
                    System.out.println("Starto! ");
                    for(int j = 1; j<ResultLong.size();j++){
                        System.out.println(ResultLat.get(j)+ResultLong.get(j));
                    }
                    
                    //不过这么搞出来的第一个的坐标是不在lat，lon关键字后面的，不要了~！上一个for从1开始就好了
                    
                    
                    
                    //搞一下这个地方的名字
                    PoiidConverter PC_name = new PoiidConverter();
                    PC_name.converter(KanjiResultArray[2], PC_name.Shiki_placetitle);
                    String ResultNewPlaceName;
                    ResultNewPlaceName = PC_name.Kekka;
                    ResultNewPlaceName = ResultNewPlaceName.split("\".\"",4)[1];
                            
                    //取出lat，lon的纯数组部分
                    FloatLong = new Vector<>();
                    FloatLat = new Vector<>();
                    System.out.println("FloatFloatFloatFloatFloatFloatFloatFloatFloatFloatFloatFloatFloatFloatFloatFloatFloat IN COMING= =");
                    WF = new WordFilter();
                    FloatLong = WF.wordfilter(ResultLong, WF.FilterLon,1,1);
                    WF = new WordFilter();
                    FloatLat = WF.wordfilter(ResultLat, WF.FilterLat,1,1);
                    for(int j = 0;j<FloatLong.size();j++){
                        System.out.println("LONG:"+FloatLong.get(j)+", LAT:"+FloatLat.get(j));
                    }
                    //画个地图吧
                    MapDraw MD2 = new MapDraw();
                    MD2.urlMap(FloatLong.get(0), FloatLat.get(0));
                    
                    System.out.println(ResultNewPlaceName);
                    
                    
                    
                    //纯净的信息们
                    //title:ResultNewPlaceName[String, 以下全部vector]
                    //name:RealName
                    //RealTime
                    //RealText
                    //FloatLong, FloatLat
                    //MapURL[String]
        }
        catch (WeiboException e) {
                e.printStackTrace();
        }
        
        
    }
}
