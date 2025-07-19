# TestGenie - Test Automation Framework

TestGenie is a test automation framework built with Java, Selenium WebDriver, and TestNG, designed to provide a scalable and maintainable solution for web application testing.

## 🛠 Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- Extent Reports
- Log4j2

## 🏗 Framework Architecture

### Core Components

1. **Base Layer (`testFrameworkUtils`)**
   - `BaseTest.java`: Base class for all test classes
   - `WebDriverSetup.java`: Handles browser configuration and initialization
   - `Listeners.java`: TestNG listeners for test execution monitoring
   - `ScreenshotUtilities.java`: Manages screenshot capture for failed tests

2. **Utility Layer (`mainFrameworkUtils`)**
   - `ExtentReportManager`: Handles test execution reporting
   - `TimestampFormatter`: Utility for timestamp formatting
   - `ContentRepo`: Contains test data and constants

3. **Function Libraries**
   - `SeleniumFunctionLibrary.java`: Core Selenium wrapper methods
   - `SauceDemoFunctionLibrary.java`: Application-specific functions

4. **Test Layer (`allProductTest`)**
   - Contains test classes organized by application/feature
   - Implements test cases using page objects and utilities

### Key Features

- **Multi-Browser Support**: Chrome, Firefox, and Edge
- **Headless Execution**: Supported for all browsers
- **Parallel Execution**: ThreadLocal implementation for thread-safe execution
- **Robust Reporting**: Extent Reports integration with screenshots
- **Logging**: Comprehensive logging using Log4j2
- **Failure Analysis**: Automatic screenshot capture on test failure
- **Configuration Management**: Properties file-based configuration

## 🚀 Getting Started

### Prerequisites

- Java JDK 20
- Maven
- Chrome/Firefox/Edge browser

### Installation

1. Clone the repository:
```bash
git clone https://github.com/im-gulshan/TestGenie.git
```

## 📁 Project Structure

```
TestGenie/
├── src/
│   ├── main/java/
│   │   ├── configSettings/
│   │   │   └── GlobalData.properties
│   │   ├── mainFrameworkUtils/
│   │   │   ├── ExtentReportManager.java
│   │   │   └── TimestampFormatter.java
│   │   ├── productFunctionLibrary/
│   │   │   ├── SeleniumFunctionLibrary.java
│   │   │   └── SauceDemoFunctionLibrary.java
│   │   └── testFrameworkUtils/
│   │       ├── BaseTest.java
│   │       ├── WebDriverSetup.java
│   │       ├── Listeners.java
│   │       └── ScreenshotUtilities.java
│   └── test/java/
│       ├── allProductTest/
│       │   └── SauceDemoWebsite/
│       └── TestCaseFile/
│           └── (Your Excel-based test case classes here)
├── TestRunner/
│   └── SauceDemo.xml
├── reports/
│   ├── htmlReports/
│   └── screenshot/
└── pom.xml

```

## 📊 Reporting

- Extent Reports are generated in `reports/htmlReports/`
- Screenshots for failed tests are saved in `reports/screenshot/`
- Test execution logs are maintained using Log4j2

## 🔧 Configuration

The framework can be configured through `GlobalData.properties`:

```properties
browser=chrome
testEnvURL=https://www.saucedemo.com/
```