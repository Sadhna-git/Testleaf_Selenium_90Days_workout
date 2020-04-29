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
import org.openqa.selenium.support.ui.Select;

public class Honda {

	public static void main(String[] args) throws InterruptedException {
		//Open chrome browser and Honda application
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				ChromeDriver driver = new ChromeDriver();
				driver.get("https://honda2wheelersindia.com");
				driver.manage().window().maximize();
				
				//To refresh the browser after opening Honda application
				driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, "r"));
				Thread.sleep(2000);
				
				//To close the popup
				driver.findElementByClassName("close").click();
				
				//To choose Dio image under Scooter
				driver.findElementByXPath("//div[@class='menurow']//a[text()='Scooter']").click();
				Thread.sleep(2000);
				driver.findElementByXPath("//a[@href='/dio-BS-VI/']").click();
				
				//To print the displacement value of Engine under Specification
				driver.findElementByXPath("//a[text()='Specifications']").click();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.findElementByXPath("//a[text()='ENGINE']").click();
				Thread.sleep(2000);
				String displacementvalueofDio = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText();
				displacementvalueofDio = displacementvalueofDio.replaceAll("cc", "");
				System.out.println("Diodisplacement - "+displacementvalueofDio);
				float Diodisplacement = Float.parseFloat(displacementvalueofDio);
				
				//To choose Activa125 image under Scooter
				driver.findElementByXPath("//div[@class='menurow']//a[text()='Scooter']").click();
				Thread.sleep(2000);
				driver.findElementByXPath("//a[@href='/activa125-BS-VI/']").click();
				
				//To print the displacement value of Engine under Specification
				driver.findElementByXPath("//a[text()='Specifications']").click();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.findElementByXPath("//a[text()='ENGINE']").click();
				Thread.sleep(2000);
				String displacementvalueofActiva125 = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText();
				displacementvalueofActiva125 = displacementvalueofActiva125.replaceAll("cc", "");
				System.out.println("Activa125displacement - "+displacementvalueofActiva125);
				float Activa125displacement = Float.parseFloat(displacementvalueofActiva125);
				
				//Comparison of displacement between Activa125 and Dio
				if (Activa125displacement > Diodisplacement) {
					System.out.println("Activa125");
				} else {
					System.out.println("Dio");

				}
				//clicking FAQ from menu and Activa 125 BS-VI under Browse By Product
				Thread.sleep(2000);
				driver.findElementByXPath("//a[text()='FAQ']").click();
				Thread.sleep(2000);
				driver.findElementByXPath("//a[text()='Activa 125 BS-VI']").click();
				
				//clicking Vehicle Price and submitting 
				driver.findElementByXPath("//a[text()=' Vehicle Price']").click();
				driver.findElementById("submit6").click();
				
				//clicking on Price link and navigating to the new window
				Thread.sleep(2000);
				driver.findElementByXPath("//a[text()='Click here to know the price of Activa 125 BS-VI.']").click();
				
				Set<String> winSet = driver.getWindowHandles();
				List<String> winList = new ArrayList<String>(winSet);
				driver.switchTo().window(winList.get(1));
				
				//To choose Tamil Nadu from State dropdown
				WebElement stateidWebElement = driver.findElementById("StateID");
				Select stateidWebElementDropDown = new Select (stateidWebElement);
				stateidWebElementDropDown.selectByVisibleText("Tamil Nadu");
				
				//To choose Chennai from City dropdown
				Thread.sleep(2000);
				WebElement cityidWebElement = driver.findElementById("CityID");
				Select cityidWebElementDropDown = new Select (cityidWebElement);
				cityidWebElementDropDown.selectByVisibleText("Chennai");
				
				//click on Search
				driver.findElementByXPath("//button[text()='Search']").click();
				
				//To print the vehicle names and their prices
				WebElement ModelandPrice = driver.findElementById("gvshow");
				WebElement tbody = ModelandPrice.findElement(By.tagName("tbody"));
				List<WebElement> rows = tbody.findElements(By.tagName("tr"));
				int size = rows.size();
				
				for (int i = 0; i < size; i++) {
					WebElement firstrow = rows.get(i);
					List<WebElement> firstrowcols = firstrow.findElements(By.tagName("td"));
					if (i==0) {
						System.out.println(firstrowcols.get(1).getText());
						System.out.println(firstrowcols.get(2).getText());
					} else {
						System.out.println(firstrowcols.get(0).getText());
						System.out.println(firstrowcols.get(1).getText());
					}
					
				}
				driver.close();
				

	}

}
