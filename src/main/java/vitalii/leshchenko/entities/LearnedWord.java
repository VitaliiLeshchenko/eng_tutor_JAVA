package vitalii.leshchenko.entities;

import com.opencsv.bean.CsvBindByName;

public class LearnedWord {
  private static final int MARKER_LEARNED_RIGHT_ANSWERS_COUNT = 20;
  private static final int MARKER_NEEDTOREPEAT_RIGHT_ANSWERS_COUNT = 15;
  @CsvBindByName(column = "article")
  private String article;
  @CsvBindByName(column = "word", required = true)
  private String word;
  @CsvBindByName(column = "wordClass", required = true)
  private WordClass wordClass;
  @CsvBindByName(column = "ukTranslation", required = true)
  private String ukTranslation;
  @CsvBindByName(column = "engMeaning")
  private String engMeaning;
  @CsvBindByName(column = "rangToLearn", required = true)
  private RangToLearn rangToLearn;
  @CsvBindByName(column = "rightAnswerCount")
  private int rightAnswerCount;
  public LearnedWord() {
  }

  public LearnedWord(String article, String word, WordClass wordClass, String ukTranslation, String engMeaning, RangToLearn rangToLearn, int rightAnswerCount) {
    this.article = article;
    this.word = word;
    this.wordClass = wordClass;
    this.ukTranslation = ukTranslation;
    this.engMeaning = engMeaning;
    this.rangToLearn = rangToLearn;
    this.rightAnswerCount = rightAnswerCount;
  }

  public String getArticle() {
    return article;
  }

  public void setArticle(String article) {
    this.article = article;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public WordClass getWordClass() {
    return wordClass;
  }

  public void setWordClass(WordClass wordClass) {
    this.wordClass = wordClass;
  }

  public String getUkTranslation() {
    return ukTranslation;
  }

  public void setUkTranslation(String ukTranslation) {
    this.ukTranslation = ukTranslation;
  }

  public String getEngMeaning() {
    return engMeaning;
  }

  public void setEngMeaning(String engMeaning) {
    this.engMeaning = engMeaning;
  }

  public RangToLearn getRangToLearn() {
    return rangToLearn;
  }

  public void setRangToLearn(RangToLearn rangToLearn) {
    this.rangToLearn = rangToLearn;
  }

  public int getRightAnswerCount() {
    return rightAnswerCount;
  }

  public void setRightAnswerCount(int rightAnswerCount) {
    this.rightAnswerCount = rightAnswerCount;
  }

  public void checkLearned() {
    if (this.getRightAnswerCount() > MARKER_NEEDTOREPEAT_RIGHT_ANSWERS_COUNT) {
      this.rangToLearn = RangToLearn.needToRepeat;
    }
    if (this.getRightAnswerCount() > MARKER_LEARNED_RIGHT_ANSWERS_COUNT) {
      this.rangToLearn = RangToLearn.learned;
    }
  }

  @Override
  public String toString() {
    return "LearnedWord{" +
        ", word='" + word + '\'' + "\n" +
        ", wordClass=" + wordClass + "\n" +
        ", ukTranslation='" + ukTranslation + '\'' + "\n" +
        ", engMeaning='" + engMeaning + '\'' + "\n" +
        ", rightAnswerCount=" + rightAnswerCount + "\n" +
        '}';
  }
}