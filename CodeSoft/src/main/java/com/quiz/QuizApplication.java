package com.quiz;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private String[] options;
    private String correctAnswer;

    public QuizQuestion(String question, String[] options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

public class QuizApplication {
    private static final int TIME_LIMIT_SECONDS = 10; // Time limit for each question in seconds
    private static int score = 0;
    private static int totalQuestions = 0;
    private static int correctAnswers = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz Application!");

        // Define quiz questions
        QuizQuestion[] quizQuestions = {
            new QuizQuestion("What is the capital of France?", new String[]{"A. Paris", "B. London", "C. Berlin"}, "A"),
            new QuizQuestion("What is 2 + 2?", new String[]{"A. 3", "B. 4", "C. 5"}, "B")
            // Add more questions here...
        };

        totalQuestions = quizQuestions.length;

        System.out.println("Answer the following questions:");

        for (int i = 0; i < quizQuestions.length; i++) {
            QuizQuestion currentQuestion = quizQuestions[i];
            System.out.println("Question " + (i + 1) + ": " + currentQuestion.getQuestion());

            // Display options
            for (String option : currentQuestion.getOptions()) {
                System.out.println(option);
            }

            System.out.print("Your answer (A, B, C): ");

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                int remainingTime = TIME_LIMIT_SECONDS;

                @Override
                public void run() {
                    if (remainingTime > 0) {
                        System.out.println("Time remaining: " + remainingTime + " seconds");
                        remainingTime--;
                    } else {
                        timer.cancel();
                        System.out.println("Time's up! Moving to the next question.");
                        System.out.println("Correct answer: " + currentQuestion.getCorrectAnswer());
                        System.out.println();
                    }
                }
            }, 0, 1000); // Timer task runs every second

            String userAnswer = scanner.nextLine().toUpperCase();

            timer.cancel(); // Cancel timer when user submits answer

            if (userAnswer.equals(currentQuestion.getCorrectAnswer())) {
                System.out.println("Correct!");
                score++; // Increment the score if the user's answer is correct
                correctAnswers++; // Increment the correct answers count
            } else {
                System.out.println("Incorrect!");
            }

            System.out.println(); // Add a newline between questions
        }

        System.out.println("Quiz is over.");
        System.out.println("Total questions: " + totalQuestions);
        System.out.println("Correct answers: " + correctAnswers);
        System.out.println("Incorrect answers: " + (totalQuestions - correctAnswers));
        System.out.println("Your final score: " + score);

        scanner.close();
    }
}



