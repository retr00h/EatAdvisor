package EatAdvisor;

public class Giudizio {
    private int voto;
    private String commento;

    public Giudizio(int voto) {
        this.voto = voto;
    }

    public Giudizio(int voto, String commento) {
        this.voto = voto;
        this.commento = commento;
    }
}
