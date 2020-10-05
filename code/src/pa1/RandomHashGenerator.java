package pa1;

import java.util.Random;

import static java.lang.Math.round;

public class RandomHashGenerator extends BaseHashGenerator {

    private static long PRIME=((1<<31)-1);   // A prime number greater than 2^32

    private long[] a;
    private long[] b;

    private int k;

    private long x;

    RandomHashGenerator(int numHashes, int filterSize) {

//        PRIME = nextPrime(filterSize);
        this.k=numHashes;
        a = new long[numHashes];
        b = new long[numHashes];

        Random rand = new Random();

        for (int i = 0; i < numHashes; i++) {
            a[i] = getCoefficient(rand);
            b[i] = getCoefficient(rand);
        }
    }

    private long getCoefficient(Random rand) {
        long c = 0;
        do {
            c = round(rand.nextDouble() * PRIME);
        } while (c == 0 || c == PRIME);
        return c;
    }

    @Override
    public void init(String s) {
        x=s.hashCode();
    }

    @Override
    public long getHash(int iteration) {
        long y= (((a[iteration%this.k] * x) % PRIME + b[iteration%this.k]) % PRIME);
        if(y<0) y=-y;
        return y;
    }

    boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;

        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 ||
                    n % (i + 2) == 0)
                return false;

        return true;
    }

    int nextPrime(int N) {
        if (N <= 1)
            return 2;

        int prime = N;
        boolean found = false;

        while (!found) {
            prime++;

            if (isPrime(prime))
                found = true;
        }
        return prime;
    }
}
