package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Переключение между вкладками ингредиентов")
public class ConstructorPageTest extends BaseTest {

    @Test
    @DisplayName("Пользователь может переключаться между вкладками: Булки, Соусы, Начинки")
    @Description("Клик по вкладкам «Соусы», «Начинки», «Булки» прокручивает страницу к соответствующей секции, и активная вкладка подсвечивается")
    public void checkClickOnTheTabBun() {
        constructorPage.clickTabFilling();
        constructorPage.clickTabSauce();
        constructorPage.clickTabBun();
        assertEquals("Булки", constructorPage.getTabIngredients());
    }

    @Test
    @DisplayName("Пользователь может переключаться между вкладками: Булки, Соусы, Начинки")
    @Description("Клик по вкладкам «Соусы», «Начинки», «Булки» прокручивает страницу к соответствующей секции, и активная вкладка подсвечивается")
    public void checkClickOnTheTabFilling() {
        constructorPage.clickTabSauce();
        constructorPage.clickTabBun();
        constructorPage.clickTabFilling();
        assertEquals("Начинки", constructorPage.getTabIngredients());
    }

    @Test
    @DisplayName("Пользователь может переключаться между вкладками: Булки, Соусы, Начинки")
    @Description("Клик по вкладкам «Соусы», «Начинки», «Булки» прокручивает страницу к соответствующей секции, и активная вкладка подсвечивается")
    public void checkClickOnTheTabSauce() {
        constructorPage.clickTabBun();
        constructorPage.clickTabFilling();
        constructorPage.clickTabSauce();
        assertEquals("Соусы", constructorPage.getTabIngredients());
    }

}