public class Tablou extends Produs {
    private String numePictor;
    private ColorsType culori;

    public Tablou(int id, String nume, int pretVanzare, int pretMinim, int an, ColorsType culori) {
        super(id, nume, pretVanzare, pretMinim, an);
        this.culori = culori;
    }
}
