package org.jsnells.day;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Day4 extends Day {
    @Override
    public Object partOne() {
        var sections = this.getInputText().split(NEW_LINE_SEPARATOR);

        int count = 0;
        for (var section : sections) {
            var ranges = section.split(",");
            var range1 = new Range(ranges[0]);
            var range2 = new Range(ranges[1]);

            if (range1.contains(range2) || range2.contains(range1)) {
                count++;
            }
        }

        return count;
    }

    @Override
    public Object partTwo() {
        var sections = this.getInputText().split(NEW_LINE_SEPARATOR);

        int count = 0;
        for (var section : sections) {
            var ranges = section.split(",");
            var range1 = new Range(ranges[0]);
            var range2 = new Range(ranges[1]);

            if (range1.overlaps(range2) || range2.overlaps(range1)) {
                count++;
            }
        }

        return count;
    }

    private static class Range {
        private final int min;
        private final int max;

        public Range(String range) {
            var split = range.split("-");

            min = Integer.parseInt(split[0]);
            max = Integer.parseInt(split[1]);
        }

        public boolean contains(Range range) {
            return this.max >= range.max && this.min <= range.min;
        }

        public boolean overlaps(Range range) {
            return (this.min <= range.min && this.max >= range.min) || (this.min <= range.max && this.max >= range.max);
        }
    }
}
