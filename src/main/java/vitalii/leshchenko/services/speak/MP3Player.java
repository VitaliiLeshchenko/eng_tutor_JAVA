package vitalii.leshchenko.services.speak;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MP3Player implements Speaker{

    String executionPath;

    public MP3Player(String path) {
        executionPath = path;
    }

    //"C:/Users/Leshchenko/IdeaProjects/eng_tutor_JAVA/src/main/resources/test.mp3"

    public void speak(String text) {
        String filePath = executionPath + "\\sounds\\" + text + ".mp3";

        try {
            FileInputStream fis = new FileInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            AdvancedPlayer player = new AdvancedPlayer(bis);
            // Play the MP3 file
            player.play();
        } catch (FileNotFoundException e) {
            runCmd_GetMp3FromAWSCLI(text, executionPath + "\\sounds");
            //this.speak(text);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public static void runCmd_GetMp3FromAWSCLI(String text, String executionPath) {
        String processedCommand = "aws polly synthesize-speech ^" +
                "    --output-format mp3 ^" +
                "    --voice-id Joanna ^" +
                "    --text \"" + text +  "\" ^" +
                "    \"" + text +  "\".mp3";
        System.out.println(processedCommand);
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", processedCommand)
                        .directory(new File(executionPath))
                        .inheritIO()
                        .start()
                        .waitFor();
            } else {
                Runtime.getRuntime().exec(new String[]{"clear"});
            }
        } catch (final Exception e) {
            System.out.println("Failed run command: " + e);
        }
    }
}
