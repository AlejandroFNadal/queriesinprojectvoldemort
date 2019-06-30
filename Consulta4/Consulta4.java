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


public class Consulta4 {

    public static void main(String[] args)  {
        //stringStoreExample();
        stringStoreExample();
    }
    public static void stringStoreExample()  {
        System.out.println("==============String store example=================");

        // In production environment, the StoreClient instantiation should be done using factory pattern
        // through a Framework such as Spring
        String bootstrapUrl = "tcp://localhost:6666";
        StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));

        StoreClient<String, String> client = factory.getStoreClient("test");
//----------------------------------------------------seleccionar todas las descripciones(sin repetir)-----------------------------------------// 
       Versioned<String> versioned = client.get("1");
       List<String> desc = new ArrayList<String>();
       String [] parts = {};
       String linea = new String();
       int cont=0;
       int i =0;
        while (versioned != null)  {
            
            linea = String.valueOf(versioned.getValue());
            parts = linea.split(",");
            if (!desc.contains(parts[6])){
                desc.add(parts[6]);
                cont++;
                System.out.println(parts[6]);
            }
            i++;
            versioned = client.get(Integer.toString(i));
        }
        System.out.println("Cantidad: "+Integer.toString(cont));
    }
}
