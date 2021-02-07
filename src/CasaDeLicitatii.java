import java.util.*;

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
    public void stergeProdus(int idProdus){
        produse.removeIf(produs -> produs.getID() == idProdus);
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
                    Thread thread2 = new Thread() {
                        @Override
                        public void run() {
                            Integer maximum = 0;
                            for(int i = 0; i < licitatie.getNrPasiMaxim(); i ++){
                                for(Broker broker : brokers){
                                    List<Integer> list = broker.brokerPay(licitatie.getId());
                                    if(list.size() == 0) continue;
                                    Integer auxMaxim = Collections.max(list);
                                    if(auxMaxim > maximum){
                                        maximum = auxMaxim;
                                    }
                                }

                                for(Broker broker : brokers){
                                    broker.adjustPay(maximum, licitatie.getId());
                                }

                                try {
                                    sleep(200);
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
                                            winnersList = broker.winner(maximum, id);
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
                                    //this.CDL.stergeProdus();
                                    return;
                                }
                            }
                        }
                    };
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

    public void listProduse(){
        if(produse.size() == 0){
            System.out.println("Nu sunt produse in casa de licitatii");
            return;
        }
        for(Produs produs : produse){
            System.out.println(produs.toString());
        }
    }

}
