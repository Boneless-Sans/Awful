package com.boneless.projects.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Lockout {
    public static void lock(String file){
        if(!Files.exists(Paths.get(System.getProperty("user.home") + "/" + file + ".txt"))){
            File zeFile = new File(System.getProperty("user.home") + "/" + file + ".txt");
            try{
                zeFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public static boolean checkFile(String file){
        //defaults to users home folder
        return Files.exists(Paths.get(System.getProperty("user.home") + "/" + file + ".txt"));
    }
}
