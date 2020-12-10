package ru.appline.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.appline.framework.utils.Product;


public class StartPage extends BasePage {
    @FindBy(xpath = "//input[@placeholder='Поиск по сайту']")
    WebElement searchElement;

    /**
     * Функция поиска продукта
     *
     * @param nameProduct - наименование продукта
     * @return SearchPage - т.е. переходим на страницу {@link ru.appline.framework.pages.SearchPage}
     */
    public SearchPage selectProductSearch(String nameProduct){
        fillInputField(searchElement, nameProduct);
        Assertions.assertEquals(nameProduct, searchElement.getAttribute("value"), "Наименование продукта " + nameProduct + " в графе поиск заполнено некорректно");
        mapProduct.put(nameProduct, new Product(nameProduct));
        searchElement.sendKeys(Keys.ENTER);
        return app.getSearchPage();
    }
}
