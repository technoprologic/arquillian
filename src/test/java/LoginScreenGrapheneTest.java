/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mzych
 */

import java.io.File;
import java.net.URL;
import static junit.framework.Assert.fail;
import org.apache.commons.io.FileUtils;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.graphene.Graphene;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import static org.jboss.arquillian.graphene.Graphene.guardHttp;
import static org.jboss.arquillian.graphene.Graphene.waitAjax;
import org.jboss.arquillian.graphene.findby.FindByJQuery;

import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import pageObjects.LoginPage;


@RunWith(Arquillian.class)
public class LoginScreenGrapheneTest {

    private static final String WEBAPP_SRC = "src/main/webapp";

    @Drone
    private WebDriver browser;

    @ArquillianResource
    private URL deploymentUrl;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return MyDeployment.createLoginScreenDeployment();
    }

    @FindBy(id = "userName")          // 1. injects an element by default location strategy ("idOrName")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(tagName = "li")                     // 2. injects a first element with given tag name
    private WebElement facesMessage;

    @FindByJQuery("p:visible")                  // 3. injects an element using jQuery selector
    private WebElement signedAs;

    @FindBy(css = "input[type=submit]")
    private WebElement whoAmI;


    
    @Test
    @RunAsClient
    public void ShouldBePosibleToSearchTestowkaAtGoogle()
            throws InterruptedException {
        browser.get("http://google.pl");
        WebElement searchField = browser.findElement(By.name("q"));
        searchField.sendKeys("testowka.pl");
        WebElement searchButton = browser.findElement(By
                .id("sblsbb"));
        searchButton.click();
        for (int second = 0;; second++) {
            if (second >= 60)
                fail("timeout");
            try {
                if (browser.findElement(By.linkText("Testowka.pl"))
                        .isDisplayed()) {
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
        WebElement linkToTestowka = browser.findElement(By
                .linkText("Testowka.pl"));
        linkToTestowka.click();
    }
    
    
    @Test
    @RunAsClient
    public void should_return_sport_news(){
        browser.get("http://sportowefakty.pl");
        WebElement footballLink = browser.findElement(By.linkText("Piłka nożna"));
        footballLink.click();
        WebElement ekstraklasaLink = browser.findElement(By.linkText("Ekstraklasa"));
        ekstraklasaLink.click();
        System.out.println();        
    }
    
    
        @Test
    @RunAsClient
    public void should_login_successfully() {
        browser.get(deploymentUrl.toExternalForm() + "login.jsf");      // 1. open the tested page

        userName.sendKeys("demo");                                      // 3. control the page
        password.sendKeys("demo");

        guardHttp(loginButton).click();

        assertEquals("Welcome", facesMessage.getText().trim());
        whoAmI.click();
        waitAjax().until().element(signedAs).is().present();
        assertTrue(signedAs.getText().contains("demo"));
    }

}
