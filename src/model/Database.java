package model;

import enums.EcoliOutTypeEnum;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Classe para ler a base de dados Ecoli
 * Dados de saída:
 * cp
 * im
 * imS
 * imL
 * imU
 * om
 * omL
 * pp
 *
 */
public class Database {
    private double[][][] data;
    private double[] input;
    private double[] output;

    public Database() {
        readData();
    }

    private void readData() {
        data = new double[336][7][];
        input = new double[7];
        output = new double[3];

        try {
            File file = new File("ecoli.data");
            Scanner scn = new Scanner(file);
            int i = 0;

            while (scn.hasNext()) {
                String row = scn.nextLine();
                readRow(row);
                data[i][0] = input;
                data[i][1] = output;
                i++;
            }

            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("Erro: arquivo com dados não encontrado.");
        }
    }

    private void readRow(String row) {
        List<String> stringRow = Arrays.stream(row.split(" ")).collect(Collectors.toList());
        removeInvalidInputs(stringRow);

        output = EcoliOutTypeEnum.getValuebyName(stringRow.get(7).toUpperCase());
        stringRow.remove(7);
        input = stringRow.stream().map(String::trim).mapToDouble(Double::parseDouble).toArray();
    }

    private void removeInvalidInputs(List<String> stringRow) {
        stringRow.removeIf(r -> r.contains("ECOLI"));
        stringRow.removeIf(String::isEmpty);
    }

    public double[][][] getData() {
        return data;
    }
}
