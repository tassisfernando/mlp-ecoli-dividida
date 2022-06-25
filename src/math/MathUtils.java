package math;

import static java.lang.Math.exp;

public class MathUtils {

    public static Double sig(Double u) {
        return 1 / (1 + exp(-u));
    }

    public static void main(String[] args) {
        System.out.println(sig(0D));
    }
}
