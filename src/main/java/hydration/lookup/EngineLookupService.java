package hydration.lookup;

import hydration.model.Engine;
import org.springframework.stereotype.Component;

@Component
public class EngineLookupService {

    public Engine findEngineById(Integer engineId){
        //make a real request to an external service
        String name = "BMW Black Series";
        Float hp = 344.9F;
        return new Engine(engineId, name, hp);
    }

}
