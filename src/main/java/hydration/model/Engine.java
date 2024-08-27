package hydration.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Engine {
    private final Integer id;
    private final String name;
    private final Float hp;
}
