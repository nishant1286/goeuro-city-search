package com.goeuro.search.api.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by nishantgupta on 22/2/16.
 * Singleton class
 */
@Slf4j
public class PropertyUtil {

    @Getter
    private static PropertyUtil instance = new PropertyUtil();


    private static Properties prop;

    private PropertyUtil() {
        init();
    }

    private static void init() {
        Properties properties = new Properties();
        InputStream is = null;
        try {
            is = PropertyUtil.class.getClassLoader().getResourceAsStream("search_service.properties");
            properties.load(is);
            prop = properties;
        } catch (final Exception e) {
            log.error("Exception in processing properties", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("Exception in closing InputStream ", e);
                }
            }
        }
    }

    /********************************************/

    public static String getValue(final String key) {
        return prop != null ? prop.getProperty(key) : null;
    }

}
