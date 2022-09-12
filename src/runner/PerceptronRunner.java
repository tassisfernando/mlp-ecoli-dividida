package runner;

import model.Database;
import model.MLP;
import model.Perceptron;

import static java.lang.Math.abs;

public class PerceptronRunner {

    public static void main(String[] args) {
        final double NI = 0.001;
        final int N_EPOCAS = 10000;
        final int QTD_H = 2;

        Database dataModel = new Database();
        double[][][] database = dataModel.getData();

        MLP mlp = new MLP(database[0][0].length, database[0][1].length, QTD_H, NI);
        double erroEp = 0D, erroAm;

        for(int e = 0; e < N_EPOCAS; e++) {
            erroEp = 0D;
            for (double[][] sample : database) {
                double[] x = sample[0];
                double[] y = sample[1];
                double[] out = mlp.treinar(x, y);

                erroAm = sumErro(y, out);
                erroEp += erroAm;
            }

            System.out.printf("Época: %s - Erro: %6f\n", (e+1), erroEp);
        }
    }

    private static double sumErro(double[] y, double[] out) {
        double sum = 0D;
        for(int i = 0; i < y.length; i++) {
            sum += abs(y[i] - out[i]);
        }

        return sum;
    }
}
