package vitalii.leshchenko;

import vitalii.leshchenko.entities.LearnedWord;
import vitalii.leshchenko.services.read.FreeCSVReader;
import vitalii.leshchenko.services.read.CSVReader;
import vitalii.leshchenko.services.write.CSVWriter;
import vitalii.leshchenko.services.write.FreeCSVWriter;

import java.util.List;

public class App 
{
    public static void main( String[] args ) {
        String fileReadPath = "C:/Users/Leshchenko/IdeaProjects/eng_tutor_JAVA/src/main/resources/vocabularyWRITE.csv";
        String fileWritePath = "C:/Users/Leshchenko/IdeaProjects/eng_tutor_JAVA/src/main/resources/vocabularyWRITE.csv";
        CSVReader reader = new FreeCSVReader(fileReadPath);
        List<LearnedWord> listWords = reader.getList();
        MainThread mainThread = new MainThread(listWords);

        mainThread.Run();

        CSVWriter<LearnedWord> writer = new FreeCSVWriter(fileWritePath);
        writer.write(listWords);
    }
}
