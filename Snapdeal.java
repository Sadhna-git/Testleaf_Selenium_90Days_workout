package seleniumworkouttestcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException {
				//Open chrome browser and Nykaa application
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				ChromeDriver driver = new ChromeDriver();
				driver.get("https://snapdeal.com");
				driver.manage().window().maximize();
				
				//To refresh the browser after opening Nykaa applciation
				driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, "r"));
				Thread.sleep(2000);
				
				//click on Educational Toys in Toys & Games under Toys, Kids' Fashion & more and click on Toys
				Actions builder = new Actions(driver);
				WebElement ToyKidFashionandMore = driver.findElementByXPath("//span[text()=\"Toys, Kids' Fashion & more\"]");
				builder.moveToElement(ToyKidFashionandMore).perform();
				
				driver.findElementByXPath("//span[text()='Educational Toys']").click();
				Thread.sleep(2000);
				
				//Choose Customer Rating 4 star and Up and offer as 40-50
				driver.findElementByXPath("//label[@for='avgRating-4.0']").click();
				Thread.sleep(4000);
				driver.findElementByXPath("//label[@for='discount-40%20-%2050']").click();
				Thread.sleep(2000);
				
				//Check for availability
				driver.findElementByXPath("//div[@class='pincode-enter js-pincode-enter']/input").sendKeys("600119");
				driver.findElementByXPath("//button[text()='Check']").click();
				Thread.sleep(2000);
				
				//Hover over on first product and click Quick view
				List<WebElement> products = driver.findElementsByXPath("//div[@class='product-tuple-description ']");
				WebElement firstproduct = products.get(0);
				builder.moveToElement(firstproduct).perform();
				
				driver.findElementByXPath("//div[@class='clearfix row-disc']/div").click();
				Thread.sleep(3000);
				
				//Click on View Details and Capture the Price of the Product and Delivery Charge
				driver.findElementByXPath("//a[@class=' btn btn-theme-secondary prodDetailBtn']").click();
				Thread.sleep(2000);
				
				String firstproductPrice = driver.findElementByClassName("pdp-final-price").getText();
				firstproductPrice= firstproductPrice.replaceAll("Rs. ", "");
				System.out.println("Price of First Product is " + firstproductPrice);
				int firstpdtprice = Integer.parseInt(firstproductPrice);
				
				String firstproductDelivercharge = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
				//firstproductDelivercharge = firstproductDelivercharge.replaceAll("(+) Rs ", "");
				int lastSpaceIndex = firstproductDelivercharge.lastIndexOf(" ");
				firstproductDelivercharge = firstproductDelivercharge.substring(lastSpaceIndex + 1, firstproductDelivercharge.length());
				System.out.println("Delivery Charge of First Product is " + firstproductDelivercharge);
				int firstpdtdeliverycharge = Integer.parseInt(firstproductDelivercharge);
				
				//Click on Add to Cart and find You Pay amount
				driver.findElementById("add-cart-button-id").click();
				Thread.sleep(2000);
				String youpay = driver.findElementByClassName("you-pay").getText();
				youpay = youpay.replaceAll("You Pay: Rs. ", "");
				System.out.println("You Pay Amount is " + youpay);
				int youpayamt = Integer.parseInt(youpay);
				
				//Validate the You Pay amount matches the sum of (price+deliver charge)
				if (youpayamt == (firstpdtprice + firstpdtdeliverycharge )) {
					System.out.println("You Pay amount matches the sum of (price+deliver charge)");
					
				}
				
				//Search for Sanitizer
				driver.findElementByXPath("//input[@id='inputValEnter']").sendKeys("Sanitizer",Keys.ENTER);
				Thread.sleep(2000);
				
				//Click on BioAyurveda Neem Power Hand Sanitizer and capture Price and Delivery Charge
				driver.findElementByXPath("//input[@id='inputValEnter']").clear();
				driver.findElementByXPath("//input[@id='inputValEnter']").sendKeys("BioAyurveda Neem Power Hand Sanitizer",Keys.ENTER);
				Thread.sleep(2000);
				driver.findElementByXPath("//p[text()='BioAyurveda Neem Power  Hand Sanitizer 500 mL Pack of 1']").click();
				
				Set<String> winSet = driver.getWindowHandles();
				List<String> winList = new ArrayList(winSet);
				driver.switchTo().window(winList.get(1));
				
				String priceofsanitizer = driver.findElementByXPath("//span[@class='pdp-final-price']").getText();
				priceofsanitizer = priceofsanitizer.replaceAll("Rs. ", "");
				System.out.println("Price of Sanitizer is " + priceofsanitizer);
				int sanitizerprice = Integer.parseInt(priceofsanitizer);
				
				String deliverychargeofsanitizer = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
				int lastspaceindex1 = deliverychargeofsanitizer.lastIndexOf(" ");
				deliverychargeofsanitizer = deliverychargeofsanitizer.substring(lastSpaceIndex + 1, deliverychargeofsanitizer.length());
				System.out.println("Delivery charge of Sanitizer is " + deliverychargeofsanitizer);
				int sanitizerdeliverycharge = Integer.parseInt(deliverychargeofsanitizer);
				
				//click on Add to Cart and click on Cart
				driver.findElementByXPath("//span[text()='ADD TO CART']").click();
				Thread.sleep(2000);
				driver.findElementByXPath("//span[@class='cartTextSpan']").click();
				
				//Print Proceed to Pay value
				Thread.sleep(5000);
				String proceedtopayvalue = driver.findElementByXPath("//input[@type='button']").getAttribute("value");
				proceedtopayvalue = proceedtopayvalue.replaceAll("PROCEED TO PAY Rs. ", " ");
				System.out.println("Proceed to Pay value is " + proceedtopayvalue);
				proceedtopayvalue = proceedtopayvalue.trim();
				int proceedtopayamount = Integer.parseInt(proceedtopayvalue);
				
				//Validate the Proceed to Pay matches the total amount of both the products				
				if (proceedtopayamount == (youpayamt + (sanitizerprice + sanitizerdeliverycharge) )) {
					System.out.println("Proceed to Pay matches the total amount of both the products");
				}
				
				driver.close();
				
				
	}

}
