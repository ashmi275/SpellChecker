package searchEngineSpellChecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import searchEngineSpellChecker.Sequences;


public class SpellCheck {
	
	private static ArrayList<String> createDict(){
		ArrayList<String> arr = new ArrayList<String>();
		try {
	            BufferedReader br =  new BufferedReader(new FileReader("src/Dictionary.txt"));
	            String line = br.readLine();
	            while (line != null) {
	                arr.add(line);
	                line = br.readLine();
	            }
		}catch(Exception e) {
			System.out.println("Error while accessing Dictionary "+ e);
		}
		return arr;
	}
	
    public static ArrayList<String> findSuggestions(String word){
        ArrayList<String> wordCorrection = new ArrayList<String>();
        try{
            ArrayList<String> ar = new ArrayList<String>();
            int i=0;
            int d=0;
            ar = createDict();

            if (!ar.contains(word)) {
            	
                for (i=0;i<ar.size();i++) {
                    d= Sequences.editDistance(word, ar.get(i));
                    if(d==1) {
                        wordCorrection.add(ar.get(i));
                    }
                }

            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return wordCorrection;
    }

	public static void main(String[] args) {
		System.out.println("Enter the word you want to search: ");
		Scanner s = new Scanner(System.in);
		String userInput = s.nextLine();
		
		ArrayList<String> dictionary = new ArrayList<String>();
		ArrayList<String> suggestion = new ArrayList<String>();
		dictionary = createDict();
		
		if(dictionary.contains(userInput)) {
			System.out.println("Word Spelled Correctly !!");
    	}else {
    		System.out.println("Word not found");
    		suggestion = findSuggestions(userInput);
    		
    		if(suggestion.size() >= 2) {
    			System.out.println("Did you mean " + suggestion.get(0) + " or " + suggestion.get(1)  + " ?");
    		}
    		else if(suggestion.size() == 1) {
    			System.out.println("Did you mean " + suggestion.get(0) + " ?");
    		}
    		else if(suggestion.size() == 0) {
    			System.out.println("No suggestions found.");
    		}
    		
    		
    		
    	}
	}

}
