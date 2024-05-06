import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Task implements Comparable<Task>, Iterable<Task>{
    private String name;
    private String description;
    private int priority;

    public Task(String name, String description, int priority) {
        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    @Override
    public String toString() {
        return "Task name: " + name + " | Description: " + description + " | Priority: " + priority;
    }

    @Override
    public int compareTo(Task o) {
        if(this.priority == o.priority) {
            return this.name.compareTo(o.name);

        }
        else {
            return String.valueOf(priority).compareTo(String.valueOf(o.priority));

        }
    }

    @Override
    public Iterator<Task> iterator() {
        return null;
    }


    @Override
    public Spliterator<Task> spliterator() {
        return Iterable.super.spliterator();
    }


}
