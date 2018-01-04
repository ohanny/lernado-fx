package fr.icodem.lernado.fx.context;

import com.google.common.eventbus.EventBus;

public class NavigationEventBus {
    private EventBus delegate = new EventBus();

    public String identifier() {
        return delegate.identifier();
    }

    public void register(Object object) {
        delegate.register(object);
    }

    public void unregister(Object object) {
        delegate.unregister(object);
    }

    public void post(Object event) {
        delegate.post(event);
    }
}
