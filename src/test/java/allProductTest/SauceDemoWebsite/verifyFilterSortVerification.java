package allProductTest.SauceDemoWebsite;

import mainFrameworkUtils.ContentRepo;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testFrameworkUtils.BaseTest;
import xpathRepo.SauceDemoXpathRepo;

import java.util.ArrayList;
import java.util.List;

public class verifyFilterSortVerification extends BaseTest {

    @Test(dataProvider = "sortOptions")
    public void TC_004_testFilterSortVerification(String testCaseId, String sortOption) throws Exception{
        logToExtent("[" + testCaseId + "] SauceDemo application launched successfully.");

        // Step 1: Login
        sdfl.loginInSauceDemo();
        logToExtent("[" + testCaseId + "] User logged in successfully.");

        // Step 2: Apply Filter
        sdfl.selectDropdownByVisibleText(sortOption, SauceDemoXpathRepo.productFilterDropdown);
        logger.info("Applied filter: {}", sortOption);
        logToExtent("[" + testCaseId + "] Filter applied: "+ sortOption);

        // Step 3: Capture Product Names/Prices
        List<String> allProductsNames = List.of();
        List<Double> allProductsPrice = List.of();

        if(sortOption.equals("Name (A to Z)") || sortOption.equals("Name (Z to A)")){
            List<WebElement> productElements = sdfl.getListOfElements(SauceDemoXpathRepo.allProduct);
            allProductsNames = new ArrayList<>();
            for (WebElement element : productElements){
                allProductsNames.add(element.getText().trim());
            }
        } else{
            List<WebElement> productPriceElements = sdfl.getListOfElements(SauceDemoXpathRepo.allProductsPrice);
            allProductsPrice = new ArrayList<>();
            for (WebElement element : productPriceElements){
                String priceText = element.getText().replace("$", "");
                double price = Double.parseDouble(priceText);
                allProductsPrice.add(price);
            }
        }

        // Step 4: Capture Product Price
        if (sortOption.equals("Name (A to Z)") || sortOption.equals("Name (Z to A)")){
            logger.info("Captured product names: {}", allProductsNames);
            logToExtent("[" + testCaseId + "] Captured product names: " + allProductsNames);
        } else {
            logger.info("Captured product prices: {}", allProductsPrice);
            logToExtent("[" + testCaseId + "] Captured product prices: " + allProductsPrice);
        }

        // Step 5: Validate Sorting
        boolean isProductSorted = switch (sortOption) {
            case "Name (A to Z)" -> sdfl.isSortedAZ(allProductsNames);
            case "Name (Z to A)" -> sdfl.isSortedZA(allProductsNames);
            case "Price (low to high)" -> sdfl.isSortedLowToHigh(allProductsPrice);
            case "Price (high to low)" -> sdfl.isSortedHighToLow(allProductsPrice);
            default -> false;
        };

        if (!isProductSorted){
            logger.error("❌ Products are not sorted in {} order.", sortOption);
            logToExtent("[" + testCaseId + "] ❌ Products are not sorted in "+sortOption+" order.");
            Assert.fail("❌ Products are not sorted in "+sortOption+" order.");
        }
        logger.info("✅ Products are sorted in {} order.", sortOption);
        logToExtent("[" + testCaseId + "] ✅ Products are sorted in "+sortOption+" order.");
    } // TC_004_01_testFilterSortVerification

    @DataProvider(name = "sortOptions")
    public String[][] getFilters(){
        List<String> filters = ContentRepo.productFilterOptions;

        String[][] data = new String[filters.size()][2];
        for (int i=0; i< filters.size(); i++){
            data[i][0] = "TC_004_"+String.format("%02d", i+1); // e.g., TC_004_01
            data[i][1] = filters.get(i); // sort option
        }
        return data;
    }

} // verifyFilterSortVerification
