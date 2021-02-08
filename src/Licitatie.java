import java.util.ArrayList;
import java.util.List;
public class Licitatie {
    private int id;
    private int nrParticipanti;
    private int idProdus;
    private int nrPasiMaxim;
    private int nrInscrisi;


    public int getPretCurent() {
        return pretCurent;
    }

    public void setPretCurent(int pretCurent) {
        this.pretCurent = pretCurent;
    }

    private int pretCurent;

    public int getNrInscrisi() {
        return nrInscrisi;
    }

    public void setNrInscrisi(int nrInscrisi) {
        this.nrInscrisi = nrInscrisi;
    }

    public Licitatie(){
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNrParticipanti(int nrParticipanti) {
        this.nrParticipanti = nrParticipanti;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public void setNrPasiMaxim(int nrPasiMaxim) {
        this.nrPasiMaxim = nrPasiMaxim;
    }

    public int getId() {
        return id;
    }

    public int getNrParticipanti() {
        return nrParticipanti;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public int getNrPasiMaxim() {
        return nrPasiMaxim;
    }

}
