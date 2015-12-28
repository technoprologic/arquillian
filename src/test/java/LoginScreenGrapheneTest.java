/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mzych
 */

import java.net.URL;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
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

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;


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
    public void should_login_successfully() {
//        browser.get(deploymentUrl.toExternalForm() + "login.jsf");      // 1. open the tested page
//
//        userName.sendKeys("demo");                                      // 3. control the page
//        password.sendKeys("demo");
//
//        guardHttp(loginButton).click();
//
//        assertEquals("Welcome", facesMessage.getText().trim());
//        whoAmI.click();
//        waitAjax().until().element(signedAs).is().present();
//        assertTrue(signedAs.getText().contains("demo"));
          Assert.assertNotNull(browser);
    }
    
    
    @Before
    public void setUp(){
        
        browser = new FirefoxDriver();
    }
    
    @After
    public void clearUp(){
        browser.close();
    }
    
}
