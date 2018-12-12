/*
 * 
 BST.java
 
 Binary Search Tree Exercises
 COSC 102, Colgate University
 
 */


import java.util.*;

public class BST
{
  // component node class
  static class Node
  {
    int key;
    Node left;
    Node right;
    
    Node(int key) { super(); this.key = key; }
  }
  
  // keeps track of root and size
  public Node root;
  private int size;
  
  
  // method to access number of keys in the tree
  public int size()
  {
    return size;
  }
  
  // standard BST insert -- non-balancing
  public boolean insert (int key)
  {
    if (root == null) {
      root = new Node(key);
      size = 1;
      return true;
    }
    
    return insert(root, key);    
  }
  
  // recursive helper for insert
  private boolean insert (Node n, int key)    
  {
    if (n.key == key)
      return false;
    
    if (key > n.key) {
      if (n.right == null) {
        n.right = new Node(key);
        size++;
        return true;
      }
      else
        return insert(n.right, key);
    }
    else {  // key < n.key
      if (n.left == null) {
        n.left = new Node(key);
        size++;
        return true;
      }
      else
        return insert(n.left, key);
    }
  }
  
  //returns the height of the BST
  public int height(){
    
    return height(this.root);
    
  }
  
  
  //recursive helper for height calculation
  private int height(Node root){
    if (root == null)
      return 0;      
    return 1 + Math.max(height(root.left), height(root.right));
      
      
  }
  
  // tests if an element is in the tree
  public boolean contains (int key)
  {
    Node n = root;
    while (n != null) {
      if (n.key == key) return true;
      if (key < n.key)
        n = n.left;
      else
        n = n.right;
    }
    
    return false;
  }
  
  // prints a preorder traversal, with X for null pointers
  public void preorder()
  {
    //Makes the initial call to the recursive helper method
    preorder(root);
    //Prints the newline character at the end of the output
    System.out.println();
  }
  
  // recursive helper for preorder traversal
  private void preorder(Node root)
  {
    //If the node is null, print an X
    if (root == null) {
      System.out.print("X ");
      return;
    }

    //otherwise, print the key value  
    System.out.print(root.key);
    System.out.print(' ');
    
    //then recursively traverse the left and right children
    preorder(root.left);
    preorder(root.right);
  }
  
  
  
  /*************
    
    Complete the methods below
    
    *************/
  
    
    private void inorder(Node root)
  {
    //If the node is null, print an X
    if (root == null) {
      System.out.print("X ");
      return;
    }
    inorder(root.left);
    
    System.out.print(root.key);
    System.out.print(' ');
    
    inorder(root.right);
  }
  
  //Prints an inorder traversal of the BST
  public void inorder()
  {
    inorder(root);
    System.out.println();
    
  }
  
  
  public int shortestBranch()
  {
  	return heightShort(this.root);
  }
  
  private int heightShort(Node root){
    if (root == null)
      return 0;      
    return 1 + Math.min(height(root.left), height(root.right));
  }
  
  public int lowestAncestor(int key1, int key2){
  	  Node preserveRoot = root;
  	  return ancestorHelper(preserveRoot, key1, key2); 
  }
  	  
  
  public int ancestorHelper(Node root, int key1, int key2)
  {

  	  //if one of the keys isn't in the tree
  	  if (!this.contains(key1) && !this.contains(key2)){
  		throw new NoSuchElementException();
  	  }
  	  
  	  //if there is no root
  	  if (root == null){
  	  	  return root.key;
  	  }
  	  
  	  //if the keys equal the roots
  	  if(key1 == root.key || key2 == root.key){
  	  	  return root.key;
  	  }
  	  
  	  //if the keys are on the right side
  	  if (key1 < root.key && key2 < root.key){
  	  	  root = root.left;
  	  	  ancestorHelper(root, key1, key2);
  	  }
  	  
  	  //if the keys are on the left side
  	  if (key1 > root.key && key2 > root.key){
  	  	  root = root.right;
  	  	  ancestorHelper(root, key1, key2);
  	  }
  	  
  	  return root.key;
   }
  
  
  
  public boolean equals(Object other)
  {
  	 if (other instanceof BST == false)
  	 	 return false;
  	 BST otherCasted = (BST) other;
  	 return equalsHelper(this.root, otherCasted.root);
  }
  	 	 
  public boolean equalsHelper(Node one, Node other){
  	  //if they are both empty
  	  if (one == null && other == null){
  	  	  return true;
  	  }
  	  
  	  if (one.key != other.key){
  	  	  return false;
  	  }

  	  return equalsHelper(one.left, other.left) && equalsHelper(one.right, other.right);
  	  
  	  //if one value doesn't equal the other
    
  }
  
  
  /************
    * 
    for testing
    
    ************/
  
  public static void main (String[] args)
  {
  	   BST tree = new BST();
       tree.insert(3);
       tree.insert(1);
       tree.insert(7);
       tree.insert(5);
       tree.insert(9);
       tree.insert(2);
       tree.insert(6);
       tree.insert(8);
       
       //Orders
       
       //tree.inorder();
       //tree.preorder();
      
      //Heights
      
      //System.out.println(tree.height());
      //System.out.println(tree.shortestBranch());
      
      //Lowest Ancestors
      
      //tree.preorder();
      //right
      //System.out.println(tree.lowestAncestor(5,8));
      //System.out.println(tree.lowestAncestor(5,9));
      //tree.preorder();
      //System.out.println(tree.lowestAncestor(6,9));
      
      //left
      //System.out.println(tree.lowestAncestor(1,2));
      
      
      BST treetwo = new BST();
      treetwo.insert(50);
      treetwo.insert(30);
      treetwo.insert(20);
      treetwo.insert(40);
      treetwo.insert(70);
      treetwo.insert(60);
      treetwo.insert(80);
      //System.out.println(treetwo.lowestAncestor(20, 40));
      
     
      BST duptree = new BST();
      duptree.insert(50);
      duptree.insert(30);
      duptree.insert(20);
      duptree.insert(40);
      duptree.insert(70);
      duptree.insert(60);
      duptree.insert(80);
     //System.out.println(treetwo.equals(duptree));
     //System.out.println(treetwo.equals(tree));
     
     
     BST testTree = new BST();
      testTree.insert(8);
      testTree.insert(3);
      testTree.insert(1);
      testTree.insert(6);
      testTree.insert(4);
      testTree.insert(7);
      testTree.insert(10);
      testTree.insert(14);
      testTree.insert(13);
      
      //testTree.inorder()
      //testTree.preorder()
  }
  
  
}