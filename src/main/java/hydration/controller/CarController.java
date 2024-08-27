package hydration.controller;

import hydration.mapper.Hydration;
import hydration.model.Engine;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CarController {

    public record CarRequest(Hydration<Integer, Engine> engine) {}

    @PostMapping("/addCar")
    public void addCar(@RequestBody CarRequest carRequest){
        //it has Optional API and some extras (thanks lombok @Delegate)
        Engine engine = carRequest.engine().orElse(null);
        //you can eventually access the left
        Optional<Integer> source = carRequest.engine().getLeft();
    }

}
