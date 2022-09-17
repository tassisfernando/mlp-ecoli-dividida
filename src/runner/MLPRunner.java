package runner;

import model.Database;
import model.MLP;
import utils.FileUtils;

import java.io.IOException;

import static java.lang.Math.abs;
import static utils.FileUtils.*;

public class MLPRunner {

    private static final int N_EPOCAS = 100000;
    private static final StringBuilder errosTreino = new StringBuilder();
    private static final StringBuilder errosTeste = new StringBuilder();
    private static final StringBuilder epocas = new StringBuilder();

    public static void main(String[] args) throws IOException {
        FileUtils.clearFiles();

        final double NI = 0.001;
        final int QTD_H = 2;

        Database dataModel = new Database();
        double[][][] dataTreino = dataModel.getDataTreino();
        double[][][] dataTeste = dataModel.getDataTeste();

        MLP mlp = new MLP(dataTreino[0][0].length, dataTreino[0][1].length, QTD_H, NI);
        double erroEpTreino, erroEpTeste, erroAm;

        for(int e = 0; e < N_EPOCAS; e++) {
            erroEpTreino = 0D;
            for (double[][] sample : dataTreino) {
                double[] x = sample[0];
                double[] y = sample[1];
                double[] out = mlp.treinar(x, y);

                erroAm = sumErro(y, out);
                erroEpTreino += erroAm;
            }

            erroEpTeste = 0D;
            for (double[][] sample : dataTeste) {
                double[] x = sample[0];
                double[] y = sample[1];
                double[] out = mlp.executar(x);

                erroAm = sumErro(y, out);
                erroEpTeste += erroAm;
            }

            System.out.printf("Época: %s\n", (e+1));
            System.out.printf("Erro Treino: %6f\n", erroEpTreino);
            System.out.printf("Erro Teste: %6f\n\n", erroEpTeste);
            escreveArquivos(e+1, erroEpTreino, erroEpTeste);
        }
    }

    private static void escreveArquivos(int epoca, Double erroTreino, Double erroTeste) {
        errosTreino.append(erroTreino).append("\n");
        errosTeste.append(erroTeste).append("\n");
        epocas.append(epoca).append("\n");

        if (epoca == N_EPOCAS) {
            try {
                FileUtils.fileWriter(ARQUIVO_BASE_TREINO, errosTreino.toString());
                FileUtils.fileWriter(ARQUIVO_BASE_TESTE, errosTeste.toString());
                FileUtils.fileWriter(ARQUIVO_EPOCAS, epocas.toString());
            } catch (IOException e) {
                System.out.println("Erro ao escrever arquivo: " + e.getMessage());
            }
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
