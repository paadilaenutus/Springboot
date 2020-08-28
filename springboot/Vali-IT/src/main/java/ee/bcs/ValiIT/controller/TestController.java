package ee.bcs.ValiIT.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/fibonacci/{fib}")
    public static int fibonacci(@PathVariable("fib") int i) {
        // 0, 1, 1, 2, 3, 5, 8, 13, 21
        // Tagasta fibonacci jada n element
        if (i == 0) {
            return 0;
        } else if (i == 1) {
            return 1;
        } else {
            return (fibonacci(i - 1)) + (fibonacci(i - 2));
        }
    }
}