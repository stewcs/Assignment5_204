import java.util.ArrayList;

/**
 * This class defines a MorseCodeTree class, which creates a generic linked binary tree of the lowercase alphabet.
 * @author Daniel Xu
 * @version 4/18/2023
 */

public class MorseCodeTree implements LinkedConverterTreeInterface<java.lang.String> {

	private TreeNode<String> root = new TreeNode<>("");

	// Constructor - calls the buildTree method
	public MorseCodeTree() {
		buildTree();
	}

	// Returns a reference to the root
	public TreeNode<String> getRoot(){
		return root;
	}

	// sets the root of the MorseCodeTree
	public void setRoot(TreeNode<String> newNode) {
		root = new TreeNode<String>(newNode);
	}

	/**
	 * @param code - the code for the new node to be added, example ".-."
	 */
	// Adds element to the correct position in the tree based on the code This method will call the recursive method addNode.
	public void insert(String code, String letter) {

		// Root is null
		if(root == null){
			root = new TreeNode<String>(letter);
		}
		else { // Recursive call
			addNode(root, code, letter);
		}
	}

	/**
	 * @param root - the root of the tree for this particular recursive instance of addNode
	 * @param code - the code for this particular recursive instance of addNode
	 * @param letter - the data of the new TreeNode to be added
	 */
	// This is a recursive method that adds element to the correct position in the tree based on the code. 
	// A '.' (dot) means traverse to the left. A "-" (dash) means traverse to the right. 
	// The code ".-" would be stored as the right child of the left child of the root Algorithm for the recursive method: 
	// 1. if there is only one character 
	//	a. if the character is '.' (dot) store to the left of the current root 
	//	b. if the character is "-" (dash) store to the right of the current root 
	//	c. return 
	// 2. if there is more than one character 
	//	a. if the first character is "." (dot) new root becomes the left child 
	//	b. if the first character is "-" (dash) new root becomes the right child 
	//	c. new code becomes all the remaining characters in the code (beyond the first character) 
	//	d. call addNode(new root, new code, letter)
	public void addNode(TreeNode<String> root, String code, String letter) {
	    // End method if passed String is empty
		if (code.isEmpty()) {
	        return;
	    }

	    TreeNode<String> child = new TreeNode<>(letter);

	    	// Add node left
	    if (code.charAt(0) == '.') {
	        if (root.leftChild == null) {
	            root.leftChild = child;
	        } else {
	            addNode(root.leftChild, code.substring(1), letter);
	        }
	        // Add node right
	    } else if (code.charAt(0) == '-') {
	        if (root.rightChild == null) {
	            root.rightChild = child;
	        } else {
	            addNode(root.rightChild, code.substring(1), letter);
	        }
	    }
	}

	/**
	 * @param code - the code that describes the traversals to retrieve the string (letter)
	 * @return result - the string (letter) that corresponds to the code
	 */
	// Fetch the data in the tree based on the code. This method will call the recursive method fetchNode
	public String fetch(String code) {
		String result = fetchNode(root, code);
		return result;
	}

	/**
	 * @param root - the root of the tree for this particular recursive instance of addNode
	 * @param code - the code for this particular recursive instance of addNode
	 * @return the string (letter) corresponding to the code
	 */
	// This is the recursive method that fetches the data of the TreeNode that corresponds with the code 
	// A '.' (dot) means traverse to the left. 
	// A "-" (dash) means traverse to the right. 
	// The code ".-" would fetch the data of the TreeNode stored as the right child of the left child of the root
	public String fetchNode(TreeNode<String> root, String code) {
		if(root == null)
			System.out.println("root null");
		// Base case: empty tree or end of code
		if (root == null || code.isEmpty()) {
			return null;
		}

		// -- Recursive Cases -- 
		
		// Check if code only has one character
		if (code.length() == 1) {
			if (code.equals(".")) {
				if (root.leftChild != null){
					return root.leftChild.getData();
				} else return null;
			} else if (code.equals("-")) {
				if (root.rightChild != null) {
					return root.rightChild.getData();
				} else return null;
			}
		}

		// If code has more than 1 character
		if(code.length() > 1) {
			if (code.charAt(0) == '.') {
				return fetchNode(root.leftChild, code.substring(1));
			} else if (code.charAt(0) == '-') {
				return fetchNode(root.rightChild, code.substring(1));
			}
		}
		return null;
	}


	// This operation is not supported in the MorseCodeTree
	public MorseCodeTree delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	// This operation is not supported in the MorseCodeTree
	public MorseCodeTree update() throws java.lang.UnsupportedOperationException{
		throw new UnsupportedOperationException();

	}

	// This method builds the MorseCodeTree by inserting the nodes of the tree level by level based on the code. 
	// The root will have a value of "" (empty string) 
	// level one: insert(".", "e"); insert("-", "t"); 
	// level two: insert("..", "i"); insert(".-", "a"); insert("-.", "n"); insert("--", "m"); etc. 
	// Look at the tree and the table of codes to letters in the assignment description.
	public void buildTree() {

		// Level 2 - e, t 
		insert(".", "e"); insert("-", "t"); 

		// Level 3 - i, a, n , m
		insert("..","i"); insert(".-","a");insert("-.","n"); insert("--","m");

		// Level 4 - s u r w d k g o
		insert("...","s"); insert("..-","u");insert(".-.","r"); insert(".--","w");
		insert("-..","d"); insert("-.-","k");insert("--.","g"); insert("---","o");

		// Level 5 - h v f l p j b x c y z q 
		insert("....","h"); insert("...-","v");insert("..-.","f"); insert(".-..","l");
		insert(".--.","p"); insert(".---","j");insert("-...","b"); insert("-..-","x");
		insert("-.-.","c"); insert("-.--","y");insert("--..","z"); insert("--.-","q");
	}

	/**
	 * @return result - an ArrayList of the items in the linked Tree
	 */
	// Returns an ArrayList of the items in the linked Tree in LNR (Inorder) Traversal order.
	// Used for testing to make sure tree is built correctly
	public ArrayList<String> toArrayList(){
		ArrayList<String> result = new ArrayList<String>();
		LNRoutputTraversal(root,result);
		return result;
	}

	/**
	 * @param root - the root of the tree for this particular recursive instance
	 * @param list - the ArrayList that will hold the contents of the tree in LNR order
	 */
	// The recursive method to put the contents of the tree in an ArrayList in LNR (In order)
	// In order - Left, root, right
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root != null) {
			LNRoutputTraversal(root.leftChild, list); 
			list.add(root.getData());                 
			LNRoutputTraversal(root.rightChild, list); 
		}
	}
}
