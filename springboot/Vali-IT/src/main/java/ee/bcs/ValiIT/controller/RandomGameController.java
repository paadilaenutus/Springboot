package ee.bcs.ValiIT.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class RandomGameController {
    Random randomGen = new Random();
    int rndm = randomGen.nextInt(100);
    int cntr = 0;

    @PutMapping("roll")
    public String rolls(@RequestBody int roll) {
        int random = rndm;
        if (roll == random) {
            cntr++;
            String result = "Sul kulus õige arvu ära arvamiseks kordi: " + String.valueOf(cntr);
            rndm = randomGen.nextInt(100);
            cntr = 0;
            return result;
        } else if (roll > random && roll <= 100) {
            cntr++;
            return "Õige arv on väiksem";
        } else if (roll < random && roll >= 0) {
            cntr++;
            return ("Õige arv on suurem");
        } else {
            return ("Viga");
        }
    }
}



