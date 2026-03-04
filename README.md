# TestGenie - Selenium Automation Framework

TestGenie is a Java + Selenium + TestNG automation framework designed around reusable function libraries, product-specific base tests, and thread-safe reporting for parallel execution.

## Tech Stack
- Java 20
- Selenium WebDriver 4
- TestNG 7
- Maven (Surefire)
- Extent Reports 5
- Log4j2
- Apache POI (available for test data utilities)

## Framework Flow
The framework follows a clear two-layer setup before each test class:

1. `BaseTest.setUp()` (in `testFramework.core`)
- Launches browser using `WebDriverSetup`
- Browser source: `-Dbrowser` override -> `GlobalData.properties` -> default `chrome`
- Headless source: `-Dheadless` override -> `headLess` in `GlobalData.properties`

2. Product base setup (in `testFramework.base`)
- `SauceDemoBaseTest.loginToSauceDemo()` opens SauceDemo URL from `SauceDemo.properties` and logs in once per class
- `BlogspotBaseTest.initBlogspot()` opens Blogspot URL from `Blogspot.properties`

3. Test execution
- Test classes extend product base classes (`SauceDemoBaseTest`, `BlogspotBaseTest`)
- Tests call reusable methods from function libraries instead of direct WebDriver actions

4. Reporting and teardown
- `Listeners` creates `ExtentTest` per test method via `ThreadLocal`
- On failure, screenshot is captured and attached to Extent report
- `BaseTest.tearDown()` quits browser after class execution
- Report is flushed on suite finish

## Architecture

### `testFramework.core`
- `BaseTest`: common setup/teardown + logger + Extent logging helper
- `WebDriverSetup`: browser bootstrapping and browser options

### `testFramework.base`
- `SauceDemoBaseTest`: product-level login-once behavior
- `BlogspotBaseTest`: product-level app initialization

### `testFramework.reporting`
- `Listeners`: TestNG lifecycle hooks, pass/fail/skip logging, screenshot integration
- `ScreenshotUtilities`: stores failure screenshots under `reports/screenshot/`

### `productFunctionLibrary`
- `SeleniumFunctionLibrary`: wrappers (`enterText`, `clickOnElement`, waits, dropdown, scroll, URL fetch)
- `SauceDemoFunctionLibrary`: SauceDemo workflows + sorting validators
- `BlogspotFunctionLibrary`: Blogspot form operations

### `mainFrameworkUtils`
- `ConfigurationManager`: thread-safe property loading + caching + system property override support
- `ExtentReportManager`: timestamped Spark report setup
- `TimestampFormatter`: timestamp utility for logs/reports/screenshots
- `ContentRepo`: expected values/test constants

### `xpathRepo`
- Centralized locator repositories for SauceDemo and Blogspot

### `src/test/java/allProductTest`
- Product-wise test classes
- SauceDemo scenarios:
  - Hamburger menu options
  - App logo and page title validation
  - Filter option validation
  - Sort-order verification using DataProvider
  - Add-to-cart and checkout flow
- Blogspot sample form test

## Project Structure
```text
SeleniumAutomation/
|-- src/
|   |-- main/java/
|   |   |-- configSettings/
|   |   |-- mainFrameworkUtils/
|   |   |-- productFunctionLibrary/
|   |   |-- testFramework/
|   |   |   |-- base/
|   |   |   |-- core/
|   |   |   `-- reporting/
|   |   `-- xpathRepo/
|   |-- main/resources/
|   |   `-- log4j2.xml
|   `-- test/java/
|       `-- allProductTest/
|-- TestRunner/
|   |-- Debugger.xml
|   |-- Regression.xml
|   `-- SauceDemo.xml
|-- reports/
|   |-- htmlReports/
|   `-- screenshot/
|-- Logs/
|-- FrameworkDetails/
|-- pom.xml
`-- README.md
```

## Configuration

### Global (`src/main/java/configSettings/GlobalData.properties`)
```properties
browser=edge
headLess=false
```

### Product-level
- `SauceDemo.properties`: `url`, `username`, `password`
- `Blogspot.properties`: `url`

### Runtime overrides (recommended for CI/CD)
Any key can be overridden from JVM system property. Examples:
```bash
mvn test -Dbrowser=chrome -Dheadless=true
mvn test -Durl=https://www.saucedemo.com/
mvn test -Dusername=standard_user -Dpassword=secret_sauce
```

## Test Execution

### Default run (uses Surefire suite in `pom.xml`)
```bash
mvn clean test
```
Default suite: `TestRunner\Regression.xml`

### Run a specific suite
```bash
mvn test -Dsurefire.suiteXmlFiles=TestRunner/SauceDemo.xml
mvn test -Dsurefire.suiteXmlFiles=TestRunner/Debugger.xml
```

### Parallel model
- Current suite files are configured as `parallel="classes"` with `thread-count="5"`
- Each test class gets its own driver instance
- Extent logging is thread-safe through `ThreadLocal`

## Reporting and Logs
- HTML reports: `reports/htmlReports/TestGenie-<timestamp>.html`
- Failure screenshots: `reports/screenshot/<test>-<timestamp>.png`
- Execution logs (Log4j2 rolling file): `Logs/TestGenieAutomation-<timestamp>.log`

## Design Pattern in Practice
- Reusable action layer in `SeleniumFunctionLibrary`
- Product-specific behavior in dedicated function libraries
- Product-specific base classes for one-time app setup/login per class
- Centralized configs and locators to reduce test duplication

## Scaling the Framework to a New Product
To onboard a new product quickly:

1. Add `<Product>.properties` in `configSettings`
2. Add `<Product>XpathRepo.java` in `xpathRepo`
3. Add `<Product>FunctionLibrary.java` extending `SeleniumFunctionLibrary`
4. Add `<Product>BaseTest.java` extending `BaseTest` (with product-level init/login)
5. Add tests under `allProductTest/<Product>Website/`
6. Add/extend a TestNG suite in `TestRunner/`
