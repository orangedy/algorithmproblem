package com.dy.basicAlgorithm;

import java.util.Stack;

public class TreeAlgorithm {

	/**
	 * 根据后序序列和中序序列求先序序列 同理可通过先序和中序求后序，但是必须要知道中序才能解题
	 * 注意，序列中不能存在相同的元素，否则直接用字符查找会出错
	 * 
	 * @param postOrder
	 * @param inOrder
	 */
	public static void printPreOrderByOther(String postOrder, String inOrder) {
		int length = postOrder.length();
		if (length == 1) {
			System.out.print(postOrder + " ");
			return;
		}
		char root = postOrder.charAt(length - 1);
		int indexOfInOrder = inOrder.indexOf(root);
		System.out.print(root + " ");
		if (indexOfInOrder > 0) {
			printPreOrderByOther(postOrder.substring(0, indexOfInOrder), inOrder.substring(0, indexOfInOrder));
		}
		if (indexOfInOrder < length) {
			printPreOrderByOther(postOrder.substring(indexOfInOrder, length - 1), inOrder.substring(indexOfInOrder + 1));
		}
	}

	public static void testPrintPreOrderByOther() {
		String postOrder = "cdbfgea";
		String inOrder = "cbdafeg";
		printPreOrderByOther(postOrder, inOrder);
		System.out.println();
	}

	/**
	 * 由后序序列和中序序列，构建树，并输出树的先序序列
	 * 
	 * @param postOrder
	 * @param inOrder
	 */
	public static void buildTreeByPostOrderAndInOrder(String postOrder, String inOrder) {
		Tree tree = buildTree(postOrder, inOrder);
		printTreeInPreOrder(tree);
		System.out.println();
	}

	/**
	 * 递归实现，每次找出根节点，然后递归的找左右孩子节点
	 * @param postOrder
	 * @param inOrder
	 * @return
	 */
	public static Tree buildTree(String postOrder, String inOrder) {
		Tree root = new Tree();
		int length = postOrder.length();
		if (length == 1) {
			root.data = postOrder.charAt(0);
			return root;
		}
		char rootData = postOrder.charAt(length - 1);
		root.data = rootData;
		int indexOfInOrder = inOrder.indexOf(rootData);
		if (indexOfInOrder > 0) {
			root.leftChild = buildTree(postOrder.substring(0, indexOfInOrder), inOrder.substring(0, indexOfInOrder));
		}
		if (indexOfInOrder < length) {
			root.rightChild = buildTree(postOrder.substring(indexOfInOrder, length - 1),
					inOrder.substring(indexOfInOrder + 1));
		}
		return root;
	}

	/**
	 * 树的先序遍历方法，递归实现，比较简单
	 * 
	 * @param tree
	 */
	public static void printTreeInPreOrder(Tree tree) {
		if (tree == null) {
			return;
		} else {
			visit(tree);
			printTreeInPreOrder(tree.leftChild);
			printTreeInPreOrder(tree.rightChild);
		}
	}
	
	/**
	 * 树的先序遍历方法，非递归实现，通过栈保存数据
	 * 
	 * @param tree
	 */
	public static void printTreeInPreOrder1(Tree tree){
		Stack<Tree> stack = new Stack<Tree>();
		if(tree == null){
			return;
		} else {
			stack.push(tree);
			while(!stack.isEmpty()){
				Tree node = stack.pop();
				visit(node);
				if(node.rightChild != null){
					stack.push(node.rightChild);
				}
				if(node.leftChild != null){
					stack.push(node.leftChild);
				}
			}
		}
	}
	
	/**
	 * 逻辑稍微复杂些，根据递归进行推算
	 * 
	 * @param tree
	 */
	public static void printTreeInPreOrder2(Tree tree){
		Stack<Tree> stack = new Stack<Tree>();
		if(tree == null){
			return;
		} else {
			Tree node = tree;
			while(node != null){
				visit(node);
				if(node.leftChild != null){
					stack.push(node.rightChild);
					node = node.leftChild;
				} else if(node.rightChild != null){
					node = node.rightChild;
				} else {
					if(stack.isEmpty()){
						return;
					}
					node = stack.pop();
				}
			}
		}
	}
	
	/**
	 * 树的中序遍历方法，递归实现，比较简单
	 * 
	 * @param tree
	 */
	public static void printTreeInInOrder(Tree tree) {
		if (tree == null) {
			return;
		} else {
			printTreeInInOrder(tree.leftChild);
			visit(tree);
			printTreeInInOrder(tree.rightChild);
		}
	}
	
	/**
	 * 树的中序遍历方法，非递归实现，逻辑有点绕，需要好好理解
	 * @param tree
	 */
	public static void printTreeInInOrder1(Tree tree) {
		Stack<Tree> stack = new Stack<Tree>();
		if(tree == null){
			return;
		} else {
			Tree node = tree;
			//逻辑过于复杂
			/*while(node != null || !stack.isEmpty()){
				if(node.leftChild != null){
					//左孩子不为空，当前节点入栈，指针指向左孩子
					stack.push(node);
					node = node.leftChild;
				}else{
					//左孩子为空，访问当前节点
					visit(node);
					if(node.rightChild != null) {
						//如果当前节点右孩子不为空，指针指向右孩子
						node = node.rightChild;
					} else{
						//如果当前节点右孩子为空，指针需要回溯到当前节点父节点
						//同时访问父节点后将指针移向父节点右孩子，即当前节点的右兄弟节点
						if(stack.isEmpty()){
							return;
						}
						node = stack.pop();
						visit(node);
						node = node.rightChild;
					}
				}
			}*/
			//简单的解法
			while(node != null || !stack.isEmpty()){
				if(node != null){
					stack.push(node);
					node = node.leftChild;
				} else {
					node = stack.pop();
					visit(node);
					node = node.rightChild;
				}
			}
		}
	}
	
	/**
	 * 树的后序遍历方法，递归实现，比较简单
	 * 
	 * @param tree
	 */
	public static void printTreeInPostOrder(Tree tree) {
		if (tree == null) {
			return;
		} else {
			printTreeInPostOrder(tree.leftChild);
			printTreeInPostOrder(tree.rightChild);
			visit(tree);
		}
	}
	
	/**
	 * 直接写较为复杂，但是可以转换思想将后序的左右中，逆转为中右左，这样其实与先序的中左右类似了
	 * 
	 * @param tree
	 */
	public static void printTreeInPostOrder1(Tree tree) {
		String invertStr = "";
		Stack<Tree> stack = new Stack<Tree>();
		if(tree == null){
			return;
		}else{
			stack.push(tree);
			while(!stack.isEmpty()){
				Tree node = stack.pop();
				invertStr += node.data;
				if(node.leftChild != null){
					stack.push(node.leftChild);
				}
				if(node.rightChild != null) {
					stack.push(node.rightChild);
				}
			}
			int length = invertStr.length();
			for(int i = 0; i < length; i++){
				System.out.print(invertStr.charAt(length - i - 1) + " ");
			}
		}
	}
	
	/**
	 * 访问函数，简单打印
	 * @param node
	 */
	public static void visit(Tree node){
		System.out.print(node.data + " ");
	}
	

	public static void main(String[] args) {
		//测试后序中序求先序
		System.out.println("测试后序中序求先序");
		testPrintPreOrderByOther();
		
		//测试构建树
		System.out.println("测试构建树");
		String postOrder = "cdbfgea";
		String inOrder = "cbdafeg";
		buildTreeByPostOrderAndInOrder(postOrder, inOrder);

		//测试遍历树
		System.out.println("测试遍历树");
		Tree tree = buildTree(postOrder, inOrder);
		System.out.println("先序递归");
		printTreeInPreOrder(tree);
		System.out.println("先序非递归");
		printTreeInPreOrder1(tree);
		System.out.println("先序非递归");
		printTreeInPreOrder2(tree);
		System.out.println("中序递归遍历");
		printTreeInInOrder(tree);
		System.out.println("中序非递归遍历");
		printTreeInInOrder1(tree);
		System.out.println("后续递归遍历");
		printTreeInPostOrder(tree);
		System.out.println("后续非递归遍历");
		printTreeInPostOrder1(tree);
		
	}
}

class Tree {
	char data;
	Tree leftChild;
	Tree rightChild;
}
