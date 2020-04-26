package seleniumworkouttestcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BigBasket {

	public static void main(String[] args) throws InterruptedException {
		//Open chrome browser and BigBasket application
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		
		//To refresh the browser after opening BigBasket application
		driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, "r"));
		Thread.sleep(2000);
		
		//To choose Rice & Rice Products  under FoodGrains and Oil from ShopByCategory
		Actions builder = new Actions(driver);
		WebElement shopbyCategory = driver.findElementByXPath("//a[text()=' Shop by Category ']");
		builder.moveToElement(shopbyCategory).perform();
		
		Actions builder1 = new Actions(driver);
		WebElement foodGrainsandOil = driver.findElementByXPath("(//a[text()='Foodgrains, Oil & Masala'])[2]");
		builder1.moveToElement(foodGrainsandOil).perform();
		Thread.sleep(2000);
		driver.findElementByXPath("(//a[text()='Rice & Rice Products'])[2]").click();
		
		//To click on Boiled & Steam Rice and choose BB Royal as Brand
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		Thread.sleep(2000);
		driver.findElementByXPath("(//span[text()='Boiled & Steam Rice'])[1]").click();
			
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElementByXPath("(//input[@value='Search by Brand'])").sendKeys("bb Royal");
		driver.findElementByXPath("//span[text()='bb Royal']").click();
		
		//To choose 5kg quantity
		Thread.sleep(3000);
		driver.findElementByXPath("//div/a[text()='Idli Rice/Idli Arisi']/../following-sibling::div[1]").click();
		List<WebElement> choosequantity = driver.findElementsByXPath("(//ul[@class='dropdown-menu drop-select'])[2]/li");
		choosequantity.get(2).click();
		
		//To print the price of 5kg rice
		Thread.sleep(3000);
		String RicePrice = driver.findElementByXPath("(//span[@class='discnt-price']/span)[2]").getText();
		System.out.println("Price of 5kg rice is Rs"+RicePrice);
		int Priceof5kgRice = Integer.parseInt(RicePrice);
		
		//To click on  add button and capture the success message
		driver.findElementByXPath("(//button[text()='Add '])[2]").click();
		String successmessage = driver.findElementById("site_msg_label").getText();
		System.out.println(successmessage);
		driver.findElementByXPath("//a[text()='Continue']").click();
		
		//To search for Dal and choose quantity of Toor dal
		Thread.sleep(2000);
		driver.findElementById("input").sendKeys("Dal", Keys.ENTER);
		Thread.sleep(3000);
		driver.findElementByXPath("//div/a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/../following-sibling::div[1]").click();
		List<WebElement> choosequantityofdal = driver.findElementsByXPath("(//ul[@class='dropdown-menu drop-select'])[2]/li");
		choosequantityofdal.get(3).click();
		Thread.sleep(3000);
		
		List<WebElement> quantitytextbox = driver.findElementsByXPath("//div[@class='input-group']/input");
		quantitytextbox.get(3).clear();
		quantitytextbox.get(3).sendKeys("2");
		
		//To print the price of 2kg Toor Dal
		Thread.sleep(3000);
		String ToorDalPrice = driver.findElementByXPath("(//span[@class='discnt-price']/span)[4]").getText();
		System.out.println("Price of 2kg Toor Dal is Rs"+ToorDalPrice);
		int Priceof2kgDal = Integer.parseInt(ToorDalPrice);
		
		//To click on  add button and hover over on My Basket
		driver.findElementByXPath("(//button[text()='Add '])[3]").click();
		driver.findElementByClassName("toast-close-button").click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Actions builder2 = new Actions(driver);
		WebElement MyBasket = driver.findElementByXPath("//span[@title='Your Basket']");
		builder2.moveToElement(MyBasket).perform();
		Thread.sleep(3000);
		
		//To validate the subtotal cost
		String subtotalcost = driver.findElementByXPath("//div[@class='row sub-cost ng-scope']/p/span/span").getText();
		System.out.println("SubTotalCost of 1pkt 5KG rice and 2pkt 2kg Toor Dal is : Rs "+subtotalcost);
		float TotalCost = Float.parseFloat(subtotalcost);
		
		if ((int)TotalCost == (Priceof5kgRice + (2 * Priceof2kgDal))) {
			System.out.println("Total cost is validated");
		}
		
		//To decrease quantity of dal from the cart
		driver.findElementByXPath("(//button[@qa='decQtyMB'])[2]").click();
		
		//Price of 1 quantity 2kg Toor Dal
		String onepacket2kgToorDal = driver.findElementByXPath("(//span[@qa='priceMB'])[2]").getText();
		System.out.println("Price of 1 packet 2kg Toor Dal : Rs" + onepacket2kgToorDal);
		float Priceof1packet2kgToorDal = Float.parseFloat(onepacket2kgToorDal);
		
		//Sub total cost after reducing quantity of Toor Dal by 1
		Thread.sleep(2000);
		String subtotalcost1 = driver.findElementByXPath("//div[@class='row sub-cost ng-scope']/p/span/span").getText();
		System.out.println("SubTotalCost1 of 1pkt 5KG rice and 1 packet 2kg Toor Dal is : Rs "+subtotalcost1);
		float TotalCost1 = Float.parseFloat(subtotalcost1);
		
		//To validate the subtotal cost after reducing quantity of Toor Dal by 1
		if ((int)TotalCost1 == (int)(Priceof5kgRice + Priceof1packet2kgToorDal)) {
			System.out.println("Updated Total cost is validated");
		}
		driver.close();
	}

}
