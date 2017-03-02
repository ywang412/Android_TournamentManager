package edu.gatech.seclass.tourneymanager.model;

/**
 * Created by Yu on 2/28/2017.
 */

public class User {

    private String username;
    private String name;
    private String phone_number;
    private int deck_id;
    private boolean is_manager;
    private String password;


    public User() {
    }

    public User(String username, String name, String phone_number, int deck_id, boolean is_manager, String password) {
        this.username = username;
        this.name = name;
        this.phone_number = phone_number;
        this.deck_id = deck_id;
        this.is_manager = is_manager;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(int deck_id) {
        this.deck_id = deck_id;
    }

    public boolean is_manager() {
        return is_manager;
    }

    public void setIs_manager(boolean is_manager) {
        this.is_manager = is_manager;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
