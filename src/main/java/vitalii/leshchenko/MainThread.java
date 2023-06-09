package vitalii.leshchenko;

import vitalii.leshchenko.entities.LearnedWord;
import vitalii.leshchenko.entities.RangToLearn;

import java.util.*;
import java.util.stream.Collectors;

public class MainThread {
  List<LearnedWord> listWords;
  Scanner scanner;

  public MainThread(List<LearnedWord> listWords) {
    if (listWords == null) {
      listWords = new LinkedList<>();
    }
    Collections.shuffle(new LinkedList<>());
    this.listWords = listWords;
    scanner = new Scanner(System.in);
  }

  public void Run() {
    System.out.println("Hello.");
    String text;
    Random random = new Random();
    LinkedList<LearnedWord> linkedList = listWords.stream()
        // remove "learned" words from list
        .filter(item -> item.getRangToLearn().equals(RangToLearn.needToLearn) ||
        // remove every second "needToRepeat" words from list
                       (item.getRangToLearn().equals(RangToLearn.needToRepeat) & random.nextBoolean()))
        .collect(Collectors.toCollection(LinkedList::new));
    Collections.shuffle(linkedList);

    int iteratorTest = 0;
    while (linkedList.size() > 0) {
      clearConsole();
      LearnedWord learnedWord = linkedList.remove();

      iteratorTest++;
      System.out.println("test : " + iteratorTest + " || tests left : " + linkedList.size());
      System.out.println(learnedWord.getUkTranslation() + " - " + learnedWord.getEngMeaning());
      text = scanner.nextLine();
      if (text.equals("-close")) break;
      if (text.equals("-help")) {
        System.out.println("write \"-close\" or \n write an answer");
        text = scanner.nextLine();
      }
        learnedWord.setRightAnswerCount(learnedWord.getRightAnswerCount() + 1);
        while (!text.equalsIgnoreCase(learnedWord.getWord())) {
          learnedWord.setRightAnswerCount(learnedWord.getRightAnswerCount() - 1);
          System.out.println(learnedWord.getWord());
          linkedList.add(learnedWord);
          text = scanner.nextLine();
      }
      learnedWord.checkLearned();
    }
  }

  public static void clearConsole() {
    try {
      final String os = System.getProperty("os.name");
      if (os.contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        Runtime.getRuntime().exec(new String[]{"clear"});
      }
    } catch (final Exception e) {
      System.out.println("Failed to clear console: " + e);
    }
  }
}
