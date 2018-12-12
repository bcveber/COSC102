import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class MyCircle extends RecShape {
  
  public MyCircle(double x, double y) {
    super();
    System.out.println(x + " " + y);
    shape  = new Ellipse2D.Double(x, y, DEFAULT_SIZE, DEFAULT_SIZE);
  }
   

  public MyCircle(RecShape s) { 
    super(s.getFillColor());
    shape = new Ellipse2D.Double(s.getShapeX(), s.getShapeY(), s.getShapeWidth(), s.getShapeHeight());
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
    //Important: Make the scale invariant point the center!  (See lab description)
    }
  
  
  public String toString() {
    return "circle " + shape.toString() + " color " + fillColor;
  }
}
