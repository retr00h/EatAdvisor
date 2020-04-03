package EatAdvisor.ristoratori;
import EatAdvisor.EatAdvisor;
import EatAdvisor.Giudizio;
import EatAdvisor.clienti.Clienti;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Ristoratori implements java.io.Serializable {

    private String nome;
    private String tipoIndirizzo;
    private String nomeIndirizzo;
    private String civico;
    private String comune;
    private String provincia;
    private String cap;
    private String telefono;
    private String url;
    private String tipologia;
    private Stack<Giudizio> giudizi = null;

    public Ristoratori (String nome, String tipoIndirizzo, String nomeIndirizzo, String civico, String comune,
                       String provincia, String cap, String telefono, String url, String tipologia) {

        this.nome = nome;
        this.tipoIndirizzo = tipoIndirizzo;
        this.nomeIndirizzo = nomeIndirizzo;
        this.civico = civico;
        this.comune = comune;
        this.provincia = provincia;
        this.cap = cap;
        this.telefono = telefono;
        this.url = url;
        this.tipologia = tipologia;
    }

    public String getNome() {
        return nome;
    }

    public String getTipoIndirizzo() {
        return tipoIndirizzo;
    }

    public String getNomeIndirizzo() {
        return nomeIndirizzo;
    }

    public String getCivico() {
        return civico;
    }

    public String getComune() {
        return comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCap() {
        return cap;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getUrl() {
        return url;
    }

    public String getTipologia() {
        return tipologia;
    }

    public Stack<Giudizio> getGiudizi() {
        return giudizi;
    }

    private void registraRistorante () {
        String filename = "data/EatAdvisor.dati";
        File f = new File(filename);
        if (f.exists() && !f.isDirectory()) {
            try {
                // lettura array ristoratori da EatAdvisor.dati
                FileInputStream fileInput = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileInput);

                Ristoratori[] ristoratoriTemp = (Ristoratori[]) in.readObject();

                in.close();
                fileInput.close();

                Ristoratori[] ristoratori = new Ristoratori[ristoratoriTemp.length+1];

                for (int i = 0; i < ristoratoriTemp.length; i++) {
                    if (ristoratoriTemp[i].nome.equals(this.nome) && ristoratoriTemp[i].tipoIndirizzo.equals(this.tipoIndirizzo) &&
                            ristoratoriTemp[i].nomeIndirizzo.equals(this.nomeIndirizzo) &&
                            ristoratoriTemp[i].civico.equals(this.civico) && ristoratoriTemp[i].comune.equals(this.comune)) {
                        System.out.println("E' gia' presente un ristorante con questo nome a questo indirizzo.\n");
                        throw new Exception();
                    } else {
                        ristoratori[i] = ristoratoriTemp[i];
                    }
                }

                // array ristoratori deserializzato

                ristoratori[ristoratori.length-1] = this;
                EatAdvisor.sortRistorantiNome(ristoratori);

                FileOutputStream fileOutput = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(fileOutput);

                // serializzazione oggetto nel file EatAdvisor.dati
                out.writeObject(ristoratori);

                out.close();
                fileOutput.close();

                System.out.println("Dati inseriti con successo!\n");

            } catch (Exception e) {
                System.out.println("Dati non inseriti");
            }
        } else {
            try {
                Ristoratori[] ristoratori = new Ristoratori[1];
                ristoratori[0] = this;

                FileOutputStream fileOutput = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(fileOutput);

                // serializzazione oggetto nel file EatAdvisor.dati
                out.writeObject(ristoratori);

                out.close();
                fileOutput.close();

                System.out.println("Dati inseriti con successo!\n");

            } catch (Exception e) {
                System.out.println("Dati non inseriti");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Gentile ristoratore, benvenuto in EatAdvisor, versione Ristoratore!\n\n" +
                "Per inserire un ristorante, inserire 1\nPer uscire, inserire 0\n");
        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = Integer.parseInt(EatAdvisor.input(input, 0));

        switch (n) {
            case 0: break;
            case 1:
                boolean finish = false;
                String nome = "";
                String tipoIndirizzo = "";
                String nomeIndirizzo = "";
                String civico = "";
                String comune = "";
                String provincia = "";
                String cap = "";
                String telefono = "";
                String url = "";
                String tipologiaRistorante = "";

                while (!finish) {
                    System.out.println("\nHa scelto di inserire un ristorante.");
                    System.out.println("Inserire il nome del ristorante: ");
                    nome = EatAdvisor.input(input, 2);
                    System.out.println("\nOk!\nInserire la tipologia di indirizzo (via, viale, corso, piazza, ...): ");
                    tipoIndirizzo = EatAdvisor.input(input, 3);
                    System.out.println("\nOk!\nInserire l'indirizzo del ristorante: ");
                    nomeIndirizzo = EatAdvisor.input(input, 4);
                    System.out.println("\nOk!\nInserire il numero civico del ristorante (42, 42A, 42B, ...): ");
                    civico = EatAdvisor.input(input, 5);
                    System.out.println("\nOk!\nInserire il comune del ristorante (Varese, Milano, Roma, ...): ");
                    comune = EatAdvisor.input(input, 6);
                    System.out.println("\nOk!\nInserire la sigla della provincia in cui si trova " +
                            "il comune del ristorante (VA, MI, RM, ...): ");
                    provincia = EatAdvisor.input(input, 7);
                    System.out.println("\nOk!\nInserire il CAP del comune " +
                            "dove si trova il ristorante (21100, 20100, 00100, ...): ");
                    cap = EatAdvisor.input(input, 8);
                    System.out.println("\nOk!\nInserire il numero di telefono del ristorante " +
                            "(+39 1234567890, 1234567890, 0332 123456): ");
                    telefono = EatAdvisor.input(input, 9);
                    System.out.println("\nOk!\nInserire la url del sito web del ristorante: ");
                    url = EatAdvisor.input(input, 10);
                    System.out.println("\nOk!\nInserire la tipologia del del ristorante (italiano, etnico, fusion): ");
                    tipologiaRistorante = EatAdvisor.input(input, 11);

                    System.out.println("\nDati inseriti:");
                    System.out.println("Nome: " + nome + "\nIndirizzo: " + tipoIndirizzo + " " + nomeIndirizzo + " " +
                            civico + ", " + comune + ", " + provincia + ", " + cap + "\nTelefono: " + telefono +
                            "\nSito web: " + url + "\nTipologia: " + tipologiaRistorante + "\n");
                    System.out.println("Inserire 1 per confermare, inserire qualunque altro carattere per reinserire: ");

                    if (input.nextLine().equals("1")) finish = true;
                }

                Ristoratori r = new Ristoratori(nome, tipoIndirizzo, nomeIndirizzo, civico, comune, provincia, cap, telefono, url, tipologiaRistorante);

                r.registraRistorante();
                break;
        }


//        Ristoratori ris = new Ristoratori("Pino pasticcino", "via", "flora", 146, "legnano", "mi", 20025, "3398335721", "www.google.com", "italiano");
//        ris.registraRistorante();
    }
}