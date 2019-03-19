package nl.fhict.s3;

import java.util.Collection;
import java.util.HashMap;

class GreetingStore {

    private HashMap<String, Greeting> store;
    private static GreetingStore instance;

    private GreetingStore(HashMap<String, Greeting> store) {
        this.store = store;
    }

    void addGreeting(Greeting greeting) {
        store.put(greeting.getName(), greeting);
    }

    Greeting getGreeting(String key) {
        return store.get(key);
    }

    public void removeGreeting(String key) {
        store.remove(key);
    }

    public void replaceGreeting(Greeting greeting) {
        store.replace(greeting.getName(), greeting);
    }

    Collection<Greeting> getAll() {
        return store.values();
    }

    static GreetingStore getInstance() {
        if (instance == null) {
            instance = new GreetingStore(new HashMap<>());
        }
        return instance;
    }
}
