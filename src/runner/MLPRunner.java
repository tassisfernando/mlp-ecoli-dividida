package runner;

import model.Database;
import model.MLP;
import utils.FileUtils;

import java.io.IOException;

import static java.lang.Math.abs;
import static utils.FileUtils.ARQUIVO_BASE_TESTE;
import static utils.FileUtils.ARQUIVO_BASE_TREINO;

public class MLPRunner {

    public static void main(String[] args) throws IOException {
        FileUtils.clearFiles();

        final double NI = 0.001;
        final int N_EPOCAS = 10000;
        final int QTD_H = 2;

        Database dataModel = new Database();
        double[][][] database = dataModel.getData();

        MLP mlp = new MLP(database[0][0].length, database[0][1].length, QTD_H, NI);
        double erroEpTreino, erroEpTeste, erroAm;

        for(int e = 0; e < N_EPOCAS; e++) {
            erroEpTreino = 0D;
            for (double[][] sample : database) {
                double[] x = sample[0];
                double[] y = sample[1];
                double[] out = mlp.treinar(x, y);

                erroAm = sumErro(y, out);
                erroEpTreino += erroAm;
            }

            erroEpTeste = 0D;
            for (double[][] sample : database) {
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
        try {
            FileUtils.fileWriter(ARQUIVO_BASE_TREINO, erroTreino, epoca);
            FileUtils.fileWriter(ARQUIVO_BASE_TESTE, erroTeste, epoca);
        } catch (IOException e) {
            System.out.println("Erro ao escrever arquivo: " + e.getMessage());
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
