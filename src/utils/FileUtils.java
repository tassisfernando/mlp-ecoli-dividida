package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtils {

    public final static String ARQUIVO_BASE_TREINO = "erroTreino.txt";
    public final static String ARQUIVO_BASE_TESTE = "erroTeste.txt";
    public final static String ARQUIVO_EPOCAS = "epocas.txt";

    public static void fileWriter(String arquivo, String conteudo) throws IOException {
        FileWriter fw = new FileWriter(arquivo);
        PrintWriter printWriter = new PrintWriter(fw);
        conteudo = conteudo.replace(".", ",");
        printWriter.println(conteudo);
        printWriter.close();
    }

    public static void clearFiles() throws IOException {
        FileWriter fw = new FileWriter(ARQUIVO_BASE_TREINO);
        fw = new FileWriter(ARQUIVO_BASE_TESTE);
        fw = new FileWriter(ARQUIVO_EPOCAS);
    }
}
