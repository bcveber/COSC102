/* class Browser
 * 
 * COSC 102, Colgate University
 * (c) 2014 Vijay Ramachandran, all rights reserved
 * 2015 Elodie Fourquet (removed RSS part...)
 * 
 * Implements a java.swing program to display HTML 3.2 web pages.
 * Uses functionality provided by the swing libraries.
 * Relies on BrowserHistory to implement some functionality.
 * 
 * main() will start the browser.  Takes no arguments.
 * 
 * DO NOT MODIFY THIS CODE.  
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

import java.net.*;
import java.io.*;
import java.util.regex.*;

public class Browser extends JPanel 
  implements ActionListener, ItemListener, HyperlinkListener {
  
  static final long serialVersionUID = 1;
  
  // Actionable JComponents, including the WebBrowserHelper
  private JButton back = new JButton("Back");
  private JButton fwd = new JButton("Forward");
  private JCheckBox src = new JCheckBox("Source");
  private JTextField addr = new JTextField(30);
  private JEditorPane ed = new JEditorPane();
  
  // helper object
  private BrowserHistory helper = new BrowserHistory(this);
  
  // Stores URL and content type currently displayed
  private String curURL = null;
  private enum Display {
    NONE, ERROR, HTML
  }
  private Display contentHTML = Display.NONE;
  private String curSrc = null;
  private URL dispURL = null;
  
  // Constructor;  creates window elements
  public Browser() {
    setLayout(new BorderLayout());
    
    // Toolbar
    JPanel toolbar = new JPanel(new FlowLayout());
    
    // Back button
    back.setActionCommand("Back");
    back.addActionListener(this);
    back.setEnabled(false);
    toolbar.add(back);
    
    // Forward button
    fwd.setActionCommand("Forward");
    fwd.addActionListener(this);
    fwd.setEnabled(false);
    toolbar.add(fwd);
    
    // Address text field
    addr.setActionCommand("AddressBar");
    addr.addActionListener(this);
    toolbar.add(addr);
    
    // Go button
    JButton go = new JButton("Go");
    go.setActionCommand("AddressBar");
    go.addActionListener(this);
    toolbar.add(go);
    
    // Source button
    src.addItemListener(this);
    src.setEnabled(true);
    toolbar.add(src);
    
    // add toolbar to the layout
    add(toolbar, BorderLayout.PAGE_START);
    
    // HTML pane
    ed.setEditable(false);
    ed.addHyperlinkListener(this);
    ed.setContentType("text/html");
    JScrollPane sp = new JScrollPane(ed);
    sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    sp.setPreferredSize(new Dimension(500, 400));
    sp.setMinimumSize(new Dimension(10, 10));
    
    add(sp, BorderLayout.CENTER);
  }
  
  // fetch page over the network
  private String getContent(URLConnection cnx) throws IOException, 
    UnknownServiceException {
    
    StringBuilder content = new StringBuilder();
    String line;
    
    BufferedReader in = new BufferedReader(new InputStreamReader(cnx.getInputStream()));
    
    while((line = in.readLine()) != null) {
      content.append(line);
      content.append("\n");
    }
    
    try { in.close(); } catch (IOException e) { }
    
    return content.toString();
  }
  
  // process the URL for loading, and call helper as necessary
  private boolean fireLink(String url) {
    
    if (url == null) return false;
    
    StringBuilder sb = new StringBuilder(url);
    int i = -1;
    while ((i = sb.indexOf(" ", i+1)) >= 0)
      sb.replace(i, i+1, "%20");
    
    try {
      url = new URI(sb.toString()).normalize().toASCIIString();
      URLConnection cnx = new URL(url).openConnection();
      String page = getContent(cnx);
      if (cnx.getContentType().contains("text/html")) {
        resetContentType();
        ed.setText("Please wait, page loading...");
        ed.getDocument().putProperty(
                                     Document.StreamDescriptionProperty, null);
        ed.setPage(url);
        setCurrent(url, Display.HTML, page);
        //helper.parseHTML(page);
      } else {
        JOptionPane.showMessageDialog(this.getTopLevelAncestor(),
                                      "Unsupported Content-Type\n" + cnx.getContentType());
        return false;
      }
      
      return true;
      
    } catch (URISyntaxException e) {
      JOptionPane.showMessageDialog(this.getTopLevelAncestor(),
                                    "Malformed URL\n" + url);
      return false;
    } catch (MalformedURLException e) {
      JOptionPane.showMessageDialog(this.getTopLevelAncestor(),
                                    "Malformed URL\n" + url);
      return false;
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this.getTopLevelAncestor(),
                                    "Error connecting to URL\n" + url);
      return false;
    }
  }        
  
  // hyperlink handler
  public void hyperlinkUpdate(HyperlinkEvent e) {
    if (e.getEventType()==HyperlinkEvent.EventType.ACTIVATED) {
      String url = e.getURL().toString();
      if (fireLink(url))
        helper.hyperlink(url);
    }
  }
  
  // view source
  private void resetContentType() {
    ed.setContentType("text/html");
    dispURL = null;
    src.setSelected(false);
  }
  
  public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.SELECTED) {
      switch (contentHTML) {
        case HTML:
          dispURL = ed.getPage();
          ed.setContentType("text/plain");
          ed.setText(curSrc);
          break;
          
        default:
          src.setSelected(false);
          break;
      }
    } else {
      switch (contentHTML) {
        case HTML:
          URL url = dispURL;
          resetContentType();
          ed.setText("Please wait, page loading...");
          try {
            ed.getDocument().putProperty(
                                         Document.StreamDescriptionProperty, null);
            ed.setPage(url);
          } catch (IOException ioe) {
            ed.setText("Error, could not re-display page.");
          }
          break;
          
        default:
          break;
      }
    }
  }
  
  // event handler
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Back")) {
      String url = helper.back();
      if (!fireLink(url)) {
        setCurrent(url, Display.ERROR, null);
        title(null);
        ed.setText("Page load unsuccessful. "
                     + "To reload, click the Go button.");
      }
    } else if (e.getActionCommand().equals("Forward")) {
      String url = helper.forward();
      if (!fireLink(url)) {
        setCurrent(url, Display.ERROR, null);
        title(null);
        ed.setText("Page load unsuccessful. "
                     + " To reload, click the Go button.");
      }
    } else if (e.getActionCommand().equals("AddressBar")) {
      String url = addr.getText();
      if (fireLink(url))
        helper.hyperlink(url);
    }
  }
  
  // GUI control for BroswerHistory
  
  // back and forward buttons
  protected void setBack(boolean enabled)
  { back.setEnabled(enabled); }
  
  protected void setFwd(boolean enabled)
  { fwd.setEnabled(enabled); }
  
  // contents of the address bar and internal display state
  private String setCurrent(String url, Display type, String page) {
    curSrc = page;
    curURL = url;
    if (url != null) {
      addr.setText(url);
      contentHTML = type;
    } else {
      addr.setText("");
      contentHTML = Display.NONE;
    }
    return url;
  }
  
  // title of the browser window
  protected void title(String title) {
    StringBuilder str = new StringBuilder("Browser");
    
    switch (contentHTML) {
      case NONE:
        break;
        
      case ERROR:
        str.append(" (Error): ");
        str.append(getURL());
        break;
        
      case HTML:
        str.append(" (HTML): ");
      str.append((title == null) ? getURL() : title);
      break;
    }
    
    ((JFrame) this.getTopLevelAncestor()).setTitle(str.toString());
  }
  
  // provides the current URL
  protected String getURL() { return curURL; }
  
  
  /* Browser.main()
   * 
   * Starts the web-browser.
   * Takes no arguments.
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      
      public void run() {
        JFrame frame = new JFrame("Browser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(new Browser());
        
        frame.pack();
        frame.setVisible(true);
        frame.toFront();
      }
    });
  }
}
