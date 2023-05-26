package vitalii.leshchenko.services.write;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WriteColumnComparator implements Comparator<String> {
  private Map<String, Integer> mapColumn;
  public WriteColumnComparator() {
    mapColumn = new HashMap<>();
    mapColumn.put("article".toUpperCase(), 1);
    mapColumn.put("word".toUpperCase(), 2);
    mapColumn.put("wordClass".toUpperCase(), 3);
    mapColumn.put("ukTranslation".toUpperCase(), 4);
    mapColumn.put("engMeaning".toUpperCase(), 5);
    mapColumn.put("rangToLearn".toUpperCase(), 6);
    mapColumn.put("rightAnswerCount".toUpperCase(), 6);
  }

  @Override
  public int compare(String obj1, String obj2) {
    if (obj1 == null || obj2 == null) {
      return 0;
    }
    obj1 = obj1.toUpperCase();
    obj2 = obj2.toUpperCase();
    if (this.mapColumn.get(obj1) == null || this.mapColumn.get(obj2) == null) {
      return 0;
    }
    if (this.mapColumn.get(obj1).equals(this.mapColumn.get(obj2))) {
      return 0;
    }
    if (this.mapColumn.get(obj1) == null ||
        this.mapColumn.get(obj1) < this.mapColumn.get(obj2)) {
      return -1;
    }
    if (this.mapColumn.get(obj2) == null ||
        this.mapColumn.get(obj2) < this.mapColumn.get(obj1)) {
      return 1;
    }
    return obj1.compareTo(obj2);
  }
}