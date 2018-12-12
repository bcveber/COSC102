
public class MyLinkedList<E> {
  
  private int size;
  private Node<E> front;
  
  //Returns the number of items in the list
  public int getSize(){
    return size;
  }
  
  //Reterieves the item (not the Node) at the specified index
  public E get(int idx){
    if (idx >= size)
      throw new IllegalArgumentException("Index out of range!");
    return nodeAt(idx).data;
  }
  
  //Retrieves the Node (not the item) at the specified index
  private Node<E> nodeAt(int idx){
    Node<E> current = front;
    for (int i = 0; i < idx; i++)
      current = current.next;
    return current;       
  }
  
  //Adds item to the end of the list
  public void add(E item){
    add(size, item);
  }
  
  
  //Inserts item at specified index
  public void add(int idx, E item){
    if (idx < 0 || idx > size)
      throw new IllegalArgumentException("Index out of range!");
    if (idx == 0)
      front = new Node<E>(item, front);
    else{
      Node<E> current = nodeAt(idx-1);
      current.next = new Node<E>(item, current.next);
    }
    size++;
  }
  
  
  public String toString(){
    StringBuilder sb = new StringBuilder("[");
    if (size == 0)
      return "[]";
    Node<E> current = front;
    for (int i = 0; i < size - 1; i++){
      sb.append(current.data + " ==> ");
      current = current.next;
    }
    sb.append(current.data + "]");
    return sb.toString();
  }
  
  //Removes the first occurence of the argument item 
  //in the Linked List
  //implement me for Part 2.2! 
  public boolean remove(E item){
  	 Node<E> currentNode = front;

  	 //Front case
  	 if (currentNode.data.equals(item) && currentNode != null){
  	 	 front = currentNode.next;
  	 	 size = (size-1);
  	 	 return true;
  	 }
  	 //middle case / end case
  	 while (currentNode.next != null){
  	 	   	 	 
  	 	 if (currentNode.next.data.equals(item)){
  	 	 	 currentNode.next =  currentNode.next.next;
  	 	 	 size = (size-1);
  	 	 	 return true;
  	 	 }
  	 	 currentNode = currentNode.next; 	 
  	 }
  	 	  
    
    return false; //placeholder
  }
  
  
  //Returns the number of occurences of the argument
  //item in the LinkedList
  //implement me for Part 2.4! 
  public int findFrequency(E item)
  
  {
    return 0;  //placeholder (hint: you might want a recursive helper function!)
  }
  
  //Inner Node class
  private static class Node<E> {
    
    private E data;                 /** The data value. */
    private Node<E> next = null;    /** The link */
    
    public Node(E data, Node<E> next) {
      this.data = data;
      this.next = next;
    }
    
    public Node(E data) {
      this(data, null);
    }
  }
  
  public static void main(String[] args){
    MyLinkedList<String> x = new MyLinkedList<String>();
    x.add("cat");
    x.add("dog");
    x.add("bird");
    x.add(1, "frog");
   //x.remove("frog"); //middle test
    //x.remove("cat"); //front test
    //x.remove("bird"); //end test
    System.out.println(x);
  }
  
}
