package day7;

import utils.DataLoader;
import utils.DataProvider;
import utils.DataType;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class OperationsDataProvider implements DataProvider {
    private static final Path PUZZLE_INPUT_PATH = Path.of("day7", "puzzle_input.txt");
    private static final Path SAMPLE_DATA_INPUT_PATH = Path.of("day7", "test_data.txt");
    private static final short SAMPLE_RESULT = 3749;
    private List<Map.Entry<Long, List<Long>>> operations = new ArrayList<>();

    public List<Map.Entry<Long, List<Long>>> getOperations() {
        return operations;
    }

    public short getSampleResult() {
        return SAMPLE_RESULT;
    }

    @Override
    public void loadData(DataType type) throws IOException {
        var resourcePath = type == DataType.PUZZLE_INPUT ? PUZZLE_INPUT_PATH : SAMPLE_DATA_INPUT_PATH;
        var rawData = DataLoader.loadData(resourcePath.toString());

        operations = Arrays.stream(rawData.split("\n"))
                .map(operation -> {
                    String[] parts = operation.split(":");
                    Long opResult = Long.parseLong(parts[0].trim());
                    List<Long> opElements = Arrays.stream(parts[1].trim().split(" "))
                            .map(Long::parseLong)
                            .toList();
                    return Map.entry(opResult, opElements);
                })
                .collect(Collectors.toList());
    }
}
