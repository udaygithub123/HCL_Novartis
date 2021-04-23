package com.Novartis.MortgageCalculator.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PaymentInterestCalc {

	public static WebDriver driver;

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver", "Chrome_Driver\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://www.mortgageloan.com/calculator");
		driver.manage().window().maximize();
		driver.findElement(By.name("LOAN_AMOUNT")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		driver.findElement(By.name("LOAN_AMOUNT")).sendKeys(Keys.BACK_SPACE);

		driver.findElement(By.name("LOAN_AMOUNT")).sendKeys("$200000");

		driver.findElement(By.id("KJE-TERM")).sendKeys("30 years");
		driver.findElement(By.id("KJE-INTEREST_RATE")).clear();

		driver.findElement(By.id("KJE-INTEREST_RATE")).sendKeys("5%");
		driver.findElement(By.id("KJE-BY_YEAR1")).click();
		driver.findElement(By.name("KJECalculate")).click();
		String monthlyPayment = driver.findElement(By.id("KJE-MONTHLY_PAYMENT")).getText();

		System.out.println("Monthly Payment is:- "+monthlyPayment);

		if (monthlyPayment.contains("$1,073.64")) {
			System.out.println("Monthly Payment after Verification is:- " + monthlyPayment+"\n");
		} else
			System.out.println("Monthly payment is incorrect");

		Thread.sleep(5000);
		driver.findElement(By.id("svg_bg_ML_ElementSectionCTA3866")).click();
		driver.findElement(By.xpath("//span[@aria-hidden='true']")).click();

		String payments_interest = driver.findElement(By.xpath("//h2[@class = 'KJEGraphTitle']")).getText();
		System.out.println("The Total Payments and Total Interests are:- ");
		System.out.println(payments_interest+"\n");

		String payments = payments_interest.replace("Total Payments ", "");
		String finalPayments = payments.replace("\n" + "Total Interest $186,513", "");

		String interest = payments_interest.replace("Total Payments $386,513" + "\n", "");
		String finalInterest = interest.replace("Total Interest ", "");

		if (finalPayments.equals("$386,513")) {
			System.out.println("Correct Total Payment Found");
		} else
			System.out.println("Correct Total payment not found");

		System.out.println("Total Payment after Verifying:- " + finalPayments+"\n");

		if (finalInterest.equals("$186,513")) {
			System.out.println("Correct Total Interest Found");
		} else
			System.out.println("Correct Interest NOT found");

		System.out.println("Total Interest after Verifying:- " + finalInterest);

		// driver.quit();

	}

}
