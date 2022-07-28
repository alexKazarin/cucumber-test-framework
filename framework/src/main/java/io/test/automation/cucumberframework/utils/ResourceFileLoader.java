package io.test.automation.cucumberframework.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class ResourceFileLoader {

    public static File getResourceAsFile(String folder, String name) {
        try {
            String fullPath = format("%s/%s", folder, name);
            InputStream is = ClassLoader.getSystemResourceAsStream(fullPath);
            final File tmpFile = File.createTempFile("tmp", name, new File(System.getProperty("user.dir")));
            log.info("Input stream : {}", is);
            log.info("Tmp file : {}", tmpFile.getAbsolutePath());
            assertThat(is).as("Input stream for %s should not be null", fullPath).isNotNull();
            FileUtils.copyInputStreamToFile(is, tmpFile);
            tmpFile.deleteOnExit();
            return  tmpFile;
        }
        catch (IOException ex) {
            log.info("Cannot handle file to be uploaded");
        }
        return null;
    }
}
