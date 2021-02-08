import java.util.Random;

public class ClientBroker {
    private Client client;
    private int pretMaxim;
    private int idLicitatie;
    private double lastMax;
    private double comision;
    public double getLastMax() {
        return lastMax;
    }

    public void updateComision(){
        double comision = 0;
        if(client instanceof PersoanaFizica && client.getNrParticipari() < 5){
            comision = (20.0 / 100.0) * lastMax;
        }
        else if(client instanceof PersoanaFizica && client.getNrParticipari() > 5){
            comision = (15.0 /100.0) * lastMax;
        }
        else if(client instanceof PersoanaJuridica && client.getNrParticipari() < 25){
            comision = (25.0 / 100.0) * lastMax;
        }
        else if(client instanceof  PersoanaJuridica && client.getNrParticipari() > 25){
            comision = (10.0 / 100.0) * lastMax;
        }
        this.comision = comision;
    }

    public void setLastMax(int lastMax) {
        this.lastMax = lastMax;
    }

    public double getCurrentMax() {
        return currentMax;
    }

    public void setCurrentMax(double currentMax) {
        this.currentMax = currentMax;
    }

    private double currentMax = 0.0;

    public ClientBroker(Client client, int pretMaxim, int idLicitatie) {
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

    public double pay() {
        int random = 0;
        while (random == 0) {
            random = new Random().nextInt(4);
        }
        if ( + (pretMaxim * random) / 100 < pretMaxim) {
            lastMax = (currentMax + (pretMaxim * random) / 100);
            updateComision();
            return lastMax - comision;
        } else {
            lastMax = pretMaxim;
            updateComision();
            return pretMaxim - comision;
        }
    }
}
