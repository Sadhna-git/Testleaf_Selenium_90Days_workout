package seleniumworkouttestcases;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {
		//Open chrome browser and Nykaa application
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		
		//To refresh the browser after opening MakeMyTrip application
		driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, "r"));
		Thread.sleep(2000);
		
		//
		driver.findElementByXPath("//span[text()='Hotels']").click();
		Thread.sleep(2000);

		
		driver.findElementById("checkin").click();
		Calendar nextMonth21 = Calendar.getInstance();
		nextMonth21.add(Calendar.MONTH, 1);
		nextMonth21.set(Calendar.DATE, 21);
		System.out.println(nextMonth21);
		int weekOfMonthIndex = nextMonth21.get(Calendar.WEEK_OF_MONTH);
		//weekOfMonthIndex = weekOfMonthIndex - 1;
		int dayOfWeekIndex = nextMonth21.get(Calendar.DAY_OF_WEEK);
		//dayOfWeekIndex -= 1;
		driver.findElementByXPath("//div[@class='DayPicker-Month'][2]/div[@class='DayPicker-Body']/div[@class='DayPicker-Week']["+weekOfMonthIndex+"]/div["+dayOfWeekIndex+"]").click();
		
		nextMonth21.add(Calendar.DATE, 5);
		System.out.println(nextMonth21.toString());
		int weekOfMonthIndex1 = nextMonth21.get(Calendar.WEEK_OF_MONTH);
		//weekOfMonthIndex1 = weekOfMonthIndex1 - 1;
		int dayOfWeekIndex1 = nextMonth21.get(Calendar.DAY_OF_WEEK);
		//dayOfWeekIndex1 = dayOfWeekIndex1 -1;
		System.out.println(weekOfMonthIndex1 + "-"+ dayOfWeekIndex1);
		driver.findElementByXPath("//div[@class='DayPicker-Month'][2]/div[@class='DayPicker-Body']/div[@class='DayPicker-Week']["+weekOfMonthIndex1+"]/div["+dayOfWeekIndex1+"]").click();
		

		driver.findElementById("guest").click();
		driver.findElementByXPath("//li[text()='2'][1]").click();
		driver.findElementByXPath("(//ul[@class='guestCounter font12 darkText'][2])/li[2]").click();
		driver.findElementByXPath("//button[text()='APPLY']").click();
		driver.findElementByXPath("//button[text()='Search']").click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementByClassName("mapCont").click();

		driver.findElementByXPath("//span[@class='mapClose']").click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElementByXPath("//label[text()='Baga']").click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementByXPath("//label[text()='5 Star']").click();
		driver.findElementByXPath("//span[text()='The Park Baga River Goa']").click();
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));
		String hotelName = driver.findElementById("detpg_hotel_name").getText();
		System.out.println(hotelName);
		driver.findElementByXPath("//span[text()='MORE OPTIONS'][1]").click();
		driver.findElementByXPath("//span[text()='SELECT'][1]").click();
		driver.findElementByClassName("close").click();
		
		//Pending : Unable to proceed because the site says the hotel is booked for the given date. Tried for all possible dates for next 6 months
		
//		12) Click on BOOK THIS NOW
//		13) Print the Total Payable amount
//		14) Close the browser  
	}

}
