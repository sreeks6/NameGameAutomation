package com.willowtreeapps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

/**
 * Created on 5/23/17.
 */
public class HomePage extends BasePage {


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void validateTitleIsPresent() {
        WebElement title = driver.findElement(By.cssSelector("h1"));
        Assert.assertTrue(title != null);
    }


    public void validateClickingFirstPhotoIncreasesTriesCounter() {
        //Wait for page to load
        sleep(6000);

        int count = Integer.parseInt(driver.findElement(By.className("attempts")).getText());

        driver.findElement(By.className("photo")).click();

        sleep(6000);

        int countAfter = Integer.parseInt(driver.findElement(By.className("attempts")).getText());

        Assert.assertTrue(countAfter > count);

    }

    public void validateClickingCorrectPhotoIncreasesStreakCounter(){
        sleep(6000);

        //Get the streak count before clicking on the right photo option
        int streakCountBefore = Integer.parseInt(driver.findElement(By.className("streak")).getText());

        int correctCountBefore = Integer.parseInt(driver.findElement(By.className("correct")).getText());

        Assert.assertTrue(streakCountBefore == 0);

        //Getting the name of the question so we can get make the correct selection
        String name = driver.findElement(By.id("name")).getText();

        WebElement gallery = driver.findElement(By.id("gallery"));

        WebElement correctPhoto = gallery.findElement(By.xpath("//div[text()='"+name+"']"));

        //Performing the selenium action interaction so we can have the driver move to the intended element and perform the click option
        Actions action = new Actions(driver);
        action.moveToElement(correctPhoto).click().perform();
        sleep(6000);

        int streakCountAfter = Integer.parseInt(driver.findElement(By.className("streak")).getText());

        int correctCountAfter = Integer.parseInt(driver.findElement(By.className("correct")).getText());

        //Asserting that the correct counter is incremented after the right photo selection is made
        Assert.assertTrue(correctCountAfter > correctCountBefore);

        //Asserting that the streak counter is incremented after the correct selection is made
        Assert.assertTrue(streakCountAfter > streakCountBefore);

    }

    public void validateMultipleStreakCounterReset(){
        sleep(5000);

        int streakCountBefore = Integer.parseInt(driver.findElement(By.className("streak")).getText());

        Assert.assertTrue(streakCountBefore > 0);
        String name = driver.findElement(By.id("name")).getText();

        WebElement gallery = driver.findElement(By.id("gallery"));

        WebElement incorrectPhoto = gallery.findElement(By.xpath("//div[not(contains(text(),'"+name+"'))]"));

        Actions action = new Actions(driver);
        action.moveToElement(incorrectPhoto).click().perform();
        sleep(6000);

        int streakCountAfter = Integer.parseInt(driver.findElement(By.className("streak")).getText());

        Assert.assertTrue(streakCountAfter == 0);

    }

    public void validateTenRandomClicks(){
        sleep(2000);

        //Getting a list of all the nodes by class name photo and clicking on each one of them
        List<WebElement> allPhotos = driver.findElements(By.className("photo"));

        for(int i=0; i<allPhotos.size();i++){
            allPhotos.get(i).click();
            sleep(1000);
        }
    }

    public void validateNameandPhotosChange(){
        sleep(2000);

        String name = driver.findElement(By.id("name")).getText();

        WebElement gallery = driver.findElement(By.id("gallery"));

        WebElement correctPhoto = gallery.findElement(By.xpath("//div[text()='"+name+"']"));

        //Performing the selenium action interaction so we can have the driver move to the intended element and perform the click option
        Actions action = new Actions(driver);
        action.moveToElement(correctPhoto).click().perform();
        sleep(8000);

        //Getting the new string under name id and verifying that the name has changed
        String newName = driver.findElement(By.id("name")).getText();

        Assert.assertTrue(name!=newName);

        //Asserting that the new page does not contain the previous name/photo under the child nodes
        Assert.assertTrue(gallery.findElements(By.xpath("//div[text()='"+name+"']")).isEmpty());
    }
}
