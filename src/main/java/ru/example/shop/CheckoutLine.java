package ru.example.shop;

/**
 * Not Thread safe
 */
public class CheckoutLine {

    private static final Integer DEFAULT_CAPACITY = 20;

    private Integer length;
    private final String id;
    private final Double serveTime;
    private final Integer capacity;

    /**
     * @param id         - CheckoutLine id
     * @param throughput - People per hour
     * @param capacity   - Max people allowed in line
     */
    public CheckoutLine(String id, Integer throughput, Integer capacity) {
        this.length = 0;
        this.id = id;
        this.serveTime = 1.0 / throughput;
        this.capacity = capacity;
    }

    /**
     * @param id         - CheckoutLine id
     * @param throughput - People per hour
     */
    public CheckoutLine(String id, Integer throughput) {
        this(id, throughput, DEFAULT_CAPACITY);
    }

    public Boolean canAcceptPerson() {
        return length < capacity;
    }

    public void acceptPerson() {
        if (canAcceptPerson()) {
            this.length++;
        } else {
            throw new IllegalStateException("Cannot accept new person at this moment");
        }
    }

    public void releasePerson() {
        this.length--;
    }

    public Double getExpectedWaitTime() {
        return serveTime * (length + 1);
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

    public Double getServeTime() {
        return serveTime;
    }

    public Integer getCapacity() {
        return capacity;
    }

}
