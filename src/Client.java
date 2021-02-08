import java.util.List;
import java.util.Random;

public class Client{
    private int id;
    private String nume;
    private String adresa;
    private int nrParticipari;
    private int nrLicitatiiCastigate;

    private CasaDeLicitatii produse;

    public Client(int id, String nume, String adresa) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
        this.nrParticipari = 0;
        this.nrLicitatiiCastigate = 0;
    }

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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getNrParticipari() {
        return nrParticipari;
    }

    public void setNrParticipari(int nrParticipari) {
        this.nrParticipari = nrParticipari;
    }

    public int getNrLicitatiiCastigate() {
        return nrLicitatiiCastigate;
    }

    public void setNrLicitatiiCastigate(int nrLicitatiiCastigate) {
        this.nrLicitatiiCastigate = nrLicitatiiCastigate;
    }

    public CasaDeLicitatii getProduse() {
        return produse;
    }

    public void setProduse(CasaDeLicitatii produse) {
        this.produse = produse;
    }

    Client() {

    }

    Client(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", nrParticipari=" + nrParticipari +
                ", nrLicitatiiCastigate=" + nrLicitatiiCastigate +
                '}';
    }

    public int pay(){
        return new Random().nextInt(50);
    }
}
