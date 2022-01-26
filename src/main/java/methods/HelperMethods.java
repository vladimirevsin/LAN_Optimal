package methods;

import classess.DataToDistribute;
import classess.InfResource;
import classess.Server;

import java.util.List;

public class HelperMethods {

    /**
     * Создание массива чисел целевой функции
     * @param resourceData
     * @return
     */
    public static double[] createParameterGoalFunction(DataToDistribute resourceData) {
        final List<Server> servers = resourceData.getServers();
        final List<InfResource> infResources = resourceData.getInfResources();

        double[] result = new double[infResources.size() * servers.size()];
        for(int i = 0; i < infResources.size(); i++) {
            for (int j = 0; j < servers.size(); j++) {
                System.out.println(i* servers.size() + j);
                result[i* servers.size() + j] = infResources.get(i).getVolume() + servers.get(j).getCost() * resourceData.getKoefCostPlaceOnServer() +
                       infResources.get(i).getCostSendToServer().get(servers.get(j)) * resourceData.getKoefCostSendToServer();
            }
        }

        return result;
    }

    /**
     * Создание массива нулевых элементов
     * @param ans
     * @param length
     * @return
     */
    public static double[] createZeroDoubleArray(int ans, int length) {
        double[] result = new double[length];
        for (int i = 0; i < result.length; i++){
            result[i] = i == ans? 1 : 0;
        }

        return result;
    }

}
