/*This test is to verify that the mutliple streak counter is reset after making an incorrect selection*/

package com.willowtreeapps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class testMultipleStreakReset {

    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.mac");
        Capabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities);
        driver.navigate().to("http://www.ericrochester.com/name-game/");
    }

    @Test
    public void test_multiple_streak_reset(){

        //Validating that the streak counter increases on the right selection
        new HomePage(driver).validateClickingCorrectPhotoIncreasesStreakCounter();

        //Validating that the streak counter is reset on the incorrect selection
        new HomePage(driver).validateMultipleStreakCounterReset();

    }

    @After
    public void teardown() {
        driver.quit();
        System.clearProperty("webdriver.chrome.driver");
    }
}
