package com.projectx.androidappdevelopment.Tabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.projectx.androidappdevelopment.Activities.MainActivity;
import com.projectx.androidappdevelopment.R;

import java.util.regex.Matcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasBackground;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class tab1Test {
    @Test
    public void onViewCreated() {
        tab1 tab1Object1 = new tab1();
        tab1Object1.getActivity();
    }

    @Test
    public void introBtnStateCheckerPasses() {
        //comment on both introButton and introText for running the test
        tab1 tab1Obj = new tab1();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertTrue(tab1Obj.introBtnStateCheck(true, appContext));
    }

    @Test
    public void introBtnStateCheckerFails() {
        //comment on both introButton and introText for running the test
        tab1 tab1Obj = new tab1();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertFalse(tab1Obj.introBtnStateCheck(false, appContext));
    }
}