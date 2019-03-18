package nl.fhict.s3;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

class GreetingStore {

    private HashMap<String, Greeting> store;

    GreetingStore(HashMap<String, Greeting> store) {
        this.store = store;
    }

    void addGreeting(Greeting greeting) {
        store.put(greeting.getName(), greeting);
    }

    public Greeting getGreeting(String key) {
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
}
