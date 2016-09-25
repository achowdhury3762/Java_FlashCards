package nyc.c4q.ashiquechowdhury;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by ashiquechowdhury on 9/25/16.
 */
public class Game {
    int questionNum = 1;
    Quiz quiz;
    private String randomWord;
    private String answer;
    private int miss =0;
    private int numCorrect =0;
    Scanner sc = new Scanner(System.in);

    public Game() throws FileNotFoundException {
        File definitions = new File("Definitions.txt");
        File words = new File("Words.txt");
        quiz = new Quiz(new FileInputStream(definitions), new FileInputStream(words));
        try {
            Quiz.sameNumLines(definitions,words);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(miss<3) {
            if(numCorrect==8){
                quiz.advancelevel();
                numCorrect=0;
            }

            randomWord = quiz.randomWord();
            answer = quiz.answer(randomWord);
            askuser();
            checkAnswer();
        }
        System.out.println("Too many mistakes, bye");
    }

    private void askuser() {
        System.out.println(questionNum + ". What is a " + answer + "?");
        questionNum++;
    }

    public void checkAnswer(){
        String input = sc.next();
        if(!input.equals(randomWord)){
            miss++;
            System.out.println("Miss " + miss);
        }
        else
            numCorrect++;
    }

}
