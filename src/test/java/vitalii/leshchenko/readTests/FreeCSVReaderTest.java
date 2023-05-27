package vitalii.leshchenko.readTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import vitalii.leshchenko.entities.LearnedWord;
import vitalii.leshchenko.services.read.FreeCSVReader;

import java.util.List;

public class FreeCSVReaderTest {
  @Test
  public void CreateInstanceTest(){
    FreeCSVReader reader = new FreeCSVReader("C:/Users/Leshchenko/IdeaProjects/eng_tutor_JAVA/src/main/resources/vocabularyWRITE.csv");
    Assertions.assertNotNull(reader);
  }

  @Test
  public void CreateGetListBeans(){
    List<LearnedWord> learnedWordList;
    FreeCSVReader reader = new FreeCSVReader("C:/Users/Leshchenko/IdeaProjects/eng_tutor_JAVA/src/main/resources/vocabularyWRITE.csv");
    learnedWordList = reader.getList();
    Assertions.assertNotNull(learnedWordList);
    Assertions.assertNotNull(learnedWordList.get(0).getWord());
  }
}
