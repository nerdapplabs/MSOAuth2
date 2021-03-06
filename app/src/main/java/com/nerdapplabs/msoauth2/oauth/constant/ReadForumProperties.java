package com.nerdapplabs.msoauth2.oauth.constant;

import com.nerdapplabs.msoauth2.MSOAuth2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Mohd. Shariq on 23/01/17.
 */

public class ReadForumProperties {
    public static Properties getPropertiesValues() throws IOException {
        Properties properties = new Properties();
        String propertiesFileName = "app.properties";
        InputStream inputStream = MSOAuth2.getContext().getAssets().open(propertiesFileName);
        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propertiesFileName + "' not found in the classpath");
        }
        return properties;
    }
}
