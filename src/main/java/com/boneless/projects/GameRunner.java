package com.boneless.projects;

import com.boneless.projects.utils.JsonFile;

import javax.swing.*;
import java.util.Arrays;

public class GameRunner {
    public static void main(String[] args){
        JsonFile.writeToArray("uh.json", "mainKey", "this is data");
    }
}