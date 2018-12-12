import java.util.Stack;

class BrowserHistory {
  
  // pointer to the browser window -- use for method calls
  private Browser browser;
  private Stack<String> backStack;
  private Stack<String> forwardStack;
  private String prevURL;
  
  
  protected BrowserHistory(Browser browser) {
  	  this.browser = browser;
  	  backStack = new Stack<String>(); //back urls
  	  forwardStack = new Stack<String>(); //forward urls
  	  prevURL = browser.getURL();
  }
  
  //return URL to go back to
  protected String back() {
  	  String urlToView = backStack.pop();
  	  forwardStack.push(browser.getURL());
  	  browser.setFwd(true);
  	  
  	  if (backStack.empty()) {
  	  	  browser.setBack(false); 
  	  }
  	  
  	  prevURL = urlToView;
  	  
  	  

  	  return urlToView;
  }
  
  //return URL to go forward to
  protected String forward() {
  	  browser.setBack(true);
  	  String urlToView = forwardStack.pop();
  	  backStack.push(browser.getURL());
  	  
  	  if (forwardStack.empty()) {
  	  	  browser.setFwd(false); 
  	  }
  	  
  	  prevURL = urlToView;
  	  
  	  return urlToView;
  }
  
  
  //called after a hyperlinked URL is succesfully reached
  protected void hyperlink(String url) {
  	  
  	  forwardStack = new Stack<String>();
  	  browser.setFwd(false);
  	 
  	  
  	  if (prevURL != null){
  	  	  browser.setBack(true);
  	  	  backStack.push(prevURL);
  	  }
  	  
  	  prevURL = url;

    System.out.println("hyperlink called: " + url);  //demonstrate when called by browser
    return;
  }
  
} 

