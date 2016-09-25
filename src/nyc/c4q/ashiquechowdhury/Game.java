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
    private String randomWord;
    private String answer;
    private int miss =0;
    private int numCorrect =0;
    Scanner sc = new Scanner(System.in);

    public Game() throws FileNotFoundException {
        File definitions = new File("Definitions.txt");
        File words = new File("Words.txt");
        Quiz quiz = new Quiz(new FileInputStream(definitions), new FileInputStream(words));
        try {
            Quiz.sameNumLines(definitions,words);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(miss<3) {
            randomWord = quiz.randomDefinition();
            System.out.println(randomWord);
            answer = quiz.associatedAnswer(randomWord);
            checkAnswer();
        }
        System.out.println("Too many mistakes, bye");
    }

    public void checkAnswer(){
        String input = sc.next();
        if(input!=answer){
            miss++;
        }
        else
            numCorrect++;
    }

}
