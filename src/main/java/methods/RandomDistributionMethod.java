package methods;

import classess.*;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class RandomDistributionMethod {

    public static PlaceServer randomDistribute(DataToDistribute dataToDistribute) {
        PlaceServer result = new PlaceServer();
        List<Server> servers = dataToDistribute.getServers();
        List<InfResource> infResources = dataToDistribute.getInfResources();
        CustomRandom customRandom = new CustomRandom(servers.size());
        infResources = infResources.stream().sorted(Comparator.comparing(InfResource::getVolume).reversed()).collect(Collectors.toList());
        Stack<InfResource> stackResources = new Stack<>();
        stackResources.addAll(infResources);
        InfResource infResource;

        try {

            while (!stackResources.empty()) {
                infResource = stackResources.pop();

                while(true) {
                    int indexServer = customRandom.generateRandom();

                    if (indexServer == -1) {
                        stackResources.push(infResource);
                        result.setCode(0);
                        result.setInfResources(stackResources);
                        result.setServers(servers);
                        return result;
                    }

                    if (servers.get(indexServer).getFree() > infResource.getVolume()) {
                        servers.get(indexServer).addResource(infResource);
                        customRandom.clearList();
                        break;
                    } else {
                        customRandom.addIdServer(indexServer);
                    }
                }
            }
        } catch (Exception ex) {
            result.setCode(-1);
            result.setMessage(ex.getMessage());
            return result;
        }

        result.setCode(0);
        result.setInfResources(stackResources);
        result.setServers(servers);
        return result;
    }
}
