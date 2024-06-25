package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {

    /*public static String readPropertiesFile(String key, String filePath) throws IOException {
        Properties pro = null;
        
        try {
            pro = new Properties();
            FileInputStream file = new FileInputStream(filePath);
            pro.load(file);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return (String) pro.get(key);
    }*/


    public static Properties readPropertiesFile(String filePath) throws IOException {
        Properties pro = null;

        try {
            pro = new Properties();
            FileInputStream file = new FileInputStream(filePath);
            pro.load(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return pro;
    }
}


    

