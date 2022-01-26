package classess;

import java.util.Random;

public class Device {

    protected int idDevice;
    protected String nameDevice;
    protected int timeToWorkTransaction;


    public Device(int idDevice, String nameDevice, int timeToWorkTransaction) {
        this.idDevice = idDevice;
        this.nameDevice = nameDevice;
        this.timeToWorkTransaction = timeToWorkTransaction;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public int getTimeToWorkTransaction() {
        return timeToWorkTransaction;
    }

    public void setTimeToWorkTransaction(int timeToWorkTransaction) {
        this.timeToWorkTransaction = timeToWorkTransaction;
    }

    /**
     * Возвращает время обработки на коммутаторе
     * @param workTimeError Погрешность времени обработки
     * @return
     */
    public double getTimeToWork(int workTimeError) {
        Random random = new Random();
        final double result = this.timeToWorkTransaction + random.nextInt(2 * workTimeError) - workTimeError;
        return result <= 0? this.timeToWorkTransaction : result;
    }

    @Override
    public String toString() {
        return nameDevice;
    }
}
