package vitalii.leshchenko.entities;

import java.util.List;

public class LearnedWordWordApiDTO {
    String word;
    List<DefinitionsDTO> definitions;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<DefinitionsDTO> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<DefinitionsDTO> definitions) {
        this.definitions = definitions;
    }

    public class DefinitionsDTO {
        String definition;
        WordClass partOfSpeech;

        public String getDefinition() {
            return definition;
        }

        public void setDefinition(String definition) {
            this.definition = definition;
        }

        public WordClass getPartOfSpeech() {
            return partOfSpeech;
        }

        public void setPartOfSpeech(WordClass partOfSpeech) {
            this.partOfSpeech = partOfSpeech;
        }
    }
}
