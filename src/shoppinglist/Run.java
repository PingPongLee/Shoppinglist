/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppinglist;

import java.util.Scanner;

/**
 *
 * @author Bruger
 */
public class Run {
    
    public void loginProcess() {
        Scanner in = new Scanner(System.in);            
        System.out.println("Enter Username:");                        
        String name = in.nextLine();
        System.out.println("Enter Password:");
        String pwd = in.nextLine();
        User currentUser = new User();
        if(currentUser.login(name, pwd)) 
        {
            System.out.println("Login success");
        } 
        else 
        {
            System.out.println("Login failed");
        }
    }        
    
}
