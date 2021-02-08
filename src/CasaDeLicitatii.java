import java.util.*;

public class CasaDeLicitatii{
    private List<Produs> produse = Collections.synchronizedList(new ArrayList<>());
    private List<Client> clienti = new ArrayList<>();
    private List<Licitatie> licitatii = new ArrayList<>();

    private List<Broker> brokers = new LinkedList<>();


    public void addProdus(Produs produs) {
        produse.add(produs);
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

    public void addClient(Client client) {
        clienti.add(client);
    }

    public void addBroker(Broker broker) {
        brokers.add(broker);
    }

    public void addLicitatie(Licitatie licitatie) {
        licitatii.add(licitatie);
    }

    public void adaugaProdus(Produs produs) {
        produse.add(produs);
    }

    public void stergeProdus(int idProdus) {
        produse.removeIf(produs -> produs.getID() == idProdus);
        listProduse();
    }

    public void listClients() {
        for (Client x : clienti) {
            System.out.println(x.toString());
        }
    }

    public void joinClient(int idClient, int idProdus, int pretMaxim) throws NotFoundAuction {

        Client client = null;
        for (Client clients : clienti) {
            if (clients.getId() == idClient) {
                client = clients;
            }
        }
        Broker broker = brokers.get(new Random().nextInt(brokers.size()));

        for (Licitatie licitatie : licitatii) {
            if (licitatie.getIdProdus() == idProdus) {
                licitatie.setNrInscrisi(licitatie.getNrInscrisi() + 1);
                broker.addClientBroker(new ClientBroker(client, pretMaxim, licitatie.getId()));
                broker.addClient(client);

                if (licitatie.getNrInscrisi() == licitatie.getNrParticipanti()) {

                    for (Produs produs : produse) {
                        if (produs.getID() == idProdus) {
                            startLicitatie(licitatie, produs);
                            return;
                        }
                    }
                }
                return;
            }
        }
        throw new NotFoundAuction();
    }


    public void primire(int pay, int idLicitatie) {

    }

    public void listProduse() {
        if (produse.size() == 0) {
            System.out.println("Nu sunt produse in casa de licitatii");
            return;
        }
        for (Produs produs : produse) {
            System.out.println(produs.toString());
        }
    }

    public void startLicitatie(Licitatie licitatie, Produs produs) {
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                Double maximum = 0.0;
                for (int i = 0; i < licitatie.getNrPasiMaxim(); i++) {
                    for (Broker broker : brokers) {
                        List<Double> list = broker.brokerPay(licitatie.getId());
                        if (list.size() == 0) continue;
                        Double auxMaxim = Collections.max(list);
                        if (auxMaxim > maximum) {
                            maximum = auxMaxim;
                        }
                    }

                    for (Broker broker : brokers) {
                        broker.adjustPay(maximum, licitatie.getId());
                    }

                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Pretul licitatiei cu id-ul " + licitatie.getId() + " este " + maximum);
                }

                if (produs.getPretMinim() < maximum) {
                    sfarsitLicitatie(licitatie, produs, maximum);
                } else {
                    System.out.println("Produsul nu s-a vandut!");
                }

            }
        };

        thread2.start();
    }

    public void sfarsitLicitatie(Licitatie licitatie, Produs produs, double pretCastigator){
        List<ClientBroker> winnersList = new ArrayList<>();
        int licitatiiCastigate = -1;
        int idWinner = 0;
        for (Broker broker : brokers) {
            winnersList = broker.winner(pretCastigator, licitatie.getId());
            if (winnersList.size() == 0) {
                continue;
            }
            for (ClientBroker clientBroker : winnersList) {
                if (clientBroker.getClient().getNrLicitatiiCastigate() > licitatiiCastigate) {
                    licitatiiCastigate = clientBroker.getClient().getNrLicitatiiCastigate();
                    idWinner = clientBroker.getClient().getId();
                }
            }
        }
        for (Broker broker1 : brokers) {
            broker1.update(idWinner, licitatie.getId(), produs.getID());
        }
        System.out.println("Produsul s-a vandut la pretul de " + pretCastigator + " clientului cu id-ul " + idWinner);
    }

}
