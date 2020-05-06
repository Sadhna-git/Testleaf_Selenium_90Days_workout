package seleniumworkouttestcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ajio {

	public static void main(String[] args) throws InterruptedException {
		//Open chrome browser and BigBasket application
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.ajio.com/shop/sale");
		driver.manage().window().maximize();
		
		//To refresh the browser after opening BigBasket application
		driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, "r"));
		Thread.sleep(2000);
		
		//Enter Bags in the Search field and Select Bags in Women Handbags
		driver.findElementByXPath("//input[@name='searchVal']").sendKeys("Bags");
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='Bags in ']/following-sibling::span[text()='Women Handbags']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@class='five-grid']").click();
		Thread.sleep(2000);

		WebElement SortBywebelement = driver.findElementByXPath("//select");
		Select SortBywebdropdown = new Select(SortBywebelement);
		SortBywebdropdown.selectByVisibleText("What's New");
		
		//Click on five grid and Select SORT BY as "What's New"
		driver.findElementByXPath("//span[text()='price']").click();
		
		//Enter Price Range Min as 2500 and Max as 5000
		driver.findElementByXPath("//input[@id='minPrice']").sendKeys("2500");
		driver.findElementByXPath("//input[@id='maxPrice']").sendKeys("5000");
		driver.findElementByXPath("//button[@class='rilrtl-button ic-toparw']").click();
		
		//Click on the product "Puma Ferrari LS Shoulder Bag"
		Thread.sleep(3000);
		driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']").click();
		Thread.sleep(3000);
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList(winSet);
		driver.switchTo().window(winList.get(1));
		
		//Verify the Coupon code for the price above 2690 is applicable for the product
		String productPrice = driver.findElementByXPath("//div[@class='prod-sp']").getText();
		
		 productPrice = productPrice.replaceAll("Rs. ", "").replaceAll(",", "");
		 System.out.println("productPrice is : " + productPrice);
		 int newProductprice = Integer.parseInt(productPrice);
		 
		 int applicablefordiscount = 2690;
		 
		 String discountprice = driver.findElementByXPath("//div[@class='promo-discounted-price']/span").getText();
		 discountprice = discountprice.substring(1);
		 System.out.println("discountprice is : " + discountprice);
		 int newdiscountprice = Integer.parseInt(discountprice);
		 
		 //Calculate the discount price for the coupon
		 int couponsavings =  newProductprice -  newdiscountprice ;
		 if (newProductprice > applicablefordiscount) {
			 System.out.println("couponsavings is : " + couponsavings);
			
		}
		 
		 //Check the availability of the product for pincode 560043(Used 635001 since 560043 is throwing error)
		 driver.findElementByXPath("//span[text()='Enter pin-code to know estimated delivery date.']").click();
		 Thread.sleep(2000);
		 driver.findElementByXPath("//input[@class='edd-pincode-modal-text']").sendKeys("635001");
		 driver.findElementByXPath("//button[text()='CONFIRM PINCODE']").click();
		 Thread.sleep(2000);
		 
		 //print the expected delivery date if it is available
		 String deliverydate = driver.findElementByXPath("//ul[@class='edd-message-success-details']/li/span").getText();
		 System.out.println("estimated deliverydate is : " + deliverydate);
		 
		 //Click on Other Informations under Product Details and Print the Customer Care address, phone and email
		 driver.findElementByXPath("//div[text()='Other information']").click();
		 String customercareaddress = driver.findElementByXPath("(//span[@class='other-info'])[6]").getText();
		 System.out.println("customercare address , email and mobile number  is : " + customercareaddress);
		 
		 //Click on ADD TO BAG and then GO TO BAG
		 driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
		 Thread.sleep(5000);
		 WebElement gotobag = driver.findElementByXPath("//div[@class='btn-cart']");
		 WebDriverWait wait = new WebDriverWait(driver, 10);
		 wait.until(ExpectedConditions.elementToBeClickable(gotobag));
		 gotobag.click();
		 
		 //Check the Order Total before apply coupon
		 Thread.sleep(2000);
		 String ordertotal = driver.findElementByXPath("//section[@id='orderTotal']/span[@class='price-value bold-font']").getText();
		 ordertotal = ordertotal.replaceAll("Rs. ", "").replaceAll(",", "");
		 System.out.println("ordertotal is : " + ordertotal);
		 
		 //Enter Coupon Code and Click Apply
		 Thread.sleep(3000);
		 if (ordertotal == productPrice) {
			 driver.findElementByXPath("//input[@id='couponCodeInput']").sendKeys("EPIC");
			 driver.findElementByXPath("//button[text()='Apply']").click();
			
		}
		 //Verify the Coupon Savings amount(round off if it in decimal) under Order Summary and the matches the amount calculated
		 Thread.sleep(2000);
		 String amountsavings = driver.findElementByXPath("//span[@class='amount-savings']").getText();
		 amountsavings = amountsavings.replaceAll("Rs. ", "");
		 float savings = Float.parseFloat(amountsavings);
		 System.out.println("Savings is : " + Math.round(savings));
		 int roundoffsavings = Math.round(savings);
		 if (couponsavings == roundoffsavings) {
			System.out.println("couponsavings and roundoffsavings matches ");
		}
		 
		 //Click on Delete and Delete the item from Bag
		 driver.findElementByXPath("//div[@class='delete-btn']").click();
		 Thread.sleep(2000);
		 driver.findElementByXPath("(//div[@class='delete-btn'])[2]").click();
		 
		 //Close all the browsers
		 driver.quit();
	}
		
}
