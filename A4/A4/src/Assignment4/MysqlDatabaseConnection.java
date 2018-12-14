package Assignment4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 *
 * @author syedmfaizan
 */
public class MysqlDatabaseConnection {
    private final String host="bluenose.cs.dal.ca";
    private final String rhost="db.cs.dal.ca";
    private final int lport=5656;
    private final int rport=3306;
    private final String username="rakhra";
    private final String password="B00766801";
    private final String bluenosePassword="B00766801";
    private final String dbname="rakhra";
    private Connection conn=null;
    private Session session=null;
    
    
    Boolean connect(){
        Boolean done=false;
        try{
            java.util.Properties config = new java.util.Properties(); 
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            this.session=jsch.getSession(this.username, this.host);
            this.session.setPassword(this.bluenosePassword);
            this.session.setConfig(config);
            this.session.connect();
            int assinged_port=this.session.setPortForwardingL(this.lport, this.rhost, this.rport);

            String url = "jdbc:mysql://localhost:" + this.lport + "/" + this.dbname;
            String driverName="com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
            this.conn = DriverManager.getConnection (url, this.username, this.password);
            done=true;
        } catch (Exception ex) {
            Logger.getLogger(MysqlDatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return done;
    }
    
    Boolean disconnect(){
        Boolean done=false;
        try {
            if(this.conn != null && !this.conn.isClosed()){
                this.conn.close();
            }
            if(this.session !=null && this.session.isConnected()){
                this.session.disconnect();
            }
            done=true;
        } catch (SQLException ex) {
            Logger.getLogger(MysqlDatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return done;
    }
    
    Connection getConnection(){
        return this.conn;
    }
    
    ResultSet executeQuery (String query){
        ResultSet rs=null;
        try{
            Statement st = this.conn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException ex) { 
            Logger.getLogger(MysqlDatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
   
    
    int executeUpdate (String query){
        int rs=0;
        try{
            Statement st = this.conn.createStatement();
            rs = st.executeUpdate(query);
        } catch (SQLException ex) { 
            Logger.getLogger(MysqlDatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

}




