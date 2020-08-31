package ee.bcs.ValiIT.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    //DAY 6 GETMAPPING, PUTMAPPING, POSTMAPPING, DELETEMAPPING
    // new list of students
    private static final List<Student> students = new ArrayList<>();

    //get all students
    @GetMapping("student")
    public static List<Student> getAllStudent() {
        return students;
    }

    //get specific student by list index
    @GetMapping("student/{id}")
    public static Student getStudent(@PathVariable("id") int a) {
        return students.get(a);
    }

    //add student
    @PostMapping("student")
    public void postdto(@RequestBody Student addStudent) {

        students.add(addStudent);
    }

    //change student data by list index
    @PutMapping("student/{id}")
    public void chng(@RequestBody Student changeStudent, @PathVariable("id") int a) {

        students.set(a, changeStudent);
    }

    //delete student data by list index
    @DeleteMapping("student/{id}")
    public void remove(@PathVariable("id") int a) {
        students.remove(a);
    }
}
