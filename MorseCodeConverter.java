import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class defines a MorseCodeConverter class, which contain a static MorseCodeTree object and constructs the MorseCodeTree.
 * @author Daniel Xu
 * @version 4/19/2023
 */

public class MorseCodeConverter {

	static MorseCodeTree tree = new MorseCodeTree();

	// Constructor
	public MorseCodeConverter() {
		tree.buildTree();
	}

	/**
	 * @return treeData - a string with all the data in the tree in LNR order with an space in between them. 
	 */
	//returns a string with all the data in the tree in LNR order with an space in between them. 
	// Uses the toArrayList method in MorseCodeTree It should return the data in this order:
	// "h s v i f u e l r a p w j b d x n c k y t z g q m o"
	// Note the extra space between j and b - that is because there is an empty string that is the root, 
	// and in the LNR traversal, the root would come between the right most child of the left tree (j) 
	// and the left most child of the right tree (b). This is used for testing purposes to make sure 
	// the MorseCodeTree has been built properly
	public static String printTree() {
		
		String treeData = "";
		ArrayList<String> result = tree.toArrayList(); 
		
		for (String node : result) {
			treeData += node + " "; 
		}
		return treeData.trim(); // trim to remove space at the end
	}

	/**
	 * @param code - the morse code to be translated
	 * @return result - English translation of the morse code
	 */
	// Converts Morse code into English. Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	// Example:
	//	code = ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
	//	string returned = "Hello World"
	public static String convertToEnglish(String code) {
		// split using spaces
		String[] phrase = code.split(" ");

		// Check if "/"
		// If not "/" , fetch.
		String result = "";
		for(String word : phrase) {
			if(word.equals("/")) {
				result += " ";
			} else {
				result += tree.fetch(word);
			}
		}
		return result;
	}
	/**
	 * @param codeFile - file to be translated
	 * @return result - English translation of the morse code
	 * @throws FileNotFoundException
	 */
	// Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	// Example:
	//	a file that contains ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
	//	string returned = "Hello World"
	public static String convertToEnglish(File codeFile) throws FileNotFoundException {
		Scanner scanner = new Scanner(codeFile);
		
		String result = "";
		
		while(scanner.hasNext()) {
			result += convertToEnglish(scanner.nextLine());
		}
		
		scanner.close();
		return result;
	}

}
