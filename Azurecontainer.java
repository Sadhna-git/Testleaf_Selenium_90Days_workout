package seleniumworkouttestcases;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class Azurecontainer {

	public static void main(String[] args) throws InterruptedException {
		//Open chrome browser and Azurecontainer application
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://azure.microsoft.com/en-in/");
		driver.manage().window().maximize();
		
		//To refresh the browser after opening Azurecontainer application
		driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, "r"));
		Thread.sleep(3000);
		
		//Navigate to Pricing and Pricing Calculator
		driver.findElementByXPath("//a[@id='navigation-pricing']").click();
		driver.findElementByXPath("//ul[@class='linkList initial-list']/li[2]").click();
		Thread.sleep(5000);
		
		//Click on Containers and Container Instances
		driver.findElementByXPath("//div[@class='category-container']/button[text()='Containers']").click();
		driver.findElementByXPath("(//span[@class='product']/span[text()='Easily run containers on Azure without managing servers'])[2]").click();

		//Scroll down the page and Select Region as "South India"
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(3000);
		WebElement regionwebelement = driver.findElementByXPath("//label[@class='calculator-dropdown-label']/following-sibling::select[@name='region']");
		Select regiondropdown = new Select(regionwebelement);
		regiondropdown.selectByVisibleText("South India");
		Thread.sleep(2000);
		
		// Select the Memory as 4GB
		WebElement memorywebelement = driver.findElementByXPath("//select[@name='memory']");
		Select memorydropdown = new Select(memorywebelement);
		memorydropdown.selectByVisibleText("4 GB");
		Thread.sleep(2000);
		
		//Set the Duration as 180000 seconds
		driver.findElementByXPath("(//input[@class='text-input numeric'])[2]").clear();
		Thread.sleep(2000);
		driver.findElementByXPath("(//input[@class='text-input numeric'])[2]").sendKeys(Keys.BACK_SPACE);
		driver.findElementByXPath("(//input[@class='text-input numeric'])[2]").sendKeys("1800000");
		Thread.sleep(2000);
		
		//Enable SHOW DEV/TEST PRICING
		driver.findElementByXPath("//button[@id='devtest-toggler']").click();
		
		//Select Indian Rupee  as currency
		WebElement currencywebelement = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select currencydropdown = new Select(currencywebelement);
		currencydropdown.selectByVisibleText("Indian Rupee (₹)");
		
		// Print the Estimated monthly price
		String monthlycost = driver.findElementByXPath("//span[text()='Monthly cost']/parent::div[@class='total']/span[@class='numeric']/span").getText();
		System.out.println("monthlycost is : " + monthlycost);
		
		//Delete the 'exported estimated' file from downloads path if the file exists
		File downloadedFile = new File("C:\\Users\\Sadhna\\Downloads\\ExportedEstimate.xlsx");
		if(downloadedFile.exists()) {
			downloadedFile.delete();
		}
		
		//Click on Export to download the estimate as excel
		driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']").click();
		Thread.sleep(2000);
		if(downloadedFile.exists()) {
			System.out.println("Exported estimate file exists in the downloads folder.");
		}	
		
		//Scroll up and Navigate to Example Scenarios and Select CI/CD for Containers
		    js.executeScript("window.scrollTo(0, document.body.scrollTop)");
		    Thread.sleep(2000);
		    driver.findElementByXPath("//a[text()='Example Scenarios']").click();
		    Thread.sleep(2000);
		    driver.findElementByXPath("//span[text()='CI/CD for Containers']").click();
		    
		    //Click Add to Estimate by scrolling to the first quarter of the page
		    Thread.sleep(2000);
		    js.executeScript("window.scrollTo(0, document.body.scrollHeight/4)");
		    driver.findElementByXPath("//button[@class='button button--secondary01 pull-right']").click();
			Thread.sleep(5000);

		    //Change the Currency as Indian Rupee
		    WebElement secondcurrencywebelement = driver.findElementByXPath("//select[@class='select currency-dropdown']");
			Select secondcurrencydropdown = new Select(secondcurrencywebelement);
			secondcurrencydropdown.selectByVisibleText("Indian Rupee (₹)");
			
			////Delete the 'exported estimated' file from downloads path if the file exists
			downloadedFile = new File("C:\\Users\\Sadhna\\Downloads\\ExportedEstimate.xlsx");
			if(downloadedFile.exists()) {
				downloadedFile.delete();
			}
			
			//Enable SHOW DEV/TEST PRICING
			driver.findElementByXPath("//button[@id='devtest-toggler']").click();
			Thread.sleep(2000);
			
			//Export the Estimate
			driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']").click();
			
			//Verify the downloded file in the local folder
			Thread.sleep(2000);
			downloadedFile = new File("C:\\Users\\Sadhna\\Downloads\\ExportedEstimate.xlsx");
			if(downloadedFile.exists()) {
				System.out.println("Exported estimate file exists in the downloads folder.");
			}
	}
}


		

