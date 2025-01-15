package com.demo.stepdefinition.pim;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class PimAdd {

    ChromeDriver driver = new ChromeDriver();
    String userPath = System.getProperty("user.dir") + "/datafiles/photos/";

    @Given("User already on PIM page")
    public void user_already_on_pim_page() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).isDisplayed();
        driver.findElement(By.xpath("//a/span[text()='PIM']")).click();
    }

    @When("User click add pim button")
    public void user_click_add_pim_button() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//button[text()=' Add ']")).click();
    }

    @And("User insert profile {string}")
    public void user_insert_profile(String fileName) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String fullPath = userPath + fileName;
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(fullPath);
    }

    @And("User input employee fullname {string}, {string}, {string}")
    public void user_input_employee_fullname(String firstName, String middleName, String lastName) {
        driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@placeholder='Middle Name']")).sendKeys(middleName);
        driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(lastName);
    }

    @And("User input employee id {string}")
    public void user_input_employee_id(String employeeId) {
        Actions actions = new Actions(driver);

        WebElement employeeIdXpath = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));

        actions.click(employeeIdXpath)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .perform();

        employeeIdXpath.sendKeys(employeeId);
    }

    @And("User enabled login details")
    public void user_enabled_login_details() {
        driver.findElement(By.xpath("//input[@type='checkbox']/following-sibling::span")).click();
    }

    @And("User input username {string}")
    public void user_input_username(String username) {
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(username);
    }

    @And("User change status to {string}")
    public void user_change_status_to(String status) {
        String statusXpath = String.format("//label[text()='%s']/span", status);
        driver.findElement(By.xpath(statusXpath)).click();
    }

    @And("User input password {string}")
    public void user_input_password(String password) {
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
    }

    @And("User input confirm password {string}")
    public void user_input_confirm_password(String confirmPassword) {
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(confirmPassword);
    }

    @And("User click save button")
    public void user_click_save_button() {
        driver.findElement(By.xpath("//button[text()=' Save ']")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//p[text()='Successfully Saved']")).isDisplayed();
    }

    @Then("User click PIM page")
    public void user_click_pim_page() {
        driver.findElement(By.xpath("//a/span[text()='PIM']")).click();
    }

    @And("User search employee name {string}, {string}, {string}")
    public void user_search_employee_name(String firstName, String middleName, String lastName) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String fullName = String.join(" ", firstName, middleName, lastName);
        driver.findElement(By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div/div/div/input")).sendKeys(fullName);
    }

    @And("User click search button")
    public void user_click_search_button() {
        driver.findElement(By.xpath("//button[text()=' Search ']")).click();
    }

    @Then("User should see the data exist")
    public void user_should_see_the_data_exist(DataTable dataTable) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Map<String, String>> data = dataTable.asMaps();

        for (Map<String, String> map : data) {
            String employeeId = map.get("employeeId");
            String firstName = map.get("firstName");
            String middleName = map.get("middleName");
            String lastName = map.get("lastName");

            String joinName = String.join(" ", firstName, middleName);
            String[] arrData = {employeeId, joinName, lastName};

            verifyDataTable(arrData);
        }
    }

    public void verifyDataTable(String[] data) {
        int j = 1;

        for (int i = 0; i < data.length; i++) {
            j++;
            String tableXpath = String.format("//div[@role='table']/div[2]/div/div/div[%s]/div", j);
            String actualData = driver.findElement(By.xpath(tableXpath)).getText();
            System.out.println("Actual Data: " + actualData);
            System.out.println("Expected Data: " + data[i]);

            Assert.assertEquals(actualData, data[i]);
        }

        driver.quit();
    }

}
