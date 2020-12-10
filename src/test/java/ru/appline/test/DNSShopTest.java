package ru.appline.test;

import org.junit.jupiter.api.Test;
import ru.appline.base.BaseTests;

public class DnsShopTest extends BaseTests {

    @Test
    public void bayInDnsShopTest(){
        app.getStartPage()
                .selectProductSearch("playstation")                     // 2. В поиске найти playstation
                .clickProductSearch("playstation 4 slim black")      // 3. Кликнуть по playstation 4 slim black
                .setNameAndPriceProduct("playstation")                              // 4. Запоминаем цену (и имя)
                .setYearWarranty(2)                                                 // 5. Доп.гарантия - выбрать 2 года и // 6. Дождаться изменения цены и запомнить цену с гарантией
                .buyProduct()                                                       // 7. Нажать Купить
                .selectProductSearch("Detroit")                                     // 8. Выполнить поиск Detroit
                .setNameAndPriceProduct("Detroit")                                  // 9. Запоминаем цену (и имя)
                .buyProduct()                                                       // 10. Нажать Купить
                .checkPriceBasket()                                                 // 11. Проверить что цена корзины стала равна сумме покупок
                .getBasketPage()                                                    // 12. Перейти в корзину
                .checkYearWarranty(2)                                               // 13. Проверить, что для приставки выбрана гарантия на 2 года
                .checkAllPrice()                                                    // 14. Проверить цену каждого из товаров и сумму
                .deleteDetroit()                                                    // 15. Удалить из корзины Detroit  // 16. Проверить что Detroit нет больше в корзине и что сумма уменьшилась на цену Detroit
                .addTwoPS4()                                                        // 17. Добавить еще 2 playstation (кнопкой +) и проверить что сумма верна (равна трем ценам playstation)
                .returnDeleteProduct();                                             // 18. Нажать вернуть удаленный товар, проверить, что Detroit появился в корзине и сумма увеличилась на его значение
    }
}
