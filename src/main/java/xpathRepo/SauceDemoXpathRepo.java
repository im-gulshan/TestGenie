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
    public static String allProductElements = "//div[@class='inventory_item_name ']";
    public static String allProductsPrice = "//div[@class='inventory_item_price']";



} // SauceDemoXpathRepo
