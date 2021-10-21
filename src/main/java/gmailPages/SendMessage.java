package gmailPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

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

    public void inputRecipient(String mail) {
        recipient.sendKeys(mail);
    }

    public void inputMessageSubject(String subject) {
        messageSubject.sendKeys(subject);
    }

    public void send(){
        sendMessage.click();
    }

    public int searchMails() {
        List<WebElement> count = webDriver.findElements(By.xpath("//*[@class='bog']//span[contains(text(), 'Simbirsoft')]"));
        return count.size();
    }

    public void inputMessageText(String content) {
        messageText.sendKeys(String.valueOf(searchMails()));
    }

    public void writeMail(String mail, String subject, String content) {
        writeMessage();
        inputRecipient(mail);
        inputMessageSubject(subject);
        inputMessageText(content);
        send();
    }
}
