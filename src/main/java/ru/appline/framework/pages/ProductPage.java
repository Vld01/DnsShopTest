package ru.appline.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.appline.framework.utils.Product;

public class ProductPage extends BasePage{
    String searchNameProduct = "playstation";

    @FindBy(xpath = "//span[contains (@class, 'product-card-price__current')]")
    WebElement productPrice;

    @FindBy(xpath = "//h1[@data-product-param = 'name']")
    WebElement productName;

    @FindBy(xpath = "//select[@class = 'form-control']")
    WebElement elementSelectWarranty;

    @FindBy(xpath = "//button[text() = 'Купить']")
    WebElement buyButton;

    @FindBy(xpath = "//input[@placeholder='Поиск по сайту']")
    WebElement searchElement;

    @FindBy(xpath = "//span[contains(@class, 'card-price__discount_active')]")
    WebElement warrantyActive;

    @FindBy(xpath = "//span[@class = 'cart-link__price']")
    WebElement basketPrice;

    /**
     * Функция сохранения имени и цены в объект
     *
     * @param searchNameProduct - наименование при поиске продукта
     * @return ProductPage - т.е. остаемся на этой странице
     */
    public ProductPage setNameAndPriceProduct(String searchNameProduct){
        this.searchNameProduct = searchNameProduct;
        elementToBeVisible(productName);
        mapProduct.get(searchNameProduct)
                .setName(productName.getText());
        elementToBeVisible(productPrice);
        mapProduct.get(searchNameProduct).setPrice(Integer.parseInt(productPrice.getText().replaceAll(" ", "").replaceAll("₽", "")));
        return this;
    }

    /**
     * Функция выбора дополнительной гарантии и запоминания цены с гарантией
     *
     * @param yearWarranty - количество лет гарантии один или два
     * @return ProductPage - т.е. остаемся на этой странице
     */
    public ProductPage setYearWarranty(int yearWarranty){
        elementToBeClickable(elementSelectWarranty).click();
        switch (yearWarranty) {
            case 1:
                elementToBeClickable(elementSelectWarranty.findElement(By.xpath("./option[text() = '1 год']"))).click();
                break;
            case 2:
                elementToBeClickable(elementSelectWarranty.findElement(By.xpath("./option[text() = '2 года']"))).click();
                break;
            default:
                elementToBeClickable(elementSelectWarranty.findElement(By.xpath("./option[text() = '-------------------']"))).click();
        }

        mapProduct.get(searchNameProduct)
                .setWarranty(yearWarranty);

        for (int i = 0; i < 20 && !warrantyActive.getText().equals("Цена изменена"); i++){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mapProduct.get(searchNameProduct)
                .setPriceWithWarranty(Integer.parseInt(productPrice.getText().replaceAll(" ", "").replaceAll("₽", "")));
        return this;
    }

    /**
     * Функция покупки продукта
     *
     * @return ProductPage - т.е. остаемся на этой странице
     */
    public ProductPage buyProduct(){
        elementToBeVisible(buyButton);
        elementToBeClickable(buyButton).click();
        return this;
    }

    /**
     * Функция поиска продукта
     *
     * @param nameProduct - наименование продукта
     * @return ProductPage - т.е. остаемся на этой странице
     */
    public ProductPage selectProductSearch(String nameProduct){
        fillInputField(searchElement, nameProduct);
        Assertions.assertEquals(nameProduct, searchElement.getAttribute("value"), "Наименование продукта " + nameProduct + " в графе поиск заполнено некорректно");
        searchElement.sendKeys(Keys.ENTER);
        mapProduct.put(nameProduct, new Product(nameProduct));
        return app.getProductPage();
    }

    /**
     * Функция проверки цены корзины
     *
     * @return ProductPage - т.е. остаемся на этой странице
     */
    public ProductPage checkPriceBasket(){
        int priceBasketWithWarranty = Integer.parseInt(elementToBeVisible(basketPrice).getText().replaceAll(" ", "").replaceAll("₽", ""));
        int actualPriceBasket = mapProduct.get("playstation").getPriceWithWarranty() + mapProduct.get("Detroit").getPrice();

        for (int i = 0; i < 20 && priceBasketWithWarranty != actualPriceBasket; i++){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i == 15) {
                action.sendKeys(Keys.chord(Keys.CONTROL, "r"));  //перезагрузка страницы
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                priceBasketWithWarranty = Integer.parseInt(elementToBeVisible(basketPrice).getText().replaceAll(" ", "").replaceAll("₽", ""));
            }
        }

        Assertions.assertEquals(priceBasketWithWarranty, actualPriceBasket, "Цена корзины не соответствует");
        return this;
    }

    /**
     * Функция перехода в корзину
     *
     * @return BasketPage - т.е. переходим на страницу {@link ru.appline.framework.pages.BasketPage}
     */
    public BasketPage getBasketPage(){
        elementToBeClickable(basketPrice).click();
        return app.getBasketPage();
    }
}
