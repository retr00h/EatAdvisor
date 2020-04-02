package EatAdvisor.ristoratori;
import EatAdvisor.Giudizio;

import java.io.*;
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

    private void registraRistorante () {

        String filename = "data/EatAdvisor.dati";
        try {
            FileOutputStream f = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(f);

            // serializzazione oggetto nel file EatAdvisor.dati
            out.writeObject(this);

            out.close();
            f.close();

            System.out.println("Dati inseriti con successo!\n");

        }
        catch(IOException e) {
            System.out.println("Dati non inseriti");
        }

    }

    public static void main(String[] args) {
        System.out.println("Gentile ristoratore, benvenuto in EatAdvisor, versione Ristoratore!\n\n" +
                "Per inserire un ristorante, inserire 1\nPer uscire, inserire 0\n");
        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        boolean valid = false;
        String s = input.nextLine();
        int n = -1;
        if (s.equals("0") || s.equals("1")) {
            n = Integer.parseInt(s);
        } else {
            while (!valid) {
                System.out.println("\nRiprovare.\n");
                s = input.nextLine();
                if (s.equals("0") || s.equals("1")) {
                    n = Integer.parseInt(s);
                    valid = true;
                }
            }
        }

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
                String tipologia = "";

                while (!finish) {
                    System.out.println("\nHa scelto di inserire un ristorante.");
                    System.out.println("Inserire il nome del ristorante: ");

                    valid = false;
                    s = input.nextLine();
                    if (s.matches("^[A-Za-z\\s]+[A-Za-z\\s]*$")) {
                        nome = s;
                    } else {
                        while (!valid) {
                            System.out.println("\nRiprovare.\n");
                            s = input.nextLine();
                            if (s.matches("^[A-Za-z\\s]+[A-Za-z\\s]*$")) {
                                nome = s;
                                valid = true;
                            }
                        }
                    }

                    System.out.println("\nOk!\nInserire la tipologia di indirizzo (via, viale, corso, piazza, ...): ");

                    valid = false;
                    s = input.nextLine().toLowerCase();
                    if (s.matches("^(via|viale|corso|piazza|piazzale|largo|lungolago|" +
                            "lungomare|rotonda|vicolo|vicoletto)$")) {
                        tipoIndirizzo = s;
                    } else {
                        while (!valid) {
                            System.out.println("\nRiprovare.\nI tipi disponibili sono: via, viale, corso, piazza, " +
                                    "piazzale, largo, lungolago, lungomare, rotonda, vicolo, vicoletto.\n");
                            s = input.nextLine().toLowerCase();
                            if (s.matches("^(via|viale|corso|piazza|piazzale|largo|lungolago|" +
                                    "lungomare|rotonda|vicolo|vicoletto)$")) {
                                tipoIndirizzo = s;
                                valid = true;
                            }
                        }
                    }

                    System.out.println("\nOk!\nInserire l'indirizzo del ristorante: ");

                    valid = false;
                    s = input.nextLine().toLowerCase();
                    if (s.matches("^[a-z0-9\\s]{1,128}$")) {
                        nomeIndirizzo = s;
                    } else {
                        while (!valid) {
                            System.out.println("\nRiprovare.\n");
                            s = input.nextLine().toLowerCase();
                            if (s.matches("^[a-z0-9\\s]{1,128}$")) {
                                nomeIndirizzo = s;
                                valid = true;
                            }
                        }
                    }

                    System.out.println("\nOk!\nInserire il numero civico del ristorante (42, 42A, 42B, ...): ");

                    valid = false;
                    s = input.nextLine().toUpperCase().replace(" ", "");
                    if (s.matches("^[0-9]{1,4}[A-Z]|[0-9]{1,4}$")) {
                        civico = s;
                    } else {
                        while (!valid) {
                            System.out.println("\nRiprovare.\n");
                            s = input.nextLine().toUpperCase().replace(" ", "");
                            if (s.matches("^[0-9]{1,4}[A-Z]|[0-9]{1,4}$")) {
                                civico = s;
                                valid = true;
                            }
                        }
                    }

                    System.out.println("\nOk!\nInserire il comune del ristorante (Varese, Milano, Roma, ...): ");

                    valid = false;
                    s = input.nextLine().toLowerCase();
                    if (s.matches("^[a-z'\\s]{1,50}$")) {
                        comune = s;
                    } else {
                        while (!valid) {
                            System.out.println("\nRiprovare.\n");
                            s = input.nextLine().toLowerCase();
                            if (s.matches("^[a-z'\\s]{1,50}$")) {
                                comune = s;
                                valid = true;
                            }
                        }
                    }

                    System.out.println("\nOk!\nInserire la sigla della provincia in cui si trova " +
                            "il comune del ristorante (VA, MI, RM, ...): ");

                    valid = false;
                    s = input.nextLine().toUpperCase();
                    if (s.matches("^[A-Z]{2}$")) {
                        provincia = s;
                    } else {
                        while (!valid) {
                            System.out.println("\nRiprovare.\n");
                            s = input.nextLine().toUpperCase();
                            if (s.matches("^[A-Z]{2}$")) {
                                provincia = s;
                                valid = true;
                            }
                        }
                    }

                    System.out.println("\nOk!\nInserire il CAP del comune " +
                            "dove si trova il ristorante (21100, 20100, 00100, ...): ");

                    valid = false;
                    s = input.nextLine();
                    if (s.matches("^[0-9]{5}$")) {
                        cap = s;
                    } else {
                        while (!valid) {
                            System.out.println("\nRiprovare.\n");
                            s = input.nextLine();
                            if (s.matches("^[0-9]{5}$")) {
                                cap = s;
                                valid = true;
                            }
                        }
                    }

                    System.out.println("\nOk!\nInserire il numero di telefono del ristorante " +
                            "(+39 1234567890, 1234567890, 0332 123456): ");

                    valid = false;
                    s = input.nextLine().replace(" ", "");
                    if (s.matches("^(\\+[0-9]{12}|[0-9]{10})$")) {
                        telefono = s;
                    } else {
                        while (!valid) {
                            System.out.println("\nRiprovare.\n");
                            s = input.nextLine().replace(" ", "");
                            if (s.matches("^(\\+[0-9]{12}|[0-9]{10})$")) {
                                telefono = s;
                                valid = true;
                            }
                        }
                    }

                    System.out.println("\nOk!\nInserire la url del sito web del ristorante: ");

                    valid = false;
                    s = input.nextLine();
                    if (s.matches("^(http://|https://)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$")) {
                        url = s;
                    } else {
                        while (!valid) {
                            System.out.println("\nRiprovare. Per un elenco completo dei formati disponibili, " +
                                    "consultare il manuale a pagina XXX.\n\n");
                            s = input.nextLine();
                            if (s.matches("^(http://|https://)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$")) {
                                url = s;
                                valid = true;
                            }
                        }
                    }

                    System.out.println("\nOk!\nInserire la tipologia del del ristorante (italiano, etnico, fusion): ");

                    valid = false;
                    s = input.nextLine();
                    if (s.matches("^(italiano|Italiano|etnico|Etnico|fusion|Fusion)$")) {
                        tipologia = s;
                    } else {
                        while (!valid) {
                            System.out.println("\nRiprovare.\n");
                            s = input.nextLine();
                            if (s.matches("^(italiano|Italiano|etnico|Etnico|fusion|Fusion)$")) {
                                tipologia = s;
                                valid = true;
                            }
                        }
                    }
                    System.out.println("\nDati inseriti:");
                    System.out.println("Nome: " + nome + "\nIndirizzo: " + tipoIndirizzo + " " + nomeIndirizzo + " " +
                            civico + ", " + comune + ", " + provincia + ", " + cap + "\nTelefono: " + telefono +
                            "\nSito web: " + url + "\nTipologia: " + tipologia + "\n");
                    System.out.println("Inserire 1 per confermare, inserire qualunque altro carattere per reinserire: ");

                    if (input.nextLine().equals("1")) finish = true;
                }

                Ristoratori r = new Ristoratori(nome, tipoIndirizzo, nomeIndirizzo, civico, comune, provincia, cap, telefono, url, tipologia);

                r.registraRistorante();
                break;
        }


//        Ristoratori ris = new Ristoratori("Pino pasticcino", "via", "flora", 146, "legnano", "mi", 20025, "3398335721", "www.google.com", "italiano");
//        ris.registraRistorante();
    }
}