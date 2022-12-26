package org.jsnells.day;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@NoArgsConstructor
public class Day8 extends Day {
    @Override
    public Object partOne() {
        var lines = Arrays.stream(this.getInputText().split(NEW_LINE_SEPARATOR)).map(s ->
        {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                list.add(Integer.parseInt(s.charAt(i) + ""));
            }
            return list;
        }).toList();

        var visibleTrees = new HashSet<Tree>();

        for (int y = 0; y < lines.size(); y++) {
            var tallestTree = -1;
            for (int x = 0; x < lines.size(); x++) {
                var tree = lines.get(y).get(x);
                if (tree > tallestTree) {
                    visibleTrees.add(new Tree(x, y));
                }
                tallestTree = Math.max(tallestTree, tree);
            }
            tallestTree = -1;
            for (int x = lines.size() - 1; x >= 0; x--) {
                var tree = lines.get(y).get(x);
                if (tree > tallestTree) {
                    visibleTrees.add(new Tree(x, y));
                }
                tallestTree = Math.max(tallestTree, tree);
            }
        }

        for (int x = 0; x < lines.size(); x++) {
            var tallestTree = -1;
            for (int y = 0; y < lines.size(); y++) {
                var tree = lines.get(y).get(x);
                if (tree > tallestTree) {
                    visibleTrees.add(new Tree(x, y));
                }
                tallestTree = Math.max(tallestTree, tree);
            }
            tallestTree = -1;
            for (int y = lines.size() - 1; y >= 0; y--) {
                var tree = lines.get(y).get(x);
                if (tree > tallestTree) {
                    visibleTrees.add(new Tree(x, y));
                }
                tallestTree = Math.max(tallestTree, tree);
            }
        }

        return visibleTrees.size();
    }

    @Override
    public Object partTwo() {
        return "";
    }

    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    private static class Tree {
        private final int x;
        private final int y;
    }
}
