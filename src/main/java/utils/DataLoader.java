package utils;

import day1.DataProvider;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DataLoader {
    public static String loadData(String resourcePath) throws IOException {
        var classLoader = DataProvider.class.getClassLoader();
        var is = classLoader.getResourceAsStream(resourcePath);
        if (is == null) {
            throw new FileNotFoundException("No test data found!");
        }
        String data = new String(is.readAllBytes());
        is.close();
        return data;
    }
}
