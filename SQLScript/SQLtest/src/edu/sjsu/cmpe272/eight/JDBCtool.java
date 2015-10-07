package edu.sjsu.cmpe272.eight; /**
 * Created by Martin on 9/23/2015.
 */
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JDBCtool {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String url = null;
    String user = null;
    String password = null;
    XTextArea resultText;
    DateFormat df;
    public void init(String url, String user, String password){
        df = new SimpleDateFormat("HH:mm:ss");//设置显示格式
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resultText.appendLine(e.toString());
        }
        try {
            /*url ="jdbc:mysql://localhost/test?user=root&password=123321&useUnicode=true&&characterEncoding=utf-8&autoReconnect=true";
            user = "root";
            password = "123321";*/
//            System.out.println("url:"+url+"\n user:"+user+"\n password:"+password);
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
            resultText.appendLine(e.toString());
        }
    }
    public void excute(String sql, XTextArea resultText){
        this.resultText = resultText;

//        resultText.appendLine("-------------------------------------------------------");
        resultText.appendLine(">>>>>>>New SQL Script Testing began at "+ df.format(new Date())+"<<<<<<<<<");
//        resultText.appendLine("--------------------------------------------------------");
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData msd;

            //temporary solution of "select"
            if(sql.indexOf("select")>0&& rs != null && rs.getMetaData()!=null) {
//            if(rs != null ) {
                msd = rs.getMetaData();
                int count = msd.getColumnCount();

    //          Result result = ResultSupport.toResult(rs);
    //          resultText.appendLine("found record{s):" + result.getRowCount());
                String fieldlabels = "";
                for (int i = 1; i <= count; i++)
                    fieldlabels = fieldlabels + msd.getColumnName(i) + " | ";
                fieldlabels = fieldlabels + "";
                resultText.appendLine(fieldlabels);

                int counter = 0;
                while(rs.next()) {
                    counter++;
                    String record = new String();
                    for(int i =1 ; i<= count; i++)
                        record = record + rs.getString(i) + " | ";
                    resultText.appendLine(record);
                }
                resultText.appendLine("[sys]: found "+ counter+" record{s):");
            }else{
                resultText.appendLine("[sys]: found 0 record{s).");
            }
        } catch (SQLException e) {
            resultText.appendLine(e.toString());
            e.printStackTrace();

        }

        try {
            if(rs != null) {
                rs.close();
                rs = null;
            }
            if(stmt != null) {
                stmt.close();
                stmt = null;
            }
            if(conn != null) {
                conn.close();
                conn = null;
            }
        } catch(Exception e) {
            resultText.appendLine(e.toString());
            e.printStackTrace();
        }
        resultText.appendLine(">>>>>Test ended at "+ df.format(new Date())+"<<<<<<");
    }
}