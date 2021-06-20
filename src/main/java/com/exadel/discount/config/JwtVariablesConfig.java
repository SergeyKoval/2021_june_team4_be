package com.exadel.discount.config;

import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.Properties;

@Configuration
@Setter
public class JwtVariablesConfig {
    private static String PATH_TO_PROPERTY_FILE;

    public static final String ACCESS_TOKEN_EXPIRATION_TIME;
    public static final String REFRESH_TOKEN_EXPIRATION_TIME;
    public static final String TOKEN_ENCRYPTION_KEY;
    public static final String REFRESH_ROLE;

    static {
        PATH_TO_PROPERTY_FILE = "jwt.properties";
        ACCESS_TOKEN_EXPIRATION_TIME = "jwt.access.expiration";
        REFRESH_TOKEN_EXPIRATION_TIME = "jwt.refresh.expiration";
        TOKEN_ENCRYPTION_KEY = "jwt.encryption.key";
        REFRESH_ROLE = "jwt.refresh.role";
    }

    private static final Properties properties = new Properties();

    public synchronized static String getProperty(String name) {
        if (properties.isEmpty()) {
            try (InputStream inputStream = JwtVariablesConfig.class.getClassLoader()
                    .getResourceAsStream(PATH_TO_PROPERTY_FILE)) {
                properties.load(inputStream);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
        return properties.getProperty(name);
    }
}
