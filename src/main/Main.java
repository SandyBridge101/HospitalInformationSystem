package main;

import models.RelationalModelMetaData;
import usecase.MainUseCase;
import usecase.OperationalUseCases;
import utils.HospitalDatabaseSetup;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {
        OperationalUseCases operationalUseCases=new OperationalUseCases();
        HospitalDatabaseSetup hs=new HospitalDatabaseSetup();

        //MainUseCase.create(hs.employeeMetaData,new Object[]{"Samuel","Amo","East Legon,Accra","+23355916207"});

        //MainUseCase.update(hs.employeeMetaData,new Object[]{"Samuel","Atta","NewYork","+233345939398",35});

        //MainUseCase.delete(35,hs.employeeMetaData);



        //EMPLOYEE
        //operationalUseCases.insert(hs.employeeMetaData.title,hs.getQueryParameters(hs.employeeMetaData.attributes),new Object[]{"John","Dorsey","Accra","+23355916207"});
        //operationalUseCases.insert(hs.employeeMetaData.title,hs.getQueryParameters(hs.employeeMetaData.attributes),new Object[]{"Jean","Grey","Accra","+23355916207"});
        //operationalUseCases.insert(hs.doctorMetaData.title,hs.getQueryParameters(hs.doctorMetaData.attributes),new Object[]{"John","Doe","Accra","+23355916207"});
        operationalUseCases.select(hs.employeeMetaData.title,hs.employeeMetaData.attributes);

        //DOCTOR
        //operationalUseCases.insert(hs.doctorMetaData.title,hs.doctorMetaData.attributes,new Object[]{"Surgeon",1});
        operationalUseCases.select(hs.doctorMetaData.title,hs.doctorMetaData.attributes);

        //NURSE
        //operationalUseCases.insert(hs.nurseMetaData.title,hs.nurseMetaData.attributes,new Object[]{2500.456,"Night",1});
        operationalUseCases.select(hs.nurseMetaData.title,hs.nurseMetaData.attributes);

        //DEPARTMENT
        //operationalUseCases.insert(hs.departmentMetaData.title,hs.getQueryParameters(hs.departmentMetaData.attributes),new Object[]{"Disease Control","Ayittey Ajo",1,1});
        operationalUseCases.select(hs.departmentMetaData.title,hs.departmentMetaData.attributes);

        //WARD
        //operationalUseCases.insert(hs.wardMetaData.title,hs.getQueryParameters(hs.wardMetaData.attributes),new Object[]{20,3,1,1});
        operationalUseCases.select(hs.wardMetaData.title,hs.wardMetaData.attributes);

        //BED
        //operationalUseCases.insert(hs.bedMetaData.title,hs.getQueryParameters(hs.bedMetaData.attributes),new Object[]{3,3,1,1});
        operationalUseCases.select(hs.bedMetaData.title,hs.bedMetaData.attributes);

        //UPDATE
        //operationalUseCases.update(hs.employeeMetaData.title,hs.employeeMetaData.attributes,new Object[]{"Tariq","Grey","Accra","+23355916207",1});

        //operationalUseCases.delete(3,hs.employeeMetaData.title,hs.employeeMetaData.attributes);











    }
}