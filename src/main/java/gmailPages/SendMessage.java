package gmailPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SendMessage {
    WebDriver webDriver;

    public SendMessage(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//*[@class='T-I T-I-KE L3']")
    private WebElement write;

    @FindBy(xpath = "//*[@class='wO nr l1']/textarea")
    private WebElement recipient;

    @FindBy(xpath = "//*[@class='aoP aoC']/tbody/tr/td/form/div[3]/div/input")
    private WebElement messageSubject;

    @FindBy(xpath = "//*[@class='Am Al editable LW-avf tS-tW']")
    private WebElement messageText;

    @FindBy(xpath = "//*[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")
    private WebElement sendMessage;

    public void writeMessage(){
        write.click();
    }

    public void inputRecipient() {
        recipient.sendKeys("rkhamidullin3@gmail.com");
    }

    public void inputMessageSubject() {
        messageSubject.sendKeys("Simbirsoft Тестовое задание. Khamidullin");
    }

    public void send(){
        sendMessage.click();
    }

}
