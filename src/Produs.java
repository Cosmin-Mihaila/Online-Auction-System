public class Produs {
    private int id;
    private String nume;
    private int pretVanzare;
    private int pretMinim;
    private int an;

    public int getID(){
        return id;
    }
    Produs(int id){
        this.id = id;
    }

    public Produs(int id, String nume, int pretVanzare, int pretMinim, int an) {
        this.id = id;
        this.nume = nume;
        this.pretVanzare = pretVanzare;
        this.pretMinim = pretMinim;
        this.an = an;
    }

    Produs(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
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
}
