package rmortgage.ui;
import rmortgage.mortgagePayment.MortgagePaymentCalc;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class MortgagePaymentFirefox {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
	//	System.setProperty("webdriver.chrome.driver", "C:\\Browser Drivers\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "C:\\Browser Drivers\\geckodriver.exe");
	//	System.setProperty("webdriver.edge.driver", "C:\\Browser Drivers\\msedgedriver.exe");
		
	//	ChromeDriver driver = new ChromeDriver();
		FirefoxDriver driver = new FirefoxDriver();
	//	EdgeDriver driver = new EdgeDriver();
		
		driver.get("https://www.rocketmortgage.com/calculators/mortgage-calculator?qlsource=RMTextLink");
		driver.manage().window().maximize();
		
		Thread.sleep(2000);
		
		WebElement price = driver.findElement(By.name("purchasePrice"));
		price.clear();
		price.sendKeys("250000");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		
		Thread.sleep(2000);
		
		WebElement pagoIniPorc = driver.findElement(By.id("buttonPercent"));
		pagoIniPorc.click();
		
		Thread.sleep(2000);
		
		js.executeScript("window.scrollBy(0,250)");
		
		WebElement porcPagoInic = driver.findElement(By.name("downPaymentPercent"));
		porcPagoInic.clear();
		porcPagoInic.sendKeys("20");
		
		js.executeScript("window.scrollBy(0,250)");
		
		WebElement loanTerm = driver.findElement(By.name("term"));
		Select select = new Select(loanTerm);
		WebElement firstOption = select.getFirstSelectedOption();	
		
		WebElement ratePerc = driver.findElement(By.name("rate"));
		ratePerc.clear();
		ratePerc.sendKeys("4");
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[contains(text(),'Interest Rate')]")).click();
		
		Thread.sleep(2000);
		
		js.executeScript("window.scrollBy(0,600)");
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//label[contains(text(),'ZIP Code')]")).click();		
		
		WebElement zipCode = driver.findElement(By.id("zip"));
		zipCode.clear();
		zipCode.sendKeys("90210");
		
		js.executeScript("window.scrollBy(0,300)");
		
		WebElement noTaxes = driver.findElement(By.id("includeTaxesNLabel"));
		noTaxes.click();
		
		WebElement calculate = driver.findElement(By.id("calculateButton"));
		calculate.click();
		
		Thread.sleep(5000);
		
		WebElement total = driver.findElement(By.xpath("//*[@data-qa='totalMonthlyPayment']"));
		System.out.println(total.getText());
		
		
		//VALORES PARA FORMULA
		WebElement price2 = driver.findElement(By.name("purchasePrice"));
		String totalPrice = price2.getText();
		System.out.println(totalPrice);
		float amount1 = Float.parseFloat(totalPrice);
		System.out.println(amount1);
		
		String initialPerc = porcPagoInic.getText();
		double initialPayment1 = Double.parseDouble(initialPerc);
		
		String finalTerm = firstOption.getText();
		System.out.println(finalTerm);
		int term1 = Integer.parseInt(finalTerm);
		
		String finalRate = ratePerc.getText();
		double rate1 = Double.parseDouble(finalRate);
		
		String totalQuote = total.getText();
		double totalQ = Double.parseDouble(totalQuote);
		
		
		//COMPARACION DE TOTAL DE CUOTA GENERADO EN WEBSITE VS TOTAL DE CUOTA GENERADO EN FORMULA
		MortgagePaymentCalc totalC = new MortgagePaymentCalc(amount1, rate1, term1, initialPayment1);
		
		totalC.finalTotal();
		System.out.println(totalC.finalTotal());

		
/*		if(.equals(totalQ)){
			System.out.println("La cuota a pagar mensualmente es: $"+quote);
		}else {
			System.out.println("Hubo un error en el cálculo de la cuota mensual");
		}*/

	}

}