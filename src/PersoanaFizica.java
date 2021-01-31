public class PersoanaFizica extends Client {
    private String dataNastere;

    public PersoanaFizica(){
        super();
    }

    public PersoanaFizica(int id, String nume, String adresa, String dataNastere) {
        super(id, nume, adresa);
        this.dataNastere = dataNastere;
    }

//    @Override
//    public String toString() {
//        return "PersoanaFizica{" +
//                "dataNastere='" + dataNastere + '\'' +
//                '}';
//    }
}
