package com.demo.stepdefinition.login;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginAdmin {

    ChromeDriver driver = new ChromeDriver();

    @Given("User is on login page")
    public void user_is_on_login_page() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("User enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
    }

    @And("User clicks on Login button")
    public void user_clicks_on_login_button() {
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();
    }

    @Then("User should be logged in to Admin Page")
    public void user_should_be_logged_in_to_admin_page() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).isDisplayed();
        driver.quit();
    }

    @Then("User should see alert error {string}")
    public void user_should_see_login_error(String errMsg) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//div[@role='alert']/div/p")).isDisplayed();
        WebElement errorMsg = driver.findElement(By.xpath("//div[@role='alert']/div/p"));

        String errorMsgTextActual = errorMsg.getText();
        System.out.println("Actual Text: " + errorMsgTextActual);
        System.out.println("Expected Text: " + errMsg);

        Assert.assertEquals(errorMsgTextActual, errMsg);
        driver.quit();
    }

    @Then("User should see login error")
    public void user_should_see_alert_error() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//span[text()='Required']")).isDisplayed();
        driver.quit();
    }





}

