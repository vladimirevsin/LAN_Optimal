package classess;

import java.util.List;

public class GeneticAlgoritmConstraint {

    private double[] constraintParams;
    private EnumTypeEqual enumTypeEqual;
    private double value;

    public GeneticAlgoritmConstraint(double[] constraintParams, EnumTypeEqual enumTypeEqual, double value) {
        this.constraintParams = constraintParams;
        this.enumTypeEqual = enumTypeEqual;
        this.value = value;
    }

    public boolean checkConstraint(List<Integer> representation) throws Exception {
        if (representation.size() != constraintParams.length) throw new Exception("Размерности не совпадают");
        double leftSide = 0.0;

        for(int i = 0; i < representation.size(); i++)
            leftSide += representation.get(i) * constraintParams[i];

        switch (enumTypeEqual) {
            case EQ: return leftSide == value;
            case LEQ: return leftSide <= value;
            case GEQ: return leftSide >= value;
        }

        return false;
    }

    public double[] getConstraintParams() {
        return constraintParams;
    }

    public void setConstraintParams(double[] constraintParams) {
        this.constraintParams = constraintParams;
    }

    public EnumTypeEqual getEnumTypeEqual() {
        return enumTypeEqual;
    }

    public void setEnumTypeEqual(EnumTypeEqual enumTypeEqual) {
        this.enumTypeEqual = enumTypeEqual;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
