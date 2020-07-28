package com.projectx.androidappdevelopment.Tabs;

import org.junit.Test;

import static org.junit.Assert.*;

public class tab1Test {

    @Test
    public void onViewCreated() {
    }
    @Test
    public void introBtnStateCheckerPasses() {

        tab1 tab1Obj = new tab1();

        assertTrue(tab1Obj.introBtnStateCheck(true));
    }
    @Test
    public void introBtnStateCheckerFails() {

        tab1 tab1Obj = new tab1();

        assertTrue(tab1Obj.introBtnStateCheck(false));
    }
}