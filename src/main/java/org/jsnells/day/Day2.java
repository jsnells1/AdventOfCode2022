package org.jsnells.day;

import lombok.NoArgsConstructor;
import org.jsnells.util.MathUtil;

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
        return Arrays.stream(this.getInputText().split(NEW_LINE_SEPARATOR))
                .mapToInt(l -> {
                    String[] choices = l.split(" ");
                    int oppChoice = scoreForChoice(choices[0]);

                    return switch (choices[1]) {
                        case "X" -> MathUtil.wrapAround(oppChoice, -1, 1, 3);
                        case "Y" -> 3 + oppChoice;
                        case "Z" -> 6 + MathUtil.wrapAround(oppChoice, 1, 1, 3);
                        default -> throw new RuntimeException();
                    };
                }).sum();
    }

    private int sumMatch(String match) {
        String[] choices = match.split(" ");
        int oppChoice = scoreForChoice(choices[0]);
        int myChoice = scoreForChoice(choices[1]);

        if (oppChoice == myChoice) {
            return 3 + myChoice;
        }

        if (MathUtil.wrapAround(myChoice, -1, 1, 3) == oppChoice) {
            return 6 + myChoice;
        }

        return myChoice;
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
