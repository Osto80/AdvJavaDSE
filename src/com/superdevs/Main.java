package com.superdevs;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n**** --FIRST SECTION-- ****\n");
        // Create a class called Person and populate a list with 10 such persons.
        System.out.println("Person class and using streams to find different values:\n");

        List<Person> users = new ArrayList<>(
                List.of(new Person("Daniel", Gender.MALE, 23_400),
                        new Person("Borat", Gender.MALE, 21_600),
                        new Person("Emelie", Gender.FEMALE, 27_400),
                        new Person("Louise", Gender.FEMALE, 22_700),
                        new Person("Lars", Gender.MALE, 27_200),
                        new Person("Goran", Gender.MALE, 19_800),
                        new Person("Leia", Gender.FEMALE, 15_600),
                        new Person("Dina", Gender.FEMALE, 25_500),
                        new Person("Per", Gender.MALE, 22_900),
                        new Person("Kristin", Gender.FEMALE, 26_200))
                );

        /*
        Double avgSalaryMen = users
                .stream()
                .filter(men -> men.getGender().equals(Gender.MALE))
                .map(Person::getSalary).collect(Collectors.averagingDouble(Double::doubleValue));
        */

        // 1.1 - Average Salary related to gender
        Double avgSalaryMen = users
                .stream()
                .filter(men -> men.getGender().equals(Gender.MALE))
                .mapToDouble(Person::getSalary)
                .summaryStatistics()
                .getAverage();

        Double avgSalaryWomen = users
                .stream()
                .filter(women -> women.getGender().equals(Gender.FEMALE))
                .mapToDouble(Person::getSalary)
                .summaryStatistics()
                .getAverage();

        System.out.println("Average salary men: " +avgSalaryMen);
        System.out.println("Average salary women: " +avgSalaryWomen);

        // 1.2 - Highest Salary
        List<Person> highestSalary = users
                .stream()
                .sorted(Comparator.comparing(Person::getSalary).reversed())
                .limit(1)
                .toList();

        // 1.3 - lowest Salary
        List<Person> lowestSalary = users
                .stream()
                .sorted(Comparator.comparing(Person::getSalary))
                .limit(1)
                .toList();

        // 1.2 and 1.3 printout
        System.out.println("Highest Salary: " + highestSalary);
        System.out.println("Lowest Salary: " +lowestSalary);

        System.out.println("\n**** --SECOND SECTION-- ****\n");
        // 2 - Create a car factory using the factory pattern
        System.out.println("Factory Pattern:\n");

        //CarFactory

        System.out.println("\n**** --THIRD SECTION-- ****\n");
        // 3 - Create a list of words. Using regex, pick out the words that contains
        // 2 or more vowels (english ie: a, e, i, o, u, y)
        System.out.println("Vowels using RegEx:\n");

        List<String> words = List.of(
                "Table", "Shoe",
                "Dog", "Bathroom",
                "Ink", "Towel",
                "Cat", "Art"

        );

        //Pattern pattern = Pattern.compile("[aeiouy] n++", Pattern.CASE_INSENSITIVE);

        Predicate<String> vowelFilter = Pattern
                .compile("([aeiouy][aeiouy])", Pattern.CASE_INSENSITIVE)
                        .asPredicate();

        List<String> filteredList = words.stream()
                .filter(vowelFilter)
                .collect(Collectors.toList());

        System.out.println(filteredList);



        System.out.println("\n**** --FOURTH SECTION-- ****\n");
        // 4 - Calculate the number of prime numbers between 0 and 500'000.
        // Divide this into 2 or more threads. Ex: Use one thread for (0 to 350'000)
        // and another for (351'000 to 500'000). More advanced and/or efficient solutions are welcome.
        System.out.println("Prime numbers between 0 and 500'000:\n");

        //System.out.println(primeNumbers(500_000).count());
        AtomicLong sum1 = new AtomicLong();
        AtomicLong sum2 = new AtomicLong();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sum1.set(primeNumbers(250000).count());
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                sum2.set(primeNumbers2(250001, 500000).count());

            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sum1.get() + sum2.get());
    }

    public static IntStream primeNumbers(int targetNum) {
        IntStream primeNum = IntStream.range(2, targetNum);
        IntFunction<IntPredicate> sieve = n -> i -> i == n || i % n != 0;
        primeNum = primeNum
                .filter(sieve.apply(2));
        for (int i = 3; i * i <= targetNum; i += 2)
            primeNum = primeNum
                    .filter(sieve.apply(i));
        return primeNum;
    }
    /// När man vill börja från annat än noll.
    public static IntStream primeNumbers2(int start, int targetNum) {
        if (start < 1) {
            start++;
        }
        IntStream primeNum = IntStream.range(start, targetNum);
        IntFunction<IntPredicate> sieve = n -> i -> i == n || i % n != 0;
        primeNum = primeNum
                .filter(sieve.apply(2));
        for (int i = 3; i * i <= targetNum; i += 2)
            if (i < start)
            primeNum = primeNum
                    .filter(sieve.apply(i));
        return primeNum;
    }




}
