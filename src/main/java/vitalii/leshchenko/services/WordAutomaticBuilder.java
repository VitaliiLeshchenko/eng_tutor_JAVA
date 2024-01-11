package vitalii.leshchenko.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import vitalii.leshchenko.entities.*;

public class WordAutomaticBuilder {

    public static void main(String[] args) {
        System.out.println(new WordAutomaticBuilder().getLearnedWord("appropriate").toString());
    }

    public LearnedWord getLearnedWord(String word) {
        String apiKey = "f682040de4msha09b8a74730b45dp1bfd64jsn43269ee6b9f1";
        Gson gson = new Gson();

        try {
            HttpClient client = HttpClient.newHttpClient();
            URI uri = URI.create("https://wordsapiv1.p.rapidapi.com/words/" + word + "/definitions");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("X-RapidAPI-Host", "wordsapiv1.p.rapidapi.com")
                    .header("X-RapidAPI-Key", apiKey)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            LearnedWordWordApiDTO wordApiDTO = gson.fromJson(response.body(),
                    LearnedWordWordApiDTO.class);

            HttpRequest requestG = HttpRequest.newBuilder()
                    .uri(URI.create("https://google-translator9.p.rapidapi.com/v2"))
                    .header("content-type", "application/json")
                    .header("X-RapidAPI-Key", apiKey)
                    .header("X-RapidAPI-Host", "google-translator9.p.rapidapi.com")
                    .method("POST", HttpRequest.BodyPublishers.ofString("{\"q\": \"" + word + "\"," +
                            "\"source\": \"en\"," +
                            "\"target\": \"uk\"," +
                            "\"format\": \"text\"" +
                            "}"))
                    .build();
            HttpResponse<String> responseG = HttpClient.newHttpClient().send(requestG,
                    HttpResponse.BodyHandlers.ofString());

            LearnedWordTranslationApiDTO translationApiDTO = gson.fromJson(responseG.body(),
                    LearnedWordTranslationApiDTO.class);

            LearnedWord learnedWord = new LearnedWord(null,
                    word,
                    wordApiDTO.getDefinitions().get(0).getPartOfSpeech(),
                    translationApiDTO.getData().getTranslations().get(0).getTranslatedText(),
                    wordApiDTO.getDefinitions().get(0).getDefinition(),
                    RangToLearn.needToLearn,
                    0
                    );

            return learnedWord;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
