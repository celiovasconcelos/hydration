package hydration.mapper;

import hydration.lookup.EngineLookupService;
import hydration.model.Engine;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@RequiredArgsConstructor
public class EngineHydration extends HydrationDeserializer<Integer, Engine> {

    private final EngineLookupService engineLookupService;

    @Override
    public Engine hydrate(Integer engineId) {
        return engineLookupService.findEngineById(engineId);
    }
}
