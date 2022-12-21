package org.jsnells.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtil {
    public int wrapAround(int start, int delta, int min, int max) {
        final int mod = max + 1 - min;
        start += delta - min;
        start += (1 - start / mod) * mod;
        return start % mod + min;
    }
}
