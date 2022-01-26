package interfaces;

import classess.ResultDistributeModel;
import javafx.collections.ObservableList;

/**
 * @author Evsin on 2019-01
 *
 */
public interface ResultDistributeTable {

    /**
     *
     *
     */
    ObservableList<ResultDistributeModel> getResources();

    /**
     * Добавление сервера
     */
    void add(ResultDistributeModel model);

    /**
     * Обновление сервера
     */
    void update(ResultDistributeModel model);

    /**
     * Удаление сервера
=     */
    void delete(ResultDistributeModel model);
}
