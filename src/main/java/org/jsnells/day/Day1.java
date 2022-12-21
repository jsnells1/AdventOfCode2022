package org.jsnells.day;

import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;

@NoArgsConstructor
public class Day1 extends Day {
    @Override
    public String partOne() {
        var elves = this.getInputText().split(EMPTY_LINE_SEPARATOR);

        int max = 0;

        for (var elf : elves) {
            max = Integer.max(max, Arrays.stream(elf.split(NEW_LINE_SEPARATOR)).mapToInt(Integer::parseInt).sum());
        }

        return Integer.toString(max);
    }

    @Override
    public String partTwo() {
        var elves = this.getInputText().split(EMPTY_LINE_SEPARATOR);

        return Arrays.stream(elves)
                .mapToInt(value -> Arrays.stream(value.split(NEW_LINE_SEPARATOR)).mapToInt(Integer::parseInt).sum())
                .boxed()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum() + "";
    }
}
