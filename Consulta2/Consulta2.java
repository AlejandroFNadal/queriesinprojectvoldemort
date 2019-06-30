package voldemort.examples;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.JsonDecoder;
import org.apache.avro.io.parsing.Parser;
import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;
import voldemort.serialization.DefaultSerializerFactory;
import voldemort.serialization.json.JsonReader;
import voldemort.utils.ByteArray;
import voldemort.versioning.Versioned;
import java.io.IOException;
import java.io.BufferedReader;


public class Consulta2 {

    public static void main(String[] args)  {
        //stringStoreExample();
        stringStoreExample();
    }

    public static int check(int cont2,StoreClient<String, String> client, int i,Versioned<String> versioned, String [] parts,String linea,List<String> crimes){

        List<String> states = new ArrayList<String>();
        int conta =0;
        int j =0;
        while (versioned != null){
            linea = String.valueOf(versioned.getValue());
            parts = linea.split(",");
            if ((crimes.get(i)).equals(parts[6])){
                if (!states.contains(parts[4])){
                   
                    states.add(parts[4]);
                    conta++;
                }
            }  
            j++;
            versioned = client.get(Integer.toString(j));

        }
        if(conta>30){
            System.out.println(crimes.get(i));
            cont2++;
        }
     return cont2;
    }




    public static void stringStoreExample()  {
        System.out.println("==============String store example=================");

        // In production environment, the StoreClient instantiation should be done using factory pattern
        // through a Framework such as Spring
        String bootstrapUrl = "tcp://localhost:6666";
        StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));

        StoreClient<String, String> client = factory.getStoreClient("test");



        

       int i = 1;

Versioned<String> versioned = client.get("1");
String [] parts = {};

List<String> crimes = new ArrayList<String>();
String linea = new String();
Integer cant = 0;
Integer cant2 = 0;
Integer contstates = 0;
int cont2=0;

while (versioned != null) {
    linea = String.valueOf(versioned.getValue());
    parts = linea.split(",");
    if(!crimes.contains(parts[6])){
        cant++;
        crimes.add(parts[6]);  
    }
    
    i++;
    versioned = client.get(Integer.toString(i));
}
versioned = client.get("1");
for(i =0 ; i<cant ; i++){
  
    cont2=check(cont2,client,i,versioned,parts,linea,crimes);
    
}
System.out.println("Cantidad: "+Integer.toString(cont2));
    }
}
