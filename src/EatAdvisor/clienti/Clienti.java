package EatAdvisor.clienti;
import EatAdvisor.EatAdvisor;
import EatAdvisor.ristoratori.Ristoratori;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

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
                // lettura stack utenti da utenti.dati
                FileInputStream fileInput = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileInput);

                Stack<Clienti> utenti = (Stack<Clienti>) in.readObject();

                in.close();
                fileInput.close();

                // stack utenti deserializzata
                utenti.push(this);

                FileOutputStream fileOutput = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(fileOutput);

                // serializzazione oggetto nel file utenti.dati
                out.writeObject(utenti);

                out.close();
                fileOutput.close();

                System.out.println("Dati inseriti con successo!\n");

            } catch(Exception e) {
                System.out.println("Dati non inseriti");
            }
        } else {
            try {
                Stack<Clienti> utenti = new Stack<Clienti>();
                utenti.push(this);

                FileOutputStream fileOutput = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(fileOutput);

                // serializzazione oggetto nel file EatAdvisor.dati
                out.writeObject(utenti);

                out.close();
                fileOutput.close();

                System.out.println("Dati inseriti con successo!\n");

            } catch(Exception e) {
                System.out.println("Dati non inseriti");
            }
        }




//        String filename = "data/utenti.dati";
//        try {
//            FileOutputStream f = new FileOutputStream(filename);
//            ObjectOutputStream out = new ObjectOutputStream(f);
//
//            // serializzazione oggetto nel file EatAdvisor.dati
//            out.writeObject(this);
//
//            out.close();
//            f.close();
//
//            System.out.println("Dati inseriti con successo!\n");
//
//        }
//        catch(IOException e) {
//            System.out.println("Dati non inseriti");
//        }
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
        int n = Integer.parseInt(EatAdvisor.input(input, 1));

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