package weibo4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import weibo4j.model.PostParameter;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONObject;
import weibo4j.util.WeiboConfig;

public class Place extends Weibo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/****************动态读取************************/
	/**
	 * 注：没有写完
	 * 
	 * 获取当前登录用户与其好友的位置动态 
	 */
	public StatusWapper friendsTimeLine () throws WeiboException {
		return Status.constructWapperStatus(client.get(WeiboConfig.getValue("baseURL") + "place/friends_timeline.json"));
	}
	
	
	/****************用户读取************************/
	/**
	 * 获取LBS位置服务内的用户信息 
	 * 
	 * 
	 */
	public JSONObject userInfoInLBS (String uid) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "place/users/show.json",new PostParameter[] {
			new PostParameter("uid", uid)
		}).asJSONObject();
	}
	
	public JSONObject userInfoInLBS (String uid,int base_app) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "place/users/show.json",new PostParameter[] {
			new PostParameter("uid", uid),
			new PostParameter("base_app", base_app)
		}).asJSONObject();
	}
	
	/**
	 * 获取用户签到过的地点列表
	 * 
	 *   注：没有写完
	 */
	public JSONObject checkinsList (String uid) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "place/users/checkins.json",new PostParameter[] {
			new PostParameter("uid", uid)
		}).asJSONObject();
	}
	
	/**
	 * 获取用户的照片列表 
	 * 
	 * 注：没有写完
	 */
	
	public JSONObject photoList (String uid) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "place/users/photos.json",new PostParameter[] {
			new PostParameter("uid", uid)
		}).asJSONObject();
	}
	
	/**
	 * 获取用户的点评列表 
	 * 
	 * 注：没有写完
	 */
	
	public JSONObject tipsList (String uid) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "place/users/tips.json",new PostParameter[] {
			new PostParameter("uid", uid)
		}).asJSONObject();
	}
	
	/****************地点读取************************/
	/**
	 * 获取地点详情 
	 * 
	 * 
	 */
	public JSONObject poisShow (String poiid) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "place/pois/show.json",new PostParameter[] {
			new PostParameter("poiid", poiid)
		}).asJSONObject();
	}
	
	public JSONObject poisShow (String poiid,int base_app) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "place/pois/show.json",new PostParameter[] {
			new PostParameter("poiid", poiid),
			new PostParameter("base_app",base_app)
		}).asJSONObject();
	}
	
	/**
	 * 获取在某个地点签到的人的列表 
	 * 
	 * 注：没写完
	 */
	public JSONObject poisUsersList (String poiid) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "place/pois/show.json",new PostParameter[] { 
			new PostParameter("poiid", poiid) }).asJSONObject();
	}
	
        //written by yimeng.lu
        //通过关键词获取poiid
        //public String Poiid;
        public String poiidSearch(String Keyword) throws WeiboException{
            //Pattern p = Pattern.compile("\"poiid\":\"(.*)\",\"title\"");  
            //Matcher m = p.matcher(testStr);  
            //while(m.find()){  
            //System.out.println(m.group(1));  
            //}
            
            return client.get(WeiboConfig.getValue("baseURL") + "place/pois/search.json",new PostParameter[] { 
			new PostParameter("keyword", Keyword) }).asString();
            
            
        }
        
        //yimeng.lu
        //通过poiid查询某个地点的微博
        //place/poi_timeline.json
        public String placeWeiboSearch(String Poiid) throws WeiboException{
        
            return client.get(WeiboConfig.getValue("baseURL") + "place/poi_timeline.json",new PostParameter[] { 
			new PostParameter("poiid", Poiid) }).asString();
        }
        
        //yimeng.lu
        //通过lat和long查询地点附近的微博
        //place/nearby_timeline.json
        public String nearbyWeiboSearch(float Lat, float Long) throws WeiboException{
        
            return client.get(WeiboConfig.getValue("baseURL") + "place/nearby_timeline.json",new PostParameter[] { 
			new PostParameter("lat", Lat) ,new PostParameter("long", Long),new PostParameter("range", 500)}).asString();
        }
        
        //yimenglu
        //通过百度地图的接口查询搜索地点附近的其他信息
        //废了
        public String placeDetailedInfoSearch(String q, String region,int scope) throws WeiboException, UnsupportedEncodingException{
            return client.get("http://api.map.baidu.com/place/v2/search", new PostParameter[]{
                            new PostParameter("scope", scope),new PostParameter("q", URLEncoder.encode(q, "UTF8")),new PostParameter("output", "json"),new PostParameter("region",URLEncoder.encode(region, "UTF8")),new PostParameter("ak", "Mdzf1zlSZmOuzxangCnaARsW")}).asString();
        }
        
        //yimenglu
        //画地图根据经纬度
        public String drawMeMap(String center_coordinate) throws WeiboException{
          return client.get(WeiboConfig.getValue("baseURL") + "location/base/get_map_image.json",new PostParameter[] { 
			new PostParameter("center_coordinate", center_coordinate), new PostParameter("zoom", 15),new PostParameter("scale", "true")}).asString();
        }
        //public String drawMeMap(String center_coordinate, String place_coordinate, String PosName) throws WeiboException{
        //  return client.get(WeiboConfig.getValue("baseURL") + "location/base/get_map_image.json",new PostParameter[] { 
	//		new PostParameter("center_coordinate", center_coordinate), new PostParameter("zoom", 15),new PostParameter("scale", "true"),new PostParameter("coordinates", place_coordinate),new PostParameter("names", PosName)}).asString();
       // }
        
	/**
	 * 获取在某个地点点评的列表 
	 * 
	 * 注：没写完
	 */
	public User poisTipsList (String poiid) throws WeiboException {
		return new User(client.get(WeiboConfig.getValue("baseURL") + "place/pois/tips.json",new PostParameter[] { 
			new PostParameter("poiid", poiid) }).asJSONObject());
	}
	
}
