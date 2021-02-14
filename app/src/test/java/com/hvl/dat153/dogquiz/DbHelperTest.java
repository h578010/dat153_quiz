package com.hvl.dat153.dogquiz;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class DbHelperTest extends TestCase {
    DbHelper db = new DbHelper(RuntimeEnvironment.application);

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testOnCreate() {
    }

    public void testOnUpgrade() {
    }

    @Test
    public void testAddQuestion() {
        int countBefore = db.getAllQuestions().size();
        Question q = new Question();
        db.addQuestion(q);
        int countAfter = db.getAllQuestions().size();
        assertEquals(countBefore + 1, countAfter);
        System.out.println("Count before: " + countBefore);
    }

    @Test
    public void testGetAllQuestions() {
        List<Question> dogs = db.getAllQuestions();
        assert(dogs.size() >= 6);

        int id = findDogId(dogs, "Golden Retriever");
        assertTrue(id != -1);
    }

    @Test
    public void testDeleteId() {
        List<Question> dogsBefore = db.getAllQuestions();
        int sizeBefore = dogsBefore.size();

        int id = findDogId(dogsBefore, "Golden Retriever");
        assert(id >= 0);

        db.deleteId(id);
        List<Question> dogsAfter = db.getAllQuestions();
        int sizeAfter = dogsAfter.size();

        assertTrue(findDogId(dogsAfter, "Golden Retriever") == -1);
        assertEquals(sizeBefore, sizeAfter+1);
    }

    private int findDogId(List<Question> dogs, String dogName) {
        Iterator<Question> it = dogs.iterator();
        while (it.hasNext()) {
            Question q = it.next();
            if (q.getCorrectOption().equals(dogName)) {
                return q.getId();
            }
        }
        return -1;
    }
}