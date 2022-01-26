

package interfaces;

import classess.Server;
import javafx.collections.ObservableList;

public interface ServerTable {

    /**
     * Получение списка серверов
     * @return
     */
    public ObservableList<Server> getServers();

    /**
     * Добавление сервера
     * @param server
     */
    void add(Server server);

    /**
     * Обновление сервера
     * @param server
     */
    void update(Server server);

    /**
     * Удаление сервера
     * @param server
     */
    void delete(Server server);
}
