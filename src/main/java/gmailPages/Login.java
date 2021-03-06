package gmailPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
    WebDriver webDriver;

    public Login(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
        this.webDriver = webDriver;
    }

    @FindBy(id = "identifierId")
    private WebElement email;

    @FindBy(xpath = "//*[@id='identifierNext']/div/button/span")
    private WebElement emailFurther;

    @FindBy(xpath = "//*[@id='password']/div[1]/div/div[1]/input")
    private WebElement password;

    @FindBy(xpath = "//*[@id='passwordNext']/div/button/span")
    private WebElement passwordFurther;

    public void inputMail(String mail) {
        email.sendKeys(mail);
    }

    public void emailSend() {
        emailFurther.click();
    }

    public void inputPassword(String pwd) {
        password.sendKeys(pwd);
    }

    public void passwordSend() {
        passwordFurther.click();
    }
}
