package org.jsnells.day;

import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor
public class Day2 extends Day {
    @Override
    public Object partOne() {
        return Arrays.stream(this.getInputText().split(NEW_LINE_SEPARATOR))
                .mapToInt(this::sumMatch)
                .sum();
    }

    @Override
    public Object partTwo() {
        return "";
    }

    private int sumMatch(String match) {
        String[] choices = match.split(" ");
        int oppChoice = scoreForChoice(choices[0]);
        int myChoice = scoreForChoice(choices[1]);

        if (oppChoice == myChoice) {
            return 3 + myChoice;
        }

        if (getTargetNum(myChoice) == oppChoice) {
            return 6 + myChoice;
        }

        return myChoice;
    }
    
    int getTargetNum(int v) {
        v -= 2;
        v += (1 - v / 3) * 3;
        return v % 3 + 1;
    }

    private int scoreForChoice(String choice) {
        return switch (choice) {
            case "A", "X" -> 1;
            case "B", "Y" -> 2;
            case "C", "Z" -> 3;
            default -> throw new RuntimeException();
        };
    }
}
