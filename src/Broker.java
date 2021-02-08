import java.util.*;

public class Broker extends Angajat{
    private List<Client> clienti = new ArrayList<>();
    private List<ClientBroker> clientiActivi = Collections.synchronizedList(new ArrayList<>());

    public Broker(){
        CasaDeLicitatii.getInstance().addBroker(this);
    }

    public void addClient(Client client){
        clienti.add(client);
    }

    public void addClientBroker(ClientBroker clientBroker){clientiActivi.add(clientBroker);}

    @Override
    public void update(int idWinner, int idLicitatie, int idProdus) {
        for(ClientBroker clientBroker : clientiActivi){
            if(clientBroker.getIdLicitatie() != idLicitatie){
                continue;
            }
            if(clientBroker.getClient().getId() == idWinner){
                CasaDeLicitatii.getInstance().stergeProdus(idProdus);
                clientBroker.getClient().setNrLicitatiiCastigate(clientBroker.getClient().getNrLicitatiiCastigate() + 1);
                System.out.println("Castigatorul are id-ul " + clientBroker.getClient().getId());
            }
            clientBroker.getClient().setNrParticipari(clientBroker.getClient().getNrParticipari() + 1);
        }

        clientiActivi.removeIf(clientBr -> clientBr.getIdLicitatie() == idLicitatie);
        updateClientList();
    }

    public void updateClientList(){
        List<Client> toDelete = new ArrayList<>();
        for(Client client : clienti){
            boolean found = false;
            for(ClientBroker clientBroker : clientiActivi){
                if (clientBroker.getClient().equals(client)) {
                    found = true;
                    break;
                }
            }

            if(!found){
                toDelete.add(client);
            }
        }

        clienti.removeAll(toDelete);

        listClients();
    }
    public void listClients(){
        if(clienti.size() == 0){
            System.out.println("Acest broker nu are niciun client");
            return;
        }
        for(Client x : clienti){
            System.out.println(x.toString());
        }
    }

    public List<Double> brokerPay(int idLicitatie){
        List<Double> list = new ArrayList<>();
        for(ClientBroker clientBroker : clientiActivi){
                if(clientBroker.getIdLicitatie() != idLicitatie){
                    continue;
                }
                double payment = clientBroker.pay();
                list.add(payment);
        }

        return list;
    }

    public void adjustPay(double currentMax, int idLicitatie){
        for(ClientBroker clientBroker : clientiActivi){
            if(clientBroker.getIdLicitatie() != idLicitatie){
                continue;
            }
            clientBroker.setCurrentMax(currentMax);
        }
    }

    public List<ClientBroker> winner(double winPrice, int id){
        List<ClientBroker> list = new ArrayList<>();
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
}
