public class PersoanaJuridica extends Client{
    private CompanyType companie;
    private double capitalSocial;

    public PersoanaJuridica(int id, String nume, String adresa,CompanyType companie, double capitalSocial) {
        super(id, nume, adresa);
        this.companie = companie;
        this.capitalSocial = capitalSocial;
    }
}
