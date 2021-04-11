/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class Armors implements Serializable{
    private String armorId;
    private String classification;
    private String timeofcreate;
    private int defense;
    private String description;
    private String status;

    public Armors() {
    }

    public Armors(String armorId, String classification, String timeofcreate, int defense, String description, String status) {
        this.armorId = armorId;
        this.classification = classification;
        this.timeofcreate = timeofcreate;
        this.defense = defense;
        this.description = description;
        this.status = status;
    }

    public String getArmorId() {
        return armorId;
    }

    public void setArmorId(String armorId) {
        this.armorId = armorId;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getTimeofcreate() {
        return timeofcreate;
    }

    public void setTimeofcreate(String timeofcreate) {
        this.timeofcreate = timeofcreate;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
   
    @Override
    public String toString() {
        return armorId + "-" + classification +"-"+ timeofcreate +"-"+ defense +"-"+
                    description+"-"+status;
    }
    
    
}
