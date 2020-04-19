package seleniumworkouttestcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
				//Open chrome browser and Nykaa application
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				ChromeDriver driver = new ChromeDriver();
				driver.get("https://www.nykaa.com/");
				driver.manage().window().maximize();
				
				//To refresh the browser after opening Nykaa applciation
				driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, "r"));
				Thread.sleep(2000);
		
				//To click on L'Oreal Paris after hovering over 'popular' under 'Brands'
				Actions builder = new Actions(driver);
				WebElement Brands = driver.findElementByXPath("//a[text()='brands']");
				builder.moveToElement(Brands).perform();
				
				Actions builder1 = new Actions(driver);
				WebElement Popular = driver.findElementByXPath("//a[text()='Popular']");
				builder1.moveToElement(Popular).perform();
				
				driver.findElementByXPath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']").click();
				
				//To go to the newly opened window and check the title contains L'Oreal Paris
				Set<String> winSet = driver.getWindowHandles();
				List<String> winList = new ArrayList<String>(winSet);
				driver.switchTo().window(winList.get(1));
				
				String Title = driver.findElementByXPath("//h1[@class='heading--fancy']").getText();
				if (Title.contains("L'OREAL PARIS")) {
					System.out.println("Title contains L'OREAL PARIS");
				}
		
				//To choose customer top rated from Sort By dropdown and choose Shampoo
				driver.findElementByXPath("//span[text()='Sort By : ']").click();
				driver.findElementByXPath("//span[text()='customer top rated']").click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				String category = driver.findElementByXPath("//div[text()='Category']").getText();
				System.out.println(category);
				if (category.contains("category")) {
					driver.findElementByXPath("//div[text()='Category']").click();
				}
				WebElement expandcategory = driver.findElementByXPath("//div[text()='Category']");
				 WebDriverWait wait = new WebDriverWait(driver, 30);
				 wait.until(ExpectedConditions.elementToBeClickable(expandcategory));
				 driver.findElementByXPath("//div[@class='filter-sidebar__filter-title pull-left'][1]").click();
				String shampoofilter = driver.findElementByXPath("//span[text()='Shampoo (21)']").getText();
				if (shampoofilter.contains("Shampoo")) {
					driver.findElementByXPath("//span[text()='Shampoo (21)']").click();
					
				}
				String filtersapplied = driver.findElementByXPath("//ul[@class='pull-left applied-filter-lists']/li").getText();
				if (filtersapplied.contains("Shampoo")) {
					System.out.println("Shampoo is applied in filters");
				}
				
				//To click on 'L'Oreal Paris Colour Protect Shampoo'
				String shampooname = driver.findElementByXPath("(//div[@class='m-content__product-list__title'])[5]").getText();
				System.out.println(shampooname);
				Thread.sleep(2000);
				driver.findElementByXPath("(//div[@class='m-content__product-list__title'])[5]").click();
				
				//To go to the newly opened window and click on 175ml , print the MRP of the product
				Set<String> winSet1 = driver.getWindowHandles();
				List<String> winList1 = new ArrayList<String>(winSet1);
				driver.switchTo().window(winList1.get(2));
				driver.findElementByXPath("//span[text()='175ml']").click();
				String productMRP = driver.findElementByXPath("//span[@class='post-card__content-price-offer'][1]").getText();
				System.out.println(productMRP);
				
				//clicking on 'Add to Bag' , navigating to shopping bag and print the grand total
				driver.findElementByXPath("//button[text()='ADD TO BAG'][1]").click();
				driver.findElementByClassName("AddBagIcon").click();
				String grandTotal = driver.findElementByXPath("//div[@class='first-col']/div").getText();
				System.out.println(grandTotal);
				
				//Clicking on Proceed, continue as guest and print the warning message
				driver.findElementByXPath("//button[@class='btn full fill no-radius proceed ']").click();
				driver.findElementByXPath("//button[text()='CONTINUE AS GUEST']").click();
				String warningMessage = driver.findElementByClassName("message").getText();
				System.out.println(warningMessage);
				
				//close all windows
				driver.quit();
	}

}
