package org.jsnells.solver;

import org.jsnells.day.Day;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;

public class Solver {
    private static final String DAY_CLASS_PATTERN = "^Day\\d+\\.class$";
    private static final String DAY_PACKAGE = "org.jsnells.day";
    private static final String DAY_PACKAGE_AS_RESOURCE = DAY_PACKAGE.replace(".", "/");

    public void solveLatest() {
        final var latestDay = getLatestDay();
        assert latestDay != null;

        var start = System.currentTimeMillis();
        var partOne = latestDay.partOne();
        var end = System.currentTimeMillis();

        System.out.println("Part 1: " + partOne.toString());
        System.out.printf("Runtime: %dms\n", end - start);

        start = System.currentTimeMillis();
        var partTwo = latestDay.partTwo();
        end = System.currentTimeMillis();

        System.out.println("Part 2: " + partTwo.toString());
        System.out.printf("Runtime: %dms\n", end - start);
    }

    private Day getLatestDay() {
        final var clazz = getLastDay();
        if (clazz != null && clazz.getSuperclass().equals(Day.class)) {
            try {
                return (Day) clazz.getConstructor().newInstance();
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    private Class<?> getLastDay() {
        final var stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(DAY_PACKAGE_AS_RESOURCE);

        if (stream == null) {
            throw new RuntimeException(String.format("No days found in %s", DAY_PACKAGE));
        }

        final var reader = new BufferedReader(new InputStreamReader(stream));


        final var clazzName = reader.lines()
                .filter(l -> l.matches(DAY_CLASS_PATTERN))
                .max(Comparator.naturalOrder());

        if (clazzName.isEmpty()) {
            return null;
        }

        try {
            return Class.forName(DAY_PACKAGE + "." + clazzName.get().replace(".class", ""));
        } catch (ClassNotFoundException e) {
            return null;
        }

    }

}
