// Fabio Cirelli, matricola 740482, sede Varese

package EatAdvisor.ristoratori;

import EatAdvisor.EatAdvisor;
import EatAdvisor.Giudizio;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Ristoratori extends EatAdvisor implements java.io.Serializable {

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
    private Giudizio[] giudizi = null;

    /**
     * Costruttore.
     * Costruisce l'oggetto Ristoratori specificando nome, tipoIndirizzo, nomeIndirizzo, civico, comune,
     * provincia, cap, telefono, url, tipologia
     */
    public Ristoratori(String nome, String tipoIndirizzo, String nomeIndirizzo, String civico, String comune,
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

    /**
     * @return nome del ristorante
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return tipoIndirizzo del ristorante
     */
    public String getTipoIndirizzo() {
        return tipoIndirizzo;
    }

    /**
     * @return nomeIndirizzo del ristorante
     */
    public String getNomeIndirizzo() {
        return nomeIndirizzo;
    }

    /**
     * @return civico del ristorante
     */
    public String getCivico() {
        return civico;
    }

    /**
     * @return comune del ristorante
     */
    public String getComune() {
        return comune;
    }

    /**
     * @return provincia del ristorante
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * @return cap del ristorante
     */
    public String getCap() {
        return cap;
    }

    /**
     * @return telefono del ristorante
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @return url del ristorante
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return tipologia del ristorante
     */
    public String getTipologia() {
        return tipologia;
    }

    /**
     * @return array giudizi del ristorante
     */
    public Giudizio[] getGiudizi() {
        return giudizi;
    }

    /**
     * Se l'array dei giudizi del ristorante non esiste, lo crea e aggiunge il giudizio.
     * Altrimenti crea un nuovo array di giudizi, aggiunge il giudizio "in testa", e poi aggiunge
     * i giudizi precedenti.
     *
     * @param g giudizio da aggiungere al ristorante invocante
     */
    public void addGiudizio(Giudizio g) {
        if (giudizi == null) {
            giudizi = new Giudizio[1];
            giudizi[0] = g;
        } else {
            Giudizio[] newGiudizi = new Giudizio[giudizi.length + 1];
            for (int i = 0; i < newGiudizi.length; i++) {
                if (i == 0) {
                    newGiudizi[i] = g;
                } else {
                    newGiudizi[i] = giudizi[i - 1];
                }
            }
            giudizi = newGiudizi;
        }
    }

    /**
     * Controlla che il file EatAdvisor.dati esista. Se esiste, legge gli i ristoranti gia' salvati e controlla
     * che il ristorante invocante non sia gia' presente.
     * Se non lo e' lo aggiunge ai ristoranti gia' presenti e li serializza.
     * <p>
     * Se il file non esiste, serializza i dati del ristorante.
     */
    private void registraRistorante() {
        String filename = "data" + File.separator + "EatAdvisor.dati";
        File f = new File(filename);

        File directory = new File("data/");
        if (!directory.exists()) {
            directory.mkdir();
        }

        if (f.exists() && !f.isDirectory()) {
            try {
                // lettura arraylist ristoratori da EatAdvisor.dati
                FileInputStream fileInput = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileInput);

                ArrayList<Ristoratori> ristoratori = (ArrayList<Ristoratori>) in.readObject();
                in.close();
                fileInput.close();


                // se il nome del ristorante invocante e' gia inserito e se l'indirizzo e' lo stesso
                // stampa un errore e interrompe il ciclo
                boolean ok = true;
                for (Ristoratori r : ristoratori) {
                    if (r.getNome().equals(nome) && r.getTipoIndirizzo().equals(tipoIndirizzo) && r.getNomeIndirizzo().equals(nomeIndirizzo) &&
                            r.getCivico().equals(civico) && r.getComune().equals(comune)) {
                        System.out.println("E' gia' presente un ristorante con questo nome a questo indirizzo.\n");
                        ok = false;
                        break;
                    }
                }

                if (ok) {
                    ristoratori.add(this);
                }

                sortRistorantiNome(ristoratori);

                FileOutputStream fileOutput = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(fileOutput);

                // serializzazione oggetto nel file EatAdvisor.dati
                out.writeObject(ristoratori);

                out.close();
                fileOutput.close();

                System.out.println("Dati inseriti con successo!\n");

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Dati non inseriti");
            }
        } else {
            try {
                ArrayList<Ristoratori> ristoratori = new ArrayList<Ristoratori>();
                ristoratori.add(this);

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

    /**
     * Controlla che il file EatAdvisor.dati esista.
     * Se esiste cerca il ristorante invocante tra quelli serializzati, lo aggiorna e serializza i dati aggiornati.
     * Questo metodo e' utilizzato solo quando viene inserito un nuovo giudizio.
     */
    public void aggiorna() {
        String filename = "data" + File.separator + "EatAdvisor.dati";
        File f = new File(filename);
        if (f.exists() && !f.isDirectory()) {
            try {
                // lettura arraylist ristoratori da EatAdvisor.dati
                FileInputStream fileInput = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileInput);

                ArrayList<Ristoratori> ristoratori = (ArrayList<Ristoratori>) in.readObject();

                in.close();
                fileInput.close();

                // trova e aggiorna le informazioni del ristorante invocante
                for (int i = 0; i < ristoratori.size(); i++) {
                    if (ristoratori.get(i).getNome().equals(nome)) {
                        ristoratori.set(i, this);
                    }
                }

                FileOutputStream fileOutput = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(fileOutput);

                // serializzazione oggetto nel file EatAdvisor.dati
                out.writeObject(ristoratori);

                out.close();
                fileOutput.close();

                System.out.println("Giudizio inserito con successo!\n");

            } catch (Exception e) {
                System.out.println("Giudizio non inserito");
            }
        } else {
            System.out.println("Qualcosa e' andato storto e non e' stato possibile inserire il giudizio.");
        }
    }

    public static void main(String[] args) {
        // ripropone il "form" per aggiungere un ristorante finche l'utente non inserisce 0 quando richiesto
        boolean finish = false;

        while(!finish) {
            System.out.println("Gentile ristoratore, benvenuto in EatAdvisor, versione Ristoratore!\n\n" +
                    "Per inserire un ristorante, inserire 1\nPer uscire, inserire 0\n");
            Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
            int n = Integer.parseInt(input(input, 0));

            switch (n) {
                case 0: finish = true; break;
                case 1:
                    boolean ok = false;
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

                    while (!ok) {
                        System.out.println("\nHa scelto di inserire un ristorante.");
                        System.out.println("Inserire il nome del ristorante: ");
                        nome = input(input, 2);
                        System.out.println("\nOk!\nInserire la tipologia di indirizzo (via, viale, corso, piazza, ...): ");
                        tipoIndirizzo = input(input, 3);
                        System.out.println("\nOk!\nInserire l'indirizzo del ristorante: ");
                        nomeIndirizzo = input(input, 4);
                        System.out.println("\nOk!\nInserire il numero civico del ristorante (42, 42A, 42B, ...): ");
                        civico = input(input, 5);
                        System.out.println("\nOk!\nInserire il comune del ristorante (Varese, Milano, Roma, ...): ");
                        comune = input(input, 6);
                        System.out.println("\nOk!\nInserire la sigla della provincia in cui si trova " +
                                "il comune del ristorante (VA, MI, RM, ...): ");
                        provincia = input(input, 7);
                        System.out.println("\nOk!\nInserire il CAP del comune " +
                                "dove si trova il ristorante (21100, 20100, 00100, ...): ");
                        cap = input(input, 8);
                        System.out.println("\nOk!\nInserire il numero di telefono del ristorante " +
                                "(+39 1234567890, 1234567890, 0332 123456): ");
                        telefono = input(input, 9);
                        System.out.println("\nOk!\nInserire la url del sito web del ristorante: ");
                        url = input(input, 10);
                        System.out.println("\nOk!\nInserire la tipologia del ristorante (italiano, etnico, fusion): ");
                        tipologiaRistorante = input(input, 11);

                        System.out.println("\nDati inseriti:");
                        System.out.println("Nome: " + nome + "\nIndirizzo: " + tipoIndirizzo + " " + nomeIndirizzo + " " +
                                civico + ", " + comune + ", " + provincia + ", " + cap + "\nTelefono: " + telefono +
                                "\nSito web: " + url + "\nTipologia: " + tipologiaRistorante + "\n");
                        System.out.println("Inserire 1 per confermare, inserire qualunque altro carattere per reinserire: ");

                        if (input.nextLine().equals("1")) ok = true;
                    }

                    Ristoratori r = new Ristoratori(nome, tipoIndirizzo, nomeIndirizzo, civico, comune, provincia, cap, telefono, url, tipologiaRistorante);

                    r.registraRistorante();
                    break;
            }
        }

    }
}