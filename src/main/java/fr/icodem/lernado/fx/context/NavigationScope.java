package fr.icodem.lernado.fx.context;

import com.google.common.collect.Maps;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;

import java.util.Map;

import static com.google.common.base.Preconditions.checkState;

public class NavigationScope implements Scope {

    private Map<Key<?>, Object> values;

    public void enter() {
        checkState(values == null, "A scoping block is already in progress");
        values = Maps.newHashMap();
    }

    public void exit() {
        checkState(values != null, "No scoping block in progress");
        values = null;
    }

    @Override
    public <T> Provider<T> scope(Key<T> key, Provider<T> unscoped) {
        return () -> get(key, unscoped);
    }

    private <T> T get(Key<T> key, Provider<T> unscoped) {
        T obj = (T)values.get(key);
        if (obj == null) {
            obj = unscoped.get();
            values.put(key, obj);
        }
        return obj;
    }
}
