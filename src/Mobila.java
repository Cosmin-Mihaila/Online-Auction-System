public class Mobila extends Produs{
    private String tip;
    private String material;

    public Mobila(int id, String nume, int pretVanzare, int pretMinim, int an, String tip, String material) {
        super(id, nume, pretVanzare, pretMinim, an);
        this.tip = tip;
        this.material = material;
    }
}
