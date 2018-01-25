/* This is test to conduct 10 clicks and then verifying that the tries and correct counters are incremented based on the results */

package com.willowtreeapps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class testTenRandomSelectionCounter {


    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.mac");
        Capabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities);
        driver.navigate().to("http://www.ericrochester.com/name-game/");
    }

    @Test
    public void test_ten_random_selection_counter() throws InterruptedException {

        //Verifying that the tries and correct counters are set to 0 when launched for the first time
        WebElement stats = driver.findElement(By.id("stats"));

        int triesBefore = Integer.parseInt(stats.findElement(By.className("attempts")).getText());

        int correctBefore = Integer.parseInt(stats.findElement(By.className("correct")).getText());

        Assert.assertTrue(triesBefore==0);
        Assert.assertTrue(correctBefore == 0);

        //Making 10 clicks through two questions. Not optimized but does the trick. Can be optimized better.
        for(int j=0;j<2;j++) {
            new HomePage(driver).validateTenRandomClicks();
            Thread.sleep(2000);
        }


        //Verifying that the tries counter is set to 10 after 10 tries and both tries and correct counters are incremented after 10 attempts
        int triesAfter = Integer.parseInt(stats.findElement(By.className("attempts")).getText());

        int correctAfter = Integer.parseInt(stats.findElement(By.className("correct")).getText());

        Assert.assertTrue(triesAfter > triesBefore);
        Assert.assertTrue(triesAfter == 10);
        Assert.assertTrue(correctAfter > correctBefore);

    }

    @After
    public void teardown() {
        driver.quit();
        System.clearProperty("webdriver.chrome.driver");
    }
}
