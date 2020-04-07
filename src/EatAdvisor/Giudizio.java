// Fabio Cirelli, matricola 740482, sede Varese

package EatAdvisor;

public class Giudizio extends EatAdvisor implements java.io.Serializable {
    private String autore;
    private int voto;
    private String commento;

    /**
     * Costruttore.
     * Costruisce l'oggetto Giudizio specificando autore e voto.
     */
    public Giudizio(String autore, int voto) {
        this.autore = autore;
        this.voto = voto;
    }

    /**
     * Costruttore.
     * Costruisce l'oggetto Giudizio specificando autore, voto e commento.
     */
    public Giudizio(String autore, int voto, String commento) {
        this.autore = autore;
        this.voto = voto;
        this.commento = commento;
    }

    /**
     * @return nickname autore del giudizio
     */
    public String getAutore() {
        return autore;
    }

    /**
     * @return voto del giudizio
     */
    public int getVoto() {
        return voto;
    }

    /**
     * @return commento del giudizio
     */
    public String getCommento() {
        return commento;
    }
}