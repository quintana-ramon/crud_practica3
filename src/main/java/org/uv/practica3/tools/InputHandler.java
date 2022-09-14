package org.uv.practica3.tools;

import java.util.Scanner;

/**
 *
 * @author qinux
 */
public class InputHandler {

    public int isInt(String message) {
        Scanner read = new Scanner(System.in);
        System.out.print(message);
        Integer i = Integer.parseInt(read.next());
        int value = i.intValue();
        return value;
    }

    public String isStr(String message) {
        System.out.print(message);
        Scanner read = new Scanner(System.in);
        String value = read.next();
        if (read.hasNextLine()) {
            value += read.nextLine();
        }
        return value;
    }
    
    public String isLine(String message) {
        System.out.println(message);
        Scanner read = new Scanner(System.in);
        String value = read.nextLine();
        return value;
    }
    
    public float isFlt(String message) {
        Scanner read = new Scanner(System.in);
        System.out.print(message);
        Float f = Float.parseFloat(read.next());
        float value = f.floatValue();
        return value;
    }

    public double isDob(String message) {
        Scanner read = new Scanner(System.in);
        System.out.print(message);
        Double d = Double.parseDouble(read.next());
        double value = d.doubleValue();
        return value;
    }    
}
