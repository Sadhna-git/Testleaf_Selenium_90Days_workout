package seleniumworkouttestcases;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class Myntra {

	public static void main(String[] args) throws InterruptedException {
		//Open chrome browser and Myntra application
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		
		//To refresh the browser after opening Myntra applciation
		driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, "r"));
		Thread.sleep(2000);
		driver.findElementByLinkText("Women").click();
		Thread.sleep(3000);
		
		//To hover over on 'women' category
		Actions builder = new Actions(driver);
		WebElement women = driver.findElementByXPath("//a[text()='Women'][1]");
		builder.moveToElement(women).perform();
		
		//To navigate into 'Jackets & Coats' 
		Thread.sleep(2000);
		driver.findElementByLinkText("Jackets & Coats").click();
		
		//To get the Total number of categories
		String titlecount = driver.findElementByClassName("title-count").getText();
		String TCnumber = titlecount.replaceAll("\\D", "");
		System.out.println(TCnumber);
		int totalcategories = Integer.parseInt(TCnumber);
		
		//To get the count of Total number of Jackets
		String JacketsCount = driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
		String JCnumber = JacketsCount.replaceAll("\\D", "");
		int totaljackets = Integer.parseInt(JCnumber);
		System.out.println(JCnumber);
		
		//To get the count of Total number of Coats
		String CoatsCount = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
		String Cnumber = CoatsCount.replaceAll("\\D", "");
		int totalcoats = Integer.parseInt(Cnumber);
		System.out.println(Cnumber);
		
		//To compare that Total number of categories = Total number of Jackets + Total number of Coats
		if (totalcategories == ( totaljackets +  totalcoats)) {
			System.out.println("Total categories is equal to Jackets + coats");
		}
		
		//To click on brand-more and filter with 'Mango' brand
		driver.findElementByXPath("(//span[@class='categories-num'])[2]").click();
		driver.findElementByXPath("//div[@class='brand-more']").click();
		driver.findElementByClassName("FilterDirectory-searchInput").sendKeys("MANGO");
		
		//To Close the pop-up x
		driver.findElementByXPath("(//label[@class=' common-customCheckbox'])").click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
		
		//To Confirm all the Coats are of brand MANGO
		List<WebElement> brand = driver.findElementsByXPath("//li[@class='product-base']/a/div[2]/h3[text()='MANGO']");
		boolean allRmangos = true;
		for (WebElement eachbrand : brand) {
			System.out.println(eachbrand.getText());
			if (!eachbrand.getText().equals("MANGO")) {
				allRmangos = false;
			}
		}
		if (allRmangos)
			System.out.println("all brands are same MANGO");
		
		//To Sort by Better Discount
		Actions builder1 = new Actions(driver);
		WebElement sortby = driver.findElementByClassName("sort-sortBy");
		builder1.moveToElement(sortby).perform();
		driver.findElementByXPath("//label[text()='Better Discount']").click();

		//To Find the price of first displayed item
		List<WebElement> productsList = driver.findElementsByXPath("//div[@class='product-price']/span/span[@class='product-discountedPrice']");
		String firstproductPrice = productsList.get(0).getText();
		System.out.println(firstproductPrice);
		
		//To Click on WishList Now
		Actions builder2 = new Actions(driver);
		WebElement image = driver.findElementByXPath("//ul[@class='results-base']/li[1]");
		builder2.moveToElement(image).perform();
		Thread.sleep(2000);
		driver.findElementByXPath("//span[@class='product-actionsButton product-addToBag'][1]/following-sibling::span").click();
		
		//To Close Browser
		driver.close();
		
		
	}

}
