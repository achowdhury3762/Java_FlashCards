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
    final private List<String> definitions = new ArrayList<>();

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
            definitions.add(definition);
            size++;
            easyDictionary.put(word,definition);
        }
    }

    public String randomDefinition(){
        Random random = new Random();
        int randomNum = random.nextInt(size+1);
        return definitions.get(randomNum);
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

    public String associatedAnswer(String randomWord) {
        return easyDictionary.get(randomWord);
    }
}
