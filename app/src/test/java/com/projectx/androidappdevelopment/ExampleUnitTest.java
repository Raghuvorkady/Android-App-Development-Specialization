package com.projectx.androidappdevelopment;

import android.content.Context;
import android.widget.Button;

import androidx.test.platform.app.InstrumentationRegistry;

import com.projectx.androidappdevelopment.Tabs.tab1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Mock
    Button introButton;

    @Test
    public void introBtnStateCheckerPasses() {

        tab1 tab1Obj = new tab1();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertTrue(tab1Obj.introBtnStateCheck(true, appContext));
    }
    @Test
    public void introBtnStateCheckerFails() {

        tab1 tab1Obj = new tab1();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertTrue(tab1Obj.introBtnStateCheck(false, appContext));
    }
}