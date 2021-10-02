package com.example.dictionarymaster;

public class Word {
    private String word_target;
    private String word_explain;

    public void setWord_target(String newTarget) {
        this.word_target = newTarget;
    }

    public void setWord_explain(String newExplain) {
        this.word_explain = newExplain;
    }

    public String getWord_target() {
        return word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }
}
