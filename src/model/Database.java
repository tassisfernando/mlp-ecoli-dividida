package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Database {
    private Double[][][] data;

    public Database() {

    }

    private void readData() {
        try {
            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            StringBuilder message = new StringBuilder(" ");
            while (s.hasNext()) {
                message.append(s.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro: arquivo com dados não encontrado.");
        }
    }
}
