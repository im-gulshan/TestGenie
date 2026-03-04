package mainFrameworkUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigurationManager {

    private static final Logger logger = LogManager.getLogger(ConfigurationManager.class);

    // Thread-safe cache to store loaded properties files
    private static final ConcurrentHashMap<String, Properties> propertiesCache = new ConcurrentHashMap<>();

    // Base directory for all configuration files
    private static final String CONFIG_DIR = "configSettings";

    // Get property value from specified properties file
    public static String getProperty(String fileName, String key) {
        // First check if system property override exists (for CI/CD runtime overrides)
        String systemProperty = System.getProperty(key);
        if (systemProperty != null) {
            logger.debug("Using system property for key '{}': {}", key, systemProperty);
            return systemProperty;
        }

        // Load properties file if not already cached
        Properties props = loadPropertiesFile(fileName);

        // Get value from properties file
        String value = props.getProperty(key);

        if (value == null) {
            logger.error("Required property '{}' not found in '{}.properties'", key, fileName);
            throw new RuntimeException(
                    "Required property '" + key + "' is missing in '" + fileName
                            + ".properties'. Please add it to the properties file.");
        }

        logger.debug("Retrieved property '{}' from '{}': {}", key, fileName, value);
        return value;
    }

    // Load properties file from configSettings directory
    private static Properties loadPropertiesFile(String fileName) {
        // Check cache first
        if (propertiesCache.containsKey(fileName)) {
            logger.debug("Using cached properties for: {}", fileName);
            return propertiesCache.get(fileName);
        }

        // Load from file
        Properties props = new Properties();
        Path configPath = Paths.get(System.getProperty("user.dir"), "src", "main", "java", CONFIG_DIR,
                fileName + ".properties");
        File configFile = configPath.toFile();

        if (!configFile.exists()) {
            logger.error("Properties file not found: {}", configFile.getAbsolutePath());
            throw new RuntimeException(
                    "Configuration file not found: " + fileName + ".properties at " + configFile.getAbsolutePath());
        }

        try (FileInputStream fis = new FileInputStream(configFile)) {
            props.load(fis);
            logger.info("Loaded properties file: {}", fileName + ".properties");

            // Cache for future use
            propertiesCache.put(fileName, props);
        } catch (IOException e) {
            logger.error("Error loading properties file: {}", fileName, e);
            throw new RuntimeException("Failed to load configuration file: " + fileName + ".properties", e);
        }

        return props;
    }

} // ConfigurationManager
