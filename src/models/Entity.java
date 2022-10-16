package models;

public class Entity {
    private static int nextId = 1;

    private final int id;

    public Entity() {
        this.id = nextId++;
    }

    public int getId() {
        return this.id;
    }
}
