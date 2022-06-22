/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Document {
    private Long id;
    private String type;//Digital or Physical
    private int numPages;
    private Date dateRecord;
    private Employee employee;
    private Organization organization;

    public Document(Long id, String type, int numPages, Date dateRecord) {
        this.id = id;
        this.type = type;
        this.numPages = numPages;
        this.dateRecord = dateRecord;
    }

    public Document() {
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getNumPages() {
        return numPages;
    }

    public Date getDateRecord() {
        return dateRecord;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public void setDateRecord(Date dateRecord) {
        this.dateRecord = dateRecord;
    }

    @Override
    public String toString() {
        return "Document{" + "id=" + id + ", type=" + type + ", numPages=" + numPages + ", dateRecord=" + dateRecord + '}';
    }
    
    
    
    
}
