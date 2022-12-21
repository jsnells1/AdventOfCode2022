package org.jsnells.day;

import lombok.Getter;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public abstract class Day {
    protected static final String NEW_LINE_SEPARATOR = "\r\n";

    protected static final String EMPTY_LINE_SEPARATOR = NEW_LINE_SEPARATOR + NEW_LINE_SEPARATOR;

    @Getter
    private final String inputText = loadInputText();

    public Object partOne() {
        return "";
    }

    public Object partTwo() {
        return "";
    }

    private String loadInputText() {
        String inputFileName = this.getClass().getSimpleName().toLowerCase() + ".txt";

        URL resource = this.getClass().getClassLoader().getResource("input/" + inputFileName);
        if (resource == null) {
            throw new RuntimeException("Cannot find file: " + inputFileName);
        }

        try {
            var file = new File(resource.toURI());
            return Files.readString(file.toPath(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
