package vitalii.leshchenko.services.write;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.HeaderColumnNameMappingStrategyBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import vitalii.leshchenko.entities.LearnedWord;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class FreeCSVWriter {
  private final String filePath;
  public FreeCSVWriter(String filePath) {
    this.filePath = filePath;
  }
  public void write(List<LearnedWord> beans) {
    Writer writer = null;
    try {
      writer = new FileWriter(filePath);

      HeaderColumnNameMappingStrategy<LearnedWord> strategy = new HeaderColumnNameMappingStrategyBuilder<LearnedWord>().build();
      strategy.setType(LearnedWord.class);
      strategy.setColumnOrderOnWrite(new WriteColumnComparator());

      StatefulBeanToCsv<LearnedWord> beanToCsv = new StatefulBeanToCsvBuilder<LearnedWord>(writer)
          .withMappingStrategy(strategy)
          .withSeparator(';')
          .withApplyQuotesToAll(false)
          .build();

      beanToCsv.write(beans);
      writer.close();

    } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
      throw new RuntimeException(e);
    }
  }
}
