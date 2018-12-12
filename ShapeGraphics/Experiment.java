import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// Driver class
// YOU DO NOT NEED TO MODIFY ANY CODE IN THIS FILE

public class Experiment {
  
  public static final int FRAME_WIDTH = 600;
  public static final int FRAME_HEIGHT = 600;
  
  private static final int RGBMAX = 256;
  
  private static final int DEFAULT_NUM_OF_SHAPES = 12;
  private static int numOfShapes;
  private static Random random = new Random();
  
  public static Color generateRandomColor(Color mix) {
    
    int red = random.nextInt(RGBMAX);
    int green = random.nextInt(RGBMAX);
    int blue = random.nextInt(RGBMAX);
    
    // mix the color
    if (mix != null) {
      red = (red + mix.getRed()) / 2;
      green = (green + mix.getGreen()) / 2;
      blue = (blue + mix.getBlue()) / 2;
    }
    
    Color color = new Color(red, green, blue);
    return color;
  }
  
  public static double getRandomX() {
    return random.nextInt(FRAME_WIDTH-RecShape.DEFAULT_SIZE);
  }
  
  public static double getRandomY() {
    return random.nextInt(FRAME_HEIGHT-RecShape.DEFAULT_SIZE);
  }
  
  private static void createAndShowGUI() {
    
    JFrame f = new JFrame("Experiment");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    
    DrawingCanvas canvas = new DrawingCanvas(numOfShapes);
    f.add(canvas);
    
    f.setBackground(Color.WHITE);
    f.setVisible(true);
    canvas.requestFocus();
  }
  
  
  public static void main (String[] args) {
    numOfShapes = DEFAULT_NUM_OF_SHAPES;
    if (args.length > 0){
      try{
        numOfShapes = Integer.parseInt(args[0]); //converts String to int
      }
      catch (NumberFormatException nfe){
        System.err.println("Invalid argument entered!  Using default value for number of shapes.");
      }
    }

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI(); 
      }
    });
    
  }
}
