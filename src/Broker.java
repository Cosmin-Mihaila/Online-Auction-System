import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Broker extends Angajat{
    private List<Client> clienti = new ArrayList<>();
    private List<ClientBroker> clientiActivi = new ArrayList<>();

    public Broker(CasaDeLicitatii casaDeLicitatii){
        this.casaDeLicitatii = casaDeLicitatii;
        this.casaDeLicitatii.addBroker(this);
    }

    public void setProduse(CasaDeLicitatii produse) {
        this.produse = produse;
    }

    public void addClient(Client client){
        clienti.add(client);
    }

    public void addClientBroker(ClientBroker clientBroker){clientiActivi.add(clientBroker);}
    CasaDeLicitatii produse;

    @Override
    public void update(int idWinner, int idLicitatie) {
        for(ClientBroker clientBroker : clientiActivi){
            if(clientBroker.getIdLicitatie() != idLicitatie){
                continue;
            }
            if(clientBroker.getClient().getId() == idWinner){
                clientBroker.getClient().setNrLicitatiiCastigate(clientBroker.getClient().getNrLicitatiiCastigate() + 1);
                System.out.println("Castigatorul are id-ul " + clientBroker.getClient().getId());
            }
            clientBroker.getClient().setNrParticipari(clientBroker.getClient().getNrParticipari() + 1);
        }

    }

    public void listClients(){
        for(Client x : clienti){
            System.out.println(x.toString());
        }
    }

    public List<Integer> brokerPay(int idLicitatie){
        List<Integer> list = new ArrayList<>();
        for(ClientBroker clientBroker : clientiActivi){
                if(clientBroker.getIdLicitatie() != idLicitatie){
                    continue;
                }
                int payment = clientBroker.pay();
                list.add(payment);
        }

        return list;
    }

    public void adjustPay(int currentMax, int idLicitatie){
        for(ClientBroker clientBroker : clientiActivi){
            if(clientBroker.getIdLicitatie() != idLicitatie){
                continue;
            }
            clientBroker.setCurrentMax(currentMax);
        }
    }

    public List<ClientBroker> winner(int winPrice, int id, int licitatiiCastigate){
        List<ClientBroker> list = new ArrayList<>();
        int newLicitatiiCastigate = licitatiiCastigate;
        for(ClientBroker clientBroker : clientiActivi){
            if(clientBroker.getIdLicitatie() != id){
                continue;
            }
            if(clientBroker.getLastMax() == winPrice){
                list.add(clientBroker);
            }
        }

        return list;
    }
    //    @Override
//    public void run() {
////        try {
////            for (int i = 0; i < 30; i++) {
////                produse.adaugaProdus(new Produs(i));
////                System.out.println("Broker: " + i);
////                Thread.sleep(Math.abs(new Random().nextLong()) % 100);
////            }
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//    }
}
