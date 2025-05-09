package usecase;

import exceptions.InvalidQueryException;
import exceptions.InvalidRequestException;
import models.RelationalModel;
import utils.HospitalDatabaseSetup;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

import static models.DataBaseCredentials.JDBC_URL;
import static models.DataBaseCredentials.JDBC_USER;
import static models.DataBaseCredentials.JDBC_PASSWORD;



public class OperationalUseCases {
    private HospitalDatabaseSetup hospitalDatabaseSetup=new HospitalDatabaseSetup();

    public void insert(String table,String[] parameters, Object[] values){


        String insertSQL = hospitalDatabaseSetup.generateStatement(parameters,table,"insert");

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            int parameterIndex=0;
            for(Object value:values){
                parameterIndex++;
                pstmt.setObject(parameterIndex,value);
            }

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Inserted " + rowsAffected + " row(s) into the "+table+" table.");

        } catch (SQLException e) {
            throw new InvalidRequestException("The Database request being made is invalid, check your parameters");

        }
    };
    public void update(String table,String[] attributes,Object[] values){

        String updateSQL = hospitalDatabaseSetup.generateStatement(attributes,table,"update");

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            int parameterIndex=0;
            for(Object value:values){
                parameterIndex++;
                pstmt.setObject(parameterIndex,value);
            } // employee ID to update

            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Updated " + rowsUpdated + " row(s) in the employee table.");

        } catch (SQLException e) {
            throw new InvalidRequestException("The Database request being made is invalid, check your parameters");
        }
    };

    public void delete(int id,String table,String[] attributes){

        String deleteSQL = hospitalDatabaseSetup.generateStatement(attributes,table,"delete");

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, id);  // Specify the ID of the employee to delete
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Deleted " + rowsAffected + " row(s) from the employee table.");

        } catch (SQLException e) {
            throw new InvalidRequestException("The Database request being made is invalid, check your parameters");
        }
    };

    public List<RelationalModel>  select(String table, String[] parameters){
        List<RelationalModel> models = new java.util.ArrayList<>(List.of());
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement stmt = conn.createStatement()) {

            String query = hospitalDatabaseSetup.generateStatement(parameters,table,"select");

            ResultSet rs = stmt.executeQuery(query);
            StringBuilder header=new StringBuilder();

            System.out.println(table+" List:");
            System.out.println("---------------------------------------------------");

            for(String parameter:parameters){

                header.append(parameter).append(" ");
            }


            System.out.printf(header.toString()+"\n");


            while (rs.next()) {
                StringBuilder row= new StringBuilder();
                RelationalModel entry=new RelationalModel();
                entry.title=table;

                for(String parameter:parameters){
                    row.append(rs.getObject(parameter)).append(" ");
                    entry.attributes.add(rs.getObject(parameter));
                }

                models.add(entry);
                System.out.printf(row.toString()+"\n");
            }

        } catch (SQLException e) {
            throw new InvalidQueryException("Data extraction failed, check your parameters");
        }

        return models;
    }

}
