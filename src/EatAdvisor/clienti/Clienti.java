package EatAdvisor.clienti;
import EatAdvisor.EatAdvisor;
import EatAdvisor.ristoratori.Ristoratori;
import EatAdvisor.Giudizio;

import java.io.*;
import java.util.Scanner;

public class Clienti implements Serializable {

    private String nome;
    private String cognome;
    private String comune;
    private String provincia;
    private String email;
    private String nickname;
    private String password;

    public Clienti (String nome, String cognome, String comune, String provincia, String email, String nickname, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.comune = comune;
        this.provincia = provincia;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    private void registraCliente() {
        String filename = "data/Utenti.dati";
        File f = new File(filename);
        if (f.exists() && !f.isDirectory()) {
            try {
                // lettura array utenti da utenti.dati
                FileInputStream fileInput = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileInput);

                Clienti[] utentiTemp = (Clienti[]) in.readObject();

                in.close();
                fileInput.close();

                Clienti[] utenti = new Clienti[utentiTemp.length+1];

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



    /*TODO:

       *****OK*****main: menu selezione utente (guest, registrazione, autenticazione);

       *****OK*****guest: può ricercare un ristorante, selezionarlo e visualizzare i giudizi;

       *****OK*****registrazione: acquisisci nome, cognome, comune di residenza, sigla provincia di residenza, indirizzo di posta,
                   nickname, password. dopo aver fatto ciò si è automaticamente loggati e si può usare l'app;

       *****OK*****autenticazione: acquisisci nickname e password, autentica, usa l'app;

       funzioni di ricerca:
          *****OK*****ricercaComune(String comune): restituisce l'elenco dei nomi dei ristoranti del comune;
          *****OK*****ricercaTipologia(String tipologia): restituisce l'elenco dei nomi dei ristoranti di quella tipologia;
          *****OK*****ricercaNome(String nome): restituisce l'elenco dei ristoranti il cui nome CONTIENE quel nome;
          *****OK*****ricercaComuneTipologia(String comune, String tipologia): restituisce l'elenco dei ristoranti di quella tipologia IN quel comune;

       funzioni di selezione:
          *****OK*****seleziona(String[] ristoranti, int n): partendo dall'elenco ristoranti, seleziona quello in posizione n-1;

       funzioni di visualizzazione:
          *****OK*****private visualizza(): usando l'attributo cliente.ristorante, ne visualizza:
                          - tutte le info del ristorante,
                          - #valori per ogni valore della scala (es. 1 giudizio da 1 stella, 3 giudizi da 2 stelle, ecc)
                          - giudizi testuali (nickname autore, valutazione numerica e testuale)
    */

    private static void menuOspite(Scanner input) {
        Ristoratori[] r;
        String comune;
        String tipologia;
        String nome;

        System.out.println("Per ricercare un ristorante per comune, inserisci 1.\nPer ricercare un ristorante " +
                "per tipologia, inserisci 2.\nPer ricercare un ristorante per nome, inserisci 3.\nPer ricercare " +
                "un ristorante di una tipologia in un comune, inserisci 4.\nPer uscire, inserisci 0.\n");
        int n = Integer.parseInt(EatAdvisor.input(input, 16));
        switch (n) {
            case 0: break;
            case 1:
                System.out.println("Hai scelto di ricercare un ristorante per comune.");
                System.out.println("Inserisci il comune del ristorante: ");
                comune = EatAdvisor.input(input, 6);
                r = EatAdvisor.ricercaComune(comune);
                if (r == null) {
                    System.out.println("Nessun ristorante trovato con comune " + comune + ".");
                } else {
                    EatAdvisor.visualizzaRistoranti(r);
                            System.out.println("Inserisci il numero del ristorante che vuoi selezionare (0 per uscire): ");
                            int sel = Integer.parseInt(EatAdvisor.input(input, 17));
                            EatAdvisor.visualizzaInfoRistorante(EatAdvisor.selezionaRistorante(r, sel));
                }
                break;
            case 2:
                System.out.println("Hai scelto di ricercare un ristorante per tipologia.");
                System.out.println("Inserisci la tipologia del ristorante: ");
                tipologia = EatAdvisor.input(input, 11);
                r = EatAdvisor.ricercaTipologia(tipologia);
                if (r == null) {
                    System.out.println("Nessun ristorante trovato con tipologia " + tipologia + ".");
                } else {
                    EatAdvisor.visualizzaRistoranti(r);
                }
                break;
            case 3:
                System.out.println("Hai scelto di ricercare un ristorante per nome.");
                System.out.println("Inserisci il nome del ristorante: ");
                nome = EatAdvisor.input(input, 2);
                r = EatAdvisor.ricercaNome(nome);
                if (r == null) {
                    System.out.println("Nessun ristorante trovato con nome " + nome + ".");
                } else {
                    EatAdvisor.visualizzaRistoranti(r);
                }
                break;
            case 4:
                System.out.println("Hai scelto di ricercare un ristorante per comune e tipologia.");
                System.out.println("Inserisci il comune del ristorante: ");
                comune = EatAdvisor.input(input, 6);
                System.out.println("Inserisci il la tipologia del ristorante: ");
                tipologia = EatAdvisor.input(input, 11);
                r = EatAdvisor.ricercaComuneTipologia(comune, tipologia);
                if (r == null) {
                    System.out.println("Nessun ristorante trovato con tipologia " + tipologia + " nel comune " + comune + ".");
                } else {
                    EatAdvisor.visualizzaRistoranti(r);
                }
                break;
        }
    }

    public static boolean aggiungiGiudizio(Scanner input) {
        System.out.println("Se desideri aggiungere un giudizio a questo ristorante, inserisci 1.");
        return Integer.parseInt(EatAdvisor.input(input, 18)) == 1;
    }

    public void giudica(Scanner input, Ristoratori r) {
        System.out.println("Inserisci un voto: ");
        int voto = Integer.parseInt(EatAdvisor.input(input, 19));
        System.out.println("(Opzionale) inserisci un commento: ");
        String commento = EatAdvisor.input(input, 20);
        if (r.getGiudizi() == null) {
            r.addGiudizio(new Giudizio(this.nickname, voto, commento));
            r.aggiorna();
        }
    }

    public static void menuAutenticato(Scanner input, Clienti c) {
        Ristoratori[] r;
        String comune;
        String tipologia;
        String nome;

        System.out.println("Per ricercare un ristorante per comune, inserisci 1.\nPer ricercare un ristorante " +
                "per tipologia, inserisci 2.\nPer ricercare un ristorante per nome, inserisci 3.\nPer ricercare " +
                "un ristorante di una tipologia in un comune, inserisci 4.\nPer uscire, inserisci 0.\n");
        int n = Integer.parseInt(EatAdvisor.input(input, 16));
        switch (n) {
            case 0: break;
            case 1:
                System.out.println("Hai scelto di ricercare un ristorante per comune.");
                System.out.println("Inserisci il comune del ristorante: ");
                comune = EatAdvisor.input(input, 6);
                r = EatAdvisor.ricercaComune(comune);
                if (r == null) {
                    System.out.println("Nessun ristorante trovato con comune " + comune + ".");
                } else {
                    EatAdvisor.visualizzaRistoranti(r);
                    System.out.println("Inserisci il numero del ristorante che vuoi selezionare (0 per uscire): ");
                    int sel = Integer.parseInt(EatAdvisor.input(input, 17));
                    Ristoratori ristorante = EatAdvisor.selezionaRistorante(r, sel);
                    EatAdvisor.visualizzaInfoRistorante(ristorante);
                    if (aggiungiGiudizio(input)) {
                        c.giudica(input, ristorante);
                    }
                }
                break;
            case 2:
                System.out.println("Hai scelto di ricercare un ristorante per tipologia.");
                System.out.println("Inserisci la tipologia del ristorante: ");
                tipologia = EatAdvisor.input(input, 11);
                r = EatAdvisor.ricercaTipologia(tipologia);
                if (r == null) {
                    System.out.println("Nessun ristorante trovato con tipologia " + tipologia + ".");
                } else {
                    EatAdvisor.visualizzaRistoranti(r);
                    System.out.println("Inserisci il numero del ristorante che vuoi selezionare (0 per uscire): ");
                    int sel = Integer.parseInt(EatAdvisor.input(input, 17));
                    Ristoratori ristorante = EatAdvisor.selezionaRistorante(r, sel);
                    EatAdvisor.visualizzaInfoRistorante(ristorante);
                    if (aggiungiGiudizio(input)) {
                        c.giudica(input, ristorante);
                    }
                }
                break;
            case 3:
                System.out.println("Hai scelto di ricercare un ristorante per nome.");
                System.out.println("Inserisci il nome del ristorante: ");
                nome = EatAdvisor.input(input, 2);
                r = EatAdvisor.ricercaNome(nome);
                if (r == null) {
                    System.out.println("Nessun ristorante trovato con nome " + nome + ".");
                } else {
                    EatAdvisor.visualizzaRistoranti(r);
                    System.out.println("Inserisci il numero del ristorante che vuoi selezionare (0 per uscire): ");
                    int sel = Integer.parseInt(EatAdvisor.input(input, 17));
                    Ristoratori ristorante = EatAdvisor.selezionaRistorante(r, sel);
                    EatAdvisor.visualizzaInfoRistorante(ristorante);
                    if (aggiungiGiudizio(input)) {
                        c.giudica(input, ristorante);
                    }
                }
                break;
            case 4:
                System.out.println("Hai scelto di ricercare un ristorante per comune e tipologia.");
                System.out.println("Inserisci il comune del ristorante: ");
                comune = EatAdvisor.input(input, 6);
                System.out.println("Inserisci il la tipologia del ristorante: ");
                tipologia = EatAdvisor.input(input, 11);
                r = EatAdvisor.ricercaComuneTipologia(comune, tipologia);
                if (r == null) {
                    System.out.println("Nessun ristorante trovato con tipologia " + tipologia + " nel comune " + comune + ".");
                } else {
                    EatAdvisor.visualizzaRistoranti(r);
                    System.out.println("Inserisci il numero del ristorante che vuoi selezionare (0 per uscire): ");
                    int sel = Integer.parseInt(EatAdvisor.input(input, 17));
                    Ristoratori ristorante = EatAdvisor.selezionaRistorante(r, sel);
                    EatAdvisor.visualizzaInfoRistorante(ristorante);
                    if (aggiungiGiudizio(input)) {
                        c.giudica(input, ristorante);
                    }
                }
                break;
        }
    }

    public static void main(String[] args) {
        boolean finish = false;

        while (!finish) {
        Clienti c;
        Ristoratori[] r;
        Ristoratori rSel;
        String comune;
        String tipologia;
        String nome;

        String cognome = "";
        String provincia = "";
        String email = "";
        String nickname = "";
        String password = "";

        System.out.println("\"Gentile utente, benvenuto in EatAdvisor, versione Cliente!\n\n" +
                "Se vuoi usare l'applicazione come ospite, inserisci 1. E' necessario registrarsi (o autenticarsi) " +
                "per avere accesso a tutte le funzionalita'.\n" +
                "Se vuoi registrarti, inserisci 2.\n" +
                "Se sei un utente registrato e vuoi autenticarti, inserisci 3.\n" +
                "Per uscire, inserisci 0.\n");

        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = Integer.parseInt(EatAdvisor.input(input, 1));

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
                        nome = EatAdvisor.input(input, 2);
                        System.out.println("\nOk!\nInserisci il tuo cognome: ");
                        cognome = EatAdvisor.input(input, 12);
                        System.out.println("\nOk!\nInserisci il tuo comune di residenza (Varese, Milano, Roma, ...): ");
                        comune = EatAdvisor.input(input, 6);
                        System.out.println("\nOk!\nInserisci la sigla della provincia in cui risiedi (VA, MI, RM, ...): ");
                        provincia = EatAdvisor.input(input, 7);
                        System.out.println("\nOk!\nInserisci il tuo indirizzo email: ");
                        email = EatAdvisor.input(input, 13);
                        System.out.println("\nOk!\nInserisci il tuo nickname: ");
                        nickname = EatAdvisor.input(input, 14);
                        System.out.println("\nOk!\nInserisci la tua password (almeno 8 caratteri, 1 lettera maiuscola, " +
                                "1 lettera minuscola e 1 numero): ");
                        password = EatAdvisor.input(input, 15);

                        System.out.println("\nDati inseriti:");
                        System.out.println("Nome: " + nome + "\nCognome: " + cognome + "\nComune: " + comune + "\nProvincia: " +
                                provincia + "\nEmail: " + email + "\nNickname: " + nickname + "\nPassword: " + password + "\n");
                        System.out.println("Inserire 1 per confermare, inserire qualunque altro carattere per reinserire: ");

                        if (input.nextLine().equals("1")) ok = true;
                    }

                    c = new Clienti(nome, cognome, comune, provincia, email, nickname, password);
                    c.registraCliente();
                    menuAutenticato(input, c);
                    break;

                case 3:
                    nickname = "";
                    String passwordInput = "";
                    password = null;
                    int utente = -1;
                    System.out.println("\nHai scelto di autenticarti.");
                    System.out.println("\nInserisci il tuo nickname: ");
                    nickname = EatAdvisor.input(input, 14);
                    Clienti[] utenti = EatAdvisor.leggiClienti();
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
                            passwordInput = EatAdvisor.input(input, 15);
                            if (passwordInput.equals(password)) {
                                c = utenti[utente];
                                menuAutenticato(input, c);
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