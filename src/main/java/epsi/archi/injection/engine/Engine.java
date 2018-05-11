package epsi.archi.injection.engine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class Engine {

    public static class DependencyMap {

        private Map<Class<?>, Object> values = new HashMap<>();

        public <T> void put(Class<? extends T> clazz, T value) {
            values.put(clazz, value);
        }

        public <T> T get(Class<T> clazz) {
            return (T) values.get(clazz);
        }
    }

    private Map<Class<?>, Supplier<?>> suppliers = new HashMap<>();
    private Map<Class<?>, List<Class<?>>> dependencies = new HashMap<>();
    private Map<Class<?>, Function<DependencyMap, ?>> factories = new HashMap<>();

    public <T> void register(Class<T> clazz, Supplier<? extends T> supplier) {
        suppliers.put(clazz, supplier);
    }

    public <T> Supplier<? extends T> defaultSupplier(Class<T> clazz) {
        return () -> {throw new RuntimeException("No supplier registered for this class ...");};
    }

    public <T> T supply(Class<T> clazz) {
        return (T) suppliers.getOrDefault(clazz, defaultSupplier(clazz)).get();
    }

    public <T> void registerDependencies(Class<T> clazz, List<Class<?>> dependencies) {
        this.dependencies.put(clazz, dependencies);
    }

    public DependencyMap getDependencies(List<Class<?>> dependencies) {
        DependencyMap result = new DependencyMap();
        dependencies.forEach(dependencyClass -> result.put(dependencyClass, supply(dependencyClass)));
        return result;
    }

    public DependencyMap getDependencies(Class<?> clazz) {
        return getDependencies(dependencies.get(clazz));
    }

    public <T> void register(Class<T> clazz, Function<DependencyMap, ? extends T> factory) {
        factories.put(clazz, factory);
    }

    public static List<Class<?>> dependencies(Class<?>... dependencies) {
        return Arrays.asList(dependencies);
    }

    public <T> void register(Class<T> clazz, List<Class<?>> dependencies, Function<DependencyMap, ? extends T> factory) {
        factories.put(clazz, factory);
        registerDependencies(clazz, dependencies);
    }

    public <T> void register(Class<T> clazz, Function<DependencyMap, ? extends T> factory, Class<?>... dependencies) {
        factories.put(clazz, factory);
        registerDependencies(clazz, dependencies(dependencies));
    }


    public <T> T build(Class<T> clazz) {
        return (T) factories.get(clazz).apply(getDependencies(clazz));
    }

    public <T> T build(Class<T> clazz, List<Class<?>> dependencies) {
        return (T) factories.get(clazz).apply(getDependencies(dependencies));
    }
}
