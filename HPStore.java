package seleniumworkouttestcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import okhttp3.internal.http.RetryAndFollowUpInterceptor;

import org.openqa.selenium.JavascriptExecutor;

public class HPStore {

	public static void main(String[] args) throws InterruptedException {
		//
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		
		options.merge(cap);
		
		//Open chrome browser and HPStore application
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://store.hp.com/in-en/");
		driver.manage().window().maximize();
		
		//To refresh the browser after opening HPStore application
		driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, "r"));
		Thread.sleep(2000);
		
		//To choose Pavillion  under Laptops
		Actions builder = new Actions(driver);
		WebElement laptops = driver.findElementByLinkText("Laptops");
		builder.moveToElement(laptops).perform();
		
		Actions builder1 = new Actions(driver);
		WebElement pavillion = driver.findElementByLinkText("Pavilion");
		builder1.moveToElement(pavillion).perform();
		driver.findElementByLinkText("Pavilion").click();
		
		//To choose Intel Core i7 under Processor
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		driver.findElementByXPath("//span[text()='Intel Core i7']").click();
		
		//To choose Hardrive capacity 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.findElementByXPath("//button[@title='Accept Cookies']").click();
		WebElement hardrivecapacity  = driver.findElementByXPath("//li[@class='item']/a/span[text()='More than 1 TB']/..");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(hardrivecapacity));
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,600)");
		// hardrivecapacity.click();
		// driver.findElementByXPath("//li[@class='item']/a/span[text()='More than 1 TB']/..").click();
		By by = By.xpath("//li[@class='item']/a/span[text()='More than 1 TB']/..");
		HPStore.retryingFindClick(driver, by);
		
		//To sort by price:low to high
		Thread.sleep(5000);
		WebElement sortbyWebElement = driver.findElementById("sorter");
		Select sortbyDropDown = new Select(sortbyWebElement);
		sortbyDropDown.selectByValue("price_asc");
		
		//To display name and price of first displayed laptop
		List<WebElement> laptopNames = driver.findElementsByXPath("//a[@class='product-item-link']");
		String firstdisplayedLaptopName = laptopNames.get(2).getText();
		System.out.println("Laptop name : " + firstdisplayedLaptopName);
		
		
		String laptopPrice = driver.findElementByXPath("//span[@id='product-price-9580']/span[@class='price']").getText();
		laptopPrice.replaceAll("\\D", "");
		System.out.println("Laptop Price : " + laptopPrice);
		int LaptopPrice = Integer.parseInt(laptopPrice);
		
		
		//Click on Add to Cart and going to Shopping Bag
		WebElement addtocart = driver.findElementByXPath("//button[@title='Add To Cart'][1]");
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(addtocart));
		//JavascriptExecutor js1 = (JavascriptExecutor) driver;
		//js1.executeScript("window.scrollBy(0,600)");
		Thread.sleep(10000);
		//addtocart.click();
		//By by1 = By.xpath("(//span[text()='Add To Cart'])[1]");
		driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").click();
		By by1 = By.xpath("//button[@title='Add To Cart'][1]");
		HPStore.retryingFindClick(driver, by1);
		//driver.findElementByXPath("//button[@title='Add To Cart']").click();
		Thread.sleep(2000);
//		driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").click();
		driver.findElementByXPath("//a[@class='action showcart']").click();
		driver.findElementByXPath("//a[@class='action primary viewcart']").click();
		
		//to check availability in given pincode
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("560037");
		driver.findElementByXPath("//button[text()='check']").click();
		
		//To check if order total = product price
		Thread.sleep(3000);
		String orderTotal = driver.findElementByXPath("(//span[@class='price'])[7]").getText();
		orderTotal.replaceAll("\\D", "");
		System.out.println("Order Total : " + orderTotal);
		
		//If order total = product price, proceed to checkout
		if (laptopPrice.equals(orderTotal)) {
			System.out.println("laptop price is same as order total");
			driver.findElementByXPath("//button[@title='Proceed to Checkout'][1]").click();
		}
		//Place the order and capture the error message
		Thread.sleep(10000);
		driver.findElementByXPath("//div[@class='place-order-primary']/button[@title='Place Order']").click();
		String errormessage = driver.findElementById("customer-email-error").getText();
		System.out.println("Error message : " + errormessage);
		
		}
	public static boolean retryingFindClick(ChromeDriver driver, By by) {
	    boolean result = false;
	    int attempts = 0;
	    while(attempts < 2) {
	        try {
	            driver.findElement(by).click();
	            result = true;
	            break;
	        } catch(StaleElementReferenceException e) {
	        	//throw e;
	        }
	        attempts++;
	    }
	    return result;
	}

}
