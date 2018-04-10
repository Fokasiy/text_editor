package com.texteditor.texteditor;

import android.text.TextUtils;

/**
 * Created by DIMA on 10.04.2018.
 */

public class TextEditHelper {

    public static String getProperString(String inputString){
        StringBuilder builder = new StringBuilder();
        String[] splittedArray = inputString.split("\\s+");

        for (int i = 0; i<splittedArray.length; i++){
            String withoutSpaces = splittedArray[i].replace("\\s+","");
            if(i==0){
                String firstWord = getProperFirstWord(withoutSpaces);
                builder.append(firstWord+" ");
            }
            else{
               withoutSpaces = checkForSeparators(withoutSpaces).toLowerCase();
               builder.append(withoutSpaces+" ");
            }
        }

        return builder.toString();
    }


    private static String getProperFirstWord(String firstWord){
        String withFirstUpper = "";
        if(!TextUtils.isEmpty(firstWord)){
            char firstLetter = firstWord.charAt(0);
            if(Character.isLetterOrDigit(firstLetter) && Character.isLowerCase(firstLetter)){
                withFirstUpper = firstWord.substring(0,1).toUpperCase() + firstWord.substring(1).toLowerCase();
            }
        }
        withFirstUpper = checkForSeparators(withFirstUpper);

        return withFirstUpper;
    }


    private static String checkForSeparators(String inputWord){
        String[] separatorsArray = new String[]{",","...", ".", ";"};
        String resultString = inputWord;

        for (int i=0; i<separatorsArray.length; i++){
            resultString = checkSpaces(resultString, separatorsArray[i]);
        }

        return resultString;
    }


    private static String checkSpaces(String inputWord, String ch){
        StringBuilder separateBuilder = new StringBuilder();
        String processedStr = "";
        int indexOf = inputWord.indexOf(ch);
        if(indexOf!=-1){
            do{
                if(indexOf!=-1){
                    String str = inputWord.substring(0,indexOf+ch.length());
                    String wc = processedStr.replace("\\s+","");
                    separateBuilder.append(wc+" ");
                    indexOf = inputWord.indexOf(ch, indexOf+1);
                }
                else{

                }
            }while(indexOf>=0);

            return separateBuilder.toString();
        }
        else
            return inputWord;
    }
}
