package br.com.georgealvessilveira;

public class Customer {

    private int id;
    private String name;

    public Customer() {
        this.id = 0;
        this.name = "";
    }

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "id: " + getId() + " name: " + getName();
    }
}
