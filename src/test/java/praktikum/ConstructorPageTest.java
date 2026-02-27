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
    public void checkClickOnTheTab() throws InterruptedException {
        assertEquals("Булки", constructorPage.getTabIngredients());

        constructorPage.clickTabFilling();
        assertEquals("Начинки", constructorPage.getTabIngredients());

        constructorPage.clickTabBun();
        assertEquals("Булки", constructorPage.getTabIngredients());

        constructorPage.clickTabSauce();
        assertEquals("Соусы", constructorPage.getTabIngredients());
    }

}