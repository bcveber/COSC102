  /*
  Queue function to be used in conjunction with radix sort. 
  */

public class MyQueue{
  
  private static class Node {
    
    private String data;
    private Node next = null;
    
    public Node(String data, Node next) {
      this.data = data;
      this.next = next;
    }
    
    public Node(String data) {
      this(data, null);
    }
  }
  
  
  private Node head = null;  
  private Node tail = null; 
  private int size = 0;     
    
    public void concat(MyQueue other){
    	if (other == null || other.size == 0){
    		return;
    	}
    	
    	if (this.size == 0){
    		this.size = other.size;
    		this.head = other.head;
    		this.tail = other.tail;
    	}
    		
    	else{
    	this.size = (other.size + this.size);
    	this.tail.next = other.head;
    	this.tail = other.tail;
    	}
    	
    	other.tail = null;
    	other.head = null;
    	other.size = 0;
  }
  
  public void add(String s){
    Node n = new Node(s);
    if (this.size == 0)
      head = tail = n;
    else{
      tail.next = n;
      tail = n;
    }
    size++;
  }

  public int size() {
    return size;
  }

  public String remove(){
    if (head == null)
      throw new IllegalStateException("Cannot remove from an empty MyQueue");
    Node temp = head; 
    head = head.next;
    if (size == 1)
      head = tail = null;
    size--;
    return temp.data;
  }

   public String toString() {
    StringBuilder sb = new StringBuilder("[");
    Node p = head;
    if (p != null) {
      while (!p.equals(tail)) {
        sb.append(p.data.toString());
        sb.append(" ==> ");
        p = p.next;
      }
      sb.append(p.data.toString());
    }
    sb.append("]");
    return sb.toString();
  } 
  
  public static void main(String[] args){
  	  MyQueue x = new MyQueue();
  	  x.add("hello");
  	  x.add("hey");
  	  
  	  MyQueue y = new MyQueue();
  	  y.add("yo");
  	  y.add("sup");
  	  x.concat(y);
  	  System.out.println(x);
  }
  	  
  
  
}


