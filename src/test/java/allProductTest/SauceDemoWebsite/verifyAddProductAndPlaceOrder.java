package allProductTest.SauceDemoWebsite;

import mainFrameworkUtils.ContentRepo;
import org.testng.Assert;
import org.testng.annotations.Test;
import testFrameworkUtils.BaseTest;
import xpathRepo.SauceDemoXpathRepo;

public class verifyAddProductAndPlaceOrder extends BaseTest {

    @Test
    public void TC_005_testAddProductAndPlaceOrder() throws Exception {

        logToExtent("SauceDemo application launched successfully.");

        // Login to the application
        sdfl.loginInSauceDemo();
        logToExtent("User logged in successfully.");

        String givenProduct = "Sauce Labs Backpack";
        String fullProductXpath = SauceDemoXpathRepo.productNamePrefix+ givenProduct +SauceDemoXpathRepo.productNameSuffix;

        //Step 1 - Add givenProduct to cart
        sdfl.clickOnElement(fullProductXpath);
        sdfl.clickOnElement(SauceDemoXpathRepo.cart);

        //Step -2 Verify if same added givenProduct is present in cart
        String productInCart = sdfl.getTextOfElement(SauceDemoXpathRepo.allProduct);
        logToExtent("Expected Product name: "+ givenProduct);
        logToExtent("Actual Product name: "+ productInCart);
        try{
            Assert.assertEquals(productInCart, givenProduct,
                    "❌ Mismatch in cart product name. Expected: "+givenProduct+
                    "but found: "+productInCart);
            logToExtent("✅ Product name matched in cart page, expected values.");
        }catch (Exception e){
            logToExtent("❌ Assertion Failed: " + e.getMessage());
            throw e; // Re-throw to let TestNG mark the test as failed
        }

        // Step-3 Checkout
        sdfl.clickOnElement(SauceDemoXpathRepo.checkout);

        // Step-4 Add Information details
        String firstName = "Gulshan", lastName = "Kumar", zipCode = "119982";
        sdfl.enterText(SauceDemoXpathRepo.firstName, firstName);
        sdfl.enterText(SauceDemoXpathRepo.lastName, lastName);
        sdfl.enterText(SauceDemoXpathRepo.zipCode, zipCode);

        logToExtent("Details Added into Information page - First Name : "+firstName+", Last Name : "+lastName+", ZipCode : "+zipCode);

        // Step - 6 Click on continue
        sdfl.clickOnElement(SauceDemoXpathRepo.continueBtn);

        // Step - 7 Verify product in overview page
        String productInOverView = sdfl.getTextOfElement(SauceDemoXpathRepo.allProduct);
        logToExtent("Product in Overview Page : "+productInOverView);
        try{
            Assert.assertEquals(productInOverView, givenProduct,
                    "❌ Mismatch in overview product name. Expected: "+givenProduct+
                            "but found: "+productInOverView);
            logToExtent("✅ Product name matched in overview page");
        }catch (Exception e){
            logToExtent("❌ Assertion Failed: " + e.getMessage());
            throw e; // Re-throw to let TestNG mark the test as failed
        }

        // Step - 8 Click on Finish
        sdfl.scrollToElement(SauceDemoXpathRepo.finish);
        sdfl.clickOnElement(SauceDemoXpathRepo.finish);

        // Step-9 Verify thankyou page msg
        String actualThankyouMsg = sdfl.getTextOfElement(SauceDemoXpathRepo.thankyouForYourOrder);
        String expectedThankyouMsg = ContentRepo.thankyouMsg;

        logToExtent("Expected Thankyou message : "+expectedThankyouMsg);
        logToExtent("Actual Thankyou message : "+actualThankyouMsg);

        try{
            Assert.assertEquals(expectedThankyouMsg, actualThankyouMsg,
                    "❌ Mismatch in actual and expected Thank-You message. Expected: "+expectedThankyouMsg+
                            "but found: "+actualThankyouMsg);
            logToExtent("✅ Thank-you messaged matched in complete page.");
        }catch (Exception e){
            logToExtent("❌ Assertion Failed: " + e.getMessage());
            throw e; // Re-throw to let TestNG mark the test as failed
        }

        //Step 10 Verify dispatched Message
        String actualDispatchMsg = sdfl.getTextOfElement(SauceDemoXpathRepo.productDispatchedMsg);
        String expectedDispatchMsg = ContentRepo.productDispatchedMsg;

        logToExtent("Expected Dispatched message : "+expectedDispatchMsg);
        logToExtent("Actual Dispatched message : "+expectedDispatchMsg);

        try{
            Assert.assertEquals(expectedDispatchMsg, actualDispatchMsg,
                    "❌ Mismatch in actual and expected Dispatched message. Expected: "+expectedDispatchMsg+
                            "but found: "+actualDispatchMsg);
            logToExtent("✅ Dispatched messaged matched in complete page.");
        }catch (Exception e){
            logToExtent("❌ Assertion Failed: " + e.getMessage());
            throw e; // Re-throw to let TestNG mark the test as failed
        }

        // Step 11 Click on Back Home button
        sdfl.clickOnElement(SauceDemoXpathRepo.backHome);

        String actualCurrentPageUrl = sdfl.getCurrentPageURL();
        String expectedCurrentPageUrl = ContentRepo.homePageUrl;
//
        logToExtent("Expected Home Page URL : "+expectedCurrentPageUrl);
        logToExtent("Actual Home Page URL  : "+actualCurrentPageUrl);

            if(!actualCurrentPageUrl.equals(expectedCurrentPageUrl)){
                Assert.fail("Assertion fialed for Home page");
            }
            logToExtent("✅ Home Page URL matched, User is on Home Page");
    }
}
