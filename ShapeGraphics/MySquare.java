import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class MySquare extends RecShape {
  
  public MySquare(double x, double y) {
    super();
    shape = new Rectangle2D.Double(x, y, DEFAULT_SIZE, DEFAULT_SIZE);
  }
  
  //Look at the argument of this constructor.  What could you use this for?
  //You will need to modify code in here when implementing the 't' key
  public MySquare(RecShape s) { 
    super(s.getFillColor());
    System.out.println("Constructor called after 't' isn't complete");
    System.out.println("hint: " + s.getFillColor() + " "
                        + s.getShapeX() + " " + s.getShapeY());
    shape = new Rectangle2D.Double(s.getShapeX(),s.getShapeY(), s.getShapeWidth(), s.getShapeHeight());  //This doesn't work, the shape will just dissapear.
                                       //what is the issue?  Hint - look at the other constructor.
  }
  
  public void moveFrame(float moveX, float moveY) {
    shape.setFrame(getShapeX()+moveX, getShapeY()+moveY, 
                   getShapeWidth(), getShapeHeight());
  }

  
  public void scale(int fac) {
    double newSide = getShapeWidth()+fac*SIZE_INC;  
    shape.setFrame(getShapeX() - (fac*SIZE_INC/2), getShapeY() - (fac*SIZE_INC/2), 
                   getShapeWidth()+fac*SIZE_INC, 
                   getShapeHeight()+fac*SIZE_INC);
    
    
    //Important: Make the scale invariant point the center! (See lab description)

  }
  
  public String toString() {
    return "rectangle " + shape.toString() + " color " + fillColor;
  }
  
  
}
