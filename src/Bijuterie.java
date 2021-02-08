public class Bijuterie extends Produs{
    private String material;
    private boolean piatraPretioasa;

    public Bijuterie(int id, String nume, int pretVanzare, int pretMinim, int an, String material, boolean piatraPretioasa) {
        super(id, nume, pretVanzare, pretMinim, an);
        this.material = material;
        this.piatraPretioasa = piatraPretioasa;
    }

    public Bijuterie(int id, String nume, String material, boolean piatraPretioasa) {
        super(id, nume);
        this.material = material;
        this.piatraPretioasa = piatraPretioasa;
    }
}
