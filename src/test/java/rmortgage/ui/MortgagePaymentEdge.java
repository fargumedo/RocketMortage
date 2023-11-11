package rmortgage.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import rmortgage.mortgagePayment.MortgageMonthlyPayment;

public class MortgagePaymentEdge {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.edge.driver", "C:\\Browser Drivers\\msedgedriver.exe");
		EdgeDriver driver = new EdgeDriver();
		
		driver.get("https://www.rocketmortgage.com/calculators/mortgage-calculator?qlsource=RMTextLink");
		driver.manage().window().maximize();
		
		Thread.sleep(2000);
		
		WebElement price = driver.findElement(By.name("purchasePrice"));
		price.clear();
		price.sendKeys("300000");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		
		Thread.sleep(2000);
		
		WebElement payIniPerc = driver.findElement(By.id("buttonPercent"));
		payIniPerc.click();
		
		Thread.sleep(2000);
		
		js.executeScript("window.scrollBy(0,250)");
		
		WebElement percPayInit = driver.findElement(By.name("downPaymentPercent"));
		percPayInit.clear();
		percPayInit.sendKeys("15");
		
		js.executeScript("window.scrollBy(0,250)");
		
		WebElement loanTerm = driver.findElement(By.name("term"));
		Select select = new Select(loanTerm);
		select.selectByValue("300");
		WebElement firstOption = select.getFirstSelectedOption();
		
		WebElement ratePerc = driver.findElement(By.name("rate"));
		ratePerc.clear();
		ratePerc.sendKeys("5");
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[contains(text(),'Interest Rate')]")).click();
		
		Thread.sleep(2000);
		
		js.executeScript("window.scrollBy(0,300)");
		
		Thread.sleep(2000);	
		
		WebElement zipCode = driver.findElement(By.id("zip"));
		zipCode.clear();
		zipCode.sendKeys("90210");
		
		WebElement noTaxes = driver.findElement(By.id("includeTaxesNLabel"));
		noTaxes.click();
		
		WebElement calculate = driver.findElement(By.id("calculateButton"));
		calculate.click();
		
		Thread.sleep(5000);
		
		WebElement total = driver.findElement(By.xpath("//*[@data-qa='totalMonthlyPayment']//descendant::span"));
		System.out.println(total.getText());
		
		
		//FORMULA VALUES
		
		String finalPrice = price.getAttribute("value");
		String finalPrice2 = finalPrice.replace(",", "");
		double amount1 = Double.parseDouble(finalPrice2);
		
		String initialPerc = percPayInit.getAttribute("value");
		double initialPayment1 = Double.parseDouble(initialPerc);
		
		String finalTerm = firstOption.getAttribute("value");
		int term1 = Integer.parseInt(finalTerm);
		
		String finalRate = ratePerc.getAttribute("value");
		double rate1 = Double.parseDouble(finalRate);
		
		String finalQuote = total.getText();
		String finalQuote2 = finalQuote.replace(",", "");
		double totalQ = Double.parseDouble(finalQuote2);
		
		
		//GENERATED WEBSITE PAYMENT AGAINST GENERATED FORMULA PAYMENT VALIDATION
		MortgageMonthlyPayment totalC = new MortgageMonthlyPayment(amount1, rate1, term1, initialPayment1);
		
		Double totalQuote = totalC.finalTotalPerc();
		System.out.println(totalQuote);
		
		if(totalQuote.equals(totalQ)){
			System.out.println("La cuota a pagar mensualmente es: $"+totalQ);
		}else {
			System.out.println("Hubo un error en el cálculo de la cuota mensual");
		}
		
		
		driver.close();


	}

}
