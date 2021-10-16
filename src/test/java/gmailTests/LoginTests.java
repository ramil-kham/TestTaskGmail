package gmailTests;

import gmailPages.Login;
import gmailPages.SendMessage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginTests {

    WebDriver webDriver;
    Login login;
    SendMessage sendMessage;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Airis\\IdeaProjects\\SimbirSoftTestTask\\src\\main\\resources\\chromedriver.exe");
        webDriver = new ChromeDriver();
        login = new Login(webDriver);
        sendMessage = new SendMessage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.google.com/intl/ru/gmail/about/");
    }

//    @AfterAll
//    void quit() {
//        webDriver.quit();
//    }

    @Test
    public void loginAuthorizationTests() throws InterruptedException {
        login.signIn();
        login.inputMail("rkhamidullin3@gmail.com");
        login.emailFurther();
        login.inputPassword("userpass");
        login.pwdFurther();
        Thread.sleep(5000);
        Assertions.assertEquals("https://mail.google.com/mail/u/0/#inbox", webDriver.getCurrentUrl());
        List<WebElement> count = webDriver.findElements(By.xpath("//*[@class='bog']//span[contains(text(), 'Simbirsoft')]"));
        int countOfMessages = count.size();
        Assertions.assertEquals(3,countOfMessages);
        sendMessage.writeMessage();
        sendMessage.inputRecipient();
        sendMessage.inputMessageSubject();
        webDriver.findElement(By.xpath("//*[@class='Am Al editable LW-avf tS-tW']")).sendKeys(String.valueOf(countOfMessages));
        sendMessage.send();
        webDriver.quit();
    }
}
