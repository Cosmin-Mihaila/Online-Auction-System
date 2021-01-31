import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class Licitatie implements Runnable {
    private int id;
    private int nrParticipanti;
    private int idProdus;
    private int nrPasiMaxim;
    private int nrInscrisi;

    private List<Broker> brokers = new ArrayList<>();

    public CasaDeLicitatii getCDL() {
        return CDL;
    }

    public void setCDL(CasaDeLicitatii CDL) {
        this.CDL = CDL;
    }

    private CasaDeLicitatii CDL;

    public void setBrokers(List<Broker> brokers) {
        this.brokers = brokers;
    }

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

    public void startLicitatie(List<Broker> brokers){
//        ExecutorService executorSerivve = Executors.newFixedThreadPool(nrParticipanti);
        setBrokers(brokers);
    }

    @Override
    public void run() {
        Integer maximum = 0;
        for(int i = 0; i < nrPasiMaxim; i ++){
            for(Broker broker : brokers){
                List<Integer> list = broker.brokerPay(id);
                if(list.size() == 0) continue;
                Integer auxMaxim = Collections.max(list);
                if(auxMaxim > maximum){
                    maximum = auxMaxim;
                }
            }

            for(Broker broker : brokers){
                broker.adjustPay(maximum, id);
            }

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Pretul licitatiei cu id-ul " + id + " este " + maximum);
        }

        for(Produs produs : CDL.getProduse()){
            if(idProdus == produs.getID()){
                if(produs.getPretMinim() < maximum){

                    List<ClientBroker> winnersList = new ArrayList<>();
                    int licitatiiCastigate = -1;
                    int idWinner = 0;
                    for(Broker broker : brokers){
                        winnersList = broker.winner(maximum, id, licitatiiCastigate);
                        if(winnersList.size() == 0){
                            continue;
                        }
                        for(ClientBroker clientBroker : winnersList){
                            if(clientBroker.getClient().getNrLicitatiiCastigate() > licitatiiCastigate){
                                licitatiiCastigate = clientBroker.getClient().getNrLicitatiiCastigate();
                                idWinner = clientBroker.getClient().getId();
                            }
                        }
                    }

                    notifyWinner(idWinner, id);
                    System.out.println("Produsul s-a vandut la pretul de " + maximum + " clientului cu id-ul " + idWinner);
                }
                else{
                    System.out.println("Produsul nu s-a vandut!");
                }
            }
        }
    }

    public void notifyWinner(int idWinner, int idLicitatie){
        for(Broker broker : brokers){
            broker.update(idWinner, idLicitatie);
        }
    }
}
