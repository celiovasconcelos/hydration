package hydration.mapper;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Delegate;
import org.apache.commons.lang3.reflect.ConstructorUtils;

import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Hydration<L, R> {

    @Getter
    private final Optional<L> left;

    @Delegate
    private final Optional<R> right;

    public static <L, R> Hydration<L, R> both(L left, R right) {
        return new Hydration<L, R>(Optional.of(left), Optional.of(right));
    }

    public static <L, R> Hydration<L, R> empty() {
        return new Hydration<L, R>(Optional.empty(), Optional.empty());
    }

    public static <L, R> Hydration<L, R> left(L left) {
        return new Hydration<L, R>(Optional.of(left), Optional.empty());
    }

    //EXTRAS

    public Optional<R> toOptional() {
        return right;
    }

    public R orElseNull() {
        return right.orElse(null);
    }

    public R orElseThrow(Class<? extends RuntimeException> ex, Object... constructorArgs) {
        return right.orElseThrow(() -> throwIt(ex, constructorArgs));
    }

    @SneakyThrows
    private RuntimeException throwIt(Class<? extends RuntimeException> ex, Object... constructorArgs) {
        return ConstructorUtils.invokeConstructor(ex, constructorArgs);
    }

}
