/*
 *
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


public class Seleccionatodo
{

    public static void main(String[] args)
    {
        //stringStoreExample();
        stringStoreExample();
    }
    public static void stringStoreExample()
    {
        System.out.println("==============String store example=================");

        String bootstrapUrl = "tcp://localhost:6666";
        StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));

        StoreClient<String, String> client = factory.getStoreClient("test");


       Integer i = 2;
       Integer j = 3;
       Versioned<String> versioned = client.get("1");
       while (versioned != null)  {

        String cont = String.valueOf(versioned.getValue());
        System.out.println(cont);
        i++;
        versioned = client.get(Integer.toString(i));

      }
    }
}
