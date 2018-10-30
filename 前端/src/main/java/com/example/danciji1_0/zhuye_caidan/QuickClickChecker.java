package com.example.danciji1_0.zhuye_caidan;

/**
 * Created by hyx on 2018/6/26.
 */

public class QuickClickChecker {
    private int threshold;
    private long lastClickTime = 0;

    public QuickClickChecker(int threshold) {
        this.threshold = threshold;
    }

    public boolean isQuick() {
        boolean isQuick = System.currentTimeMillis() - lastClickTime <= threshold;
        lastClickTime = System.currentTimeMillis();
        return isQuick;
    }
}