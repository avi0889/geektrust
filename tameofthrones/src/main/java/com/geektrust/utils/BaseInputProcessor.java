package com.geektrust.utils;


import com.geektrust.exception.InvalidInputException;
import com.geektrust.exception.NoSuchKingdomException;


public abstract class BaseInputProcessor {

    public boolean isQuestion(String input) {
        return isRulerQuestion(input) || isAlliesQuestion(input);
    }

    public boolean isRulerQuestion(String question) {
        return question.equals(Constants.rulerQuestion);
    }

    public boolean isAlliesQuestion(String question) {
        return question.startsWith(Constants.alliesQuestion);
    }

    abstract public void processInput(String input) throws InvalidInputException, NoSuchKingdomException;
}
