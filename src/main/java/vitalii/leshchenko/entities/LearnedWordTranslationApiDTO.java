package vitalii.leshchenko.entities;

import java.util.List;

/*
    created by GPT
*/
public class LearnedWordTranslationApiDTO {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TranslationData{" +
                "data=" + data +
                '}';
    }

    public static class Data {
        private List<Translation> translations;

        public List<Translation> getTranslations() {
            return translations;
        }

        public void setTranslations(List<Translation> translations) {
            this.translations = translations;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "translations=" + translations +
                    '}';
        }
    }

    public static class Translation {
        private String translatedText;

        public String getTranslatedText() {
            return translatedText;
        }

        public void setTranslatedText(String translatedText) {
            this.translatedText = translatedText;
        }

        @Override
        public String toString() {
            return "Translation{" +
                    "translatedText='" + translatedText + '\'' +
                    '}';
        }
    }
}
