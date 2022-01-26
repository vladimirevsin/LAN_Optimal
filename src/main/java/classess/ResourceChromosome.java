package classess;

import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;
import org.apache.commons.math3.genetics.BinaryChromosome;

import java.util.Arrays;
import java.util.List;

public class ResourceChromosome extends BinaryChromosome {
    private List<Double> functionParameters;
    public List<Integer> representation;

    public ResourceChromosome(List<Integer> representation, List<Double> functionParameters) throws InvalidRepresentationException {
        super(representation);
        this.functionParameters = functionParameters;
        this.representation = representation;
    }

    public ResourceChromosome(Integer[] representation, List<Double> functionParameters) {
        super(representation);
        this.functionParameters = functionParameters;
        this.representation = Arrays.asList(representation);
    }

    @Override
    protected void checkValidity(List<Integer> chromosomeRepresentation) throws InvalidRepresentationException {

    }

    @Override
    public AbstractListChromosome<Integer> newFixedLengthChromosome(List<Integer> chromosomeRepresentation) {
        return new ResourceChromosome(chromosomeRepresentation, functionParameters);
    }



    @Override
    public double fitness() {
        double result = 0.0;
        final List<Integer> representation = getRepresentation();
        for(int i = 0; i < functionParameters.size(); i++)
            result += functionParameters.get(i) * representation.get(i);

        return result;
    }
}
