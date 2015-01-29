/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppinglist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
        
/**
 *
 * @author Administrator
 */
public class SQLTools extends Connector {
     
    /**
     * Method that inserts a row into the specified table. First parameter is data to insert as key / value pair, where key is the column name. 
     * Last parameter specifies which table to insert to. Returns the number of rows affected.
     * @param dataToInsert
     * @param table
     * @return rows affected
     */ 
    public int insert(Map<String, String> dataToInsert, String table) 
    {
        Iterator it = dataToInsert.entrySet().iterator();
        String columnsToInsertIn = "(";
        String dataForColumns = "(";        
        while(it.hasNext())
        {           
            Map.Entry pairs = (Map.Entry)it.next();          
            columnsToInsertIn += pairs.getKey() + ", ";
            dataForColumns += "'" + pairs.getValue() + "', ";
        }
        columnsToInsertIn = columnsToInsertIn.substring(0, columnsToInsertIn.length() - 2);
        columnsToInsertIn += ")";
        dataForColumns = dataForColumns.substring(0, dataForColumns.length() - 2);
        dataForColumns += ")";        
        String stmt = "INSERT INTO " + table + " " + columnsToInsertIn + " VALUES " + dataForColumns;
        try
        {            
            Statement query = conn.createStatement();
            return query.executeUpdate(stmt);
        }
        catch(SQLException se)
        {            
            System.out.println(se.toString() + "\nSQL Query that was run: " + stmt);            
            return 0;
        }                
    }
    
    /**
     * Returns a single row in the MySQL database as a key value paired map (column = key).
     * @param column
     * @param identifier
     * @return  Map
     */
    public Map<String, String> getRow(String column, String identifier) 
    { 
        Map<String, String> rowData = new HashMap<>();
        try 
        {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE `" + column + "` = '" + identifier + "'");
//            pstmt.setString(1, column); -> For some reason prepared statements does not work. inserting ` renders the query faulty.
//            pstmt.setString(2, identifier);      
            ResultSet rs = pstmt.executeQuery();    
            ResultSetMetaData metadata = rs.getMetaData();
            int numberOfColumns = metadata.getColumnCount();                        
            while(rs.next())
                {
                for(int i = 1; i < numberOfColumns; i++) {
                    String cName = metadata.getColumnName(i);  
                    String cData = rs.getString(cName);
                    rowData.put(cName, cData);                    
                }
            }
        }
        catch(SQLException se)
        {              
            se.printStackTrace();                                
        } 
        return rowData;
    }     
    
}
