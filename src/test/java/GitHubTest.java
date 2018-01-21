import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * This class will contain a series of test skeletons for
 * exercising the following Github features.
 *
 * 1. A test to see if a registered user can successfully log in
 * 2. A test to see if an unregistered user cannot log in
 * 3. A test to see if a registered user can create a new repository
 * 4. A test to see if a registered user cannot create a new repository
 *    when it has the same name as an existing repository.
 *
 */
public class GitHubTest {

  private WebDriver driver;

  private static final String GIT_HUB_URL = "https://github.com";

  //Enter the username for the Github account you create here
  private static final String USERNAME = "mounika.daripally@gmail.com";

  //Enter the password for the Github account you create here.
  //Note: Do not use any of your real-life passwords!
  private static final String PASSWORD = "Test123!";
  
  private static final String INVALID_PASWORD = "test";
  
  private static final String EXP_TITLE = "GitHub";

  @Before
  public void setup() throws InterruptedException {
    driver = new ChromeDriver();

    
    // And now use this to visit Github
    driver.get(GIT_HUB_URL);
    driver.manage().window().maximize();
    Thread.sleep(5000);
  }

  /**
   * Test to see if an user can successfully login to Github.
 * @throws InterruptedException 
   *
   * @TODO - Complete this test
   */
  @Test
  public void testGitHubLoginSuccess() throws InterruptedException {
	  driver.findElement(By.linkText("Sign in")).click();
	  Thread.sleep(3000);
	  WebElement UserName = driver.findElement(By.name("login"));
		UserName.sendKeys(USERNAME);
	  WebElement password = driver.findElement(By.name("password"));
        password.sendKeys(PASSWORD);	
      driver.findElement(By.name("commit")).click();
      Thread.sleep(4000);
      String actual_title = driver.getTitle();
      if (EXP_TITLE.equals(actual_title))
      {
    	  System.out.println("Verification Successful - User Logged In.");
      }
      else
      {
    	  System.out.println("Verification UnSuccessful - Invalid Credentials.");
     }
  }
  /**
   * Test to see if an unknown user cannot login to Github.
 * @throws InterruptedException 
   *
   * @TODO - Complete this test
   */
  
  @Test
  public void testGitHubLoginFailure() throws InterruptedException {
	  driver.findElement(By.linkText("Sign in")).click();
	  Thread.sleep(3000);
	  WebElement UserName = driver.findElement(By.name("login"));
		UserName.sendKeys(USERNAME);
	  WebElement password = driver.findElement(By.name("password"));
        password.sendKeys(INVALID_PASWORD);	
      driver.findElement(By.name("commit")).click();
      Thread.sleep(4000);
      String actual_title = driver.getTitle();
      if (EXP_TITLE.equals(actual_title))
      {
    	  System.out.println("Verification Successful - User Logged In.");
      }
      else
      {
    	  System.out.println("Verification UnSuccessful - Invalid Credentials.");
     }
  }
  /**
   * Test to see that a registered user can successfully create
  Thread.sleep(4000);
   * a new repository.
 * @throws InterruptedException 
   *
   * @TODO - Complete this test
   */

  @Test
  public void testStartAProjectSuccess() throws InterruptedException {
	  driver.findElement(By.linkText("Sign in")).click();
	  Thread.sleep(3000);
	  WebElement UserName = driver.findElement(By.name("login"));
		UserName.sendKeys(USERNAME);
	  WebElement password = driver.findElement(By.name("password"));
        password.sendKeys(PASSWORD);	
      driver.findElement(By.name("commit")).click();
      Thread.sleep(4000);
      driver.findElement(By.linkText("New repository")).click();
      String s = RandomStringUtils.randomAlphabetic(10); 
      driver.findElement(By.name("repository[name]")).sendKeys(s);
      Thread.sleep(2000);
      boolean submitbuttonEnabled = driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[4]/button")).isEnabled();
      if (submitbuttonEnabled==true)
      {
    	WebElement submitbutton = driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[4]/button"));
    	submitbutton.click();
    	Thread.sleep(2000);
    	System.out.println("Repository Created!");
      }
    }

  /**
   * Test to see that a registered user cannot create a new repository
   * when it has the same name as an existing repository.
 * @throws InterruptedException 
   *
   * @TODO - Complete this test
   */
  
  @Test
  public void testStartAProjectFailure() throws InterruptedException {
	  driver.findElement(By.linkText("Sign in")).click();
	  Thread.sleep(3000);
	  WebElement UserName = driver.findElement(By.name("login"));
		UserName.sendKeys(USERNAME);
	  WebElement password = driver.findElement(By.name("password"));
        password.sendKeys(PASSWORD);	
      driver.findElement(By.name("commit")).click();
      Thread.sleep(4000);
      String repo_name = driver.findElement(By.xpath("//*[@id='your_repos']/div/div[2]/ul/li[1]/a/span/span")).getText();
      driver.findElement(By.linkText("New repository")).click();
      //Considering that the repository with name "test" is already present.
     // driver.findElement(By.name("repository[name]")).sendKeys("test");
      driver.findElement(By.name("repository[name]")).sendKeys(repo_name);
      Thread.sleep(2000);
      boolean submitbuttonEnabled = driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[4]/button")).isEnabled();
      if (submitbuttonEnabled==true)
      {
    	WebElement submitbutton = driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[4]/button"));
    	submitbutton.click();
      }
      else
      {
    	  System.out.println("Repository already exists. Use a different Name.");
    }
  }

  @After
  public void tearDown() {
    driver.close();
  }

}
