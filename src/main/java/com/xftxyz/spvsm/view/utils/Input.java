package com.xftxyz.spvsm.view.utils;

import java.util.Scanner;

/**
 * 当有多个Scanner对象时，调用close()方法，会顺便关闭System.in对象，
 * 如果调用了其他Scanner对象的方法，会出现java.util.NoSuchElementException异常
 * 
 * 此类是对Scanner的简单封装，用于统一获取输入和关闭Scanner对象
 */
public class Input {
    private static Scanner input = new Scanner(System.in);

    public static void close() {
        input.close();
    }

    public static String nextLine() {
        return input.nextLine();
    }
}
