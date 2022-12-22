package org.jsnells.day;

import lombok.NoArgsConstructor;

import java.util.*;

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
                    return charToInt(c);
                }
            }

            return 0;
        }).sum();
    }

    @Override
    public Object partTwo() {
        var lines = Arrays.stream(this.getInputText().split(NEW_LINE_SEPARATOR)).toList();

        List<List<String>> groups = new ArrayList<>();

        for (int i = 0; i < lines.size(); i += 3) {
            int end = Math.min(lines.size(), i + 3);
            groups.add(lines.subList(i, end));
        }

        return groups.stream().mapToInt(group ->
        {
            var elf2 = group.get(1).chars().mapToObj(c -> (char) c).toList();
            var elf3 = group.get(2).chars().mapToObj(c -> (char) c).toList();
            for (var c : group.get(0).toCharArray()) {
                if (elf2.contains(c) && elf3.contains(c)) {
                    return charToInt(c);
                }
            }

            return 0;
        }).sum();
    }

    private int charToInt(Character c) {
        if (c >= 97) {
            return c - 96;
        }

        return c - 38;
    }
}
