/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppinglist;

import java.util.*;

/**
 *
 * @author Bruger
 */
public class User {
        
    /**
     * Method that returns true if the user is found in the database AND the password matches the one in the db.
     * @param username
     * @param inputPwd
     * @return boolean
     */
    public boolean login(String username, String inputPwd) 
    {     
        SQLTools sql = new SQLTools();      
        if(sql.isConnected() == true) {
            try {               
                //
                Map<String, String> userInfo = sql.getRow("username", username);    
//                NOTE: To loop over each entry in a map with key value pairs, the below could be used                
//                for (Map.Entry<String, String> entry : map.entrySet())
//                {
//                    System.out.println(entry.getKey() + "/" + entry.getValue());
//                }
                if(!userInfo.isEmpty()) 
                {
                    String dbPwd = userInfo.get("pwd");                    
                    String md5InputPwd = Tools.convertToMd5(inputPwd);    
                    return md5InputPwd.equals(dbPwd);
                }
                else 
                {
                   return false; 
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());        
                return false;
            }            
        }
        else
        {
            return false;
        }      
    }
    
    /**
     * Method that creates a user on the database using the SQLTools class. Returns true if user was created with success, otherwise false.
     * @param username
     * @param password
     * @param firstname
     * @param lastname
     * @return boolean
     */
    public boolean createUser(String username, String password, String firstname, String lastname) 
    {
        SQLTools check = new SQLTools();
        if(check.getRow("username", username).isEmpty())
        {
            // JAhallo
            //if(username != "" || password != "" || firstname != "" || lastname != "")
            if(!username.isEmpty() && !password.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty())
            {
                try
                {
                    if(check.isConnected()) 
                    {
                        //SQLTools newUser = new SQLTools();
                        Map<String, String> userData = new HashMap<String, String>();
                        userData.put("username", username);
                        userData.put("pwd", Tools.convertToMd5(password));
                        userData.put("firstname", firstname);
                        userData.put("lastname", lastname);
                        int userid = check.insert(userData, "users");
                        if(userid>0)
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                catch (Exception e)
                {
                    return false;
                }
            }
            else 
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    
}

