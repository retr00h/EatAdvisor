package EatAdvisor.clienti;

import EatAdvisor.EatAdvisor;
import EatAdvisor.Giudizio;
import EatAdvisor.ristoratori.Ristoratori;

import java.io.*;
import java.util.Scanner;

public class Clienti extends EatAdvisor implements Serializable {

    private String nome;
    private String cognome;
    private String comune;
    private String provincia;
    private String email;
    private String nickname;
    private String password;

    /**
     * Costruttore.
     * Costruisce l'oggetto Clienti specificando nome, cognome, comune,
     * provincia, email, nickname e password
     */
    public Clienti(String nome, String cognome, String comune, String provincia, String email, String nickname, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.comune = comune;
        this.provincia = provincia;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    /**
     * Controlla che il file Utenti.dati esista. Se esiste, legge gli utenti gia' salvati e controlla
     * che l'utente invocante non sia gia' presente.
     * Se non lo e' lo aggiunge agli utenti gia' presenti e li serializza.
     * <p>
     * Se il file non esiste, serializza i dati dell'utente.
     */
    private void registraCliente() {
        String filename = "data/Utenti.dati";
        File f = new File(filename);

        // crea la directory se non esiste
        File directory = new File("data/");
        if (!directory.exists()) {
            directory.mkdir();
        }

        if (f.exists() && !f.isDirectory()) {
            try {
                // lettura array utenti da utenti.dati
                FileInputStream fileInput = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileInput);

                Clienti[] utentiTemp = (Clienti[]) in.readObject();

                in.close();
                fileInput.close();

                Clienti[] utenti = new Clienti[utentiTemp.length + 1];

                // se il nickname dell'utente invocante e' gia inserito, stampa un errore e interrompe
                // l'esecuzione del metodo
                for (int i = 0; i < utentiTemp.length; i++) {
                    if (utentiTemp[i].nickname.equals(this.nickname)) {
                        System.out.println("E' gia' presente un utente con questo nickname.");
                        throw new Exception();
                    } else {
                        utenti[i] = utentiTemp[i];
                    }
                }

                // array utenti deserializzato
                utenti[utenti.length-1] = this;

                FileOutputStream fileOutput = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(fileOutput);

                // serializzazione oggetto nel file utenti.dati
                out.writeObject(utenti);

                out.close();
                fileOutput.close();

                System.out.println("Dati inseriti con successo!\n");

            } catch (Exception e) {
                System.out.println("Dati non inseriti");
            }
        } else {
            try {
                Clienti[] utenti = new Clienti[1];
                utenti[0] = this;

                FileOutputStream fileOutput = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(fileOutput);

                // serializzazione oggetto nel file utenti.dati
                out.writeObject(utenti);

                out.close();
                fileOutput.close();

                System.out.println("Dati inseriti con successo!\n");

            } catch (Exception e) {
                System.out.println("Dati non inseriti");
            }
        }
    }

    /**
     * Metodo statico che visualizza il menu per l'utente non autenticato.
     * Effettua chiamate ai metodi:
     * EatAdvisor.input(Scanner input, int op)
     * EatAdvisor.ricercaComune(String comune)
     * EatAdvisor.ricercaTipologia(String tipologia)
     * EatAdvisor.ricercaNome(String nome)
     * EatAdvisor.ricercaComuneTipologia(String comune, String tipologia)
     * EatAdvisor.visualizzaRistoranti(Ristoratori r)
     * EatAdvisor.selezionaRistorante(Ristoratori[] r, int n)
     * EatAdvisor.visualizzaInfoRistorante(Ristoratori r)
     *
     * @param input scanner da utilizzare per ottenere dati dall'utente
     */
    private static void menuOspite(Scanner input) {
        // ripropone il menu ospite finche l'uitente non inserisce 0 quando richiesto
        boolean finish = false;
        while (!finish) {
            Ristoratori[] r;
            String comune;
            String tipologia;
            String nome;

            System.out.println("Per ricercare un ristorante per comune, inserisci 1.\nPer ricercare un ristorante " +
                    "per tipologia, inserisci 2.\nPer ricercare un ristorante per nome, inserisci 3.\nPer ricercare " +
                    "un ristorante di una tipologia in un comune, inserisci 4.\nPer uscire, inserisci 0.\n");
            int n = Integer.parseInt(input(input, 16));
            switch (n) {
                case 0:
                    finish = true;
                    break;
                case 1:
                    System.out.println("Hai scelto di ricercare un ristorante per comune.");
                    System.out.println("Inserisci il comune del ristorante: ");
                    comune = input(input, 6);
                    r = ricercaComune(comune);
                    if (r == null) {
                        System.out.println("Nessun ristorante trovato con comune " + comune + ".");
                    } else {
                        visualizzaRistoranti(r);
                        System.out.println("Inserisci il numero del ristorante che vuoi selezionare (0 per uscire): ");
                        int sel = Integer.parseInt(input(input, 17));
                        if (sel != 0) {
                            Ristoratori ristorante = selezionaRistorante(r, sel);
                            visualizzaInfoRistorante(ristorante);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Hai scelto di ricercare un ristorante per tipologia.");
                    System.out.println("Inserisci la tipologia del ristorante: ");
                    tipologia = input(input, 11);
                    r = ricercaTipologia(tipologia);
                    if (r == null) {
                        System.out.println("Nessun ristorante trovato con tipologia " + tipologia + ".");
                    } else {
                        visualizzaRistoranti(r);
                        System.out.println("Inserisci il numero del ristorante che vuoi selezionare (0 per uscire): ");
                        int sel = Integer.parseInt(input(input, 17));
                        if (sel != 0) {
                            Ristoratori ristorante = selezionaRistorante(r, sel);
                            visualizzaInfoRistorante(ristorante);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Hai scelto di ricercare un ristorante per nome.");
                    System.out.println("Inserisci il nome del ristorante: ");
                    nome = input(input, 2);
                    r = ricercaNome(nome);
                    if (r == null) {
                        System.out.println("Nessun ristorante trovato con nome " + nome + ".");
                    } else {
                        visualizzaRistoranti(r);
                        System.out.println("Inserisci il numero del ristorante che vuoi selezionare (0 per uscire): ");
                        int sel = Integer.parseInt(input(input, 17));
                        if (sel != 0) {
                            Ristoratori ristorante = selezionaRistorante(r, sel);
                            visualizzaInfoRistorante(ristorante);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Hai scelto di ricercare un ristorante per comune e tipologia.");
                    System.out.println("Inserisci il comune del ristorante: ");
                    comune = input(input, 6);
                    System.out.println("Inserisci il la tipologia del ristorante: ");
                    tipologia = input(input, 11);
                    r = ricercaComuneTipologia(comune, tipologia);
                    if (r == null) {
                        System.out.println("Nessun ristorante trovato con tipologia " + tipologia + " nel comune " + comune + ".");
                    } else {
                        visualizzaRistoranti(r);
                        System.out.println("Inserisci il numero del ristorante che vuoi selezionare (0 per uscire): ");
                        int sel = Integer.parseInt(input(input, 17));
                        if (sel != 0) {
                            Ristoratori ristorante = selezionaRistorante(r, sel);
                            visualizzaInfoRistorante(ristorante);
                        }
                    }
                    break;
            }
        }

    }

    /**
     * Chiede all'utente se ha intenzione di inserire un giudizio tramite l'inserimento di una stringa.
     * Effettua una chiamata al metodo EatAdvisor.input(Scanner input, int op)
     *
     * @param input scanner da utilizzare per ottenere dati dall'utente
     * @return true se e solo se la stringa inserita corrisponde a 1, false altrimenti
     */
    public static boolean aggiungiGiudizio(Scanner input) {
        System.out.println("Se desideri aggiungere un giudizio a questo ristorante, inserisci 1.");
        return Integer.parseInt(input(input, 18)) == 1;
    }

    /**
     * Chiede all'utente di inserire un voto e un commento (opzionale), e aggiunge il nuovo giudizio.
     * Effettua chiamate ai metodi:
     * EatAdvisor.input(Scanner input, int op)
     * Ristoratori.getGiudizi()
     * Ristoratori.addGiudizio()
     * Giudizio(String nickname, int voto, String commento)
     * Ristoratori.aggiorna()
     *
     * @param input scanner da utilizzare per ottenere dati dall'utente
     * @param r     ristoratore selezionato al quale aggiungere il giudizio
     */
    private void giudica(Scanner input, Ristoratori r) {
        System.out.println("Inserisci un voto (min1,2,3,4,5max): ");
        int voto = Integer.parseInt(input(input, 19));
        System.out.println("(Opzionale) inserisci un commento: ");
        String commento = input(input, 20);
        r.addGiudizio(new Giudizio(this.nickname, voto, commento));
        r.aggiorna();
    }

    /**
     * Metodo statico che visualizza il menu per l'utente autenticato.
     * Effettua chiamate ai metodi:
     * EatAdvisor.input(Scanner input, int op)
     * EatAdvisor.ricercaComune(String comune)
     * EatAdvisor.ricercaTipologia(String tipologia)
     * EatAdvisor.ricercaNome(String nome)
     * EatAdvisor.ricercaComuneTipologia(String comune, String tipologia)
     * EatAdvisor.visualizzaRistoranti(Ristoratori r)
     * EatAdvisor.selezionaRistorante(Ristoratori[] r, int n)
     * EatAdvisor.visualizzaInfoRistorante(Ristoratori r)
     * EatAdvisor.aggiungiGiudizio(Scanner input)
     * Clienti.giudica(Scanner input, Ristoratori r)
     *
     * @param input scanner da utilizzare per ottenere dati dall'utente
     */
    public void menuAutenticato(Scanner input) {
        // ripropone il menu autenticato finche l'utente non inserisce 0 quando richiesto
        boolean finish = false;
        while (!finish) {
            Ristoratori[] r;
            String comune;
            String tipologia;
            String nome;

            System.out.println("Per ricercare un ristorante per comune, inserisci 1.\nPer ricercare un ristorante " +
                    "per tipologia, inserisci 2.\nPer ricercare un ristorante per nome, inserisci 3.\nPer ricercare " +
                    "un ristorante di una tipologia in un comune, inserisci 4.\nPer uscire, inserisci 0.\n");
            int n = Integer.parseInt(input(input, 16));
            switch (n) {
                case 0:
                    finish = true;
                    break;
                case 1:
                    System.out.println("Hai scelto di ricercare un ristorante per comune.");
                    System.out.println("Inserisci il comune del ristorante: ");
                    comune = input(input, 6);
                    r = ricercaComune(comune);
                    if (r == null) {
                        System.out.println("Nessun ristorante trovato con comune " + comune + ".");
                    } else {
                        visualizzaRistoranti(r);
                        System.out.println("Inserisci il numero del ristorante che vuoi selezionare (0 per uscire): ");
                        int sel = Integer.parseInt(input(input, 17));
                        if (sel != 0) {
                            Ristoratori ristorante = selezionaRistorante(r, sel);
                            visualizzaInfoRistorante(ristorante);
                            if (aggiungiGiudizio(input)) {
                                this.giudica(input, ristorante);
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Hai scelto di ricercare un ristorante per tipologia.");
                    System.out.println("Inserisci la tipologia del ristorante: ");
                    tipologia = input(input, 11);
                    r = ricercaTipologia(tipologia);
                    if (r == null) {
                        System.out.println("Nessun ristorante trovato con tipologia " + tipologia + ".");
                    } else {
                        visualizzaRistoranti(r);
                        System.out.println("Inserisci il numero del ristorante che vuoi selezionare (0 per uscire): ");
                        int sel = Integer.parseInt(input(input, 17));
                        if (sel != 0) {
                            Ristoratori ristorante = selezionaRistorante(r, sel);
                            visualizzaInfoRistorante(ristorante);
                            if (aggiungiGiudizio(input)) {
                                this.giudica(input, ristorante);
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Hai scelto di ricercare un ristorante per nome.");
                    System.out.println("Inserisci il nome del ristorante: ");
                    nome = input(input, 2);
                    r = ricercaNome(nome);
                    if (r == null) {
                        System.out.println("Nessun ristorante trovato con nome " + nome + ".");
                    } else {
                        visualizzaRistoranti(r);
                        System.out.println("Inserisci il numero del ristorante che vuoi selezionare (0 per uscire): ");
                        int sel = Integer.parseInt(input(input, 17));
                        if (sel != 0) {
                            Ristoratori ristorante = selezionaRistorante(r, sel);
                            visualizzaInfoRistorante(ristorante);
                            if (aggiungiGiudizio(input)) {
                                this.giudica(input, ristorante);
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("Hai scelto di ricercare un ristorante per comune e tipologia.");
                    System.out.println("Inserisci il comune del ristorante: ");
                    comune = input(input, 6);
                    System.out.println("Inserisci il la tipologia del ristorante: ");
                    tipologia = input(input, 11);
                    r = ricercaComuneTipologia(comune, tipologia);
                    if (r == null) {
                        System.out.println("Nessun ristorante trovato con tipologia " + tipologia + " nel comune " + comune + ".");
                    } else {
                        visualizzaRistoranti(r);
                        System.out.println("Inserisci il numero del ristorante che vuoi selezionare (0 per uscire): ");
                        int sel = Integer.parseInt(input(input, 17));
                        if (sel != 0) {
                            Ristoratori ristorante = selezionaRistorante(r, sel);
                            visualizzaInfoRistorante(ristorante);
                            if (aggiungiGiudizio(input)) {
                                this.giudica(input, ristorante);
                            }
                        }
                    }
                    break;
            }
        }

    }

    public static void main(String[] args) {
        boolean finish = false;

        // ripropone il menu principale finché l'utente non inserisce 0 quando richiesto
        while (!finish) {
        Clienti c;
        String comune;
        String nome;

        String cognome = "";
        String provincia = "";
        String email = "";
        String nickname = "";
        String password = "";

        System.out.println("Gentile utente, benvenuto in EatAdvisor, versione Cliente!\n\n" +
                "Se vuoi usare l'applicazione come ospite, inserisci 1. E' necessario registrarsi (o autenticarsi) " +
                "per avere accesso a tutte le funzionalita'.\n" +
                "Se vuoi registrarti, inserisci 2.\n" +
                "Se sei un utente registrato e vuoi autenticarti, inserisci 3.\n" +
                "Per uscire, inserisci 0.\n");

            Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
            int n = Integer.parseInt(input(input, 1));

            switch (n) {
                case 0: finish = true; break;
                case 1:
                    System.out.println("\nHai scelto di utilizzare l'applicazione come ospite.\n");
                    menuOspite(input);
                    break;

                case 2:
                    boolean ok = false;
                    nome = "";
                    cognome = "";
                    comune = "";
                    provincia = "";
                    email = "";
                    nickname = "";
                    password = "";

                    while (!ok) {
                        System.out.println("\nHai scelto di registrarti.");
                        System.out.println("Inserisci il tuo nome: ");
                        nome = input(input, 2);
                        System.out.println("\nOk!\nInserisci il tuo cognome: ");
                        cognome = input(input, 12);
                        System.out.println("\nOk!\nInserisci il tuo comune di residenza (Varese, Milano, Roma, ...): ");
                        comune = input(input, 6);
                        System.out.println("\nOk!\nInserisci la sigla della provincia in cui risiedi (VA, MI, RM, ...): ");
                        provincia = input(input, 7);
                        System.out.println("\nOk!\nInserisci il tuo indirizzo email: ");
                        email = input(input, 13);
                        System.out.println("\nOk!\nInserisci il tuo nickname: ");
                        nickname = input(input, 14);
                        System.out.println("\nOk!\nInserisci la tua password (almeno 8 caratteri, 1 lettera maiuscola, " +
                                "1 lettera minuscola e 1 numero): ");
                        password = input(input, 15);

                        System.out.println("\nDati inseriti:");
                        System.out.println("Nome: " + nome + "\nCognome: " + cognome + "\nComune: " + comune + "\nProvincia: " +
                                provincia + "\nEmail: " + email + "\nNickname: " + nickname + "\nPassword: " + password + "\n");
                        System.out.println("Inserire 1 per confermare, inserire qualunque altro carattere per reinserire: ");

                        if (input.nextLine().equals("1")) ok = true;
                    }

                    c = new Clienti(nome, cognome, comune, provincia, email, nickname, password);
                    c.registraCliente();
                    // una volta registrato, l'utente rimane automaticamente autenticato per la durata dell'utilizzo
                    c.menuAutenticato(input);
                    break;

                case 3:
                    String passwordInput;
                    password = null;
                    int utente = -1;
                    System.out.println("\nHai scelto di autenticarti.");
                    System.out.println("\nInserisci il tuo nickname: ");
                    nickname = input(input, 14);
                    Clienti[] utenti = leggiClienti();
                    if (utenti == null) {
                        System.out.println("Utente non trovato.");
                    } else {
                        for (int i = 0; i < utenti.length; i++) {
                            if (utenti[i].nickname.equals(nickname)) {
                                password = utenti[i].password;
                                utente = i;
                                break;
                            }
                        }
                        if (password == null) {
                            System.out.println("Utente non trovato.");
                        } else {
                            System.out.println("Inserisci la password: ");
                            passwordInput = input(input, 15);
                            if (passwordInput.equals(password)) {
                                c = utenti[utente];
                                c.menuAutenticato(input);
                            } else {
                                System.out.println("Password errata.");
                            }
                        }
                    }
                    break;
            }
        }
    }
}