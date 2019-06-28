import java.util.Scanner;
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


public class ClientExample{

    public static void main(String[] args)  {
        //stringStoreExample();
        stringStoreExample();
    }
	
	public static void stringStoreExample()  {
        System.out.println("==============String store example=================");

        String bootstrapUrl = "tcp://localhost:6666";
        StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));

        StoreClient<String, String> client = factory.getStoreClient("test");
        

       Integer i = 1;
       StringBuilder sb = new StringBuilder();
       try {
	    BufferedReader br = Files.newBufferedReader(Paths.get("prueba.txt"));

            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                
                client.put(Integer.toString(i), line);
                sb.append(line).append("\n");
                i++;
            }
        }
        catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
   
    }
}
