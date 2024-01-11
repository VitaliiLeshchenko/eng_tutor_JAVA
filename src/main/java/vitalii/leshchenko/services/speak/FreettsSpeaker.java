package vitalii.leshchenko.services.speak;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreettsSpeaker implements Speaker {
    VoiceManager voiceManager;
    Voice voice;
    public FreettsSpeaker() {
        System.setProperty("freetts.voices",
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        // Create a VoiceManager
        voiceManager = VoiceManager.getInstance();
        // Use the default voice
        voice = voiceManager.getVoice("kevin16");
        if (voice == null) {
           System.err.println("Cannot find a voice named kevin16. Please check available voices.");
           System.exit(1);
        }
        // Allocate the resources for the voice
        voice.allocate();
    }
    @Override
    public void speak(String message) {
              voice.speak(message);
    }

    public void voiceDeallocate() {
        voice.deallocate();
    }
}
