import java.math.BigInteger;

public class Rational extends Number implements Comparable<Rational> {

    public static final Rational NaN = new Rational(0, 0);
    public static final Rational POSITIVE_INFINITY = new Rational(1, 0);
    public static final Rational NEGATIVE_INFINITY = new Rational(-1, 0);
    public static final Rational ZERO = new Rational(0, 1);
    private static final long serialVersionUID = 51354135351319L;
    private final long mNumerator;
    private final long mDenominator;

    public Rational(long numerator, long denominator) {
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        if (denominator == 0 && numerator > 0) {
            mNumerator = 1;// +Inf
            mDenominator = 0;
        } else if (denominator == 0 && numerator < 0) {
            mNumerator = -1;// -Inf
            mDenominator = 0;
        } else if (denominator == 0 && numerator == 0) {
            mDenominator = 0;// NaN
            mNumerator = 0;
        } else if (numerator == 0) {
            mNumerator = 0;
            mDenominator = 1;
        } else {
            long gcd = gcd(numerator, denominator);
            mNumerator = numerator / gcd;
            mDenominator = denominator / gcd;
        }
    }

    public int getmDenominator() {
        return mDenominator;
    }

    public int getmNumerator() {
        return mNumerator;
    }

    public boolean isNaN() {
        return mDenominator == 0 && mNumerator == 0;
    }

    public boolean isInfinite() {
        return mNumerator != 0 && mDenominator == 0;
    }

    public boolean isFinite() {
        return mDenominator != 0;
    }

    public boolean isZero() {
        return isFinite() && mNumerator == 0;
    }

    private boolean isPosInf() {
        return mDenominator == 0 && mNumerator > 0;
    }

    private boolean isNegInf() {
        return mDenominator == 0 && mNumerator < 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Rational && equals((Rational) obj);
    }

    private boolean equals(Rational other) {
        return (mNumerator == other.mNumerator && mDenominator == other.mDenominator);
    }

    @Override
    public String toString() {
        if (isNaN()) {
            return "NaN";
        } else if (isPosInf()) {
            return "∞";
        } else if (isNegInf()) {
            return "-∞";
        } else {
            return mNumerator + "/" + mDenominator;
        }
    }

    @Override
    public int hashCode() {
        int numeratorFlipped = mNumerator << 16 | mNumerator >>> 16;
        return mDenominator ^ numeratorFlipped;
    }

    public static long gcd(long numerator, long denominator) {
        long a = numerator;
        long b = denominator;
        while (b != 0) {
            long oldB = b;
            b = a % b;
            a = oldB;
        }
        return Math.abs(a);
    }

    @Override
    public float floatValue() {
        float num = mNumerator;
        float den = mDenominator;
        return num / den;
    }

    @Override
    public double doubleValue() {
        double num = mNumerator;
        double den = mDenominator;
        return num / den;
    }

    @Override
    public int intValue() {
        if (isPosInf()) {
            return Integer.MAX_VALUE;
        } else if (isNegInf()) {
            return Integer.MIN_VALUE;
        } else if (isNaN()) {
            return 0;
        } else {
            return mNumerator / mDenominator;
        }
    }

    @Override
    public long longValue() {
        if (isPosInf()) {
            return Long.MAX_VALUE;
        } else if (isNegInf()) {
            return Long.MIN_VALUE;
        } else if (isNaN()) {
            return 0;
        } else {
            return mNumerator / mDenominator;
        }
    }

    @Override
    public short shortValue() {
        return (short) intValue();
    }

    @Override
    public int compareTo(Rational o) {
        if (o == null) {
            throw new NullPointerException("another Rational is null");
        }
        if (equals(o)) {
            return 0;
        } else if (isNaN()) {
            return 1;
        } else if (o.isNaN()) {
            return -1;
        } else if (isPosInf() || o.isNegInf()) {
            return 1;
        } else if (isNegInf() || o.isPosInf()) {
            return -1;
        }

        long thisNumerator = this.mNumerator * o.mDenominator;
        long otherNumerator = o.mNumerator * this.mDenominator;

        return thisNumerator - otherNumerator;
    }

    private static NumberFormatException invalidRational(String s) {
        throw new NumberFormatException("Invalid Rational: \"" + s + "\"");
    }

    public static Rational parseRational(String string) throws NumberFormatException {
        if (string == null) {
            throw new NullPointerException("str is null");
        }
        if (string.equals("NaN")) {
            return NaN;
        } else if (string.equals("Infinity") || string.equals("∞")) {
            return POSITIVE_INFINITY;
        } else if (string.equals("-Infinity") || string.equals("-∞")) {
            return NEGATIVE_INFINITY;
        }
        int sep_ix = string.indexOf(":");
        if (sep_ix < 0) {
            sep_ix = string.indexOf("/");
        }
        if (sep_ix < 0) {
            invalidRational(string);
        }
        try {
            return new Rational(Integer.parseInt(string.substring(0, sep_ix)),
                    Integer.parseInt(string.substring(sep_ix + 1)));
        } catch (NumberFormatException ignore) {
            throw invalidRational(string);
        }
    }

    public Rational add(Rational o) {
        long thisNumerator = this.mNumerator * o.mDenominator;
        long otherNumerator = o.mNumerator * this.mDenominator;
        long numerator = thisNumerator + otherNumerator;
        long denominator = this.mDenominator * o.mDenominator;
        return new Rational(numerator, denominator);

    }

    public Rational subtract(Rational o) {
        return this.add(o.opposite());
    }

    public Rational multiply(Rational o) {
        long numerator = this.mNumerator * o.mNumerator;
        long denominator = this.mDenominator * o.mDenominator;
        return new Rational(numerator, denominator);

    }

    public Rational divide(Rational o) {
        return this.multiply(o.overturned());
    }

    public Rational opposite() {
        return new Rational(-this.mNumerator, this.mDenominator);
    }

    public Rational overturned() {
        return new Rational(this.mDenominator, this.mNumerator);
    }

    public Rational abs() {
        if (this.mNumerator < 0) {
            return this.opposite();
        } else {
            return this;
        }
    }

    public static void main(String[] args) {
        Rational a = new Rational(-2, 6);
        Rational b = new Rational(7, -2);

        System.out.println(a);
        System.out.println(b);
        System.out.println(a.add(b));
        System.out.println(a.subtract(b));
        System.out.println(a.multiply(b));
        System.out.println(a.divide(b));
        System.out.println(a.abs());
    }

}
