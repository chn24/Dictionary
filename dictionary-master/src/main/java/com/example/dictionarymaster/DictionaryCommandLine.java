package com.example.dictionarymaster;

import java.util.Map;
import java.util.Scanner;

public class DictionaryCommandLine extends DictionaryManagement {
    private static Scanner scan = new Scanner(System.in);
    public static void showAllWords() {
        int Check = 0;
        System.out.printf("%-5s| %-15s| %-15s%n", "No", "English", "Vietnamese");
        for(Map.Entry<String, String> getWord : dictionary.entrySet()) {
            Check++;
            System.out.printf("%-5d| %-15s| %-15s%n", Check, getWord.getKey(), getWord.getValue());
        }
    }

    public static void dictionaryBasic() {
        DictionaryManagement.insertFromCommandline();
        showAllWords();

    }

    public static void dictionaryAdvanced() {
        DictionaryManagement.insertFromFile();

        DictionaryManagement.dictionaryLookup();
        DictionaryManagement.dictionaryEditing();
        DictionaryManagement.dictionaryDelete();
        DictionaryManagement.dictionaryExportToFile();
        showAllWords();
    }

    public static void dictionarySearcher() {
        System.out.print("Searcher: ");
        String Search = scan.next();

        for (Map.Entry<String, String> getWord : dictionary.entrySet()){
            int k = 0;
            String WordNS = getWord.getKey();
            for (int i = 0; i < Search.length(); i++){
                if(Search.charAt(i) != WordNS.charAt(i)){
                    k =1;
                    break;
                }
            }
            if (k == 0)System.out.println(getWord.getKey());
        }
    }


}

