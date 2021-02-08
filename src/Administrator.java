public class Administrator extends Angajat implements Runnable{
    @Override
    public void update(int idWinner, int idLicitatie, int idProdus) {

    }

    public Administrator(){
    }

    @Override
    public void run() {

    }

    public void addProdus(Produs produs){
        CasaDeLicitatii.getInstance().addProdus(produs);
    }
}
