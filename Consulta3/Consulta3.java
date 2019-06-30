/*
 * Copyright 2008-2009 LinkedIn, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * Modificado con motivos puramente educativos.
 */

package voldemort.examples;


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
import java.util.*;


public class Consulta3{

    public static void main(String[] args)  {
        //stringStoreExample();
        stringStoreExample();
    }
    public static void stringStoreExample()  {
        System.out.println("==============String store example=================");

        String bootstrapUrl = "tcp://localhost:6666";
        StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));
        StoreClient<String, String> client = factory.getStoreClient("test");
        Integer i = 2;
        Integer j = 3;


        Versioned<String> versioned = client.get("1");
        String cont;
        String part1;
        double latitud =0.0;
        String [] parts;
        Integer c =0;
        double  linferior = 38.499;
        double lsuperior = 38.5;

        while (versioned != null)  {

            cont = String.valueOf(versioned.getValue());
            parts = cont.split(",");
            try{
                latitud = Float.valueOf(parts[7].trim()).floatValue();
            }
            catch (NumberFormatException nfe)
            {
                System.err.println("NumberFormatException: " + nfe.getMessage());
            }

            if (latitud > linferior && latitud <lsuperior){
                System.out.println(parts[0]+"\t"+parts[5]);
                c++;
            }
            i++;
            versioned = client.get(Integer.toString(i));
          }
System.out.println("Cantidad :"+Integer.toString(c));
    }
}
