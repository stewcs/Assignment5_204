import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for MorseCodeTree
 * @author Daniel Xu
 * @version 4/19/2023
 */

class MorseCodeTreeTestStudent {

	MorseCodeTree tree;
	TreeNode<String> root;
	ArrayList<String> list = new ArrayList<String>();

	@BeforeEach
	void setUp() throws Exception {
		tree = new MorseCodeTree();
		root = new TreeNode<String>("CS2");
		list = tree.toArrayList();
	}

	@AfterEach
	void tearDown() throws Exception {
		tree = null;
		root = null;
		list = null;
	}

	@Test
	public void testGetRoot() 
	{
		assertEquals(tree.getRoot().getData(), "");
		assertEquals(root.getData(), "CS2");
	}
	
	@Test
	public void testSetRoot() 
	{
		tree.setRoot(root);
		assertEquals(tree.getRoot().getData(), "CS2");
		
		root = new TreeNode<String>("SAS");
		tree.setRoot(root);
		assertEquals(tree.getRoot().getData(), "SAS");
	}

	@Test
	public void TestAddNode() 
	{
		tree.addNode(tree.getRoot(), "....", "h");
		assertEquals(tree.fetch("...."), "h");
		
		tree.addNode(tree.getRoot(), "-.-", "k");
		assertEquals(tree.fetch("-.-"), "k");
	}
	
	@Test
	public void TestFetchNode() 
	{
		tree.addNode(tree.getRoot(), "....", "h");
		assertEquals(tree.fetchNode(tree.getRoot(), "...."), "h");
	}
	
	@Test
	public void TestToArrayList() 
	{
		System.out.print(list);
		assertEquals(list.get(1), "s");
		assertEquals(list.get(2), "v");
		assertEquals(list.get(3), "i");
	}
}
