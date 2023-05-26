package vitalii.leshchenko.writerComparatorTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vitalii.leshchenko.services.write.WriteColumnComparator;

import java.util.Comparator;

public class WriterColumnComparatorTest {

  @Test
  public void compareTests(){
    Comparator<String> comparator = new WriteColumnComparator();
    Assertions.assertEquals(-1,
        comparator.compare("article", "word"));
    Assertions.assertEquals(1,
        comparator.compare("word", "article"));
    Assertions.assertEquals(0,
        comparator.compare("word", "word"));
    Assertions.assertEquals(0,
        comparator.compare(null, "word"));
    Assertions.assertEquals(0,
        comparator.compare(null, "wor"));
    Assertions.assertEquals(0,
        comparator.compare(null, null));
  }
}
