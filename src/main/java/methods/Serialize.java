package methods;


import classess.DataToDistribute;
import classess.InfResource;
import classess.Server;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Serialize {

    DataToDistribute servers;

    public static void main(String[] args) throws IOException {
        List<Server> servers = new ArrayList<>();
        List<InfResource> infResources = new ArrayList<>();
        Server server1 = new Server(1, 2,2.0, "Server1", 1);
        Server server2 = new Server(2, 1,2.0, "Server2", 1);
        Server server3 = new Server(3, 4,2.0, "Server3", 1);
        Server server4 = new Server(4, 5,2.0, "Server4", 1);
        Server server5 = new Server(5, 7,2.0, "Server5", 1);
        Server server6 = new Server(6, 5,2.0, "Server6", 1);

        servers.add(server1);
        servers.add(server2);
        servers.add(server3);
        servers.add(server4);
        servers.add(server5);
        servers.add(server6);

        InfResource infResource1 = new InfResource(1, 3);

        Hashtable<Server, Double> tableRes1 = new Hashtable<>();
        tableRes1.put(server1, 2.41);
        tableRes1.put(server2, 1.21);
        tableRes1.put(server3, 3.2);
        tableRes1.put(server5, 4.2);
        tableRes1.put(server6, 3.2);
        tableRes1.put(server4, 2.5);


        infResource1.setCostSendToServer(tableRes1);

        InfResource infResource2 = new InfResource(2, 20);

        Hashtable<Server, Double> tableRes2 = new Hashtable<>();
        tableRes2.put(server1, 2.21);
        tableRes2.put(server2, 1.16);
        tableRes2.put(server3, 4.1);
        tableRes2.put(server5, 4.2);
        tableRes2.put(server6, 3.2);
        tableRes2.put(server4, 2.5);

        infResource2.setCostSendToServer(tableRes2);

        InfResource infResource3 = new InfResource(3, 5);

        Hashtable<Server, Double> tableRes3 = new Hashtable<>();
        tableRes3.put(server1, 1.12);
        tableRes3.put(server2, 3.14);
        tableRes3.put(server3, 8.1);
        tableRes3.put(server5, 4.2);
        tableRes3.put(server6, 3.2);
        tableRes3.put(server4, 2.5);

        infResource3.setCostSendToServer(tableRes3);


        InfResource infResource4 = new InfResource(4, 7);

        Hashtable<Server, Double> tableRes4 = new Hashtable<>();
        tableRes4.put(server1, 1.2);
        tableRes4.put(server2, 4.2);
        tableRes4.put(server3, 2.6);
        tableRes4.put(server4, 1.12);
        tableRes4.put(server5, 3.14);
        tableRes4.put(server6, 8.1);

        infResource4.setCostSendToServer(tableRes4);


        InfResource infResource5 = new InfResource(5, 9);

        Hashtable<Server, Double> tableRes5 = new Hashtable<>();
        tableRes5.put(server1, 4.31);
        tableRes5.put(server2, 2.1);
        tableRes5.put(server3, 2.1);
        tableRes5.put(server4, 1.2);
        tableRes5.put(server5, 4.2);
        tableRes5.put(server6, 2.6);

        infResource5.setCostSendToServer(tableRes5);


        InfResource infResource6 = new InfResource(6, 2);

        Hashtable<Server, Double> tableRes6 = new Hashtable<>();
        tableRes6.put(server1, 2.4);
        tableRes6.put(server2, 3.2);
        tableRes6.put(server3, 2.0);
        tableRes6.put(server4, 1.2);
        tableRes6.put(server5, 4.2);
        tableRes6.put(server6, 2.6);

        infResource6.setCostSendToServer(tableRes6);

        InfResource infResource7 = new InfResource(1, 3);

        Hashtable<Server, Double> tableRes7 = new Hashtable<>();
        tableRes7.put(server1, 2.41);
        tableRes7.put(server2, 1.21);
        tableRes7.put(server3, 3.2);
        tableRes7.put(server5, 4.2);
        tableRes7.put(server6, 3.2);
        tableRes7.put(server4, 2.5);


        infResource7.setCostSendToServer(tableRes7);

        InfResource infResource8 = new InfResource(2, 20);

        Hashtable<Server, Double> tableRes8 = new Hashtable<>();
        tableRes8.put(server1, 2.21);
        tableRes8.put(server2, 1.16);
        tableRes8.put(server3, 4.1);
        tableRes8.put(server5, 4.2);
        tableRes8.put(server6, 3.2);
        tableRes8.put(server4, 2.5);

        infResource8.setCostSendToServer(tableRes8);

        InfResource infResource9 = new InfResource(3, 5);

        Hashtable<Server, Double> tableRes9 = new Hashtable<>();
        tableRes9.put(server1, 1.12);
        tableRes9.put(server2, 3.14);
        tableRes9.put(server3, 8.1);
        tableRes9.put(server5, 4.2);
        tableRes9.put(server6, 3.2);
        tableRes9.put(server4, 2.5);

        infResource9.setCostSendToServer(tableRes9);


        InfResource infResource10 = new InfResource(4, 7);

        Hashtable<Server, Double> tableRes10 = new Hashtable<>();
        tableRes10.put(server1, 1.2);
        tableRes10.put(server2, 4.2);
        tableRes10.put(server3, 2.6);
        tableRes10.put(server4, 1.12);
        tableRes10.put(server5, 3.14);
        tableRes10.put(server6, 8.1);

        infResource10.setCostSendToServer(tableRes10);


        InfResource infResource11 = new InfResource(5, 9);

        Hashtable<Server, Double> tableRes11 = new Hashtable<>();
        tableRes11.put(server1, 4.31);
        tableRes11.put(server2, 2.1);
        tableRes11.put(server3, 2.1);
        tableRes11.put(server4, 1.2);
        tableRes11.put(server5, 4.2);
        tableRes11.put(server6, 2.6);

        infResource11.setCostSendToServer(tableRes11);


        InfResource infResource12 = new InfResource(6, 2);

        Hashtable<Server, Double> tableRes12 = new Hashtable<>();
        tableRes12.put(server1, 2.4);
        tableRes12.put(server2, 3.2);
        tableRes12.put(server3, 2.0);
        tableRes12.put(server4, 1.2);
        tableRes12.put(server5, 4.2);
        tableRes12.put(server6, 2.6);

        infResource12.setCostSendToServer(tableRes12);

        infResources.add(infResource1);
        infResources.add(infResource2);
        infResources.add(infResource3);
        infResources.add(infResource4);
        infResources.add(infResource5);
        infResources.add(infResource6);
        infResources.add(infResource7);
        infResources.add(infResource8);
        infResources.add(infResource9);
        infResources.add(infResource10);
        infResources.add(infResource11);
        infResources.add(infResource12);

        DataToDistribute dataToDistribute = new DataToDistribute(servers, infResources, 0.7, 0.3);

//        ArrayList<Server> serverList = new ArrayList<Server>(dataToDistribute.getServers());
//        ArrayList<InfResource> resourceList = new ArrayList<InfResource>(dataToDistribute.getInfResources());

         Gson json = new GsonBuilder().serializeNulls().create();

//         String allServers = json.toJson(serverList);
//         String allResource = json.toJson(resourceList);
//
//         Type allServersType = new TypeToken<ArrayList<Server>>(){}.getType();
//         Type allResourceType = new TypeToken<ArrayList<InfResource>>(){}.getType();

        String dataToDist = json.toJson(dataToDistribute);

        FileWriter file = new FileWriter("servers.json");
        try  {
            file.append(dataToDist);
//            file.write("\n");
//            file.append(allResource);
            System.out.println("Successfully Copied JSON Object to File...");
        } catch (IOException e){
            e.printStackTrace();
        }
        finally {
            file.flush();
            file.close();
        }


        List<String> strings = Files.readAllLines(Paths.get("servers.json"), StandardCharsets.UTF_8);


        DataToDistribute dataToDistribute1 = json.fromJson(strings.get(0), DataToDistribute.class);

        for (int i = 0; i < dataToDistribute1.getInfResources().size(); i++) {
            dataToDistribute1.getInfResources().get(i).setServers(dataToDistribute1.getServers());
        }

        int a = 0;

//       ArrayList<InfResource> resourceDeSer = json.fromJson(strings.get(1), allResourceType);

        //System.out.println(resourceDeSer.get(2));

    }
}
