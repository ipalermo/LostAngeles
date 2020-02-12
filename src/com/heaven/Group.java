package com.heaven;

public class Group {
    private Integer id;
    private Integer size;
    private Integer destination;
    private Integer waitingTime;

    public Group(Integer id, Integer size, Integer destination) {
        this.id = id;
        this.size = size;
        this.destination = destination;
        this.waitingTime = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public Integer getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Integer waitingTime) {
        this.waitingTime = waitingTime;
    }
}
