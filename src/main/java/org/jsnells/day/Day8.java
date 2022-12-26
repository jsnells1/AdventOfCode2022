package org.jsnells.day;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@NoArgsConstructor
public class Day8 extends Day {
    @Override
    public Object partOne() {
        var lines = this.getParsedInput();

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
        var lines = this.getParsedInput();

        int maxScenicScore = 0;
        for (int x = 0; x < lines.size(); x++) {
            for (int y = 0; y < lines.size(); y++) {
                var tree = lines.get(y).get(x);

                // North scenic score
                int north = y;
                int northScore = 0;
                while (--north >= 0) {
                    northScore++;

                    if (this.getTree(lines, x, north) >= tree)
                        break;
                }

                // South scenic score
                int south = y;
                int southScore = 0;
                while (++south < lines.size()) {
                    southScore++;

                    if (this.getTree(lines, x, south) >= tree)
                        break;
                }

                // East scenic score
                int east = x;
                int eastScore = 0;
                while (++east < lines.size()) {
                    eastScore++;

                    if (this.getTree(lines, east, y) >= tree)
                        break;
                }

                // West scenic score
                int west = x;
                int westScore = 0;
                while (--west >= 0) {
                    westScore++;

                    if (this.getTree(lines, west, y) >= tree)
                        break;
                }

                maxScenicScore = Math.max(maxScenicScore, northScore * southScore * eastScore * westScore);
            }
        }

        return maxScenicScore;
    }


    private int getTree(List<List<Integer>> trees, int x, int y) {
        return trees.get(y).get(x);
    }

    private List<List<Integer>> getParsedInput() {
        return Arrays.stream(this.getInputText().split(NEW_LINE_SEPARATOR)).map(s ->
        {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                list.add(Integer.parseInt(s.charAt(i) + ""));
            }
            return list;
        }).toList();
    }

    @EqualsAndHashCode
    @RequiredArgsConstructor
    private static class Tree {
        private final int x;
        private final int y;
    }
}
