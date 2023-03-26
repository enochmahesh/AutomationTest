package test.pickme;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Set;

public class SeleniumTests{
    public static WebDriver driver;
    @BeforeTest
    public void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test(priority = 0,enabled = true)
    public  static void getTableData() {
        driver.get("http://the-internet.herokuapp.com/tables");
        WebElement table = driver.findElement(By.id("table1"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        int i;
            for (i = 0; i < rows.size(); i++) {
                List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
                    System.out.println("  ");
                int j;
                for (j = 0; j < cells.size()-1; j++) {
                    System.out.print(cells.get(j).getText()+"  ");
                }
            }
    }
    @Test(priority = 1,enabled = true)
    public  static void dropDwntest() {
        String optionTxt = null;
        driver.get("http://the-internet.herokuapp.com/dropdown");
        WebElement dropDwn = driver.findElement(By.id("dropdown"));
        Select select=new Select(dropDwn);
        List<WebElement> optList=select.getOptions();
        boolean isOptionPresent = false;
        for (WebElement option : optList) {
            optionTxt = option.getText();
            System.out.println(optionTxt);
            if (optionTxt.equals("Option 2")) {
                isOptionPresent = true;
                  }
        }
        Assert.assertEquals(isOptionPresent,true);


    }
    @Test(priority = 2,enabled = true)
    public  static void getImages() {
        boolean isTxt=false;
        driver.get("http://the-internet.herokuapp.com/hovers");
        List<WebElement> imgs=driver.findElements(By.className("figure"));
        Actions act=new Actions(driver);
        for (int i=0;i<imgs.size();i++) {
            act.moveToElement(imgs.get(i)).build().perform();
            String hovTxt=imgs.get(i).getText();
            System.out.println("Hovering text is " + imgs.get(i).getText());
            if (hovTxt.contains("user1")){
                isTxt=true;
            }
            Assert.assertEquals(isTxt,true);
        }
    }
    @Test(priority = 3,enabled = true)
    public  static void getWindows() throws InterruptedException {

        // Get current window handle
        String mainWindowHandle = driver.getWindowHandle();

        driver.get("http://the-internet.herokuapp.com/windows");
        String currentTitle= driver.getTitle();
        Thread.sleep(2000);
        WebElement clickLink = driver.findElement(By.xpath("/html/body/div[2]/div/div/a"));
        clickLink.click();

        // Get all window handles
        Set<String> allWindowHandles = driver.getWindowHandles();

        // Loop through the window handles and switch to the new tab
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        String newTitle= driver.getTitle();
        driver.switchTo().window(mainWindowHandle);
        System.out.println("The title of the first tab was "+"\""+currentTitle+"\""+" and the new tab title is "+"\""+newTitle+"\""+".");

        // Switch back to the original tab
        driver.switchTo().window(mainWindowHandle);
        System.out.println("The user has come back to the first tab. The title text is "+"\""+driver.getTitle()+"\"");
    }
    @AfterTest
    public  static void tearDown(){

        driver.close();
        driver.quit();
    }
}
