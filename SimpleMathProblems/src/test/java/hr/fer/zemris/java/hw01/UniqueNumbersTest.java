package hr.fer.zemris.java.hw01;

import static org.junit.Assert.*;

import org.junit.Test;

import hr.fer.zemris.java.hw01.UniqueNumbers.TreeNode;

public class UniqueNumbersTest {

	@Test
	public void testAddNode() {
		TreeNode glava = InitializeTree();
		
		assertEquals(42, glava.value);
		assertEquals(21, glava.left.value);
		assertEquals(35, glava.left.right.value);
		assertEquals(76, glava.right.value);
	}

	@Test
	public void testTreeSize() {
		TreeNode glava = InitializeTree();
		
		int velicina = UniqueNumbers.treeSize(glava);
		assertEquals(4, velicina);
	}

	@Test
	public void testContainsValue() {
		TreeNode glava = InitializeTree();
		boolean duplikat = UniqueNumbers.containsValue(glava, 76);
		assertEquals(true, duplikat);
	}
	
	public static TreeNode InitializeTree() {
		TreeNode glava = null;
		glava = UniqueNumbers.addNode(glava, 42);
		glava = UniqueNumbers.addNode(glava, 76);
		glava = UniqueNumbers.addNode(glava, 21);
		glava = UniqueNumbers.addNode(glava, 76);
		glava = UniqueNumbers.addNode(glava, 35);
		
		return glava;
	}

}
