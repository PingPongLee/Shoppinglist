/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppinglist;

import java.sql.*;

/**
 *
 * @author Bruger
 */
public class Connector {
    
    private String host = "62.198.56.192";
    private String port = "3306";
    private String user = "shoppinglist";
    private String pass = "abcd1234";
    private String db = "shoppinglist";
    
    private String jdbc = "jdbc:mysql://" + host + ":" + port + "/" + db;
       
    public Connection conn; 
    
    /**
     * Class initiator.
     */
    public Connector() 
    {
        connect();
    }
    
    
    /**
     * Connects to the specified MySQL database (using setDbConnection method) with specified credentials (using setDbCredentials method). If nothing is given, the defaults are used. Returns true if connection is succeeded. Otherwise false.
     * @return Boolean
     */
    private boolean connect() 
    { 
        try {                                               
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(jdbc,user,pass);            
            return true;        
        }catch(SQLException se){              
            se.printStackTrace();
            return false;
        }catch (ClassNotFoundException e) {
            Tools.msgBox("Missing MySQL Connector driver", "Missing driver!", "Error");
            return false;
        }                     
    }
    
    /**
     * Checks if the connection is alive. Returns true if connection is ok, and false otherwise.
     * @return Boolean
     */
    public boolean isConnected() 
    {
        if(conn != null) 
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    

    /**
     * Sets the MySQL Connection information. Cannot be empty.
     * @param host
     * @param port
     * @param db 
     */
    public void setDbConnection(String host, String port, String db) 
    {
        if(!host.isEmpty()) { this.host = host; }
        if(!port.isEmpty()) { this.port = port; }
        if(!db.isEmpty()) { this.db = db; }
    }    
    
    /**
     * Sets the MySQL user credentials. Cannot be empty strings.
     * @param username
     * @param password 
     */
    
    public void setDbCredentials(String username, String password) 
    {
        if(!username.isEmpty()) { this.user = username; }
        if(!password.isEmpty()) { this.pass = password; }
    }       
    
}

