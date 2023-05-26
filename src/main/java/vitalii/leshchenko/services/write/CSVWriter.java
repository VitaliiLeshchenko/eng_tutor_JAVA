package vitalii.leshchenko.services.write;

import java.util.List;

public interface CSVWriter<E> {
  public void write(List<E> list);
}
