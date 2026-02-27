package praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header extends BasePage {

    private WebDriverWait wait;

    @FindBy(xpath = "//p[normalize-space(text())='Личный Кабинет']")
    private WebElement personalAccountLink;

    @FindBy(xpath = "//p[normalize-space(text())='Конструктор']")
    private WebElement constructorLink;

    @FindBy(xpath = "//div[@class='AppHeader_header__logo__2D0X2']/a")
    private WebElement logoStellarBurgers;

    public Header(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Кликаем на 'Личный Кабинет' в хедере")
    public void clickPersonalAccountLink() {
        personalAccountLink.click();
    }

    @Step("Кликаем на 'Конструктор' в хедере")
    public void clickConstructorLink() {
        constructorLink.click();
    }

    @Step("Кликаем на логотип в хедере")
    public void clickLogoStellarBurgers() {
        logoStellarBurgers.click();
    }
}