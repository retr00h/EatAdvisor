package EatAdvisor.clienti;

import EatAdvisor.ristoratori.Ristoratori;

import java.io.*;
import java.util.Scanner;
import java.security.MessageDigest;

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
        String filename = "data/utenti.dati";
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

    /*TODO:

       main: menu selezione utente (guest, registrazione, autenticazione);

       guest: può ricercare un ristorante, selezionarlo e visualizzare i giudizi;

       registrazione: acquisisci nome, cognome, comune di residenza, sigla provincia di residenza, indirizzo di posta,
       nickname, password. dopo aver fatto ciò si è automaticamente loggati e si può usare l'app;

       autenticazione: acquisisci nickname e password, autentica, usa l'app;

       funzioni di ricerca:
          ricercaComune(String comune): restituisce l'elenco dei nomi dei ristoranti del comune;
          ricercaTipologia(String tipologia): restituisce l'elenco dei nomi dei ristoranti di quella tipologia;
          ricercaNome(String nome): restituisce l'elenco dei ristoranti il cui nome CONTIENE quel nome;
          ricercaComuneTipologia(String comune, String tipologia): restituisce l'elenco dei ristoranti di quella tipologia IN quel comune;

       funzioni di selezione:
          seleziona(String[] ristoranti, int n): partendo dall'elenco ristoranti, seleziona quello in posizione n-1;

       funzioni di visualizzazione:
          private visualizza(): usando l'attributo cliente.ristorante, ne visualizza:
              - tutte le info del ristorante,
              - #valori per ogni valore della scala (es. 1 giudizio da 1 stella, 3 giudizi da 2 stelle, ecc)
              - giudizi testuali (nickname autore, valutazione numerica e testuale)
    */


    public static void main(String[] args) {

        Clienti c;

        System.out.println("\"Gentile utente, benvenuto in EatAdvisor, versione Cliente!\n\n" +
                "Se vuoi usare l'applicazione come ospite, inserisci 1. L'utente ospite non ha accesso a tutte le funzionalita'.\n" +
                "Se vuoi registrarti per avere accesso a tutte le funzionalita', inserisci 2.\n" +
                "Se sei un utente registrato e vuoi autenticarti, inserisci 3.\n" +
                "Per uscire, inserisci 0.\n");

        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        boolean valid = false;
        String s = input.nextLine();
        int n = -1;
        if (s.equals("0") || s.equals("1") || s.equals("2") || s.equals("3")) {
            n = Integer.parseInt(s);
        } else {
            while (!valid) {
                System.out.println("\nRiprovare.\n");
                s = input.nextLine();
                if (s.equals("0") || s.equals("1") || s.equals("2") || s.equals("3")) {
                    n = Integer.parseInt(s);
                    valid = true;
                }
            }
        }

        switch (n) {
            case 0: break;

            case 1:

                break;

            case 2:
                boolean finish = false;
                String nome = "";
                String cognome = "";
                String comune = "";
                String provincia = "";
                String email = "";
                String nickname = "";
                String password = "";

                while (!finish) {
                    System.out.println("\nHai scelto di registrarti.");
                    System.out.println("Inserisci il tuo nome: ");

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

                    System.out.println("\nOk!\nInserisci il tuo cognome: ");

                    valid = false;
                    s = input.nextLine();
                    if (s.matches("^[A-Za-z\\s]+[A-Za-z\\s]*$")) {
                        cognome = s;
                    } else {
                        while (!valid) {
                            System.out.println("\nRiprovare.\n");
                            s = input.nextLine();
                            if (s.matches("^[A-Za-z\\s]+[A-Za-z\\s]*$")) {
                                cognome = s;
                                valid = true;
                            }
                        }
                    }

                    System.out.println("\nOk!\nInserisci il tuo comune di residenza (Varese, Milano, Roma, ...): ");

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

                    System.out.println("\nOk!\nInserisci la sigla della provincia in cui risiedi (VA, MI, RM, ...): ");

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

                    System.out.println("\nOk!\nInserisci il tuo indirizzo email: ");

                    valid = false;
                    s = input.nextLine().replace(" ", "");
                    if (s.matches("^[a-zA-Z0-9_.-]{1,64}@[a-zA-Z0-9.-]{1,}\\.[a-zA-Z]{2,3}$")) {
                        email = s;
                    } else {
                        while (!valid) {
                            System.out.println("\nRiprovare.\n");
                            s = input.nextLine().replace(" ", "");
                            if (s.matches("^[a-zA-Z0-9_.-]{1,64}@[a-zA-Z0-9.-]{1,}\\.[a-zA-Z]{2,3}$")) {
                                email = s;
                                valid = true;
                            }
                        }
                    }

                    System.out.println("\nOk!\nInserisci il tuo nickname: ");

                    valid = false;
                    s = input.nextLine();
                    if (s.matches("^[A-Za-z0-9_.\\-]{1,64}$")) {
                        nickname = s;
                    } else {
                        while (!valid) {
                            System.out.println("\nRiprovare. Per un elenco completo dei formati disponibili, " +
                                    "consultare il manuale a pagina XXX.\n\n");
                            s = input.nextLine();
                            if (s.matches("^[A-Za-z0-9_.\\-]{1,64}$")) {
                                nickname = s;
                                valid = true;
                            }
                        }
                    }

                    System.out.println("\nOk!\nInserisci la tua password (almeno 8 caratteri, 1 lettera maiuscola, " +
                            "1 lettera minuscola e 1 numero): ");

                    valid = false;
                    s = input.nextLine();
                    if (s.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})")) {
                        password = s;
                    } else {
                        while (!valid) {
                            System.out.println("\nRiprovare.\n");
                            s = input.nextLine();
                            if (s.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})")) {
                                password = s;
                                valid = true;
                            }
                        }
                    }
                    System.out.println("\nDati inseriti:");
                    System.out.println("Nome: " + nome + "\nCognome: " + cognome + "\nComune: " + comune + "\nProvincia: " +
                            provincia + "\nEmail: " + email + "\nNickname: " + nickname + "\nPassword: " + password + "\n");
                    System.out.println("Inserire 1 per confermare, inserire qualunque altro carattere per reinserire: ");

                    if (input.nextLine().equals("1")) finish = true;
                }

                c = new Clienti(nome, cognome, comune, provincia, email, nickname, password);

                c.registraCliente();
                break;

            case 3:

                break;
        }
    }
}