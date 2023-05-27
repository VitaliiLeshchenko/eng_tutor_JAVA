package vitalii.leshchenko.MainThreadTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vitalii.leshchenko.MainThread;
import vitalii.leshchenko.entities.LearnedWord;
import vitalii.leshchenko.entities.RangToLearn;
import vitalii.leshchenko.entities.WordClass;

import java.util.ArrayList;
import java.util.Arrays;

public class MainThreadTests {
  @Test
  public void createInstanceTest() {
    MainThread mainThread = new MainThread(new ArrayList<LearnedWord>());
    Assertions.assertNotNull(mainThread);
  }

  @Test
  public void createInstanceWithNullParamTest() {
    MainThread mainThread = new MainThread(null);
    Assertions.assertNotNull(mainThread);
  }

  @Test
  public void fullTest() {
    LearnedWord learnedWord1 = new LearnedWord(
        "an",
        "word",
        WordClass.noun,
        "Слово",
        "",
        RangToLearn.needToLearn,
        0);
    LearnedWord learnedWord2 = new LearnedWord(
        "an",
        "word",
        WordClass.noun,
        "Слово",
        "",
        RangToLearn.needToLearn,
        0);
    LearnedWord learnedWord3 = new LearnedWord(
        "an",
        "word",
        WordClass.noun,
        "Слово",
        "",
        RangToLearn.needToLearn,
        0);
    MainThread mainThread = new MainThread(
        new ArrayList<LearnedWord>(Arrays.asList(learnedWord1, learnedWord2, learnedWord3))
    );
    Assertions.assertNotNull(mainThread);
  }
}
