
package model;

import java.io.Serializable;


public class Client extends Person {
    private boolean state;
    private String type;
    private String notes;

    public Client(boolean state, String type, String notes, Long id, String fullName, String address, String phone, String email) {
        super(id, fullName, address, phone, email);
        this.state = state;
        this.type = type;
        this.notes = notes;
    }

    public Client(boolean state, String type, String notes) {
        this.state = state;
        this.type = type;
        this.notes = notes;
    }

    public Client() {
    }

    public Client(Long id, String fullName, String address, String phone, String email) {
        super(id, fullName, address, phone, email);
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Client{" + "state=" + state + ", type=" + type + ", notes=" + notes + '}'+' ' + super.toString();
    }

    
    
    
    
    
}
