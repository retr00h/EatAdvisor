package ristoratori;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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

    public Ristoratori (String nome, String tipoIndirizzo, String nomeIndirizzo, int civico, String comune,
                       String provincia, int cap, String telefono, String url, String tipologia) {

        String[] controlloVia = {"via", "viale", "corso", "piazza", "piazzale"};
        boolean ok = false;

        if (nome.matches("^[A-Za-z\\s]{1,}[A-Za-z\\s]{0,}$")) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Il nome del ristorante deve essere composto di caratteri alfanumerici.");
        }

        for (String x : controlloVia) {
            if (tipoIndirizzo.toLowerCase().equals(x)) {
                ok = true;
                break;
            }
        }
        if (ok) {
            this.tipoIndirizzo = tipoIndirizzo.toLowerCase();
        } else {
            throw new IllegalArgumentException("La tipologia di via inserita non e' valida.");
        }

        if (nomeIndirizzo.toLowerCase().matches("^[A-Za-z\\s]{1,}[A-Za-z\\s]{0,}$")) {
            this.nomeIndirizzo = nomeIndirizzo.toLowerCase();
        } else {
            throw new IllegalArgumentException("Il nome dell'indirizzo deve essere composto di caratteri alfanumerici.");
        }

        if (civico > 0) {
            this.civico = Integer.toString(civico);
        } else {
            throw new IllegalArgumentException("Il nome del ristorante deve essere composto di caratteri alfanumerici.");
        }

        if (comune.matches("^[A-Za-z\\s]{1,}[A-Za-z\\s]{0,}$")) {
            this.comune = comune;
        } else {
            throw new IllegalArgumentException("Il nome del comune deve essere composto di caratteri.");
        }

        if (provincia.toUpperCase().matches("^[A-Z]{2}$")) {
            this.provincia = provincia.toUpperCase();
        } else {
            throw new IllegalArgumentException("Il nome della provincia deve essere composto da due caratteri.");
        }

        if (cap > 9999 && cap < 100000) {
            this.cap = Integer.toString(cap);
        } else {
            throw new IllegalArgumentException("Il cap inserito non e' valido.");
        }

        if (telefono.matches("^[0-9]{10}$")) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("Il numero di telefono inserito non e' valido. Riprovare assicurandosi di non inserire spazi.");
        }


        if (url.matches("^(http://|https://)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$")) {
            this.url = url;
        } else {
            throw new IllegalArgumentException("La url inserita non e' valida.");
        }

        if (tipologia.toLowerCase().equals("italiano") || tipologia.toLowerCase().equals("etnico") || tipologia.toLowerCase().equals("fusion")) {
            this.tipologia = tipologia.toLowerCase();
        } else {
            throw new IllegalArgumentException("La tipologia di ristorante inserita non e' valida.");
        }

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

            System.out.println("Il ristorante e' stato inserito\n");

        }
        catch(IOException e) {
            System.out.println("Il ristorante non e' stato inserito");
        }

    }

    public static void main(String[] args) {
        Ristoratori ris = new Ristoratori("Pino pasticcino", "via", "flora", 146, "legnano", "mi", 20025, "3398335721", "www.google.com", "italiano");
        ris.registraRistorante();
    }
}