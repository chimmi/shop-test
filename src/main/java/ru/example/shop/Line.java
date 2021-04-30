package ru.example.shop;

/**
 * Not Thread safe
 */
public class Line {

    private static final Integer DEFAULT_CAPACITY = 20;

    private Integer length;
    private final String id;
    private final Double averageWaitTime;
    private final Integer capacity;

    /**
     * @param id         - Line id
     * @param throughput - People per hour
     * @param capacity   - Max people allowed in line
     */
    public Line(String id, Integer throughput, Integer capacity) {
        this.length = 0;
        this.id = id;
        this.averageWaitTime = 1.0 / throughput;
        this.capacity = capacity;
    }

    /**
     * @param id         - Line id
     * @param throughput - People per hour
     */
    public Line(String id, Integer throughput) {
        this(id, throughput, DEFAULT_CAPACITY);
    }

    public Boolean canAcceptPerson() {
        return length < capacity;
    }

    public void acceptPerson() {
        this.length++;
    }

    public void releasePerson() {
        this.length--;
    }

    public Double getExpectedWaitTime() {
        return averageWaitTime * (length + 1);
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public Double getAverageWaitTime() {
        return averageWaitTime;
    }

    public Integer getCapacity() {
        return capacity;
    }

}
