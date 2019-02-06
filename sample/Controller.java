package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

/*@author: Nicholas Slezak
* Created on: 1/26/2019
*/

public class Controller implements Initializable {

    @FXML
    TableView<Repair_Order> FlagSheet;
    @FXML
    TableColumn<Repair_Order, LocalDate> date;
    @FXML
    TableColumn<Repair_Order, String> RONumber;
    @FXML
    TableColumn<Repair_Order, String> Operation;
    @FXML
    TableColumn<Repair_Order, String> ClockHours;
    @FXML
    TableColumn<Repair_Order, String> FlagHours;
    @FXML
    TableColumn<Repair_Order, String> Skill;

    @FXML
    TextField OperationField, RONumberField, SkillField, dateField, FlagHoursField, ClockHoursField, selectByDateField;

    @FXML
    Button Submit, SearchButton;

    public Controller(){

    }

    public TextField getOperationField() {
        return OperationField;
    }

    public TextField getRONumberField() {
        return RONumberField;
    }

    public TextField getSkillField() {
        return SkillField;
    }

    public TextField getDateField() {
        return dateField;
    }

    public TextField getClockHoursField() {
        return ClockHoursField;
    }

    public TextField getFlagHoursField() {
        return FlagHoursField;
    }

    public Button getSubmit() {
        return Submit;
    }

    public void setDate(TextField newDate) {
        dateField = newDate;
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY");
        dateField.setText(String.valueOf(dtf.format(localDateTime)));
    }

    public void addDateTime(){
        dateField.setOnMouseEntered(event -> {
            setDate(dateField);
            event.consume();
        });
    }

    public void submitButtonAction()throws Exception{
        Submit.setOnAction(event -> {     //action event with submit button to enter data into table on next line
            FlagSheet.getItems().add(new Repair_Order(LocalDate.now(), RONumberField.getText(), OperationField.getText(),ClockHoursField.getText(),FlagHoursField.getText(),SkillField.getText()));
            InsertStatement();
            OperationField.clear();       //Clearing textfields after entering items into table
            RONumberField.clear();
            ClockHoursField.clear();
            FlagHoursField.clear();
            dateField.clear();
            SkillField.clear();
            event.consume();});
    }

    public void SearchButtonAction() throws Exception{
        SearchButton.setOnAction(event -> {
            try {
                SelectByDate();
                selectByDateField.clear();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        date.setCellValueFactory(new PropertyValueFactory<Repair_Order, LocalDate>("date"));
        Operation.setCellValueFactory(new PropertyValueFactory<Repair_Order, String>("Operation"));
        RONumber.setCellValueFactory(new PropertyValueFactory<Repair_Order, String>("RONumber"));
        ClockHours.setCellValueFactory(new PropertyValueFactory<Repair_Order, String>("ClockHours"));
        FlagHours.setCellValueFactory(new PropertyValueFactory<Repair_Order, String>("FlagHours"));
        Skill.setCellValueFactory(new PropertyValueFactory<Repair_Order, String>("Skill"));
    }

    /*public ObservableList<Repair_Order> getRO(){
        ObservableList<Repair_Order> RepairOrder = FXCollections.observableArrayList();
        RepairOrder.add(i, new Repair_Order(LocalDate.now(), RONumberField.getText(), OperationField.getText(), ClockHoursField.getText(), FlagHoursField.getText(), SkillField.getText()));

        return RepairOrder;
    }*/

   public static Connection getConnection() throws Exception{
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://LocalHost:3306/FlagSheetDB?autoReconnect=true&useSSL=false";
            String username = "root";
            String password = "October#51983";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url, username, password);
            //System.out.println("Connection Complete");
            return conn;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
       return null;
   }

   public void InsertStatement(){
       try{
           Connection conn = getConnection();
           PreparedStatement insertinto = conn.prepareStatement
                   ("INSERT INTO RepairOrder (RepairOrderNumbr, Date, ClockHours, Skill, Operation_idOperation) VALUES("
                           +RONumberField.getText()+", now(),"+ClockHoursField.getText()+",'"+SkillField.getText()+"','"+OperationField.getText()+"')");
           insertinto.executeUpdate();
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
   }

   public void SelectByDate() {
       try{
           String selectRepairOrder = "SELECT * FROM RepairOrder INNER JOIN Operation ON RepairOrder.Operation_idOperation = Operation.idOperation WHERE RepairOrder.Date = '"+selectByDateField.getText()+"'";
           Connection conn = getConnection();
           ResultSet resultSet = conn.createStatement().executeQuery(selectRepairOrder);
           while(resultSet.next() && !selectByDateField.equals(null)){
               FlagSheet.getItems().add(new Repair_Order(resultSet.getDate("Date").toLocalDate(), resultSet.getString("RepairOrderNumbr"), resultSet.getString("Operation_idOperation"), resultSet.getString("ClockHours"), resultSet.getString("FlagHours"), resultSet.getString("Skill")));
           }
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
       }
}
