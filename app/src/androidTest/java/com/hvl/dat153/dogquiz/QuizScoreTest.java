package com.hvl.dat153.dogquiz;

import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static org.hamcrest.Matchers.equalTo;


@RunWith(AndroidJUnit4.class)
public class QuizScoreTest {

    @Rule
    public ActivityTestRule<QuizActivity> scoreTestRule = new ActivityTestRule<>(QuizActivity.class);

    @Test
    public void checkScores() {

        // First question, wrong answer is selected
        onView(withId(R.id.radio3)).perform(click());
        // Answer is submitted with button
        onView(withId(R.id.button_answer)).perform(click());
        // Check the score
        assertThat(scoreTestRule.getActivity().getScore(), equalTo(0));
        // Click button to move to next question
        onView(withId(R.id.button_answer)).perform(click());
        // Select a wrong answer
        onView(withId(R.id.radio2)).perform(click());
        // Answer is submitted with button
        onView(withId(R.id.button_answer)).perform(click());
        // Check the score
        assertThat(scoreTestRule.getActivity().getScore(), equalTo(0));
        // Click button to move to next question
        onView(withId(R.id.button_answer)).perform(click());
        // Select the right answer (which is always radio button 1, will fix this later)
        onView(withId(R.id.radio1)).perform(click());
        // Answer is submitted with button and score will be updated
        onView(withId(R.id.button_answer)).perform(click());
        // Check the score
        assertThat(scoreTestRule.getActivity().getScore(), equalTo(1));
    }
}
