package praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage extends BasePage{

    private WebDriverWait wait;

    @FindBy(xpath = "//a[normalize-space(text())='Профиль']")
    private WebElement buttonProfile;

    @FindBy(xpath = "//a[normalize-space(text())='История заказов']")
    private WebElement linkHistoryOrders;

    @FindBy(xpath = "//button[normalize-space(text())='Выход']")
    private WebElement buttonExit;

    @FindBy(xpath = "//p[normalize-space(text())='В этом разделе вы можете изменить свои персональные данные']")
    private WebElement profileInfoText;

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Step("Получаем текст кнопки 'Профиль'")
    public String getButtonProfile() {
        return buttonProfile.getText();
    }

    @Step("Получаем текст кнопки 'История заказов'")
    public String getLinkHistoryOrders() {
        return linkHistoryOrders.getText();
    }

    @Step("Получаем текст кнопки 'Выход'")
    public String getButtonExit() {
        return buttonExit.getText();
    }

    @Step("Получаем текст информационного сообщения в профиле")
    public String getProfileInfoText() {
        return profileInfoText.getText();
    }

    @Step("Ожидание видимости кнопки 'Профиль'")
    public void waitForTitleProfile() {
        wait.until(ExpectedConditions.visibilityOf(buttonProfile));
    }

    @Step("Ожидание видимости кнопки 'Выход'")
    public void waitForButtonExit() {
        wait.until(ExpectedConditions.visibilityOf(buttonExit));
    }

    @Step("Кликаем на кнопку 'Выход'")
    public void clickButtonExit() {
        buttonExit.click();
    }
}