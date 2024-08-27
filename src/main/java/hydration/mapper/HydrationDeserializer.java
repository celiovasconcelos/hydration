package hydration.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

public abstract class HydrationDeserializer<L, R> extends StdDeserializer<Hydration<L, R>> {

    public HydrationDeserializer() {
        super((Class<?>) null);
    }

    @Override
    public Hydration<L, R> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        L left = deserializationContext.readValue(jsonParser, getClassOfL());
        if (left == null) return Hydration.empty();
        R right = hydrate(left);
        return right == null ?  Hydration.left(left) : Hydration.both(left, right);
    }

    @SuppressWarnings("unchecked")
    private Class<L> getClassOfL() {
        final ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<L>) type.getActualTypeArguments()[0];
    }

    public abstract R hydrate(L left);
}