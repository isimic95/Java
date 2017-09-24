package hr.fer.zemris.java.hw01;

import java.util.Scanner;

/**
 * Class implements a tree which consists of unique numbers..
 * 
 * 
 * @author Ivica Šimić
 *
 */
public class UniqueNumbers {
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TreeNode root = null;
		
		while(true) {
			System.out.print("Unesite cijeli broj > ");
			if(sc.hasNextInt()) {
				int n = sc.nextInt();
				root = addNode(root, n);
			} else {
				String elem = sc.next();
				if(elem.compareTo("kraj")==0) {
					break;
				}
				System.out.printf("%s nije cijeli broj.%n", elem);
			}
		}
		sc.close();
		
		System.out.print("Ispis od najmanjeg: ");
		leftToRightInOrder(root);
		System.out.println();
		System.out.print("Ispis od najvećeg: ");
		rightToLeftInOrder(root);
		
	}
	/**
	 * Class which represents node data structure.
	 *
	 * @author Ivica
	 */
	static class TreeNode {
		
		/** Left node. */
		TreeNode left;
		
		/** Right node. */
		TreeNode right;
		
		/** Node value. */
		int value;
	}
	
	/**
	 * Adds a new node with the given value to the tree.
	 * If null is passed as an argument it creates a new root.
	 * If the value is already in the tree, skips it and prints out a message.
	 * 
	 *
	 * @param root Root of the tree
	 * @param value
	 * @return root Root of the tree
	 */
	public static TreeNode addNode(TreeNode root, int value) {
		
		TreeNode novi = new TreeNode();
		novi.value=value;
		novi.left=null;
		novi.right=null;
		
		if(root==null) {
			root=novi;
			return root;
		}
		
		if(containsValue(root, value)) {
			System.out.println("Čvor sa danom vrijednošću već postoji u stablu!");
			return root;
		} 
		
		TreeNode temp = root;
		
		while(temp!=null) {
			
			if(temp.value<value) {
				if(temp.right==null) {
					temp.right=novi;
					System.out.println("Dodano!");
					return root;
				}
				temp=temp.right;
			} else {
				if(temp.left==null) {
					temp.left=novi;
					System.out.println("Dodano!");
					return root;
				}
				temp=temp.left;
			}
		}
		return root;
	}
	
	/**
	 * Returns the tree size.
	 *
	 * @param root Root of the tree for which we are calculating size.
	 * @return size as integer
	 */
	public static int treeSize(TreeNode root) {
		
		if(root==null) {
			return 0;
		}
		
		return treeSize(root.left) + 1 + treeSize(root.right);
		
	}
	
	/**
	 * Checks if the tree contains passed value.
	 *
	 * @param root Root of the tree for which we are doing the check.
	 * @param value
	 * @return true, if successful
	 */
	public static boolean containsValue(TreeNode root, int value) {
		
		if(root==null) {
			return false;
		}
		
		if(root.value==value) {
			return true;
		} else if(root.value<value) {
			return containsValue(root.right, value);
		} else {
			return containsValue(root.left, value);
		}
	}
	
	/**
	 * Prints out the elements of the tree in ascending order.
	 * @param root Root of the tree which we are printing out.
	 */
	public static void leftToRightInOrder(TreeNode root) {
		if (root==null) {
			return;
		}
		
		leftToRightInOrder(root.left);
		System.out.print(root.value+" ");
		leftToRightInOrder(root.right);
	}
	
	/**
	 * Prints out the elements of tree in descending order.
	 * 
	 * @param root Root of the tree which we are printing out
	 */
	public static void rightToLeftInOrder(TreeNode root) {
		if (root==null) {
			return;
		}
		
		rightToLeftInOrder(root.right);
		System.out.print(root.value+" ");
		rightToLeftInOrder(root.left);
	}
} 
