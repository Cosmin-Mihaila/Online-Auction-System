import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CasaDeLicitatii implements Runnable{
    private int capacitate = 20;
    private List<Produs> produse = new ArrayList<>();
    private List<Client> clienti = new ArrayList<>();
    private List<Licitatie> licitatii = new ArrayList<>();

    private List<Broker> brokers = new LinkedList<>();

    public int getCapacitate() {
        return capacitate;
    }

    public void addProdus(Produs produs){
        produse.add(produs);
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public List<Client> getClienti() {
        return clienti;
    }

    public void setClienti(List<Client> clienti) {
        this.clienti = clienti;
    }

    public List<Licitatie> getLicitatii() {
        return licitatii;
    }

    public void setLicitatii(List<Licitatie> licitatii) {
        this.licitatii = licitatii;
    }

    public List<Broker> getBrokers() {
        return brokers;
    }

    public void setBrokers(List<Broker> brokers) {
        this.brokers = brokers;
    }

    public static void setInstance(CasaDeLicitatii instance) {
        CasaDeLicitatii.instance = instance;
    }

    /*Implementam Singleton pattern*/
    private static CasaDeLicitatii instance;

    public static CasaDeLicitatii getInstance() {
        if (instance == null) {
            instance = new CasaDeLicitatii();
        }
        return instance;
    }

    public void setProduse(List<Produs> produse) {
        this.produse = produse;
    }

    public void addClient(Client client){
        clienti.add(client);
    }

    public void addBroker(Broker broker){
        brokers.add(broker);
    }

    public void addLicitatie(Licitatie licitatie){licitatii.add(licitatie);}

//    Lock lock = new ReentrantLock();
//    Condition neVid = lock.newCondition();
//    Condition nePlin = lock.newCondition();

//    public void adaugaProdus(Produs produs) {
//        lock.lock();
//        try {
//            while (capacitate == produse.size()) {
//                nePlin.await();
//            }
//
//            produse.add(produs);
//            neVid.signal();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        finally {
//            lock.unlock();
//        }
//    }
//
//    public Produs stergeProdus() {
//        lock.lock();
//        Produs out = new Produs();
//
//        try {
//            while (produse != null && produse.size() == 0) {
//                neVid.await();
//            }
//
//            assert produse != null;
//            out = produse.remove(produse.size() - 1);
//            nePlin.signal();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
//
//        return out;
//    }

    public void adaugaProdus(Produs produs){
        produse.add(produs);
    }

    public void listClients(){
        for(Client x : clienti){
            System.out.println(x.toString());
        }
    }

    public void joinClient(int idClient, int idProdus, int pretMaxim) throws NotFoundAuction{

        Client client= null;
        for(Client clients : clienti){
            if(clients.getId() == idClient) {
                client = clients;
            }
        }
        Broker broker = brokers.get(new Random().nextInt(brokers.size()));

        for(Licitatie licitatie : licitatii){
            if(licitatie.getIdProdus() == idProdus){
                licitatie.setNrInscrisi(licitatie.getNrInscrisi() + 1);
                broker.addClientBroker(new ClientBroker(client, pretMaxim, licitatie.getId()));
                broker.addClient(client);
                if(licitatie.getNrInscrisi() == licitatie.getNrParticipanti()){
                    notifyAllBrokers(licitatie.getId());
                    licitatie.setBrokers(brokers);
                    Thread thread = new Thread(licitatie);
                    thread.start();
                }
                return;
            }
        }
        throw new NotFoundAuction();
    }

    public void notifyAllBrokers(int idLicitatie){
//        for(Broker broker : brokers){
//            broker.update(idLicitatie, 10);
//        }
    }

    @Override
    public void run(){

    }

    public void primire(int pay, int idLicitatie){

    }

}
