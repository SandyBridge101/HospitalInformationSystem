package utils;

import models.DataBaseCredentials;
import models.RelationalModelMetaData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

import static models.DataBaseCredentials.JDBC_DB_NAME;
import static models.DataBaseCredentials.JDBC_URL;

public class HospitalDatabaseSetup {
    public RelationalModelMetaData employeeMetaData;
    public RelationalModelMetaData doctorMetaData;
    public RelationalModelMetaData nurseMetaData;
    public RelationalModelMetaData departmentMetaData;
    public RelationalModelMetaData wardMetaData;
    public RelationalModelMetaData patientMetaData;
    public RelationalModelMetaData bedMetaData;
    public RelationalModelMetaData hospitalizationMetaData;
    public RelationalModelMetaData transferMetaData;

    // JDBC URL, username and password of MySQL server

    private Connection connection;

    // Change this to your actual password

    public HospitalDatabaseSetup() {

        setModels();

        System.out.println("Setting up configurations for database: "+JDBC_DB_NAME + "......");

    }

    public String[] getQueryParameters(String[] attributes){
        return Arrays.copyOfRange(attributes, 1, attributes.length);
    }

    public void setModels(){
        //new RelationalModelMetaData("EMPLOYEE", new String[]{"number","firstname","surname","address","telephone"});
        //new RelationalModelMetaData("NURSE", new String[]{"number","firstname","surname","address","telephone"});

        employeeMetaData=new RelationalModelMetaData("EMPLOYEE", new String[]{"number", "firstname", "surname", "address", "telephone"});
        doctorMetaData=new RelationalModelMetaData("DOCTOR", new String[]{"specialty", "EMPLOYEE_number"});
        nurseMetaData=new RelationalModelMetaData("NURSE", new String[]{"salary", "rotation", "EMPLOYEE_number"});
        departmentMetaData=new RelationalModelMetaData("DEPARTMENTS", new String[]{"code", "name", "building", "DOCTOR_EMPLOYEE_number", "NURSE_EMPLOYEE_number"});
        wardMetaData=new RelationalModelMetaData("WARD", new String[]{"number", "number_of_beds", "DEPARTMENTS_code", "DEPARTMENTS_DOCTOR_EMPLOYEE_number", "DEPARTMENTS_NURSE_EMPLOYEE_number"});
        patientMetaData=new RelationalModelMetaData("PATIENT", new String[]{"number", "firstname", "surname", "address", "telephone"});
        bedMetaData=new RelationalModelMetaData("BED", new String[]{"number", "WARD_number", "WARD_DEPARTMENTS_code", "WARD_DEPARTMENTS_DOCTOR_EMPLOYEE_number", "WARD_DEPARTMENTS_NURSE_EMPLOYEE_number"});
        hospitalizationMetaData=new RelationalModelMetaData("HOSPITALIZATION", new String[]{
                "number", "diagnosis", "PATIENT_number", "BED_number", "BED_WARD_number",
                "BED_WARD_DEPARTMENTS_code", "BED_WARD_DEPARTMENTS_DOCTOR_EMPLOYEE_number",
                "BED_WARD_DEPARTMENTS_NURSE_EMPLOYEE_number"});
        transferMetaData=new RelationalModelMetaData("TRANSFER", new String[]{
                "number", "new_ward", "HOSPITALIZATION_number", "HOSPITALIZATION_PATIENT_number",
                "HOSPITALIZATION_BED_number", "HOSPITALIZATION_BED_WARD_number",
                "HOSPITALIZATION_BED_WARD_DEPARTMENTS_code",
                "HOSPITALIZATION_BED_WARD_DEPARTMENTS_DOCTOR_EMPLOYEE_number",
                "HOSPITALIZATION_BED_WARD_DEPARTMENTS_NURSE_EMPLOYEE_number"});
    }

    public String generateStatement(String[] attributes,String table,String operation){
        StringBuilder attributeSection= new StringBuilder();
        String statement="";
        String primary="";

        switch (operation) {
            case "insert":
                StringBuilder paramsSection= new StringBuilder();
                for(String attribute:attributes){

                    attributeSection.append(attribute).append(",");
                    paramsSection.append("?,");

                }
                attributeSection.deleteCharAt(attributeSection.length()-1);
                paramsSection.deleteCharAt(paramsSection.length()-1);
                //"INSERT INTO employee (id, name, position, salary) VALUES (?, ?, ?, ?)"
                statement = String.format("INSERT INTO %s (%s) VALUES (%s)",table,attributeSection.toString(),paramsSection);
                break;

            case "update":
                //"UPDATE employee SET salary = ?, position = ? WHERE id = ?"

                for(String attribute:attributes){
                    if(Objects.equals(attribute, attributes[0])){
                        primary=attribute;
                    }else{
                        attributeSection.append(attribute).append(" = ?,");
                    }
                }
                attributeSection.deleteCharAt(attributeSection.length()-1);
                statement = String.format("UPDATE %s SET %s WHERE %s = ?",table,attributeSection.toString(),primary);
                break;
            case "select":
                //"SELECT number, firstname, surname, address, telephone FROM PATIENT"
                for(String attribute:attributes){
                    attributeSection.append(attribute).append(",");
                }
                attributeSection.deleteCharAt(attributeSection.length()-1);
                statement = String.format("SELECT %s FROM %s",attributeSection.toString(),table);
                break;
            case "delete":
                //"DELETE FROM employee WHERE id = ?"
                statement = String.format("DELETE FROM %s WHERE %s = ?",table,attributes[0]);
                break;
            default:
                statement = "";

        }
        System.out.println("QUERY: "+statement);

        return statement;


    }


    public Connection getConnection(){
        return this.connection;
    }
}
