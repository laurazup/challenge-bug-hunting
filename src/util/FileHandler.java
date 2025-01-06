package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static List<String> readLines(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static void writeLine(String filePath, String content, boolean append) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, append))) {
            bw.write(content);
            bw.newLine();
        }
    }
}