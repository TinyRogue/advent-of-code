package utils;

import java.io.IOException;

public interface DataProvider {
    void loadData(DataType type) throws IOException;
}
