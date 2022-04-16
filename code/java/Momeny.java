import java.security.InvalidParameterException;

public class Momeny implements Comparable<Momeny> {
    private long integer;
    private int decimal;

    public Momeny() {
        this.integer = 0;
        this.decimal = 0;
    }

    public Momeny(long integer, int decimal) {
        if (decimal < 0) {
            throw new InvalidParameterException("decimal must >= zero");
        }
        while (decimal > 999) {
            decimal /= 10;
        }
        this.integer = integer;
        this.decimal = decimal;
    }

    public Momeny(String momeny) {
        int index = momeny.indexOf(".");
        if (index > 0) {
            this.integer = Long.parseLong(momeny, 0, index, 10);
            this.decimal = Integer.parseInt(momeny, index + 1, Math.min(momeny.length(), index + 4), 10);
        } else {
            this.integer = Long.parseLong(momeny);
            this.decimal = 0;
        }
    }

    public Momeny add(Momeny other) {
        long i = this.integer + other.integer;
        int d = this.decimal + other.decimal;
        if (d > 999) {
            long a = d / 1000;
            d = d % 1000;
            i = i + a;
        }
        return new Momeny(i, d);
    }

    public Momeny max(Momeny m1, Momeny m2) {
        return m1.compareTo(m2) >= 0 ? m1 : m2;
    }

    public Momeny min(Momeny m1, Momeny m2) {
        return m1.compareTo(m2) >= 0 ? m2 : m1;
    }

    @Override
    public int compareTo(Momeny o) {
        long i = this.integer - o.integer;
        if (i > 0) {
            return 1;
        } else if (i < 0) {
            return -1;
        } else {
            return this.decimal - o.decimal;
        }
    }

    @Override
    public int hashCode() {
        return Long.hashCode(this.integer) << 10 & Integer.hashCode(this.decimal);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Momeny) {
            Momeny other = (Momeny) obj;
            return this.integer == other.integer && this.decimal == other.decimal;
        }
        return false;
    }

    public Momeny sub(Momeny other) {
        Momeny big = max(this, other);
        Momeny small = min(this, other);
        long i = big.integer - small.integer;
        int d = big.decimal - small.decimal;
        if (d < 0) {
            i = i - 1;
            d = 1000 + d;
        }
        if (big.equals(other)) {
            i = -i;
        }
        return new Momeny(i, d);
    }

    @Override
    public String toString() {
        String str = String.valueOf(this.decimal);
        if (this.decimal < 10) {
            str = "00" + str;
        } else if (this.decimal < 100) {
            str = "0" + str;
        }
        return this.integer + "." + str;
    }

    public static void main(String[] args) {
        Momeny m1 = new Momeny();
        Momeny m2 = new Momeny(16, 100000);
        Momeny m3 = new Momeny("12.9899999");

        System.out.println(m1);
        System.out.println(m2);
        System.out.println(m3);
        System.out.println(m3.add(m2));
        System.out.println(m3.sub(m2));
        System.out.println(m2.sub(m3));
    }
}
