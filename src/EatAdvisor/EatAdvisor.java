// Fabio Cirelli, matricola 740482, sede Varese

package EatAdvisor;

import EatAdvisor.clienti.Clienti;
import EatAdvisor.ristoratori.Ristoratori;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class EatAdvisor {

    /**
     * Per op 0, 1 e 16 controlla direttamente se s e' valida.
     * Per tutti gli altri op ammessi confronta s con una regex e ne ritorna il risultato boolean.
     * Per gli op non ammessi ritorna false.
     *
     * @param  s  stringa da validare
     * @param  op tipo di stringa
     * @return      valore boolean che rappresenta la validita' della stringa s
     */
    private static boolean validate(String s, int op) {
        /* per ogni caso possibile di input controlla che sia valido e ritorna il conseguente valore booleano
            selezioneRistorante 0
            selezioneCliente    1
            nome                2
            tipoIndirizzo       3
            nomeIndirizzo       4
            civico              5
            comune              6
            provincia           7
            cap                 8
            telefono            9
            url                 10
            tipologiaRistorante 11

            cognome             12
            email               13
            nickname            14
            password            15

            selezioneRicerca    16
            selezioneRistorante 17

            selezioneGiudizio   18
            voto                19
            commento            20
         */
        String regexNome = "^[A-Za-z\\s]+[A-Za-z\\s]*$";
        String regexTipoIndirizzo = "^(via|viale|corso|piazza|piazzale|largo|lungolago|lungomare|rotonda|vicolo|vicoletto)$";
        String regexNomeIndirizzo = "^[a-z0-9\\s]+$";
        String regexCivico = "^[0-9]{1,4}[A-Z]|[0-9]{1,4}$";
        String regexComune = "^[a-z'\\s]+$";
        String regexProvincia = "^[A-Z]{2}$";
        String regexCap = "^[0-9]{5}$";
        String regexTelefono = "^(\\+[0-9]{12}|[0-9]{10})$";
        String regexUrl = "^(http://|https://)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
        String regexTipologiaRistorante = "^(italiano|etnico|fusion)$";

        String regexCognome = "^[A-Za-z\\s]+[A-Za-z\\s]*$";
        String regexEmail = "^[a-zA-Z0-9_.-]{1,64}@[a-zA-Z0-9.-]{1,}\\.[a-zA-Z]{2,3}$";
        String regexNickname = "^[A-Za-z0-9_.\\-]+$";
        String regexPassword = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})";

        switch (op) {
            case 0: return s.equals("0") || s.equals("1");
            case 1: return s.equals("0") || s.equals("1") || s.equals("2") || s.equals("3");
            case 2: return s.matches(regexNome);
            case 3: return s.matches(regexTipoIndirizzo);
            case 4: return s.matches(regexNomeIndirizzo);
            case 5: return s.matches(regexCivico);
            case 6: return s.matches(regexComune);
            case 7:
                return s.matches(regexProvincia);
            case 8:
                return s.matches(regexCap);
            case 9:
                return s.matches(regexTelefono);
            case 10:
                return s.matches(regexUrl);
            case 11:
                return s.matches(regexTipologiaRistorante);

            case 12:
                return s.matches(regexCognome);
            case 13:
                return s.matches(regexEmail);
            case 14:
                return s.matches(regexNickname);
            case 15:
                return s.matches(regexPassword);
            case 16:
                return s.equals("0") || s.equals("1") || s.equals("2") || s.equals("3");
            case 17:
                try {
                    int i = Integer.parseInt(s);
                    if (i > 0) {
                        return true;
                    }
                } catch (Exception e) {
                    return false;
                }
            case 18:
                try {
                    int i = Integer.parseInt(s);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            case 19:
                try {
                    int i = Integer.parseInt(s);
                    return i >= 1 && i <= 5;
                } catch (Exception e) {
                    return false;
                }
            case 20:
                return s.length() <= 256;
            default: return false;
        }
    }

    /**
     * Legge s da input, fa un primo confronto utilizzando EatAdvisor.validate(String s, int op).
     * Se tale confronto da' esito positivo, ritorna s.
     * Altrimenti itera finche' EatAdvisor.validate(String s, int op) non risulta true.
     * Per alcuni op viene modificata la stringa in input tramite String.toLowerCase() o String.toUpperCase().
     *
     * @param input stringa da validare
     * @param op    tipo di stringa
     * @return stringa inserita in input
     */
    public static String input(Scanner input, int op) {
        // per ogni caso possibile di input (vedere il metodo validate) legge la stringa, controlla che sia valida,
        // e se non lo e', continua a richiederla finche non ne viene inserita una valida
        String s;
        switch (op) {
            case 0:
                s = input.nextLine();
                if (validate(s, 0)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine();
                        if (validate(s, 0)) {
                            return s;
                        }
                    }
                }
            case 1:
                s = input.nextLine();
                if (validate(s, 1)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine();
                        if (validate(s, 1)) {
                            return s;
                        }
                    }
                }
            case 2:
                s = input.nextLine();
                if (validate(s, 2)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine();
                        if (validate(s, 2)) {
                            return s;
                        }
                    }
                }
            case 3:
                s = input.nextLine().toLowerCase();
                if (validate(s, 3)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\nI tipi disponibili sono: via, viale, corso, piazza, " +
                                "piazzale, largo, lungolago, lungomare, rotonda, vicolo, vicoletto.\n");
                        s = input.nextLine().toLowerCase();
                        if (validate(s, 3)) {
                            return s;
                        }
                    }
                }
            case 4:
                s = input.nextLine().toLowerCase();
                if (validate(s, 4)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine().toLowerCase();
                        if (validate(s, 4)) {
                            return s;
                        }
                    }
                }
            case 5:
                s = input.nextLine().toUpperCase().replace(" ", "");
                if (validate(s, 5)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine().toUpperCase().replace(" ", "");
                        if (validate(s, 5)) {
                            return s;
                        }
                    }
                }
            case 6:
                s = input.nextLine().toLowerCase();
                if (validate(s, 6)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine().toLowerCase();
                        if (validate(s, 6)) {
                            return s;
                        }
                    }
                }
            case 7:
                s = input.nextLine().toUpperCase();
                if (validate(s, 7)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine().toUpperCase();
                        if (validate(s, 7)) {
                            return s;
                        }
                    }
                }
            case 8:
                s = input.nextLine();
                if (validate(s, 8)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine();
                        if (validate(s, 8)) {
                            return s;
                        }
                    }
                }
            case 9:
                s = input.nextLine().replace(" ", "");
                if (validate(s, 9)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine().replace(" ", "");
                        if (validate(s, 9)) {
                            return s;
                        }
                    }
                }
            case 10:
                s = input.nextLine();
                if (validate(s, 10)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare. Per un elenco completo dei formati disponibili, " +
                                "consultare il manuale a pagina XXX.\n\n");
                        s = input.nextLine();
                        if (validate(s, 10)) {
                            return s;
                        }
                    }
                }
            case 11:
                s = input.nextLine().toLowerCase();
                if (validate(s, 11)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine().toLowerCase();
                        if (validate(s, 11)) {
                            return s;
                        }
                    }
                }
            case 12:
                s = input.nextLine();
                if (validate(s, 12)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine();
                        if (validate(s, 12)) {
                            return s;
                        }
                    }
                }
            case 13:
                s = input.nextLine().replace(" ", "");
                if (validate(s, 13)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine().replace(" ", "");
                        if (validate(s, 13)) {
                            return s;
                        }
                    }
                }
            case 14:
                s = input.nextLine();
                if (validate(s, 14)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare. Per un elenco completo dei formati disponibili, " +
                                "consultare il manuale a pagina XXX.\n\n");
                        s = input.nextLine();
                        if (validate(s, 14)) {
                            return s;
                        }
                    }
                }
            case 15:
                s = input.nextLine();
                if (validate(s, 15)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine();
                        if (validate(s, 15)) {
                            return s;
                        }
                    }
                }
            case 16:
                s = input.nextLine();
                if (validate(s, 16)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine();
                        if (validate(s, 16)) {
                            return s;
                        }
                    }
                }
            case 17:
                s = input.nextLine();
                if (validate(s, 17)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine();
                        if (validate(s, 17)) {
                            return s;
                        }
                    }
                }
            case 18:
                s = input.nextLine();
                if (validate(s, 18)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine();
                        if (validate(s, 18)) {
                            return s;
                        }
                    }
                }
            case 19:
                s = input.nextLine();
                if (validate(s, 19)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare. Valori consentiti: 1, 2, 3, 4, 5.\n");
                        s = input.nextLine();
                        if (validate(s, 19)) {
                            return s;
                        }
                    }
                }
            case 20:
                s = input.nextLine();
                if (validate(s, 20)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nMax 256 caratteri. Riprovare.\n");
                        s = input.nextLine();
                        if (validate(s, 20)) {
                            return s;
                        }
                    }
                }
            default: return null;
        }
    }

    /**
     * Metodo statico che ordina l'arraylist ristoratori tramite bubble sort
     *
     * @param ristoratori arraylist da ordinare
     */
    public static void sortRistorantiNome(ArrayList<Ristoratori> ristoratori) {
        int n = ristoratori.size();
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > i; j--) {
                if (ristoratori.get(j).getNome().compareToIgnoreCase(ristoratori.get(j - 1).getNome()) < 0) {
                    Ristoratori temp = ristoratori.get(j);
                    ristoratori.set(j, ristoratori.get(j - 1));
                    ristoratori.set(j - 1, temp);
                }
            }
        }
    }

    /**
     * Metodo statico che ritorna un arraylist di ristoratori del comune
     *
     * @param comune comune da cercare
     * @return arraylist Ristoratori contenente i ristoranti trovati
     */
    public static ArrayList<Ristoratori> ricercaComune(String comune) {
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
                // arraylist ristoratori deserializzato
                // per ogni ristorante nell'arraylist, controlla che il comune inserito sia uguale a quello del ristorante.
                // nel caso lo sia, il ristorante viene aggiunto ad un altro arraylist.
                // viene ritornato il secondo arraylist
                ArrayList<Ristoratori> rok = new ArrayList<>();
                for (int i = 0; i < ristoratori.size(); i++) {
                    if (ristoratori.get(i).getComune().equals(comune)) {
                        rok.add(ristoratori.get(i));
                    }
                }

                return rok;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("\nQualcosa e' andato storto e non e' stato possibile leggere i dati dei ristoranti.\n");
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Metodo statico che ritorna un arraylist di ristoratori della tipologia
     *
     * @param tipologia tipologia da cercare
     * @return arraylist Ristoratori contenente i ristoranti trovati
     */
    public static ArrayList<Ristoratori> ricercaTipologia(String tipologia) {
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
                // arraylist ristoratori deserializzato
                // per ogni ristorante nell'arraylist, controlla che la tipologia inserita sia uguale a quella del ristorante.
                // nel caso lo sia, il ristorante viene aggiunto ad un altro arraylist.
                // viene ritornato il secondo arraylist
                ArrayList<Ristoratori> rok = new ArrayList<>();
                for (int i = 0; i < ristoratori.size(); i++) {
                    if (ristoratori.get(i).getTipologia().equals(tipologia)) {
                        rok.add(ristoratori.get(i));
                    }
                }

                return rok;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("\nQualcosa e' andato storto e non e' stato possibile leggere i dati dei ristoranti.\n");
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Metodo statico che ritorna un arraylist di ristoratori il cui nome contiene nome
     *
     * @param nome nome da cercare
     * @return arraylist Ristoratori contenente i ristoranti trovati
     */
    public static ArrayList<Ristoratori> ricercaNome(String nome) {
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
                // arraylist ristoratori deserializzato
                // per ogni ristorante nell'arraylist, controlla che il nome inserito sia contenuto in quello del ristorante.
                // nel caso lo sia, il ristorante viene aggiunto ad un altro arraylist.
                // viene ritornato il secondo arraylist
                ArrayList<Ristoratori> rok = new ArrayList<>();
                for (int i = 0; i < ristoratori.size(); i++) {
                    if (ristoratori.get(i).getNome().toLowerCase().contains(nome.toLowerCase())) {
                        rok.add(ristoratori.get(i));
                    }
                }

                return rok;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("\nQualcosa e' andato storto e non e' stato possibile leggere i dati dei ristoranti.\n");
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Metodo statico che, a partire dalla stringa s inserita, ritorna la stessa stringa,
     * con il primo carattere maiuscolo
     *
     * @param s stringa della quale rendere maiuscolo il primo carattere
     * @return stringa s con il primo carattere maiuscolo
     */
    private static String capitalize(String s) {
        // se la stringa non e' nulla o vuota, concatena il primo carattere della stringa reso maiuscolo al resto
        // della stringa
        if (s == null || s.isEmpty()) {
            return s;
        }

        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * Metodo statico che stampa a video i giudizi di r
     *
     * @param r ristorante del quale visualizzare i giudizi
     */
    public static void visualizzaGiudizi(Ristoratori r) {
        // se g non e' null visualizza il numero di giudizi con voto == i incrementando di 1 la posizione corrispondente
        // nell'array n, poi visualizza i giudizi
        Giudizio[] g = r.getGiudizi();
        if (g != null) {
            int[] n = new int[5];
            for (int i = 0; i < g.length; i++) {
                int voto = g[i].getVoto();
                switch (voto) {
                    case 1:
                        n[0]++;
                        break;
                    case 2:
                        n[1]++;
                        break;
                    case 3:
                        n[2]++;
                        break;
                    case 4:
                        n[3]++;
                        break;
                    case 5:
                        n[4]++;
                        break;
                }
            }
            System.out.println();
            for (int i = 0; i < n.length; i++) {
                System.out.println("Gudizi con voto " + (i + 1) + ": " + n[i]);
            }
            System.out.println();
            System.out.print("Giudizi:\n");
            for (Giudizio giudizio : g) {
                System.out.println("Autore: " + giudizio.getAutore());
                System.out.println("Voto: " + giudizio.getVoto());
                if (giudizio.getCommento() != null) {
                    System.out.println("Commento: " + giudizio.getCommento() + "\n");
                }
                System.out.println();
            }
        } else {
            System.out.println("Giudizi: nessuno, per ora...\n");
        }
    }

    /**
     * Metodo statico che stampa a video un numero progressivo,
     * i nomi e gli indirizzi dei ristoranti nell'arraylist ristoratori specificato
     *
     * @param ristoratori arraylist di ristoranti dei quali visualizzare numero, nome e indirizzo
     */
    public static void visualizzaRistoranti(ArrayList<Ristoratori> ristoratori) {
        if (ristoratori != null) {
            int i = 1;
            for (Ristoratori r : ristoratori) {
                if (r != null) {
                    String indirizzo = capitalize(r.getTipoIndirizzo()) + " " +
                            capitalize(r.getNomeIndirizzo()) + ", #" +
                            r.getCivico() + ", " + capitalize(r.getComune()) + ", " +
                            r.getProvincia() + " " + r.getCap();
                    System.out.println("Ristorante " + (i));
                    System.out.println("Nome: " + r.getNome());
                    System.out.println("Indirizzo: " + indirizzo);
                    System.out.println();
                }
                i++;
            }
        }
    }

    /**
     * Metodo statico che riceve un arraylist di ristoranti e un intero n > 0.
     * Ritorna il ristorante n-esimo
     *
     * @param ristoratori arraylist dal quale selezionare un ristorante
     * @param n           ristorante da selezionare
     * @return ristorante selezionato
     */
    public static Ristoratori selezionaRistorante(ArrayList<Ristoratori> ristoratori, int n) {
        n--;
        if (n >= 0 && n < ristoratori.size()) {
            return ristoratori.get(n);
        } else {
            return null;
        }
    }

    /**
     * Metodo statico che visualizza le informazioni del ristorante selezionato
     *
     * @param r ristorante del quale visualizzare le informazioni complete
     */
    public static void visualizzaInfoRistorante(Ristoratori r) {
        if (r != null) {
            String indirizzo = capitalize(r.getTipoIndirizzo()) + " " + capitalize(r.getNomeIndirizzo()) + ", #" +
                    r.getCivico() + ", " + capitalize(r.getComune()) + ", " + r.getProvincia() + " " + r.getCap();
            System.out.println("Nome: " + r.getNome());
            System.out.println("Indirizzo: " + indirizzo);
            System.out.println("Telefono: " + r.getTelefono());
            System.out.println("Sito web: " + r.getUrl());
            System.out.println("Tipologia: " + capitalize(r.getTipologia()));
            visualizzaGiudizi(r);
        }
    }

    /**
     * Metodo statico che legge le informazioni dei clienti dal file Utenti.dati
     *
     * @return arraylist Clienti contenente i clienti letti
     */
    public static ArrayList<Clienti> leggiClienti() {
        String filename = "data" + File.separator + "Utenti.dati";
        File f = new File(filename);
        ArrayList<Clienti> utenti = null;
        if (f.exists() && !f.isDirectory()) {
            try {
                // lettura array utenti da utenti.dati
                FileInputStream fileInput = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileInput);
                utenti = (ArrayList<Clienti>) in.readObject();
                in.close();
                fileInput.close();
                return utenti;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Qualcosa e' andato storto e non e' stato possibile leggere i dati degli utenti.");
            }
        } else {
            System.out.println("Non ci sono utenti registrati.");
        }
        return utenti;
    }

}