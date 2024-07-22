package telran.util.test;

public class Person implements Comparable<Person>{
    long id;
    String name;
    public Person(long id, String name) {
        this.id = id;
        this.name = name;
    }
    long getId() {
        return id;
    }
    String getName() {
        return name;
    }
    @Override
    public int compareTo(Person arg0) {
        return Long.compare(id, arg0.id);
    }
}
