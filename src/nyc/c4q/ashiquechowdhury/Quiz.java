package nyc.c4q.ashiquechowdhury;

import java.io.*;
import java.util.*;

/**
 * Created by ashiquechowdhury on 9/24/16.
 */
public class Quiz {
    final private Map<String, String> easyDictionary = new HashMap<>();
    final private Map<String, String> mediumDictionary = new HashMap<>();
    final private Map<String, String> hardDictionary = new HashMap<>();
    private int size = 0;
    int level = 1;
    final private List<String> easyDefinitions = new ArrayList<>();
    final private List<String> mediumDefinitions = new ArrayList<>();
    final private List<String> hardDefinitions = new ArrayList<>();


    InputStream definitionFile;
    InputStream wordFile;

    //Constructor to take files and put into Hashmap
    Quiz(InputStream definitionFile, InputStream wordFile){
        this.definitionFile = definitionFile;
        this.wordFile = wordFile;
        Scanner wordScanner = new Scanner(wordFile);
        Scanner definitionScanner = new Scanner(definitionFile);


        while(wordScanner.hasNext()){
            String definition = definitionScanner.nextLine();
            String word = wordScanner.nextLine();
//            System.out.println(definition + " : " + word);
            size++;
            easyDictionary.put(word,definition);
            mediumDictionary.put(word, definition);
            hardDictionary.put(word, definition);
//            if(!wordScanner.hasNext()) {
//                for (String i : easyDictionary.keySet()) {
//                    System.out.println(i);
//                }
//            }
            easyDefinitions.add(word);
            mediumDefinitions.add(word);
            hardDefinitions.add(word);
        }
    }

    public String randomEasyWord(){
        Random random = new Random();
        int randomNum = random.nextInt(size);
        return easyDefinitions.get(randomNum);

    }

    public String randomMediumWord(){
        Random random = new Random();
        int randomNum = random.nextInt(size);
        String word = easyDefinitions.get(randomNum);
        return mediumDefinitions.get(randomNum);
    }

    public String randomHardWord(){
        Random random = new Random();
        int randomNum = random.nextInt(size);
        String word = easyDefinitions.get(randomNum);
        return hardDefinitions.get(randomNum);
    }

    //Checks if files have same line size
    public static boolean sameNumLines(File definitionFile, File wordFile) throws IOException, IllegalArgumentException{
        LineNumberReader lnr = new LineNumberReader(new FileReader(definitionFile));
        lnr.skip(Long.MAX_VALUE);
        int linenum = lnr.getLineNumber() + 1;
        lnr = new LineNumberReader(new FileReader(definitionFile));
        lnr.skip(Long.MAX_VALUE);
        int secondLineNum = lnr.getLineNumber() + 1;
        lnr.close();

        return linenum == secondLineNum;
    }

    public void start() {
        System.out.println(easyDictionary.keySet());
        System.out.println(easyDictionary.values());
    }

    public String associatedEasyAnswer(String randomWord) {
        return easyDictionary.get(randomWord);
    }

    public String associatedMediumAnswer(String randomWord) {
        return mediumDictionary.get(randomWord);
    }

    public String associatedHardAnswer(String randomWord) {
        return hardDictionary.get(randomWord);
    }

    public void advancelevel() {
        level++;
    }

    public String randomWord() {
        if(level == 1){
            return randomEasyWord();
        }
        else if(level ==2){
            return randomMediumWord();
        }
        else
            return randomHardWord();
    }

    public String answer(String key) {
        if(level == 1){
            return associatedEasyAnswer(key);
        }
        else if(level ==2){
            return associatedMediumAnswer(key);
        }
        else
            return associatedHardAnswer(key);
    }
}
