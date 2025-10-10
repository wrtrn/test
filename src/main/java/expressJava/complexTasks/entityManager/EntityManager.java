package expressJava.complexTasks.entityManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


public class EntityManager<T extends Entity> {

    private CopyOnWriteArrayList<T> entities = new CopyOnWriteArrayList<>();

    public void addElement(T entity) {
        entities.add(entity);
    }

    public boolean removeElement(T entity) {
        return entities.remove(entity);
    }

    public List<T> getAllElements() {
        return List.copyOf(entities);
    }

    public List<T> ageFilter(int minAge, int maxAge) {
        return entities.stream().filter(entity -> entity.getAge() >= minAge && entity.getAge() <= maxAge).collect(Collectors.toList());
    }

    public List<T> nameFilter(String name) {
        return entities.stream().filter(entity -> entity.getName().equals(name)).collect(Collectors.toList());
    }

    public List<T> activityFilter(boolean isActive) {
        return entities.stream().filter(entity -> entity.isActive() == isActive).collect(Collectors.toList());
    }
}
