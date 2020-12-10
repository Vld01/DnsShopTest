package ru.appline.framework.managers;

import ru.appline.framework.pages.BasketPage;
import ru.appline.framework.pages.ProductPage;
import ru.appline.framework.pages.SearchPage;
import ru.appline.framework.pages.StartPage;

/**
 * Класс для управления страницами
 */
public class PageManager {

    /**
     * Менеджер страниц
     */
    private static PageManager pageManager;

    /**
     * Стартовая страница
     */
    StartPage startPage;

    /**
     * Страница поиска товара
     */
    SearchPage searchPage;

    /**
     * Страница товара
     */
    ProductPage productPage;

    /**
     * Страница корзины
     */
    BasketPage basketPage;

    /**
     * Конструктор специально сделал приватным (синглтон)
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация ManagerPages
     *
     * @return ManagerPages
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link ru.appline.framework.pages.StartPage}
     *
     * @return StartPage
     */
    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    /**
     * Ленивая инициализация {@link ru.appline.framework.pages.SearchPage}
     *
     * @return SearchPage
     */
    public SearchPage getSearchPage() {
        if (searchPage == null) {
            searchPage = new SearchPage();
        }
        return searchPage;
    }

    /**
     * Ленивая инициализация {@link ru.appline.framework.pages.ProductPage}
     *
     * @return ProductPage
     */
    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;
    }

    /**
     * Ленивая инициализация {@link ru.appline.framework.pages.BasketPage}
     *
     * @return BasketPage
     */
    public BasketPage getBasketPage() {
        if (basketPage == null) {
            basketPage = new BasketPage();
        }
        return basketPage;
    }
}
