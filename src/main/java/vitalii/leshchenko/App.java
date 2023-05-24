package vitalii.leshchenko;

import com.opencsv.bean.MappingStrategy;
import vitalii.leshchenko.entities.LearnedWord;
import vitalii.leshchenko.services.read.FreeCSVReader;
import vitalii.leshchenko.services.read.Read;
import vitalii.leshchenko.services.write.FreeCSVWriter;

import java.util.List;

public class App 
{
    public static void main( String[] args ) {
        String fileReadPath = "C:/Users/Leshchenko/IdeaProjects/eng_tutor_JAVA/src/main/resources/vocabularyWRITE.csv";
        String fileWritePath = "C:/Users/Leshchenko/IdeaProjects/eng_tutor_JAVA/src/main/resources/vocabularyWRITE.csv";
        Read reader = new FreeCSVReader(fileReadPath);
        List<LearnedWord> listWords = reader.getLearnedWordList();
        for (LearnedWord learnedWord : listWords) {
            System.out.println(learnedWord.toString());
        }
        FreeCSVWriter writer = new FreeCSVWriter(fileWritePath);
        writer.write(listWords);
    }
}
