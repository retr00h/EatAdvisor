package EatAdvisor;
import EatAdvisor.ristoratori.Ristoratori;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
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
        /*
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
         */
        String regexNome = "^[A-Za-z\\s]+[A-Za-z\\s]*$";
        String regexTipoIndirizzo = "^(via|viale|corso|piazza|piazzale|largo|lungolago|lungomare|rotonda|vicolo|vicoletto)$";
        String regexNomeIndirizzo = "^[a-z0-9\\s]{1,128}$";
        String regexCivico = "^[0-9]{1,4}[A-Z]|[0-9]{1,4}$";
        String regexComune = "^[a-z'\\s]{1,50}$";
        String regexProvincia = "^[A-Z]{2}$";
        String regexCap = "^[0-9]{5}$";
        String regexTelefono = "^(\\+[0-9]{12}|[0-9]{10})$";
        String regexUrl = "^(http://|https://)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
        String regexTipologiaRistorante = "^(italiano|Italiano|etnico|Etnico|fusion|Fusion)$";

        String regexCognome = "^[A-Za-z\\s]+[A-Za-z\\s]*$";
        String regexEmail = "^[a-zA-Z0-9_.-]{1,64}@[a-zA-Z0-9.-]{1,}\\.[a-zA-Z]{2,3}$";
        String regexNickname = "^[A-Za-z0-9_.\\-]{1,64}$";
        String regexPassword = "^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})";

        switch (op) {
            case 0: return s.equals("0") || s.equals("1");
            case 1: return s.equals("0") || s.equals("1") || s.equals("2") || s.equals("3");
            case 2: return s.matches(regexNome);
            case 3: return s.matches(regexTipoIndirizzo);
            case 4: return s.matches(regexNomeIndirizzo);
            case 5: return s.matches(regexCivico);
            case 6: return s.matches(regexComune);
            case 7: return s.matches(regexProvincia);
            case 8: return s.matches(regexCap);
            case 9: return s.matches(regexTelefono);
            case 10: return s.matches(regexUrl);
            case 11: return s.matches(regexTipologiaRistorante);

            case 12: return s.matches(regexCognome);
            case 13: return s.matches(regexEmail);
            case 14: return s.matches(regexNickname);
            case 15: return s.matches(regexPassword);
            case 16: return s.equals("0") || s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4");
            default: return false;
        }
    }

    /**
     * Legge s da input, fa un primo confronto utilizzando EatAdvisor.validate(String s, int op).
     * Se tale confronto da' esito positivo, ritorna s.
     * Altrimenti itera finche' EatAdvisor.validate(String s, int op) risulta false.
     * Per alcuni op viene modificata la stringa in input tramite String.toLowerCase() o String.toUpperCase().
     *
     * @param  input  stringa da validare
     * @param  op tipo di stringa
     * @return      stringa inserita in input
     */
    public static String input(Scanner input, int op) {
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
                s = input.nextLine();
                if (validate(s, 11)) {
                    return s;
                } else {
                    while (true) {
                        System.out.println("\nRiprovare.\n");
                        s = input.nextLine();
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
            default: return null;
        }
    }

    /**
     * Metodo statico che ordina ristoratori tramite bubble sort
     *
     * @param  ristoratori  array da ordinare
     */
    public static void sortRistorantiNome(Ristoratori[] ristoratori) {
        int n = ristoratori.length;
        for (int i = 0; i < n; i++) {
            for (int j = n-1; j > i; j--) {
                if (ristoratori[j].getNome().compareToIgnoreCase(ristoratori[j-1].getNome()) < 0) {
                    Ristoratori temp = ristoratori[j];
                    ristoratori[j] = ristoratori[j-1];
                    ristoratori[j-1] = temp;
                }
            }
        }
    }

    public static Ristoratori[] ricercaComune(String comune) {
        String filename = "data/EatAdvisor.dati";
        File f = new File(filename);
        if (f.exists() && !f.isDirectory()) {
            try {
                // lettura array ristoratori da EatAdvisor.dati
                FileInputStream fileInput = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileInput);
                Ristoratori[] ristoratori = (Ristoratori[]) in.readObject();
                in.close();
                fileInput.close();
                int elementiNull = 0;
                // array ristoratori deserializzato
                for (int i = 0; i < ristoratori.length; i++) {
                    if (!ristoratori[i].getComune().toLowerCase().equals(comune.toLowerCase())) {
                        ristoratori[i] = null;
                        elementiNull++;
                    }
                }
                if (elementiNull == ristoratori.length) {
                    return null;
                } else {
                    Ristoratori[] ristoratoriOk = new Ristoratori[ristoratori.length - elementiNull];
                    for (int i = 0; i < ristoratori.length - elementiNull; i++) {
                        if (ristoratori[i] != null) ristoratoriOk[i] = ristoratori[i];
                    }
                    return ristoratoriOk;
                }
            } catch (Exception e) {
                System.out.println("\nQualcosa e' andato storto e non e' stato possibile leggere i dati dei ristoranti.\n");
                return null;
            }
        } else {
            return null;
        }
    }

    protected static Ristoratori[] ricercaTipologia(String tipologia) {
        String filename = "data/EatAdvisor.dati";
        File f = new File(filename);
        if (f.exists() && !f.isDirectory()) {
            try {
                // lettura array ristoratori da EatAdvisor.dati
                FileInputStream fileInput = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileInput);
                Ristoratori[] ristoratori = (Ristoratori[]) in.readObject();
                in.close();
                fileInput.close();
                int elementiNull = 0;
                // array ristoratori deserializzato
                for (int i = 0; i < ristoratori.length; i++) {
                    if (!ristoratori[i].getTipologia().equals(tipologia)) {
                        ristoratori[i] = null;
                        elementiNull++;
                    }
                }
                if (elementiNull == ristoratori.length) {
                    return null;
                } else {
                    Ristoratori[] ristoratoriOk = new Ristoratori[ristoratori.length - elementiNull];
                    for (int i = 0; i < ristoratori.length - elementiNull; i++) {
                        if (ristoratori[i] != null) ristoratoriOk[i] = ristoratori[i];
                    }
                    return ristoratoriOk;
                }
            } catch (Exception e) {
                System.out.println("\nQualcosa e' andato storto e non e' stato possibile leggere i dati dei ristoranti.\n");
                return null;
            }
        } else {
            return null;
        }
    }

    protected Ristoratori[] ricercaNome(String nome) {
        String filename = "data/EatAdvisor.dati";
        File f = new File(filename);
        if (f.exists() && !f.isDirectory()) {
            try {
                // lettura array ristoratori da EatAdvisor.dati
                FileInputStream fileInput = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileInput);
                Ristoratori[] ristoratori = (Ristoratori[]) in.readObject();
                in.close();
                fileInput.close();
                int elementiNull = 0;
                // array ristoratori deserializzato
                for (int i = 0; i < ristoratori.length; i++) {
                    if (!ristoratori[i].getNome().toLowerCase().matches(nome.toLowerCase())) {
                        ristoratori[i] = null;
                        elementiNull++;
                    }
                }
                if (elementiNull == ristoratori.length) {
                    return null;
                } else {
                    Ristoratori[] ristoratoriOk = new Ristoratori[ristoratori.length - elementiNull];
                    for (int i = 0; i < ristoratori.length - elementiNull; i++) {
                        if (ristoratori[i] != null) ristoratoriOk[i] = ristoratori[i];
                    }
                    return ristoratoriOk;
                }
            } catch (Exception e) {
                System.out.println("\nQualcosa e' andato storto e non e' stato possibile leggere i dati dei ristoranti.\n");
                return null;
            }
        } else {
            return null;
        }
    }

    protected Ristoratori[] ricercaComuneTipologia(String comune, String tipologia) {
        Ristoratori[] ristoratori = ricercaComune(comune);
        int elementiNull = 0;
        if (ristoratori != null){
            for (int i = 0; i < ristoratori.length; i++) {
                if (ristoratori[i] == null) {
                    elementiNull++;
                } else {
                    if (!ristoratori[i].getTipologia().toLowerCase().equals(tipologia.toLowerCase())) {
                        ristoratori[i] = null;
                        elementiNull++;
                    }
                }
            }
            if (elementiNull == ristoratori.length) {
                return null;
            } else {
                Ristoratori[] ristoratoriOk = new Ristoratori[ristoratori.length - elementiNull];
                for (int i = 0; i < ristoratori.length - elementiNull; i++) {
                    if (ristoratori[i] != null) ristoratoriOk[i] = ristoratori[i];
                }
                return ristoratoriOk;
            }
        } else return null;
    }

    private static String capitalize(String s) {
        if(s == null || s.isEmpty()) {
            return s;
        }

        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static void visualizzaGiudizi(Ristoratori r) {
        System.out.print("Giudizi: ");
        if (r.getGiudizi() != null) {
            for (Giudizio g : r.getGiudizi()) {
                System.out.println("\nAutore: " + g.getAutore());
                System.out.println("Voto: " + g.getVoto());
                if (g.getCommento() != null) System.out.println("Commento: " + g.getCommento() + "\n");
                System.out.println();
            }
        } else {
            System.out.println("nessuno, per ora...\n");
        }
    }


    public static void visualizzaRistorante(Ristoratori[] ristoratori) {
        if (ristoratori != null) {
            for (Ristoratori r : ristoratori) {
                String indirizzo = capitalize(r.getTipoIndirizzo()) + " " + capitalize(r.getNomeIndirizzo()) + ", #" +
                        r.getCivico() + ", " + capitalize(r.getComune()) + ", " + r.getProvincia() + " " + r.getCap();
                System.out.println("Nome: " + r.getNome());
                System.out.println("Indirizzo: " + indirizzo);
                System.out.println("Telefono: " + r.getTelefono());
                System.out.println("Sito web: " + r.getUrl());
                System.out.println("Tipologia: " + capitalize(r.getTipologia()));
            }
        }
    }

}