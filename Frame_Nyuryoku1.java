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
public class Frame_Nyuryoku1 extends javax.swing.JFrame {

    /**
     * Creates new form Frame_Nyuryoku
     */
    public Frame_Nyuryoku1() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setText("地点名称");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("确定");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton1)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private static String FloatLatTrans;
    private static String FloatLongTrans;
    private static String TitleTrans;
    public void setLat(String Lat){
        FloatLatTrans = Lat;
    }
    public void setLong(String Long){
        FloatLongTrans = Long;
    }
    public static String getLat(){
        return FloatLatTrans;
    }
    public static String getLong(){
        return FloatLongTrans;
    }
    public void  setTitleTrans(String Title){
        TitleTrans = Title;
    }
    public static String getTitleTrans(){
        return TitleTrans;
    }
    public static Vector<String> NameTrans1;
    public void setNameTrans1(Vector<String> RealNameIn){
        //for(int j = 0;j<RealNameIn.size();j++){
        //    NameTrans.add( RealNameIn.get(j).toString());
        //}
        NameTrans1.addAll(RealNameIn);
    }
    public static String getNameTrans1(int i){
        return NameTrans1.get(i);
    }
    
    static Vector<String> RealName = new Vector<>();
    
    public static String getNameTrans(int i){
        return RealName.get(i);
    }
    static Vector<String> RealTime = new Vector<>();
    public static String getTimeTrans(int i){
        return RealTime.get(i);
    }
    static Vector<String> RealText = new Vector<>();
    public static String getTextTrans(int i){
        return RealText.get(i);
    }
    static Vector<String> FloatLong = new Vector<>();
    static Vector<String> FloatLat = new Vector<>();
    public static String getFloatLong(int i){
        return FloatLong.get(i);
    }
    public static String getFloatLat(int i){
        return FloatLat.get(i);
    }
    
    
    
    
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        String Text;
        Text = jTextField1.getText();
        setTitleTrans(Text);
        
        weibo4j.Place wp = new weibo4j.Place();
        wp.client.setToken("2.00IatpcDHJIpLD539d7e3848zYztqD");
        try {
            //用Text的数据得到一个带有Poiid信息的JSON串存入GeoInfo
            String GeoInfo = wp.poiidSearch(Text);
            //正则取出里面的Poiid部分
            PoiidConverter PC = new PoiidConverter();
            PC.converter(GeoInfo, PC.Shiki_poiid);
            //用该Poiid进行搜索，得到的结果转码，存入
            String UTFSearchResult = wp.placeWeiboSearch((PC.POIIDs).get(0));
            UnicodeConverter UC = new UnicodeConverter();
            String KanjiResult = UC.convert(UTFSearchResult);
            
            //把多条结果的集合进行分割
            String[] KanjiResultArray = KanjiResult.split("poiid");
            int KanjiLength = KanjiResultArray.length;
            
            //用正则表达式读取分割好的KanjiResultArray[]里的screen_name字段
            Vector<String> ResultName = new Vector<>();
            for(int j = 0;j<KanjiLength;j++){
                PoiidConverter PC_name = new PoiidConverter();
                PC_name.converter(KanjiResultArray[j], PC_name.Shiki_name);
                ResultName.add(PC_name.Kekka);
            }


            //用正则表达式读取分割好的KanjiResultArray[]里的text字段
            Vector<String> ResultText = new Vector<>();
            for(int k = 0;k<KanjiLength;k++){
                PoiidConverter PC_text = new PoiidConverter();
                PC_text.converter(KanjiResultArray[k], PC_text.Shiki_text);
                ResultText.add(PC_text.Kekka);
            }

            //用正则表达式读取分割好的KanjiResultArray[]里的时间字段
            Vector<String> ResultTime = new Vector<>();
            for(int k = 0;k<KanjiLength;k++){
                PoiidConverter PC_time = new PoiidConverter();
                PC_time.converter(KanjiResultArray[k], PC_time.Shiki_time);
                ResultTime.add(PC_time.Kekka);
            }

            //去掉多余的符号
            WordFilter WF = new WordFilter();
            
            RealName = WF.wordfilter(ResultName, WF.FilterName,1,0);
            for(int j = 0;j<RealName.size();j++){
                        System.out.println(RealName.get(j));
                    }
            for(int j = RealName.size();j<20;j++){
                RealName.add("0");
            }
            //setNameTrans(RealName);
            WF = new WordFilter();
            RealTime = WF.wordfilter(ResultTime, WF.FilterName,1,0);
            for(int j = RealTime.size();j<20;j++){
                RealTime.add("0");
            }
            WF = new WordFilter();
            RealText = WF.wordfilter(ResultText, WF.FilterName,1,0);
            for(int j = RealText.size();j<20;j++){
                RealText.add("0");
            }

            //nearSearcher新加段
            //用正则表达式读取分割好的KanjiResultArray[]里的lat，long字段
            Vector<String> ResultLong = new Vector<>();
            Vector<String> ResultLat = new Vector<>();
            for(int j = 0;j<KanjiLength;j++){
                PoiidConverter PC_long = new PoiidConverter();
                PoiidConverter PC_lat = new PoiidConverter();
                PC_long.converter(KanjiResultArray[j], PC_long.Shiki_long);
                ResultLong.add(PC_long.Kekka);
                PC_lat.converter(KanjiResultArray[j], PC_lat.Shiki_lat);
                ResultLat.add(PC_lat.Kekka);
            }

            //取出lat，lon的纯数组部分
            //Vector<String> FloatLong = new Vector<>();
            //Vector<String> FloatLat = new Vector<>();
            WF = new WordFilter();
            FloatLong = WF.wordfilter(ResultLong, WF.FilterLon,1,1);
            for(int j = FloatLong.size();j<20;j++){
                FloatLong.add("0");
            }
            WF = new WordFilter();
            FloatLat = WF.wordfilter(ResultLat, WF.FilterLat,1,1);
            for(int j = FloatLat.size();j<20;j++){
                FloatLat.add("0");
            }
            
            
            //画个地图吧
            MapDraw MD = new MapDraw();
            MD.urlMap(FloatLong.get(2), FloatLat.get(2));
            
            setLat(FloatLat.get(2));
            setLong(FloatLong.get(2));
        }
        catch (WeiboException e) {
                e.printStackTrace();
        }
        new Frame_Tenji1().setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame_Nyuryoku1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_Nyuryoku1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_Nyuryoku1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_Nyuryoku1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame_Nyuryoku1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
