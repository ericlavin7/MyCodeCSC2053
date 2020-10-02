import java.io.*;
import java.util.*;
import java.util.Map.Entry;


//@ Eric Lavin


public class Project1 {
	
	public static void main (String[] args) throws IOException {
		
		
		ArrayList<String> bookLines = readBook();
		ArrayList<String> stopListLines = readStopList();
		HashMap<String, Integer> wordMap = wordCount(bookLines);
		HashMap<Character, Integer> charMap = charCount(bookLines);
		
		/*
		 * Utilizes another class for userInteraction
		 */
		new UserInteraction(wordMap);
		
		System.out.println();
        charTopTen(charMap);
        System.out.println();
        System.out.println();
        stringTopTen(wordMap);
        System.out.println();
        System.out.println();
        HashMap<String, Integer> wordMapSemantic = deleteNonSemanticWords(wordMap, stopListLines);
        stringTopTen(wordMapSemantic);
        
	}
	
	/*This method reads the tom-sawyer.txt file into the program and stores each line in an ArrayList called fileLines.  
	 * It utilizes a try catch to catch any file not found exceptions. */
	
	public static ArrayList<String> readBook() throws IOException {
		String bookName = "tom-sawyer.txt";
		
		ArrayList<String> fileLines = new ArrayList<>();
		
		try {
		BufferedReader lineReader = new BufferedReader(new FileReader(bookName));
		
			String lineFromText = null;
			
			while ((lineFromText = lineReader.readLine()) != null) {
				fileLines.add(lineFromText);
			}
			
			lineReader.close();
		
		} catch (FileNotFoundException e) {
			System.out.println("File was not found.  Please retry.");
		}
		return fileLines;
	}
	
	/*This method reads the stop-list.txt file and stores each line in an ArrayList called fileLines. 
	 * It also utilizes a try catch to catch any file not found exceptions.*/
	public static ArrayList<String> readStopList() throws IOException {
		String bookName = "stop-list.txt";
		
		ArrayList<String> fileLines = new ArrayList<>();
		
		try {
		BufferedReader lineReader = new BufferedReader(new FileReader(bookName));
		
			String lineFromText = null;
			
			while ((lineFromText = lineReader.readLine()) != null) {
				fileLines.add(lineFromText);			
				}
			
			lineReader.close();
		
		} catch (FileNotFoundException e) {
			System.out.println("File was not found.  Please retry.");
		}
		
		return fileLines;
	}
	
	/*This method passes in the ArrayList fileLines.  
	 * It then iterates through that array list and stores each new value in a HashMap called wordMap.
	 * If the value is already present in the HashMap, the method simply replaces the K,V pairing with K,V+1.
	 */
	public static HashMap<String, Integer> wordCount(ArrayList<String> fileLines){
		HashMap<String, Integer> wordMap = new HashMap<>();
		Scanner scan = null;
		
		for (String line: fileLines) {
			if (line != null) {
			scan = new Scanner(line);
			}
			
			while(scan.hasNext()) {
				scan.useDelimiter("[^a-zA-Z']");
				String word = scan.next();
				word = word.toLowerCase();
				word = word.replaceAll("^'+", "");
				word = word.replaceAll("'+$", "");
				
				if (word.isBlank()) {
				}
				else if (wordMap.containsKey(word)) {
					wordMap.put(word, wordMap.get(word) + 1);
				}
				else {
					wordMap.put(word, 1);
				}
			}
		}
		return wordMap;
	}
	
	/*This method also passes in the ArrayList filesLine.
	 * It iterates through fileLines and stores each character in a char[] array called charArray
	 * Then, it iterates through the charArray and stores each character in a HashMap called charMap.
	 * Remember, in this instance, we only care about the letters a-z, so it does not store any other characters except those that
	 * are present in the English alphabet.
	 *  */
	public static HashMap<Character, Integer> charCount(ArrayList<String> fileLines){
		HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
		
		@SuppressWarnings("unused")
		Scanner scan = null;
		
		for (String line: fileLines) {
			if (line != null) {
			scan = new Scanner(line);
			char[] charArray = line.toLowerCase().toCharArray(); 
			
	        for (char c : charArray) { 
	            if (charMap.containsKey(c)) {  
	                charMap.put(c, charMap.get(c) + 1); 
	            } 
	            else if (c < 97 || c > 122) {
	            	//do nothing, these characters are not in the English alphabet
	            }
	            else { 
	                // If char is not present in charCountMap, 
	                // we can create a new K,V pair, obviously with K = c and V = 1
	                charMap.put(c, 1); 
	            } 
	        }
		}	
	}
		return charMap;
	}
	
	/*This method passes in the recently created charMap and simply prints out the ten keys with the top ten greatest values. */
	public static void charTopTen(HashMap<Character, Integer> charMap){
		
		HashMap<Character, Integer> topTen = new HashMap<>();
		HashMap<Character, Integer> holder = new HashMap<>();
        char maxAppearChar = ' ';
        int i = 1;
        
        while (i <= 10){	
        int maxValueInMap=(Collections.max(charMap.values())); //returns max value in HashMap
          for (Entry<Character, Integer> entry : charMap.entrySet())
          {  
              if (entry.getValue()==maxValueInMap)
              {
                  System.out.println("The #" + i + " most used letter is : " + entry.getKey() + "  and it appears  " +maxValueInMap+ "  times in the text.");
                  // Print the key with max value
                  maxAppearChar = entry.getKey();
                  topTen.put(maxAppearChar, maxValueInMap);
                  
              }
        	}
          charMap.remove(maxAppearChar, maxValueInMap);
          holder.put(maxAppearChar, maxValueInMap);
          ++i;
        }
       //return topTen;

	}
	
	/*This method passes in the recently created wordMap and simply prints out the ten keys with the top ten greatest values. */
	public static void stringTopTen(HashMap<String, Integer> wordMap){
		
		HashMap<String, Integer> topTen = new HashMap<>();
        String maxAppearString = "";
        int i = 1;
        
        while (i <= 10){	
        int maxValueInMap=(Collections.max(wordMap.values())); //returns max value in HashMap
          for (Entry<String, Integer> entry : wordMap.entrySet())
          {  
              if (entry.getValue()==maxValueInMap)
              {
                  System.out.println("The #" + i + " most used word is : " + entry.getKey() + "  and it appears  " +maxValueInMap+ "  times in the text.");
                  // Print the key with max value
                  maxAppearString = entry.getKey();
                  topTen.put(maxAppearString, maxValueInMap);
                  
              }
        	}
          wordMap.remove(maxAppearString, maxValueInMap);
          ++i;
        }
       //return topTen;
	}
	
	/*This method looks at each word in the ArrayList stopListLines and removes the word from the HashMap wordMap if it is present  */
	public static HashMap<String, Integer> deleteNonSemanticWords (HashMap<String, Integer> wordMap, ArrayList<String> stopListLines){
		//remember the top ten words from wordMap are already deleted from the HashMap
		for (int i = 0; i < 574; i++) {
			String word = stopListLines.get(i);
			word.equalsIgnoreCase(word);
			wordMap.remove(word);
			
		}
		return wordMap;
	}

}
