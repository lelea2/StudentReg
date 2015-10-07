/**
 * Created by Martin on 9/23/2015.
 */
package test;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JDBCtooltest {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String url = null;
    String user = null;
    String password = null;
//    XTextArea resultText;
    DateFormat df;
    public void init(){
        df = new SimpleDateFormat("HH:mm:ss");//设置显示格式
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            url ="jdbc:mariadb://localhost/test?user=root&password=123321&useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
            user = "root";
            password = "123321";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void excute(String sql){
//        this.resultText = resultText;

//        resultText.appendLine("-------------------------------------------");
//        resultText.appendLine("--Execute SQL Testing | Began at "+ df.format(new Date())+"--");
//        resultText.appendLine("-------------------------------------------");
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);//??sql??
            ResultSetMetaData msd = rs.getMetaData();
            int count = msd.getColumnCount();
            System.out.println("number of Columns:" + count);
            String fieldlabels ="";
            for(int i =1 ; i<= count; i++)
                fieldlabels = fieldlabels+ msd.getColumnName(i) + " | ";
            fieldlabels = fieldlabels+ "";
            System.out.println(fieldlabels) ;
            while(rs.next()) {
                String record = new String();
                for(int i =1 ; i<= count; i++)
                    record = record + rs.getString(i) + " | ";
                System.out.println(record);
            }
        } catch (SQLException e) {
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
            System.out.println("???????");
            e.printStackTrace();
        }
    }
    public static void main(String[] arg){
        /*JDBCtooltest jb = new JDBCtooltest();
        jb.init();
        jb.excute("select * from user;");*/
        String str = "abcds;dfefdfdl;111123232;";
        String[] strs = str.split(";");
        for (String s : strs)
            System.out.println(s);
    }
}