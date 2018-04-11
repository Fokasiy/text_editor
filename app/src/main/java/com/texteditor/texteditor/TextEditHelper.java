package com.texteditor.texteditor;

import android.text.TextUtils;

/**
 * Created by DIMA on 10.04.2018.
 */

public class TextEditHelper {

    private static final String[] mSeparatorsArray = new String[]{",", "...", "'", "’"};

    public static String getProperString(String inputString){

        String separatedStr = checkForSeparators(inputString).toLowerCase();
        String strSentences = separateSentences(separatedStr+" ").trim();

        return strSentences;
    }


    private static String getProperFirstWord(String firstWord){
        String withFirstUpper = "";
        if(!TextUtils.isEmpty(firstWord)){
            char firstLetter = firstWord.charAt(0);
            if(Character.isLetterOrDigit(firstLetter) && Character.isLowerCase(firstLetter)){
                withFirstUpper = firstWord.substring(0,1).toUpperCase() + firstWord.substring(1).toLowerCase();
            }
        }

        return withFirstUpper;
    }


    private static String checkForSeparators(String inputWord){
        String resultString = inputWord;

        for (int i=0; i<mSeparatorsArray.length; i++){
            resultString = checkSpaces(resultString, mSeparatorsArray[i]);
        }

        return resultString;
    }


    private static String checkSpaces(String inputWord, String ch){
        StringBuilder separateBuilder = new StringBuilder();
        int startPartIndex = 0;
        int indexOf = 0;
        if(indexOf!=-1){
            do{
                indexOf = inputWord.indexOf(ch, startPartIndex);

                if(indexOf!=-1){
                    String str = inputWord.substring(startPartIndex,indexOf);
                    String wc = str.trim();
                    startPartIndex = indexOf+ch.length();
                    if(ch.equals("'") || ch.equals("’") || ch.equals("...")){
                        separateBuilder.append(wc+ch);
                    }
                    else{
                        separateBuilder.append(wc+ch+" ");
                    }
                }
                else{
                    String str = inputWord.substring(startPartIndex).trim();
                    separateBuilder.append(str);
                }
            }while(indexOf>=0);

            return separateBuilder.toString();
        }
        else
            return inputWord;
    }


    private static String separateSentences(String inputString){

        int countOfDots = countOccurrences(inputString, '.');

        if(countOfDots == 0) return getProperFirstWord(inputString);

        String[] stringArray = inputString.split("\\.");

        if(stringArray.length>1){
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i<stringArray.length; i++){
                String separateSentence = stringArray[i].trim();
                if(TextUtils.isEmpty(separateSentence) && !TextUtils.isEmpty(builder.toString())){
                    if(countOfDots!=0){
                        countOfDots--;
                        builder.append(".");
                    }
                    continue;
                }
                else{
                    separateSentence = getProperFirstWord(separateSentence);
                }

                if(i==0){
                    builder.append(separateSentence+(countOfDots!=0?".":""));
                    countOfDots--;
                }
                else{
                    builder.append(" "+separateSentence+(countOfDots!=0?".":""));
                    countOfDots--;
                }
            }

            return builder.toString();
        }
        else
            return getProperFirstWord(inputString);
    }


    private static int countOccurrences(String haystack, char needle)
    {
        int count = 0;
        for (int i=0; i < haystack.length(); i++)
        {
            if (haystack.charAt(i) == needle)
            {
                count++;
            }
        }
        return count;
    }
}
