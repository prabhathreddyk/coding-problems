package edu.code.process;

import edu.code.model.BinaryTree;
import edu.code.model.BinaryTreeNode;
import static edu.code.process.utility.BinaryTreeUtility.*;
import edu.code.process.utility.CollectionUtils;

public class BinaryTreeImplementation {

	public static void main(String[] args) {
		BinaryTreeImplementation bti = new BinaryTreeImplementation();
		BinaryTree binaryTree = getBinaryTree();
		/*BinaryTree balancedBinaryTree = getBalancedBinaryTree();

		System.out.println("isBinaryTreeBalanced with Traverse:" + bti.isBinaryTreeBalanced(binaryTree));
		System.out.println("isBinaryTreeBalanced :" + (maxDepth(binaryTree.getRoot()) - minDepth(binaryTree.getRoot()) < 2));
		
		System.out.println("is BinaryTree BST : "+ CollectionUtils.isSorted(inOrderTraverse(binaryTree)));
		System.out.println("is BinaryTree BST : "+ CollectionUtils.isSorted(inOrderTraverse(balancedBinaryTree)));

		System.out.println("kth(2nd) smallest in BST : " + kthSmallestInBST(balancedBinaryTree, 2));
		System.out.println("kth(2nd) smallest in Not BST : " + kthSmallestInBST(binaryTree, 2));
		
		// Iterative 
		System.out.println(" Inorder Iterative: "+ inOrderTraverseIterative(balancedBinaryTree));
		System.out.println(" PreOrder Iterative: "+ preOrderIterative(balancedBinaryTree));
		System.out.println(" PostOrder Iterative: "+ postOrderIterative(balancedBinaryTree));
		
		// Recursive
		System.out.println(" PreOrder Recursive: "+ preOrderTraversal(balancedBinaryTree));
		System.out.println(" PostOrder Recursive: "+ postOrder(balancedBinaryTree));
		
		//Build Binary tree from Inorder and postOrder
		int[] inorder = {9,3,15,20,7};
		int[] postorder = {9,15,7,20,3};
		System.out.println("Root element : " + buildTree(inorder, postorder));*/
		
	//	System.out.println(serialize(binaryTree.getRoot()));
	//	System.out.println(deserialize( serialize(binaryTree.getRoot())));
		System.out.println(widthOfBinaryTree(binaryTree.getRoot()));


	}

	private static BinaryTree getBinaryTree() {
		BinaryTree binaryTree = new BinaryTree(1);
		binaryTree.getRoot().setLeft(new BinaryTreeNode(3, new BinaryTreeNode(5, null, null), null));
		binaryTree.getRoot().setRight(new BinaryTreeNode(2, null, null));
		return binaryTree;
	}
	
	private static BinaryTree getBalancedBinaryTree() {
		BinaryTree binaryTree = new BinaryTree(15);
		binaryTree.getRoot().setLeft(new BinaryTreeNode(4, null, null));
		binaryTree.getRoot().setRight(new BinaryTreeNode(20, new BinaryTreeNode(16, null, null), new BinaryTreeNode(22, null, null)));
		return binaryTree;
	}
	
	private boolean isBinaryTreeBalanced(BinaryTree binaryTree){
		inOrderTraverse(binaryTree);
		return binaryTree.getMaxDepth() - binaryTree.getMinDepth() < 2;
	}

}
