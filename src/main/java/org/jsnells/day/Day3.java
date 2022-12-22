package org.jsnells.day;

import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
public class Day3 extends Day {
    @Override
    public Object partOne() {
        return Arrays.stream(this.getInputText().split(NEW_LINE_SEPARATOR)).mapToInt(line -> {
            var compartmentOne = line.substring(0, line.length() / 2);
            var compartmentTwo = line.substring(line.length() / 2);

            Set<Character> chars = new HashSet<>();
            for (char c : compartmentOne.toCharArray()) {
                chars.add(c);
            }

            for (char c : compartmentTwo.toCharArray()) {
                if (chars.contains(c)) {
                    if (c >= 97) {
                        return c - 96;
                    }

                    return c - 38;
                }
            }

            return 0;
        }).sum();
    }

    @Override
    public Object partTwo() {
        return "";
    }

}
