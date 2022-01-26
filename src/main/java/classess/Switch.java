package classess;

import java.util.Random;

/**
 * Коммутатор
 */
public class Switch extends Device {

    /**
     * Иденификатор коммутатора
     */
    private int idSwitch;

    /**
     * НаименованиеКоммутатора
     */
    private String nameSwitch;
    private int TimeToWorkOnSwitch;

    /**
     *
     * @param idSwitch Иденификатор коммутатора
     * @param timeToWorkOnSwitch время обработки на коммутаторе
     * @param nameSwitch Наименование коммутатора, если имя равно "", то ставится стандартное имя
     */
    public Switch(int idSwitch, int timeToWorkOnSwitch, String nameSwitch) {
        super(idSwitch, nameSwitch, timeToWorkOnSwitch);
        this.idSwitch = idSwitch;
        this.nameSwitch = nameSwitch == ""? "Switch" + String.valueOf(idSwitch) : nameSwitch;
        TimeToWorkOnSwitch = timeToWorkOnSwitch;
    }

    /**
     *
     * @param idSwitch Иденификатор коммутатора
     * @param timeToWorkOnSwitch время обработки на коммутаторе
     */
//    public Switch(int idSwitch, int timeToWorkOnSwitch) {
//        super(idSwitch, "Switch" + idSwitch, timeToWorkOnSwitch);
//        this.idSwitch = idSwitch;
//        TimeToWorkOnSwitch = timeToWorkOnSwitch;
//        this.nameSwitch = "Switch" + String.valueOf(idSwitch);
//    }

    /**
     * Возвращает время обработки на коммутаторе
     * @param workTimeError Погрешность времени обработки
     * @return
     */
    public double getTimeToWork(int workTimeError) {
        Random random = new Random();
        return this.TimeToWorkOnSwitch + random.nextInt(2 * workTimeError) - workTimeError;
    }

    public int getIdSwitch() {
        return idSwitch;
    }

    public void setIdSwitch(int idSwitch) {
        this.idSwitch = idSwitch;
    }

    public String getNameSwitch() {
        return nameSwitch;
    }

    public void setNameSwitch(String nameSwitch) {
        this.nameSwitch = nameSwitch;
    }

    public int getTimeToWorkOnSwitch() {
        return TimeToWorkOnSwitch;
    }

    public void setTimeToWorkOnSwitch(int timeToWorkOnSwitch) {
        TimeToWorkOnSwitch = timeToWorkOnSwitch;
    }

    @Override
    public String toString() {
        return nameSwitch;
    }

    public String toString2() {
        return "Switch{" +
                "idSwitch=" + idSwitch +
                ", nameSwitch='" + nameSwitch + '\'' +
                ", TimeToWorkOnSwitch=" + TimeToWorkOnSwitch +
                ", idDevice=" + idDevice +
                ", nameDevice='" + nameDevice + '\'' +
                ", timeToWorkTransaction=" + timeToWorkTransaction +
                '}';
    }
}
