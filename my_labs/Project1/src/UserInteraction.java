import java.util.HashMap;
import java.util.Scanner;

public class UserInteraction{
	

	public UserInteraction (HashMap<String, Integer> wordMap) {
	Scanner reader = new Scanner (System.in);
	
	System.out.println("What word do you want to know the number of appearances of? Note: typing in a number or a random sequence of characters will automatically return 0.");
	String userInput = reader.nextLine();
	
	int numberOfUses = getNumberOfUses(userInput, wordMap);
	System.out.println("The word " + userInput + " appears " + numberOfUses + " times in the text.");
	
	reader.close();
	}
	
	/*This method returns the number of appearances that a given string makes in the text. */
	public static int getNumberOfUses(String userInput, HashMap<String, Integer> wordMap) {
		if (wordMap.containsKey(userInput)) {
			return wordMap.get(userInput);
		}
		else {
			return 0;
		}
	}
}
