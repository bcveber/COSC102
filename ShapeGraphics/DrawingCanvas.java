import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

public class DrawingCanvas extends JComponent {
  
  private static final int NONE = -1;
  
  private static Random random = new Random();
  
  //Provided: two hard-coded shapes
  //For Part 1, you will only draw these two hard coded shapes!
  private RecShape oneSquare;
  private RecShape oneCircle;
   
  //For Part 2, you will instead be using an array of many shapes!
  
  private DrawingCanvasMouseListener mouseListener;
  private RecShape[] shapes;
  
  //[For Part 2] It is reccomended that you keep track of which shape is selected
  //tracking its index will make some of these functions easier AND more efficient!
  private int selectedIndex = -1;
  
  public DrawingCanvas(int numOfShapes) {
    super();     // call the constructor of JComponent
    System.out.println("Canvas created, number of shapes = " + numOfShapes);  
                        //In Part 2, you will use the initArray() method instead!
    initArray(numOfShapes);
    initMouseListener();
  }
  /*
  private void initHardCoded() {
    oneSquare =  new MySquare(Experiment.getRandomX(), Experiment.getRandomY());
    oneCircle =  new MyCircle(Experiment.getRandomX(), Experiment.getRandomY());
  }
  */
  private void initArray(int numOfShapes) {
    
    // initializes your array of Shapes
    //  - Your window should start with the specified number of shapes (see the constructor's arguments)
    //  - Of these shapes, 50% should be squares and 50% should be circles

    shapes = new RecShape[numOfShapes];
    for (int i=0; i<numOfShapes; i++){
    	if (i%2 == 0){
    		shapes[i] = new MySquare(Experiment.getRandomX(), Experiment.getRandomY());
    	}
    	else{
    		shapes[i] = new MyCircle(Experiment.getRandomX(), Experiment.getRandomY());
    	}
    		
    }
    	
    
  }
  
  
  private void initMouseListener() {
    requestFocus();
    addKeyListener(new KeyAdapter() {
      
      // Handles keyboard events (keys pressed on the keyboard)
      public void keyPressed(KeyEvent e) {
        //Prints the key pressed
        System.out.println("key " + e.getKeyChar() + " " + e.getKeyCode());
        
        //'Q' quits the program
        if (e.getKeyCode()==KeyEvent.VK_Q)
          System.exit(1);
        
        //'+' Grows the shape
        else if (e.getKeyCode()==KeyEvent.VK_EQUALS) 
          scaleSelectedShape(1);
        
        //'-' Shrinks the Shape
        else if (e.getKeyCode()==KeyEvent.VK_MINUS) 
          scaleSelectedShape(-1);
        
        //'t' switches the selected item to the opposite shape
        else if (e.getKeyCode()==KeyEvent.VK_T) 
          switchSelectedShape();
      }
    });
    mouseListener = new DrawingCanvasMouseListener();
    addMouseListener(mouseListener);
    addMouseMotionListener(mouseListener);
  }
  
  
  // (re)Paints all of the shapes in the window
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    
    Graphics2D g2d = (Graphics2D)g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON);
    
    //For Part 1, this is just painting the two hard coded RecShape objects
    //oneCircle.paintComponent(g2d);  
    //oneSquare.paintComponent(g2d);   
    
    //For Part 2, this will now be printing everything in your array
    	
    for (int i=0; i<shapes.length; i++){ // runs a for loop painting each square
    	shapes[i].paintComponent(g2d);		
    }
    	
    
  }
  
  //Scales the currently selected shape by a specific vector
  //(Either +1 or -1)
  public void scaleSelectedShape(int vector) {

    //For Part 1, determines which of the hard-coded shapes is selected
    for (int i=0; i < shapes.length; i++){
		if (shapes[i].getSelected())
		  shapes[i].scale(vector);
    
    //For Part 2, you will need to only scale the selected shape in your array
    repaint();      
    
  }
  }
  
  //Ensures that all shapes are deselected
  public void deselect() {
    //For Part 1, this just needs to look at the two hard coded shapes
    //if( selectedIndex < 0) return; //already deselected
    if (selectedIndex>=0 && shapes[selectedIndex].getSelected())
      shapes[selectedIndex].setSelected(false);
    
    //For Part 2, you will need to determine which shape in your array is selected
    selectedIndex = -1;
  }
  
  
  //Switches the currently selected shape to be the opposite shape
  //retains the original shape's color and size
  //(ex: if the shape is a Circle it becomes a Square)
  public void switchSelectedShape() {
    //Complete this method!
    //For Part 1, just looks at the two hard coded shapes
    //For Part 2, you will need to look through your array
    //(instanceof will be handy here!)
    if (selectedIndex != NONE) {
      if (oneSquare.getSelected()) {
        if (oneSquare instanceof MySquare) {
          oneSquare = new MyCircle(oneSquare);
          
        } else {
          oneSquare = new MySquare(oneSquare);
        }
        oneSquare.setSelected(true);
        selectedIndex = 0;
        repaint();
        return;
      } else {
      	  if (oneCircle instanceof MyCircle) {
          oneCircle = new MySquare(oneCircle);
          
        } else {
          oneCircle = new MyCircle(oneCircle);
        }
        oneCircle.setSelected(true);
        selectedIndex = 0;
        repaint();
        return;
        
      }
     
    }
  }
  
  /**
   * An inner class to respond to mouse events.
   */
  private class DrawingCanvasMouseListener implements MouseListener,
    MouseMotionListener {
    
    private int newX, newY;
    
    //Handles the mouse click event
    public void mousePressed(MouseEvent event) {
      
      newX = event.getX();
      newY = event.getY();
      
      //For Part 1 - this will determine if the mouse was clicked inside
      //either of the hard-coded shapes (using the contains method)
      
      //For Part 2 - this will need to check all of the shapes in your array
      //to determine which one (if any) were selected.
      
      //Remember, more recently selected shapes should be drawn ON TOP 
      //of less recently selected shapes!  
      for (int i=0; i < shapes.length; i++){
      	  
		  if (shapes[i].contains(newX, newY)) {
			deselect();
			selectedIndex = i;
			shapes[i].setSelected(true);
			repaint();
			return;
		  }
      //if neither is selected by mouse down the previously selected
      //shape is deselected
      deselect();
      repaint();
      
    }
    }
    
    /**
     * Handles dragging a shape 
     * the selected shape, if there is one selected, is moved
     */
    public void mouseDragged(MouseEvent event) {          
      //For Part 2, you will need to move whichever shape is selected
      //in your array.  You should NOT search through your entire
      //array to find your selected shape (look at your instance variables!)
      
		  if (selectedIndex != NONE) {
			int moveX = event.getX()-newX;
			int moveY = event.getY()-newY;
			
			if (shapes[selectedIndex].getSelected())
			  shapes[selectedIndex].moveFrame(moveX, moveY);
			
			
			
			newX = event.getX();
			newY = event.getY();
			repaint(); 
		  }
      
    }
    
    
    
    /*
     * These methods are required by the interfaces. 
     * You don't need to modify the below code
     */
    
    public void mouseReleased(MouseEvent event) { }
    
    public void mouseClicked(MouseEvent event) { }
    
    public void mouseEntered(MouseEvent event) { }
    
    public void mouseExited(MouseEvent event) { }
    
    public void mouseMoved(MouseEvent event) { }
    
  }
}
