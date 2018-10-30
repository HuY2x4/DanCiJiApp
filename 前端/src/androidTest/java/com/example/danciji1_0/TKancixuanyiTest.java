package com.example.danciji1_0;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hyx on 2018/6/26.
 */
public class TKancixuanyiTest {
    TKancixuanyi TK =new TKancixuanyi();
    @Test
    public void kancixuanyi() throws Exception {
        TK.kancixuanyi("number");
        TK.kancixuanyi("中文");
        TK.kancixuanyi("chaojichangdedanci");
        TK.kancixuanyi("");
        TK.kancixuanyi("123456");
    }

}