package gmailTests;


import gmailPages.ConfigProperties;
import gmailPages.Login;
import gmailPages.SendMessage;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Epic("Google mail")
@Story("Google mail testing")
public class LoginTests {
    WebDriver webDriver;
    Login login;
    SendMessage sendMessage;

    @BeforeEach
    void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        webDriver = new RemoteWebDriver(new URL("http://192.168.1.5:4444/wd/hub"), capabilities);
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        login = new Login(webDriver);
        sendMessage = new SendMessage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("https://mail.google.com/");
    }

    @Step("Авторизация и отправка письма")
    @Test
    public void loginAuthorizationTests() throws InterruptedException {
        login.inputMail(ConfigProperties.getProperty("mail"));
        login.emailSend();
        login.inputPassword(ConfigProperties.getProperty("password"));
        login.passwordSend();
        int countOfMessages = sendMessage.searchMails();
        sendMessage.writeMail(ConfigProperties.getProperty("mail"), ConfigProperties.getProperty("subject"),
                String.valueOf(sendMessage.searchMails()));
        webDriver.get(webDriver.getCurrentUrl());
        int countOfMessagesAfterSend = sendMessage.searchMails();
        Assertions.assertEquals(countOfMessages+1,countOfMessagesAfterSend);
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }
}
