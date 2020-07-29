package com.projectx.androidappdevelopment.Tabs;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class tab3Test {

    @Test
    public void introBtnStateCheckerPasses() {
        //comment on both introButton and introText for running the test
        tab3 tab3Obj = new tab3();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertTrue(tab3Obj.introBtnStateCheck(true, appContext));
    }

    @Test
    public void introBtnStateCheckerFails() {
        //comment on both introButton and introText for running the test
        tab3 tab3Obj = new tab3();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertFalse(tab3Obj.introBtnStateCheck(false, appContext));
    }
}