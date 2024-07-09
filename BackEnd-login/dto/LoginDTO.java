package org.example.dto;

public class LoginDTO
{
    Integer id;
    String type;

    public LoginDTO() {
    }

    public LoginDTO(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
