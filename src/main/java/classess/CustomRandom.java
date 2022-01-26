package classess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomRandom {
    /**
     * Идентификаторы занятых серверов
     */
    private List<Integer> idServers;

    /**
     * Количество серверов
     */
    private int countServer;

    public CustomRandom(int countServer) {
        this.countServer = countServer;
        this.idServers = new ArrayList<>();
    }

    /**
     * Для тестирования
     * @param idServers
     * @param countServer
     */
    public CustomRandom(List<Integer> idServers, int countServer) {
        this.idServers = idServers;
        this.countServer = countServer;
    }

    public void addIdServer(int id) {
        idServers.add(id);
    }

    public void clearList() {
        idServers.clear();
    }

    public int generateRandom() {

        if (countServer == idServers.size()) return -1;

        Random rand = new Random();
        int random = rand.nextInt(countServer);
        while(idServers.contains(random)) {
            random = rand.nextInt(countServer);
        }

        return random;
    }
}
