package org.jsnells.day;

import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;

@NoArgsConstructor
public class Day6 extends Day {
    @Override
    public Object partOne() {
        var in = this.getInputText();

        for (int i = 3; i <= in.length(); i++) {
            var set = new HashSet<Character>();
            var list = List.of(in.charAt(i), in.charAt(i - 1), in.charAt(i - 2), in.charAt(i - 3));

            if (list.stream().allMatch(set::add)) {
                return i + 1;
            }
        }

        throw new RuntimeException();
    }

    @Override
    public Object partTwo() {
        var in = this.getInputText();

        for (int i = 13; i <= in.length(); i++) {
            var set = new HashSet<Character>();

            boolean duplicate = false;

            for (int j = 0; j < 14; j++) {
                if (!set.add(in.charAt(i - j))) {
                    duplicate = true;
                    break;
                }
            }

            if (!duplicate) {
                return i + 1;
            }

        }
        
        throw new RuntimeException();
    }
}
