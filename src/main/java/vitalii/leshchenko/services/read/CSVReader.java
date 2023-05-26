package vitalii.leshchenko.services.read;

import vitalii.leshchenko.entities.LearnedWord;

import java.util.List;

public interface CSVReader {
  public List<LearnedWord> read();
}
