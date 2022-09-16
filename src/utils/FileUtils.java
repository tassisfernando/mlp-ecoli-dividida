package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileUtils {

    public final static String ARQUIVO_BASE_TREINO = "erroTreino.txt";
    public final static String ARQUIVO_BASE_TESTE = "erroTeste.txt";

    public static void fileWriter(String arquivo, Double erro, int epoca) throws IOException {
        String conteudoAnterior = fileReader(arquivo);
        FileWriter fw = new FileWriter(arquivo);
        PrintWriter printWriter = new PrintWriter(fw);
        if (!conteudoAnterior.isEmpty()) {
            printWriter.print(conteudoAnterior);
        }
        printWriter.println(epoca + " " + erro);
        printWriter.close();
    }

    private static String fileReader(String nome) throws IOException {
        File arquivo = new File(nome);
        Scanner inArchive = new Scanner(arquivo);
        StringBuilder conteudo = new StringBuilder();

        while (inArchive.hasNext()) {
            conteudo.append(inArchive.nextLine()).append("\n");
        }

        return conteudo.toString();
    }

    public static void clearFiles() throws IOException {
        FileWriter fw = new FileWriter(ARQUIVO_BASE_TREINO);
        fw = new FileWriter(ARQUIVO_BASE_TESTE);
    }
}
