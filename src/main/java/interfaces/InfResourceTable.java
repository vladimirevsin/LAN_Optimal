package interfaces;

import classess.InfResource;
import javafx.collections.ObservableList;

/**
 * @author Evsin on 2019-01
 *
 */
public interface InfResourceTable {

    /**
     *
     * @return
     */
    ObservableList<InfResource> getResources();

    /**
     * Добавление сервера
     * @param infResource
     */
    void add(InfResource infResource);

    /**
     * Обновление сервера
     * @param infResource
     */
    void update(InfResource infResource);

    /**
     * Удаление сервера
     * @param infResource
     */
    void delete(InfResource infResource);
}
