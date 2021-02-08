public class Tablou extends Produs {
    private final String numePictor;
    private final ColorsType culori;

    public Tablou(int id, String nume, int pretVanzare, int pretMinim, int an, String numePictor, ColorsType culori) {
        super(id, nume, pretVanzare, pretMinim, an);
        this.numePictor = numePictor;
        this.culori = culori;
    }

    @Override
    public String toString() {
        return "Tablou{" +
                "numePictor='" + numePictor + '\'' +
                ", culori=" + culori +
                '}';
    }
}
