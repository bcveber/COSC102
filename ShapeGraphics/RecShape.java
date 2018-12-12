import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.geom.RectangularShape;
import java.awt.Graphics2D;


public abstract class RecShape extends JPanel {
  //JPanel extends JComponent
  //http://docs.oracle.com/javase/7/docs/api/javax/swing/JPanel.html
  
  //JComponent is an abstract class
  //http://docs.oracle.com/javase/7/docs/api/javax/swing/JComponent.html
  
  //YOU DO NOT NEED TO MODIFY ANY CODE IN THIS FILE
  protected static int DEFAULT_SIZE = 50;
  protected static int SIZE_INC = 5;
  
  private boolean isSelected = false;

  private Color outlineColor = Color.BLACK;
  
  protected Color fillColor = Experiment.generateRandomColor(Color.WHITE);
  protected RectangularShape shape;  
  //RectangularShape parent of both Rectangle2D and Ellipse2D
  //http://docs.oracle.com/javase/7/docs/api/java/awt/geom/RectangularShape.html
  
  public RecShape() {
    super();
  }
  
  public RecShape(Color c) {
    super();
    fillColor = c;
  }
  
  public Color getFillColor() {
    return fillColor; 
  }
  
  public void setSelected(boolean b) {
    isSelected = b;
  }
  
  public boolean getSelected() {
    return isSelected;
  }
  
  public boolean contains(int x, int y) {
    return shape.contains(x, y);
  }
    
  public  double getShapeX() { return shape.getX(); }
  public  double getShapeY() { return shape.getY(); }
  public  double getShapeWidth() { return shape.getWidth(); }
  public  double getShapeHeight() { return shape.getHeight(); }
  
  // Since RecShape is an abstract class, its children
  // will need to write implementations of the below
  // abstract methods
  
  public abstract void moveFrame(float x, float y); 
  
  public abstract void scale(int i); 
  
  //This overrides the paintComponent method of JPanel
  public void paintComponent(Graphics g) { 
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    
    g2.setPaint(fillColor);
    g2.fill(shape);
    
    if (isSelected) {
      g2.setPaint(outlineColor);
      g2.draw(shape);
    }
  }
  
}
