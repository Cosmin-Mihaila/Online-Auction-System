public class Produs {
    private final int id;
    private final String nume;
    private int pretVanzare;
    private int pretMinim;
    private int an;

    public int getID(){
        return id;
    }

   Produs(int id, String nume, int pretVanzare, int pretMinim, int an) {
        this.id = id;
        this.nume = nume;
        this.pretVanzare = pretVanzare;
        this.pretMinim = pretMinim;
        this.an = an;
    }

    public Produs(int id, String nume){
        this.id = id;
        this.nume = nume;
    }

    public int getId() {
        return id;
    }


    public String getNume() {
        return nume;
    }


    public double getPretVanzare() {
        return pretVanzare;
    }

    public void setPretVanzare(int pretVanzare) {
        this.pretVanzare = pretVanzare;
    }

    public double getPretMinim() {
        return pretMinim;
    }

    public void setPretMinim(int pretMinim) {
        this.pretMinim = pretMinim;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", pretVanzare=" + pretVanzare +
                ", pretMinim=" + pretMinim +
                ", an=" + an +
                '}';
    }
}
