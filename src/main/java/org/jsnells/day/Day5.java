package org.jsnells.day;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@NoArgsConstructor
public class Day5 extends Day {
    @Override
    public Object partOne() {
        var inputs = this.getInputText().split(EMPTY_LINE_SEPARATOR);
        var startingStack = inputs[0].split(NEW_LINE_SEPARATOR);

        List<Stack<String>> stacks = new ArrayList<>();
        for (int i = 0; i < (startingStack[0].length() + 1) / 4; i++) {
            stacks.add(new Stack<>());
        }

        for (int i = startingStack.length - 2; i >= 0; i--) {
            for (int j = 1, k = 0; j <= startingStack[i].length(); j += 4, k++) {
                var c = startingStack[i].charAt(j);

                if (c != ' ') {
                    stacks.get(k).push(c + "");
                }
            }
        }

        for (var instruction : inputs[1].split(NEW_LINE_SEPARATOR)) {
            var split = instruction.split(" ");

            var amount = Integer.parseInt(split[1]);
            var from = stacks.get(Integer.parseInt(split[3]) - 1);
            var to = stacks.get(Integer.parseInt(split[5]) - 1);

            for (int i = 0; i < amount; i++) {
                to.push(from.pop());
            }
        }

        return stacks.stream().map(Stack::pop).collect(Collectors.joining());
    }

    @Override
    public Object partTwo() {
        var inputs = this.getInputText().split(EMPTY_LINE_SEPARATOR);
        var startingStack = inputs[0].split(NEW_LINE_SEPARATOR);

        List<Stack<String>> stacks = new ArrayList<>();
        for (int i = 0; i < (startingStack[0].length() + 1) / 4; i++) {
            stacks.add(new Stack<>());
        }

        for (int i = startingStack.length - 2; i >= 0; i--) {
            for (int j = 1, k = 0; j <= startingStack[i].length(); j += 4, k++) {
                var c = startingStack[i].charAt(j);

                if (c != ' ') {
                    stacks.get(k).push(c + "");
                }
            }
        }

        for (var instruction : inputs[1].split(NEW_LINE_SEPARATOR)) {
            var split = instruction.split(" ");

            var amount = Integer.parseInt(split[1]);
            var from = stacks.get(Integer.parseInt(split[3]) - 1);
            var to = stacks.get(Integer.parseInt(split[5]) - 1);

            var temp = new Stack<String>();
            for (int i = 0; i < amount; i++) {
                temp.push(from.pop());
            }

            while (!temp.empty()) {
                to.push(temp.pop());
            }
        }

        return stacks.stream().map(Stack::pop).collect(Collectors.joining());
    }
}
