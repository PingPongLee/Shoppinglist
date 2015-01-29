/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppinglist;

import java.io.UnsupportedEncodingException;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public abstract class Tools {
       
     /**
     * Converts input String to a MD5 hash and returns it.
     * @param String
     * @return String converted to MD5 hash
     * @throws UnsupportedEncodingException 
     */
    public static String convertToMd5(final String md5) throws UnsupportedEncodingException {
        StringBuffer sb=null;
        try {
            final java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            final byte[] array = md.digest(md5.getBytes("UTF-8"));
            sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (final java.security.NoSuchAlgorithmException e) {
        }
        return sb.toString();
    }
    
    /**
     * Shows a popup messagebox with specified strings. Type can be either Information, Error or Warning
     * @param infoMessage
     * @param titleBar 
     * @param type
     */
    public static void msgBox(String infoMessage, String titleBar, String type)
    {
        if(type == "Error") 
        {
            JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.ERROR_MESSAGE);
        }
        else if(type == "Warning")
        {
            JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.WARNING_MESSAGE); 
        }
        else
        {
            JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE); 
        }
    }
    
} 
