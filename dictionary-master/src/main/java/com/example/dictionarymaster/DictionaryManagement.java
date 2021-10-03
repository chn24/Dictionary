package com.example.dictionarymaster;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;

public class DictionaryManagement extends Dictionary {
    private static final Scanner scan = new Scanner(System.in);
//    public static void insertFromCommandline() {
//        System.out.print(" Number insert: ");
//        int n = scan.nextInt();
//        for ( int i = 1; i <= n; i++) {
//            Word word = new Word();
//            System.out.print("New Word: ");
//            word.setWord_target(scan.next());
//            scan.nextLine();
//            System.out.print("Mean:  ");
//            word.setWord_explain(scan.nextLine());
//            dictionary.put(word.getWord_target(), word.getWord_explain());
//        }
//    }
    public static void insertFromFile() {
        try {
            FileReader fr = new FileReader("data.txt");
            BufferedReader br = new BufferedReader(fr);
            String insert = "";
            while (true){
                insert = br.readLine();
                if (insert == null){
                    break;
                }
                String txt[] = insert.split("\t");
                dictionary.put(txt[0], txt[1]);
            }
        }catch (Exception e){ }
    }
    public static void dictionaryLookup() {
        System.out.print("Searching: ");
        String wordSearch = scan.next();
        System.out.println( dictionary.getOrDefault(  wordSearch ,"Word not found"));

    }
    public static void dictionaryEditing() {
        System.out.print("Editing: ");
        String wordEdit = scan.next();
        scan.nextLine();
        System.out.print("New mean: ");
        String newMean = scan.next();
        dictionary.put(wordEdit,newMean);
    }

    public static void dictionaryDelete() {
        System.out.print("Word need Delete: ");
        String wordDelete = scan.next();
        if( dictionary.containsKey(wordDelete) ) {
            dictionary.remove(wordDelete);
        }
        else {
            System.out.println("No word found");
        }
    }
    public static void dictionaryExportToFile() {
        try {
            FileWriter fw = new FileWriter("data.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Map.Entry<String, String> getWord : dictionary.entrySet()){
                String WordtoS = getWord.getKey() + "\t" + getWord.getValue();
                bw.write(WordtoS);
                bw.newLine();
            }
            bw.close();
            fw.close();
        }catch (Exception e){ }
    }

}

