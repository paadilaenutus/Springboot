package ee.bcs.ValiIT.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController2 {
    @GetMapping("/min/{a}/{b}")
    public static int min(@PathVariable("a") int a, @PathVariable("b") int b) {
        return a < b ? a : b;
    }

    @GetMapping("/max/{a}/{b}")
    public static int max(@PathVariable("a") int a, @PathVariable int b) {
        return a > b ? a : b;
    }

    @GetMapping("abs")
    public static int abs(@RequestParam("abs") int a) {
        if (a < 0) {
            a *= -1;
        }
        return a;
    }


    //LESSON 3
    @GetMapping("even")
    public static boolean isEven(@RequestParam("even") int b) {
        return b % 2 == 0;
    }

    @GetMapping("fact")
    public int factorial(@RequestParam("fact") int fact) {
        return Lesson3.factorial(fact);
    }

    @GetMapping("sort")
    public static int[] sort(@RequestParam("sort") int[] a) {
        return Lesson3.sort(a);
    }

    @GetMapping("/reverse/{String}")
    public static String reverseString(@PathVariable("String") String a) {
        return Lesson3.reverseString(a);
    }

    @GetMapping("prime/{prim}")
    public static boolean isPrime(@PathVariable("prim") int x) {
        return Lesson3.isPrime(x);
    }

    @GetMapping("arr/{sum}")
    public static int sum(@PathVariable("sum") int[] x) {
        return Lesson3.sum(x);
    }

    @GetMapping("sum/{a}/{b}")
    public static int sum(@PathVariable("a") int x, @PathVariable("b") int y) {
        return Lesson3.sum(x, y);
    }

    //LESSON 3 DONE

    @GetMapping("efib")
    public static int evenFibonacci(@RequestParam("evenFib") int x) {
        return Lesson3Hard.evenFibonacci(x);
    }

    @GetMapping("morse")
    public static String morseCode(@RequestParam("tenno") String text) {
        return Lesson3Hard.morseCode(text);
    }

    //LESSON3HARD DONE


}
