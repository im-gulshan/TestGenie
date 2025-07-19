package xpathRepo;

public class SauceDemoXpathRepo {
    public static String userName = "//input[@id='user-name']";
    public static String password = "//input[@id='password']";
    public static String login = "//input[@id='login-button']";
    public static String hamburgerMenu = "//button[contains(@id, 'burger-menu')]";
    public static String hamburgerMenuSubOptions = "//a[contains(@class, 'menu-item')]";
    public static String appLogo = "//div[@class='app_logo']";
    public static String productTitle = "//span[@class='title']";
    public static String productFilterOptions = "//select[@class='product_sort_container']/option";
    public static String productFilterDropdown = "product_sort_container";
    public static String allProduct = "//div[contains(@class, 'item_name')]";
    public static String allProductsPrice = "//div[@class='inventory_item_price']";
    public static String productNamePrefix = "//div[text() = '";
    public static String productNameSuffix = "']//ancestor::div[@class='inventory_item_label']//following-sibling::div//button";
    public static String cart = "//a[contains(@class, 'shopping_cart')]";
    public static String checkout = "//button[@id='checkout']";
    public static String firstName = "//input[@id='first-name']";
    public static String lastName = "//input[@id='last-name']";
    public static String zipCode = "//input[@id='postal-code']";
    public static String continueBtn = "//input[@id='continue']";
    public static String finish = "//button[@id = 'finish']";
    public static String thankyouForYourOrder = "//h2[@class= 'complete-header']";
    public static String productDispatchedMsg = "//div[@class='complete-text']";
    public static String backHome = "//button[@id='back-to-products']";



} // SauceDemoXpathRepo
