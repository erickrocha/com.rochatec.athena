package com.rochatec.mongo.file;

import com.rochatec.mongo.exception.FileConfigException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by epr on 07/10/14.
 */
public class FileUtils {

    public static File findConfigFile(String fileName){
        String classPath = System.getProperty("java.class.path");
        String[] pathElements = classPath.split(System
                .getProperty("path.separator"));
        for (String element : pathElements){
            if (element.endsWith("classes")){
                File file = new File(element);
                return findDirectory(file,fileName);
            }
        }
        return null;
    }

    private static File findDirectory(File begin,String directoryName){
        if (begin.isFile() && begin.getName().equals(directoryName)){
            return begin;
        }else if (begin.isDirectory()){
            for (File file : begin.listFiles()){
               return findDirectory(file,directoryName);
            }
        }else {
            return null;
        }
      return null;
    }

    public static ResourceBundle getMongoConfig() throws FileConfigException{
        File file = findConfigFile("mongoconfig.properties");
        if (file == null){
            throw  new FileConfigException();
        }
        try {
            ResourceBundle bundle = new PropertyResourceBundle(new FileInputStream(file));
            return bundle;
        }catch (FileNotFoundException ex){
            throw  new FileConfigException(ex.getCause());
        }catch (IOException ex){
            throw  new FileConfigException(ex.getCause());
        }
    }
}
