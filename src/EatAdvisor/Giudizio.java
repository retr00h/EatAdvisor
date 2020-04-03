package EatAdvisor;

public class Giudizio {
    private String autore;
    private int voto;
    private String commento;

    public Giudizio(String autore, int voto) {
        this.autore = autore;
        this.voto = voto;
    }

    public Giudizio(String autore, int voto, String commento) {
        this.autore = autore;
        this.voto = voto;
        this.commento = commento;
    }

    public String getAutore() {
        return autore;
    }

    public int getVoto() {
        return voto;
    }

    public String getCommento() {
        return commento;
    }
}
