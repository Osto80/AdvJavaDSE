package com.superdevs;

import java.util.ArrayList;

public class PrimeCounter implements Runnable {

    int start;
    int end;
    ArrayList<Integer> primes;

    @Override
    public void run() {
        for (int n = start; n <= end; n++) {
            boolean prime = true;
            int i = 2;
            while(i <= n / 2 ) {
                if (n % i == 0) {
                    prime = false;
                    break;
                }
                i++;
            }
            if (prime && n > 1) {
                primes.add(n);
            }
        }
    }

    public PrimeCounter(int start, int end, ArrayList<Integer> primes) {
        this.start = start;
        this.end = end;
        this.primes = primes;
    }
}
