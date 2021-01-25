import java.util.List;

public class CasaDeLicitatii {
    private List<? extends Produs> produse;
    private List<? extends Client> clienti;
    private List<? extends Licitatie> licitatii;

    /*Implementam Singleton pattern*/
    private static CasaDeLicitatii instance;

    public static CasaDeLicitatii getInstance() {
        if (instance == null) {
            instance = new CasaDeLicitatii();
        }
        return instance;
    }


}
