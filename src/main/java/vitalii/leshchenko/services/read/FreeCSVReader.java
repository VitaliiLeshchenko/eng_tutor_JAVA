package vitalii.leshchenko.services.read;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import vitalii.leshchenko.entities.LearnedWord;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FreeCSVReader implements CSVReader {
  private String filePath;
  private List<LearnedWord> learnedWordList;

  public List<LearnedWord> getList() {
    return learnedWordList;
  }

  public FreeCSVReader(String filePath) {
    this.filePath = filePath;
    try (FileReader reader = new FileReader(this.filePath)) {
      HeaderColumnNameMappingStrategy<LearnedWord> strategy = new HeaderColumnNameMappingStrategy<>();
      strategy.setType(LearnedWord.class);

      CsvToBean<LearnedWord> csvToBean = new CsvToBeanBuilder<LearnedWord>(reader)
          .withMappingStrategy(strategy)
          .withSeparator(';')
          .build();
      this.learnedWordList = csvToBean.parse();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
