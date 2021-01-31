import java.util.Random;

public class ClientBroker {
    private Client client;
    private int pretMaxim;
    private int idLicitatie;

    public int getLastMax() {
        return lastMax;
    }

    public void setLastMax(int lastMax) {
        this.lastMax = lastMax;
    }

    private int lastMax;

    public int getCurrentMax() {
        return currentMax;
    }

    public void setCurrentMax(int currentMax) {
        this.currentMax = currentMax;
    }

    private int currentMax = 0;

    public ClientBroker(Client client, int pretMaxim, int idLicitatie){
        this.client = client;
        this.pretMaxim = pretMaxim;
        this.idLicitatie = idLicitatie;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getPretMaxim() {
        return pretMaxim;
    }

    public void setPretMaxim(int pretMaxim) {
        this.pretMaxim = pretMaxim;
    }

    public int getIdLicitatie() {
        return idLicitatie;
    }

    public void setIdLicitatie(int idLicitatie) {
        this.idLicitatie = idLicitatie;
    }

    public int pay(){
        int random = 0;
        while(random == 0){
            random = new Random().nextInt(3);
        }
       if(currentMax + (pretMaxim*random)/100 < pretMaxim) {
            lastMax = (currentMax + (pretMaxim*random) / 100);
           return (currentMax + (pretMaxim*random) / 100);
       }
       else{
           lastMax = pretMaxim;
           return pretMaxim;
       }
    }
}
