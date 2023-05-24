package vitalii.leshchenko.services.read;

import vitalii.leshchenko.entities.LearnedWord;

import java.util.List;

public interface Read {
  public List<String[]> read();
  public List<LearnedWord> getLearnedWordList();
}
