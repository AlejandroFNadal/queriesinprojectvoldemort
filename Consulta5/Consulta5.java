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


public class Consulta5{

    public static void main(String[] args)  {
        //stringStoreExample();
        stringStoreExample();
    }
    public static int check(int cont2,StoreClient<String, String> client, int i,Versioned<String> versioned, String [] parts,String linea,List<String> crimes){


        boolean flag=true;
        int j =0;
        while (versioned != null){
          linea = String.valueOf(versioned.getValue());
          parts = linea.split(",");
            if ((crimes.get(i)).equals(parts[6])){
                if (parts[2].equals("3")){
                    flag=false;
                    //states.add(parts[2])
                    //conta++;
                }
            }
            j++;
            versioned = client.get(Integer.toString(j));

        }
        if(flag){
            System.out.println(crimes.get(i));
            cont2++;
        }

     return cont2;
    }
    public static void stringStoreExample()  {
        System.out.println("==============String store example=================");

        String bootstrapUrl = "tcp://localhost:6666";
        StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));
        StoreClient<String, String> client = factory.getStoreClient("test");
        Integer i = 2;
        Integer j = 3;
        String linea = new String();
        Integer cant =0;
        int cont2=0;
        Versioned<String> versioned = client.get("2");
        String [] parts = new String[8];
        List<String> crimes = new ArrayList<String>();

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
            versioned = client.get("2");
            for(i =0 ; i<cant ; i++)
            {

                cont2=check(cont2,client,i,versioned,parts,linea,crimes);
            }
            System.out.println(cont2);
    }
}
