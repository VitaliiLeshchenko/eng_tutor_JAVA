package vitalii.leshchenko;

import vitalii.leshchenko.entities.LearnedWord;
import vitalii.leshchenko.services.WordAutomaticBuilder;
import vitalii.leshchenko.services.read.FreeCSVReader;
import vitalii.leshchenko.services.read.CSVReader;
import vitalii.leshchenko.services.write.CSVWriter;
import vitalii.leshchenko.services.write.FreeCSVWriter;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App 
{
    public static void main( String[] args ) {
      String dbFilePath = "src/main/resources/vocabularyWRITE.csv";
      Scanner scanner = new Scanner(System.in);
      try {
        String jarPath = new File(App.class.getProtectionDomain().getCodeSource().getLocation()
            .toURI()).getParent();
        ArrayList<String> files = new App().listFilesUsingJavaIO(jarPath);
        if (files.size() > 0) {
          int i;
          for (i = 0; i < files.size(); i++) {
            System.out.println(i + ". " + files.get(i));
          }
          int choseDB = Integer.parseInt(scanner.nextLine());
          dbFilePath = files.get(choseDB);
        }
      } catch (URISyntaxException e) {
        throw new RuntimeException(e);
      } catch (NumberFormatException | IndexOutOfBoundsException e) {
        System.out.println("You typed wrong number. \n" +
            "try other times");
        System.exit(0);
      }
      CSVReader reader = new FreeCSVReader(dbFilePath);
      List<LearnedWord> listWords = reader.getList();


      System.out.println("If you want to add new words press \"-add\" else press enter. Press \"q\" to stop");
      String text = scanner.nextLine();
      if (text.equals("-add")) {
        WordAutomaticBuilder wordAutomaticBuilder = new WordAutomaticBuilder();
        while (true) {
          text = scanner.nextLine();
          if (text.equals("q")) break;
          if (listWords.stream().map(LearnedWord::getWord).anyMatch(w -> w.equals("listWords"))) {
            System.out.println("This word is already in dictionary. ");
            break;
          }
          LearnedWord learnedWord = wordAutomaticBuilder.getLearnedWord(text);
          System.out.println(learnedWord.toString());
          listWords.add(learnedWord);
        }
      }

      MainThread mainThread = new MainThread(listWords);
      String debug = null;
      if (args != null && args.length > 0) {
        debug = "debug";
      }
      mainThread.Run(debug);

      CSVWriter<LearnedWord> writer = new FreeCSVWriter(dbFilePath);
      writer.write(listWords);
    }

  public ArrayList<String> listFilesUsingJavaIO(String dir) {
    return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
        .filter(file -> !file.isDirectory())
        .map(File::getAbsolutePath)
        .filter(filePath -> filePath.endsWith("csv"))
        .collect(Collectors.toCollection(ArrayList::new));
  }
}
