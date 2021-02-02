package cpa.qa.library;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interactive;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebEventListener extends AbstractWebDriverEventListener {

	Functions fun = new Functions();

	public void afterClickOn(WebElement element, WebDriver driver) {

//		String browserName = driver.toString();
//		if(browserName.contains("InternetExplorer")) {
//			//System.out.println("Browser is  IE");
//			JavascriptExecutor jse = (JavascriptExecutor) ((Interactive)driver);
//			jse.executeScript("arguments[0].click();", element);
//			// element.sendKeys(Keys.ENTER);
//			fun.waitUntilAngularReady();
//			//System.out.println("Clicked on: " + element.toString());
//		}
//
//		else{
//			//System.out.println("Browser is not IE");
//		}
	}
}
