package ristoratori;

import java.io.File;

public class Ristoratori {

    private void registraRistorante(String nomeR, String via, String nomeI, String civico, String comune,
                                    String provincia, String cap, String telefono, String url, String tipologia) {
        try {
            File f = new File("../../data/EatAdvisor.dati");
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {

    }
}