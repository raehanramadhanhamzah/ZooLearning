package com.example.zoolearning;

import java.util.ArrayList;

public class Question {
    private final String question;
    private final String option1;
    private final String option2;
    private final String correctAnswer;

    public Question(String question, String option1, String option2, String correctAnswer) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.correctAnswer = correctAnswer;
    }


    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

}

