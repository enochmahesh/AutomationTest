package test.pickme;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Test(priority = 0)
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

    @Test(priority = 1,enabled = false)
    public  static void dropDwntest() {
        driver.get("http://the-internet.herokuapp.com/dropdown");
        WebElement dropDwn = driver.findElement(By.id("dropdown"));
        Select select=new Select(dropDwn);
        select.selectByVisibleText("Option 1");
        select.selectByVisibleText("Option 2");
    }
    @Test(priority = 2,enabled = false)
    public  static void getImages() {
        driver.get("http://the-internet.herokuapp.com/hovers");
        WebElement img1 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/img"));
        WebElement img2 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/img"));
        WebElement img3 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/img"));

        Actions act=new Actions(driver);
        act.moveToElement(img1).build().perform();
        act.moveToElement(img2).build().perform();
        act.moveToElement(img3).build().perform();

    }
    @Test(priority = 3,enabled = false)
    public  static void getWindows() {
        driver.get("http://the-internet.herokuapp.com/windows");
        WebElement clickLink = driver.findElement(By.xpath("/html/body/div[2]/div/div/a"));
        clickLink.click();
    }

    @AfterTest
    public  static void tearDown(){

        driver.close();
        driver.quit();
    }

}
