package vitalii.leshchenko;

import vitalii.leshchenko.entities.LearnedWord;
import vitalii.leshchenko.entities.RangToLearn;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MainThread {
  List<LearnedWord> listWords;
  Scanner scanner;
//todo java.lang.NullPointerException: Cannot invoke "java.util.List.size()" because "list" is null
  public MainThread(List<LearnedWord> listWords) {
    Collections.shuffle(listWords);
    this.listWords = listWords;
    scanner = new Scanner(System.in);
  }

  public void Run() {
    System.out.println("Hello.");
    String text;
    Queue<LearnedWord> queue = listWords.stream()
        .filter(item ->
            item.getRangToLearn()
                .equals(RangToLearn.needToLearn)).collect(Collectors.toCollection(LinkedList::new));
    int iteratorTest = 0;
    while (queue.size() > 0) {
      clearConsole();
      LearnedWord learnedWord = queue.remove();

      iteratorTest++;
      System.out.println("test : " + iteratorTest + " || tests left : " + queue.size());
      System.out.println(learnedWord.getUkTranslation() + " - " + learnedWord.getEngMeaning());
      text = scanner.nextLine();
      learnedWord.setRightAnswerCount(learnedWord.getRightAnswerCount() + 1);
      while (!text.equalsIgnoreCase(learnedWord.getWord())) {
        learnedWord.setRightAnswerCount(learnedWord.getRightAnswerCount() - 1);
        System.out.println(learnedWord.getWord());
        queue.add(learnedWord);
        text = scanner.nextLine();
      }
    }
  }

  public static void clearConsole() {
    try {
      final String os = System.getProperty("os.name");
      if (os.contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        Runtime.getRuntime().exec("clear");
      }
    } catch (final Exception e) {
      System.out.println("Failed to clear console: " + e);
    }
  }
}
