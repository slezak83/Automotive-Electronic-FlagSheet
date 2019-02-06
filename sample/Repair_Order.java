package sample;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

/*@author: Nicholas Slezak
* Created on: 1/26/2019
*/

public class Repair_Order {

    private SimpleStringProperty RONumber, Operation, ClockHours, FlagHours, Skill;

    private LocalDate date;

    public Repair_Order(LocalDate date, String RONumber, String Operation, String ClockHours, String FlagHours, String Skill){
        this.date = date;
        this.RONumber = new SimpleStringProperty(RONumber);
        this.Operation = new SimpleStringProperty(Operation);
        this.ClockHours = new SimpleStringProperty(ClockHours);
        this.FlagHours = new SimpleStringProperty(FlagHours);
        this.Skill = new SimpleStringProperty(Skill);
    }

    public String getRONumber() {
        return RONumber.get();
    }

    public SimpleStringProperty RONumberProperty() {
        return RONumber;
    }

    public String getOperation() {
        return Operation.get();
    }

    public SimpleStringProperty operationProperty() {
        return Operation;
    }

    public String getClockHours() {
        return ClockHours.get();
    }

    public SimpleStringProperty clockHoursProperty() {
        return ClockHours;
    }

    public String getFlagHours() {
        return FlagHours.get();
    }

    public SimpleStringProperty flagHoursProperty() {
        return FlagHours;
    }

    public String getSkill() {
        return Skill.get();
    }

    public SimpleStringProperty skillProperty() {
        return Skill;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setRONumber(String RONumber) {
        this.RONumber.set(RONumber);
    }

    public void setOperation(String operation) {
        this.Operation.set(operation);
    }

    public void setClockHours(String clockHours) {
        this.ClockHours.set(clockHours);
    }

    public void setFlagHours(String flagHours) {
        this.FlagHours.set(flagHours);
    }

    public void setSkill(String skill) {
        this.Skill.set(skill);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
