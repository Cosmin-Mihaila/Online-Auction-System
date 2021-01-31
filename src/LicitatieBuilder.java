public class LicitatieBuilder {
    private Licitatie licitatie = new Licitatie();

    public LicitatieBuilder withId(int id){
        licitatie.setId(id);
        return this;
    }

    public LicitatieBuilder withNrParticipanti(int nrParticipanti){
        licitatie.setNrParticipanti(nrParticipanti);
        return this;
    }

    public LicitatieBuilder withIdProdus(int idProdus){
        licitatie.setIdProdus(idProdus);
        return this;
    }

    public LicitatieBuilder withNrPasiMaxim(int nrPasiMaxim){
        licitatie.setNrPasiMaxim(nrPasiMaxim);
        return this;
    }

    public LicitatieBuilder withCDL(CasaDeLicitatii casaDeLicitatii){
        licitatie.setCDL(casaDeLicitatii);
        return this;
    }

    public Licitatie build() {
        return licitatie;
    }
}
