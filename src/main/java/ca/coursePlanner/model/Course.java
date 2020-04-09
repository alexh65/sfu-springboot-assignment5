package ca.coursePlanner.model;

import ca.coursePlanner.Observer.Observer;

import java.util.ArrayList;

public class Course implements Comparable<Course>{
    private long id;
    private String catalogNumber;
    private ArrayList<Offering> offerings;
    private ArrayList<Observer> observers = new ArrayList<>();

    public Course(long id, String catalogNumber) {
        this.id = id;
        this.catalogNumber = catalogNumber;
        this.offerings = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public long getId() {
        return id;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public ArrayList<Offering> getOfferings() {
        return offerings;
    }

    public void addOffering(Offering offering){
        offerings.add(offering);
    }

    @Override
    public String toString() {
        return getClass().getName() + "{id: " + id + ", catalogNumber: " + catalogNumber + "}";
    }

    @Override
    public int compareTo(Course course) {
        return catalogNumber.compareTo(course.catalogNumber);
    }
}