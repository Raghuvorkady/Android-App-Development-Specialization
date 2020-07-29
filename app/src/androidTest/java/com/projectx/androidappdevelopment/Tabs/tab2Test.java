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
public class tab2Test {
    @Test
    public void onViewCreated() {
        tab2 tab2Object1 = new tab2();
        tab2Object1.getActivity();
    }

    @Test
    public void introBtnStateCheckerPasses() {
        //comment on both introButton and introText for running the test
        tab2 tab2Obj = new tab2();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertTrue(tab2Obj.introBtnStateCheck(true, appContext));
    }

    @Test
    public void introBtnStateCheckerFails() {
        //comment on both introButton and introText for running the test
        tab2 tab2Obj = new tab2();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertFalse(tab2Obj.introBtnStateCheck(false, appContext));
    }
}