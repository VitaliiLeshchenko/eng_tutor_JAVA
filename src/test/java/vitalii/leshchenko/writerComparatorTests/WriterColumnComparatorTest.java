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
        comparator.compare("article".toUpperCase(), "word".toUpperCase()));
    Assertions.assertEquals(1,
        comparator.compare("word".toUpperCase(), "article".toUpperCase()));
    Assertions.assertEquals(0,
        comparator.compare("word".toUpperCase(), "word".toUpperCase()));
    Assertions.assertEquals(0,
        comparator.compare(null, "word".toUpperCase()));
    Assertions.assertEquals(0,
        comparator.compare(null, "wor".toUpperCase()));
    Assertions.assertEquals(0,
        comparator.compare(null, null));
  }
}
