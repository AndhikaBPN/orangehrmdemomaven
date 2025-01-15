package com.demo.testlistener;

import io.cucumber.java.After;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class ListenerRunner {

    ChromeDriver driver = new ChromeDriver();

    @After
    public void afterScenario() {
        System.out.println("After Scenario");
        driver.quit();
    }

}
