package org.example.dto;

public class IdDTO
{
    int nextID;

    public IdDTO(int nextID) {
        this.nextID = nextID;
    }

    public IdDTO() {
    }

    public void setNextID(int nextID) {
        this.nextID = nextID;
    }
    public int getNextID() {
        return nextID;
    }
}
