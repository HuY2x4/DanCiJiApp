package com.example.danciji1_0;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hyx on 2018/6/26.
 */
public class TDengluTest {




    TDenglu ttDenglu=new TDenglu();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void teDenglu() throws Exception {
        assertEquals(true,ttDenglu.teDenglu("Hyxzucc","123456"));
        assertEquals(true,ttDenglu.teDenglu("admin","admin"));
        assertEquals(true,ttDenglu.teDenglu("test1","123"));

    }

    @Test
    public void count() throws Exception {
        ttDenglu.count(2);
        ttDenglu.count(3);
        ttDenglu.count(10);
        ttDenglu.count(30);


    }
}