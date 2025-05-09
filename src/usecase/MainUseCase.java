package usecase;

import models.RelationalModel;
import models.RelationalModelMetaData;
import utils.HospitalDatabaseSetup;

import java.util.List;

public class MainUseCase {
    public static OperationalUseCases operationalUseCases=new OperationalUseCases();
    public static HospitalDatabaseSetup hospitalDatabaseSetup=new HospitalDatabaseSetup();

    public static void create(RelationalModelMetaData metaData,Object[] values){
        if(metaData.title.equals("DOCTOR")||metaData.title.equals("NURSE")){
            operationalUseCases.insert(metaData.title,metaData.attributes,values);
        }else{
            operationalUseCases.insert(metaData.title,hospitalDatabaseSetup.getQueryParameters(metaData.attributes),values);
        }
    }

    public static List<RelationalModel> get(RelationalModelMetaData metaData){
        return operationalUseCases.select(metaData.title,metaData.attributes);
    }

    public static void update(RelationalModelMetaData metaData,Object[] newData){
        //new Object[]{"Tariq","Grey","Accra","+23355916207",1}
        operationalUseCases.update(metaData.title,metaData.attributes,
               newData );
    }
    public static void delete(int id,RelationalModelMetaData metaData){
        operationalUseCases.delete(id,metaData.title,metaData.attributes);
    }


}
