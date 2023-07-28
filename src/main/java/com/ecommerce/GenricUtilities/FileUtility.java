package com.ecommerce.GenricUtilities;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class FileUtility {


    /**
     * @ this method is used for read the data from properties file
     * @author Ashish
     * @param key
     * @return
     * @throws FileNotFoundException
     */
    public String getDataFromFile(String key) throws FileNotFoundException {
    FileInputStream fis = new FileInputStream(IPathConstants.filePath);
    Properties properties = new Properties();
    return properties.getProperty(key);
}
}
