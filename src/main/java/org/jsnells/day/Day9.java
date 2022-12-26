package org.jsnells.day;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;

@NoArgsConstructor
public class Day9 extends Day {
    @Override
    public Object partOne() {
        var head = new Location(0, 0, null);
        var tail = new Location(0, 0, null);

        var seen = new HashSet<Location>();
        seen.add(tail);

        for (var line : this.getInputText().split(NEW_LINE_SEPARATOR)) {
            var moves = Integer.parseInt(line.substring(2));
            for (int i = 0; i < moves; i++) {
                switch (line.charAt(0)) {
                    case 'U' -> head = head.up();
                    case 'D' -> head = head.down();
                    case 'L' -> head = head.left();
                    case 'R' -> head = head.right();
                }

                if (tail.distanceFrom(head) > 1) {
                    tail = head.previousLocation;
                    seen.add(tail);
                }
            }
        }


        return seen.size();
    }

    @Override
    public Object partTwo() {
        return "";
    }

    @EqualsAndHashCode
    @RequiredArgsConstructor
    private static class Location {
        private final int x;
        private final int y;

        @EqualsAndHashCode.Exclude
        private final Location previousLocation;

        public Location up() {
            return new Location(x, y + 1, this);
        }

        public Location down() {
            return new Location(x, y - 1, this);
        }

        public Location left() {
            return new Location(x - 1, y, this);
        }

        public Location right() {
            return new Location(x + 1, y, this);
        }

        public int distanceFrom(Location other) {
            var xDiff = Math.abs(other.x - this.x);
            var yDiff = Math.abs(other.y - this.y);
            return Math.max(xDiff, yDiff);
        }
    }
}
