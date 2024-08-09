package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {
    static Properties pro = null;

    public static Properties readPropertiesFile(String filePath) throws IOException {
        try {
            pro = new Properties();
            FileInputStream file = new FileInputStream(filePath);
            pro.load(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return pro;
    }

    public static String getHDFCErgoUserID() {
        String userID = pro.getProperty("Hdfc_Ergo_User_Id");
        if (userID != null) {
            return userID;
        } else {
            throw new RuntimeException("HDFC Ergo userID not specified in the Credentials file");
        }
    }

    public static String getHDFCErgoPassword() {
        String password = pro.getProperty("Hdfc_Ergo_Password");
        if (password != null) {
            return password;
        } else {
            throw new RuntimeException("HDFC Ergo Password not specified in the Credentials file");
        }
    }

    public static String getSaharaLifeUserId() {
        String userID = pro.getProperty("Sahara_Life_UserID");
        if (userID != null) {
            return userID;
        } else {
            throw new RuntimeException("Sahara Life Insurance company userID not specified in the Credentials file");
        }
    }

    public static String getSaharaLifePassword() {
        String password = pro.getProperty("Sahara_Life_Password");
        if (password != null) {
            return password;
        } else {
            throw new RuntimeException("Sahara Life Insurance company password not specified in the Credentials file");
        }
    }

    public static String getCallCenterUserID() {
        String userID = pro.getProperty("CallCenter_UserID");
        if (userID != null) {
            return userID;
        } else {
            throw new RuntimeException("Call Center userID not specified in the Credentials file");
        }
    }

    public static String getCallCenterPassword() {
        String password = pro.getProperty("CallCenter_Password");
        if (password != null) {
            return password;
        } else {
            throw new RuntimeException("Call Center password not specified in the Credentials file");
        }
    }

    public static String getUnitedIndiaInsUserID() {
        String userID = pro.getProperty("United_India_UserID");
        if (userID != null) {
            return userID;
        } else {
            throw new RuntimeException("United India insurance company userID not specified in the Credentials file");
        }
    }

    public static String getUnitedIndiaInsPassword() {
        String password = pro.getProperty("United_India_Password");
        if(password != null){
            return password;
        }
        else{
            throw new RuntimeException("United India insurance company password not specified in the Credentials file");
        }
    }

    public static String getGodDigitUserID() {
        String userID = pro.getProperty("God_Digit_Username");
        if (userID != null) {
            return userID;
        } else {
            throw new RuntimeException("God Digit userID not specified in the Credentials file");
        }
    }

    public static String getGodDigitPassword() {
        String password = pro.getProperty("God_Digit_Password");
        if (password != null) {
            return password;
        } else {
            throw new RuntimeException("HDFC Ergo Password not specified in the Credentials file");
        }
    }
}


    

