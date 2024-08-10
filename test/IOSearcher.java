package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class IOSearcher {

    private static boolean searchInFile(String word, String fileName) throws Exception{
        try{
            Scanner myScaner = new Scanner(new BufferedReader(new FileReader(fileName)));
            while(myScaner.hasNext()){
                if(myScaner.next().equals(word)){
                    myScaner.close();
                    return true;
                }
            }
            myScaner.close();
            return false;
        } catch (IOException e) {
            throw e;
        }
    }

    public static boolean search(String word, String...fileNames) throws Exception{
        for (String fileName : fileNames) {
            try{ if (IOSearcher.searchInFile(word, fileName))
                return true;
            }catch (IOException e){
                throw e;
            }
        }
        return false;
    }
}
