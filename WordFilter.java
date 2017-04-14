/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _geosearch;

import java.util.Vector;
import weibo4j.model.WeiboException;

/**
 *
 * @author luyimeng
 */
public class WordFilter {
    Vector<String> R = new Vector<>();
    int Rs;
    String FilterName = "\".\"";
    String FilterLat = "\":|}";
    String FilterLon = "\":|,";
    public Vector<String> wordfilter(Vector<String> Result, String Filter, int place, int startnumber){
        //把已经分出来的字段再去除不用的符号之类的
        System.out.println("split_start");
        Rs = Result.size();
        for(int j = startnumber;j<((Result.size())-1+startnumber);j++){
            //PoiidConverter PC_Rname = new PoiidConverter();
            //PC_Rname.converter(ResultName.get(j), PC_Rname.Shiki_Rname);
            //String[] RnameList = new String[5];
            try{
                System.out.println(Result.get(j));
            
                System.out.println((Result.get(j)).split(Filter)[place]);
                //(ResultName.get(j)).split("\".\"");
                R.add(((Result.get(j)).split(Filter))[place]);
            }
            catch (Exception e){
                R.add("0");
            }
        }

        
        
        return R;
    }
    public void printer(){
        //打印，测试一下
        System.out.println("R_start");
        for(int j = 0;j<(Rs-1);j++){
            System.out.println(R.get(j));
        }
        System.out.println("R_end");
    }
}
