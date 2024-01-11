package vitalii.leshchenko;

import vitalii.leshchenko.entities.LearnedWord;
import vitalii.leshchenko.entities.RangToLearn;
import vitalii.leshchenko.services.speak.MP3Player;
import vitalii.leshchenko.services.speak.Speaker;

import java.io.File;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class MainThread {
  List<LearnedWord> listWords;
  Scanner scanner;
  Speaker mp3Speaker;
  String path;

  public MainThread(List<LearnedWord> listWords) {
    if (listWords == null) {
      listWords = new LinkedList<>();
    }
    Collections.shuffle(new LinkedList<>());
    this.listWords = listWords;
    scanner = new Scanner(System.in);

    try {
        path = new File(App.class.getProtectionDomain().getCodeSource().getLocation()
                .toURI()).getParent();
        mp3Speaker = new MP3Player(path);
    } catch (URISyntaxException e) {
        throw new RuntimeException(e);
    }
  }

  public void Run() {
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
      System.out.println("    " + learnedWord.getRangToLearn() + " - " + learnedWord.getRightAnswerCount());
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
        // Speak the text
        mp3Speaker.speak(learnedWord.getWord());
    }
  }

  public void addNewWords() {

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
