
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muaz Aljarhi
 */
public class streamBenchMark {
    
       public static void main(String[] args) {
    //Calculating square root of even numbers from 1 to N       
    int min = 1;
    int max = 10000000;

    List<Integer> sourceList = new ArrayList<>();
    for (int i = min; i < max; i++) {
        sourceList.add(i);
    }

    List<Double> result = new LinkedList<>();


    //Collections approach
    long t0 = System.nanoTime();
    long elapsed = 0;
    for (Integer i : sourceList) {
        if(i % 2 == 0){
            result.add( doSomeCalculate(i));
        }
    }
    elapsed = System.nanoTime() - t0;       
    System.out.printf("Collections: Elapsed time:\t %d ns \t(%f seconds)%n", elapsed, elapsed / Math.pow(10, 9));


    //Stream approach
    Stream<Integer> stream = sourceList.stream();       
    t0 = System.nanoTime();
    result = stream.filter(i -> i%2 == 0).map(i -> doSomeCalculate(i))
            .collect(Collectors.toList());
    elapsed = System.nanoTime() - t0;       
    System.out.printf("Streams: Elapsed time:\t\t %d ns \t(%f seconds)%n", elapsed, elapsed / Math.pow(10, 9));


    //Parallel stream approach
    stream = sourceList.stream().parallel();        
    t0 = System.nanoTime();
    result = stream.filter(i -> i%2 == 0).map(i ->  doSomeCalculate(i))
            .collect(Collectors.toList());
    elapsed = System.nanoTime() - t0;       
    System.out.printf("Parallel streams: Elapsed time:\t %d ns \t(%f seconds)%n", elapsed, elapsed / Math.pow(10, 9));      
}

static double doSomeCalculate(int input) {
    for(int i=0; i<100000; i++){
        Math.sqrt(i+input);
    }
    return Math.sqrt(input);
}
}
