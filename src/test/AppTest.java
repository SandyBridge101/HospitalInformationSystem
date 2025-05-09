package test;


import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;


import models.*;
import usecase.MainUseCase;
import usecase.OperationalUseCases;
import utils.HospitalDatabaseSetup;

public class AppTest {
    OperationalUseCases operationalUseCases=new OperationalUseCases();
    HospitalDatabaseSetup hospitalDatabaseSetup=new HospitalDatabaseSetup();


    @Before
    public void setUp() {
        //MainUseCase.create(hospitalDatabaseSetup.employeeMetaData,new Object[]{"Ama","Atta","Accra","+233207600953"});
        //List<RelationalModel> models=MainUseCase.get(hospitalDatabaseSetup.employeeMetaData);
        //System.out.println(models.getLast().attributes.get(0));

    }

    @Test
    public void testInsert() {
        MainUseCase.create(hospitalDatabaseSetup.employeeMetaData,new Object[]{"John","Dorsey","Accra","+23355916207"});

        List<RelationalModel> models=MainUseCase.get(hospitalDatabaseSetup.employeeMetaData);

        RelationalModel testModel=models.getLast();

        assertEquals("John",testModel.attributes.get(1));

    }

    @Test
    public void testUpdate() {
        List<RelationalModel> models=MainUseCase.get(hospitalDatabaseSetup.employeeMetaData);
        int id= Math.toIntExact((long) models.getFirst().attributes.get(0));

        MainUseCase.update(hospitalDatabaseSetup.employeeMetaData,new Object[]{"Tariq","Grey","Italy","+23355916207",id});

        List<RelationalModel> testModels=MainUseCase.get(hospitalDatabaseSetup.employeeMetaData);

        RelationalModel testModel=testModels.getFirst();

        assertEquals("Tariq",testModel.attributes.get(1));
        assertEquals("Grey",testModel.attributes.get(2));
        assertEquals("Italy",testModel.attributes.get(3));
        assertEquals("+23355916207",testModel.attributes.get(4));

    }

    @Test
    public void testDelete() {
        List<RelationalModel> models=MainUseCase.get(hospitalDatabaseSetup.employeeMetaData);
        int id= Math.toIntExact((long) models.getLast().attributes.get(0));
        int modelsSize=models.size();

        MainUseCase.delete(id,hospitalDatabaseSetup.employeeMetaData);

        assertEquals(modelsSize-1,MainUseCase.get(hospitalDatabaseSetup.employeeMetaData).size());

    }

}
