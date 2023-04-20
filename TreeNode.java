/**
 * This class defines a TreeNode element with 3 attributes: reference to data, left child, and right child.
 * @author Daniel Xu
 * @version 4/17/2023
 */

public class TreeNode<T> {
	private T dataNode;
	protected TreeNode<T> leftChild;
	protected TreeNode<T> rightChild;

	
	// Make a basic node
	public TreeNode(T dataNode) {
		this.dataNode = dataNode;
		this.leftChild = null;
		this.rightChild = null;
	}

	// Make a deep copy
	public TreeNode(TreeNode<T> node) {
		// node null
	    if (node == null) {
	        return;
	    }
	    
	   
	   
	    if (node.leftChild != null) {
	        this.leftChild = new TreeNode<>(node.leftChild);
	    }
	    if (node.rightChild != null) {
	        this.rightChild = new TreeNode<>(node.rightChild);
	    }
	    this.dataNode = node.dataNode;
	}
	
	// Return data of node
	public T getData() {
		return this.dataNode;
	}
}
