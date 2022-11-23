package com.example.learnotjihimba;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {
    public static List<QuestionsList> level1Questions() {
        final List<QuestionsList> questionsLists = new ArrayList<>();
        final QuestionsList question1 = new QuestionsList("Omaihi","Milk", "Coffe", "Bread", "Sugar", "Milk", "");
        final QuestionsList question2 = new QuestionsList("Moro","Good night", "Good evening", "Come", "Good morning", "Milk", "");
        final QuestionsList question3 = new QuestionsList("Ondjima","Goat", "Cow", "Giraffe", "Monkey", "Monkey","");
        final QuestionsList question4 = new QuestionsList("Omboroto","Juice", "Macoroni", "Bread", "Meat", "Bread", "");
        final QuestionsList question5 = new QuestionsList("Oruihi","Apple", "Rice", "Spice", "Banana", "Rice","");
        final QuestionsList question6 = new QuestionsList("Okaatu kovimariva", "Cow", "Cave", "Wallet","Rice", "Wallet", "");
        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);
        return questionsLists;
    }
    public static List<QuestionsList> level2Questions() {
        final List<QuestionsList> questionsLists = new ArrayList<>();
        final QuestionsList question1 = new QuestionsList("Omaihi","Milk", "Coffe", "Bread", "Sugar", "Milk", "");
        final QuestionsList question2 = new QuestionsList("Moro","Good night", "Good evening", "Come", "Good morning", "Milk", "");
        final QuestionsList question3 = new QuestionsList("Ondjima","Goat", "Cow", "Giraffe", "Monkey", "Monkey","");
        final QuestionsList question4 = new QuestionsList("Omboroto","Juice", "Macoroni", "Bread", "Meat", "Bread", "");
        final QuestionsList question5 = new QuestionsList("Oruihi","Apple", "Rice", "Spice", "Banana", "Rice","");
        final QuestionsList question6 = new QuestionsList("Okaatu kovimariva", "Cow", "Cave", "Wallet","Rice", "Wallet", "");
        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);
        return questionsLists;
    }
    public static List<QuestionsList> level3Questions() {
        final List<QuestionsList> questionsLists = new ArrayList<>();
        final QuestionsList question1 = new QuestionsList("Omaihi","Milk", "Coffe", "Bread", "Sugar", "Milk", "");
        final QuestionsList question2 = new QuestionsList("Moro","Good night", "Good evening", "Come", "Good morning", "Milk", "");
        final QuestionsList question3 = new QuestionsList("Ondjima","Goat", "Cow", "Giraffe", "Monkey", "Monkey","");
        final QuestionsList question4 = new QuestionsList("Omboroto","Juice", "Macoroni", "Bread", "Meat", "Bread", "");
        final QuestionsList question5 = new QuestionsList("Oruihi","Apple", "Rice", "Spice", "Banana", "Rice","");
        final QuestionsList question6 = new QuestionsList("Okaatu kovimariva", "Cow", "Cave", "Wallet","Rice", "Wallet", "");
        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);
        return questionsLists;
    }
    public static List<QuestionsList> getQuestions(String selectedTopicName){
        switch (selectedTopicName){
            case "level 1":
                return level1Questions();
            case "level 2":
                return level2Questions();
            default:
                return level3Questions();
        }
    }
}
