package com.rochatec.pos.athena.utils;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.List;

/**
 * Created by epr on 01/07/15.
 */
public class FileLocator {

    public static String getPath(String pathBase,String sufix,String ignoreSufix){
        File fileBegin = new File(pathBase);
        List<File> files = Lists.newArrayList();
        search(fileBegin,files,ignoreSufix);
        for (File file : files){
            if (file.getAbsolutePath().contains(sufix)){
                return file.getAbsolutePath();
            }
        }
        return null;
    }

    private static void search(File file,List<File> files,String ignoreSufix){
        if (file.isDirectory() && (!file.getAbsolutePath().endsWith(ignoreSufix))){
            for (File currentFile : file.listFiles()){
                search(currentFile,files,ignoreSufix);
            }
        }else {
            files.add(file);
        }
    }
}
