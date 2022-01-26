package classess;

import java.util.List;

public class ResultSimulatePath {
    private String path;
    private double weigthPath;
    private List<String> separateSection;

    public ResultSimulatePath(String path, double weigthPath, List<String> separateSection) {
        this.path = path;
        this.weigthPath = weigthPath;
        this.separateSection = separateSection;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getWeigthPath() {
        return weigthPath;
    }

    public void setWeigthPath(double weigthPath) {
        this.weigthPath = weigthPath;
    }

    public List<String> getSeparateSection() {
        return separateSection;
    }

    public void setSeparateSection(List<String> separateSection) {
        this.separateSection = separateSection;
    }
}
