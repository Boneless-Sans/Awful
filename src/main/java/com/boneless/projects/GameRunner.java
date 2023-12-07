package com.boneless.projects;

import com.boneless.code.neighborhood.Painter;
import com.boneless.code.neighborhood.PainterListener;
import com.boneless.code.neighborhood.PainterPlus;
import com.boneless.code.u3test.q5.Bat;
import com.boneless.code.u3test.q5.Bird;
import com.boneless.code.u3test.q5.FlyingAnimal;
import com.boneless.projects.utils.JsonFile;

import javax.swing.*;
import java.util.Arrays;

public class GameRunner {
    public static void main(String[] args) {
        PainterPlus lisa = new PainterPlus();
        lisa.paint("red");
        lisa.move();
        lisa.paint("red");
        lisa.addPaintBucket(5,0,10);
        lisa.runToPaint();
        lisa.move();
        lisa.paint("red");
    }
}
