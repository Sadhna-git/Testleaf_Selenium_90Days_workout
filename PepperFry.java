package seleniumworkouttestcases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PepperFry {

	public static void main(String[] args) throws InterruptedException, IOException {
		//
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		
		options.merge(cap);
		
		//Open chrome browser and Honda application
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.pepperfry.com/");
		driver.manage().window().maximize();
		
		//To refresh the browser after opening Honda application
		driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, "r"));
		Thread.sleep(4000);
		
		//Hover over on Furniture and click office chairs under Chairs

		driver.switchTo().frame("notification-frame-~10cb64399");
		driver.findElementById("webklipper-publisher-widget-container-notification-close-div").click();
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		driver.findElementByXPath("//div[@id='regPopUp']/a[@href='javascript:void(0);']").click();
		Thread.sleep(2000);
		Actions builder = new Actions(driver);
		WebElement Furniture = driver.findElementByXPath("//a[text()='Furniture']");
		builder.moveToElement(Furniture).perform();
		Thread.sleep(2000);
		driver.findElementByXPath("//a[text()='Office Chairs']").click();
		
		//click Executive chairs and make the height as 50
		driver.findElementByXPath("//h5[text()='Executive Chairs']").click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.switchTo().frame("notification-frame-173040712");
		driver.findElementById("webklipper-publisher-widget-container-notification-close-div").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//div[3]/input[@type='number']").clear();
		driver.findElementByXPath("//div[3]/input[@type='number']").sendKeys("50");
		Thread.sleep(2000);
		driver.findElementByXPath("(//div[@class='pf-col sm-12'])[3]").click();
		
		//Add "Poise Executive Chair in Black Colour" chair to Wishlist
		Thread.sleep(2000);
		WebElement addtowishlist = driver.findElementByXPath("(//div[@class='pf-col md-4 sm-6 card-10x11 pf-margin-bottom20 clipprods'])[2]/div/div[4]/div[2]/a[@class='clip-heart-icn pf-right']");
		builder.moveToElement(addtowishlist).perform();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(addtowishlist));
		Thread.sleep(10000);
		addtowishlist.click();
		
		//Hover over on Homeware and click Pressure cookers
		WebElement homeware = driver.findElementByXPath("//a[text()='Homeware']");
		builder.moveToElement(homeware).perform();
		driver.findElementByXPath("//a[text()='Pressure Cookers']").click();
		
		//Filter with Prestige brand and 1 to 3 ltr capacity
		driver.findElementByXPath("//label[text()='Prestige']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//label[text()='1 Ltr - 3 Ltr']").click();
		Thread.sleep(3000);
		
		//Adding Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr to wishlist
		driver.findElementByXPath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']").click();
		
		//Verify the number of items in Wishlis
		String numberofwishlistitems = driver.findElementByXPath("//div[@class='wishlist_bar']/span").getText();
		if (numberofwishlistitems.equals("2")) {
			System.out.println("Number of Items in wish list is 2" );
		}
		
		//Navigate to Wishlist
		driver.findElementByClassName("wishlist_bar").click();
		Thread.sleep(15000);
		
		//Move Pressure Cooker only to Cart from Wishlist
		driver.findElementByXPath("//a[@class='gridview pf-icon pf-icon-grid-view']").click();
		Thread.sleep(5000);
		
		//Check for the availability for Pincode 600128
		driver.findElementByXPath("//a[@data-wishlistitem='1676140']").click(); 
		driver.findElementByXPath("//span[text()='Showing availability at']/following-sibling::input").sendKeys("600128");
		driver.findElementByXPath("//a[text()='Check']").click();
		Thread.sleep(2000);
		
		//Click Proceed to Pay Securely
		driver.findElementByXPath("//a[text()='Proceed to pay securely ']").click();
		Thread.sleep(2000);
		
		//Click Proceed to Pay
		driver.findElementByXPath("//a[text()='PLACE ORDER']").click();
		Thread.sleep(2000);
		
		//Capture the screenshot of the item under Order Item
		driver.findElementByXPath("//span[text()='(1 Items)']").click();
		WebElement ordersummary = driver.findElementByXPath("//li[@id='payment_cart_1676140']");
		File srcFile = ordersummary.getScreenshotAs(OutputType.FILE);
		File destFile=new File("./src/screenshots/screenshot2.png");
    	FileUtils.copyFile(srcFile, destFile);
    	
    	driver.close();
	}
}


