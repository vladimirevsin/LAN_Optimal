package methods;

import classess.DataToDistribute;
import classess.InfResource;
import classess.PlaceServer;
import classess.Server;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BranchAndBoundsMethod {

    /**
     * Распределение ресурсов по серверам методом ветвей и границ
     * @param resourceData
     * @return
     */
    public static PlaceServer distributeResources(DataToDistribute resourceData) {
        LinearObjectiveFunction linearObjectiveFunction = new LinearObjectiveFunction(HelperMethods.createParameterGoalFunction(resourceData), 0);
        Collection<LinearConstraint> constraints = createConstraints(resourceData);

        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(new MaxIter(1000), linearObjectiveFunction, new
                LinearConstraintSet(constraints), GoalType.MINIMIZE, new
                NonNegativeConstraint(true));


        double[] answer = solution.getKey();
        int ans = checkAnswer(answer);
        int countIter = 0;
        int countElement = resourceData.getInfResources().size() * resourceData.getServers().size();
        while (ans != -1) {

            countIter++;
            if (countIter >= 10000) {
                return new PlaceServer(-1, "Solve cannot be found with current source data", resourceData.getServers());
            }

            boolean branch1isComplete = true;
            boolean branch2isComplete = true;
            Collection<LinearConstraint> constraintsBranch1 = new ArrayList<>();
            Collection<LinearConstraint> constraintsBranch2 = new ArrayList<>();
            constraintsBranch1.addAll(constraints);
            constraintsBranch2.addAll(constraints);
            PointValuePair solutionBranch1 = null;
            PointValuePair solutionBranch2 = null;
            try {
                constraintsBranch1.add(new LinearConstraint(HelperMethods.createZeroDoubleArray(ans, countElement),
                        Relationship.EQ, 0));

                solutionBranch1 = solver.optimize(new MaxIter(1000), linearObjectiveFunction, new
                        LinearConstraintSet(constraintsBranch1), GoalType.MINIMIZE, new
                        NonNegativeConstraint(true));

            } catch (NoFeasibleSolutionException ex) {
                branch1isComplete = false;
            }

            try {
                constraintsBranch2.add(new LinearConstraint(HelperMethods.createZeroDoubleArray(ans, countElement),
                        Relationship.GEQ, 1));

                solutionBranch2 = solver.optimize(new MaxIter(1000), linearObjectiveFunction, new
                        LinearConstraintSet(constraintsBranch2), GoalType.MINIMIZE, new
                        NonNegativeConstraint(true));

            } catch (NoFeasibleSolutionException ex) {
                branch2isComplete = false;
            }

            if (!(branch1isComplete || branch2isComplete)) {
                return new PlaceServer(-1, "Решение не может быть обнаружено по заданным условиям", resourceData.getServers());
            } else if (!branch1isComplete) {
                answer = solutionBranch2.getPoint();
                constraints = constraintsBranch2;
                ans = checkAnswer(answer);
            } else if (!branch2isComplete) {
                answer = solutionBranch1.getPoint();
                constraints = constraintsBranch1;
                ans = checkAnswer(answer);
            } else if (solutionBranch1.getValue() < solutionBranch2.getValue()) {
                answer = solutionBranch2.getPoint();
                constraints = constraintsBranch2;
                ans = checkAnswer(answer);
            } else {
                answer = solutionBranch1.getPoint();
                constraints = constraintsBranch1;
                ans = checkAnswer(answer);
            }
        }

        for (int i = 0; i < answer.length; i++) {

            if (answer[i] == 1.0) {
                final int indexResource = (int)Math.floor(i / resourceData.getServers().size());
                final int indexServer = i - indexResource * resourceData.getServers().size();
                resourceData.getServers().get(indexServer).addResource(resourceData.getInfResources().get(indexResource));
            }
        }

        return new PlaceServer(0, "Ok", resourceData.getServers());
    }

    /**
     * Создание ограничений
     * @param resourceData
     * @return
     */
    public static Collection<LinearConstraint> createConstraints(DataToDistribute resourceData) {
        final List<Server> servers = resourceData.getServers();
        final List<InfResource> infResources = resourceData.getInfResources();

        Collection<LinearConstraint> constraints = new
                ArrayList<LinearConstraint>();

        for (int i = 0; i < servers.size(); i++) {

            double[] paramConstraints = HelperMethods.createZeroDoubleArray(-1, infResources.size() * servers.size());

            for(int j = 0; j < infResources.size(); j++) {
                paramConstraints[j * servers.size() + i] = infResources.get(j).getVolume();
            }

            constraints.add(new LinearConstraint(paramConstraints,
                    Relationship.LEQ, servers.get(i).getMaxCapacity()));
        }

        for (int i = 0; i < infResources.size(); i++) {
            double[] paramConstraints = HelperMethods.createZeroDoubleArray(-1, infResources.size() * servers.size());

            for (int j = 0; j < servers.size(); j++) {
                paramConstraints[i * servers.size() + j] =  infResources.get(i).getVolume();
            }

            constraints.add(new LinearConstraint(paramConstraints,
                    Relationship.EQ, infResources.get(i).getVolume()));
        }

        return constraints;

    }

    /**
     * Проверка решения
     * @param answers
     * @return
     */
    private static int checkAnswer(double[] answers) {
        int index = -1;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] - Math.round(answers[i]) != 0) {
                index = i;
                break;
            }
        }

        return index;
    }

}
