package ee.bcs.ValiIT.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.Scanner;
@RestController
public class RandomGameController {
    Random random = new Random();
    int rndm = random.nextInt(100);
    int cntr = 0;

    @PutMapping ("roll")
    public String rolls(@RequestBody int roll) {
        int random = rndm;
        do {
           // roll = 0;
            if (roll == random) {
                cntr++;
                return "Sul kulus õige arvu ära arvamiseks kordi: " + String.valueOf(cntr);
            } else if (roll > random && roll <= 100){
                cntr++;
                return "Õige arv on väiksem";
            } else if (roll < random && roll >= 0) {
                cntr++;
                return("Õige arv on suurem");
            } else {
                return("Viga");
            }
        }while (roll < random && roll >= 0 || roll > random && roll <= 100);
    }
}
