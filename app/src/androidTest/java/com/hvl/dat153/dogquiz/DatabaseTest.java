package com.hvl.dat153.dogquiz;

import android.Manifest;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.GrantPermissionRule;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    @Rule
    public IntentsTestRule<AddActivity> addTestRule = new IntentsTestRule<AddActivity>(AddActivity.class);


    @Test
    public void addTest() {
        int countBefore = addTestRule.getActivity().getNoOfDogs();

        Matcher<Intent> expected = allOf(
                hasAction(Intent.ACTION_CHOOSER)
        );

        Instrumentation.ActivityResult result = createStub();
        intending(expected).respondWith(result);
        onView(withId(R.id.select_btn)).perform(click());
        intended(expected);
        onView(withId(R.id.editText)).perform(replaceText("Papillon"));
        onView(withId(R.id.add_btn)).perform(click());

        int countAfter = addTestRule.getActivity().getNoOfDogs();
        assertEquals(countBefore+1, countAfter);
    }

    private Instrumentation.ActivityResult createStub() {
        Resources resources = InstrumentationRegistry.getInstrumentation().getContext().getResources();
        Uri image = Uri.parse("android.resource://com.hvl.dat153.dogquiz/drawable/" + R.drawable.papillon);
        Intent result = new Intent();
        result.setData(image);
        return new Instrumentation.ActivityResult(Activity.RESULT_OK, result);
    }

}



