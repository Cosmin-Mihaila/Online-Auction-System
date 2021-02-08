import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        CasaDeLicitatii CDL = CasaDeLicitatii.getInstance();

        Administrator administrator = new Administrator();

        if(true) {
            File myObj = new File("rulare.txt");
            Scanner myReader = new Scanner(myObj);
            while (true) {
                String newCommand = myReader.nextLine();
                String[] newCommandsSplit = newCommand.split(" ", 100);
                if (newCommandsSplit[0].equals("quit") || newCommandsSplit[0].equals("exit")) {
                    break;
                } else if (newCommandsSplit[0].equals("newPF")) {
                    PersoanaFizica persoanaFizica = new PersoanaFizica(Integer.parseInt(newCommandsSplit[1]), newCommandsSplit[2], newCommandsSplit[3], newCommandsSplit[4]);
                    CDL.addClient(persoanaFizica);
                } else if (newCommandsSplit[0].equals("join")) {
                    try {
                        CDL.joinClient(Integer.parseInt(newCommandsSplit[1]), Integer.parseInt(newCommandsSplit[2]), Integer.parseInt(newCommandsSplit[3]));
                    } catch (NotFoundAuction exception) {
                        exception.printStackTrace();
                    }
                } else if(newCommandsSplit[0].equals("newBroker")){
                    Broker broker = new Broker();
                } else if(newCommandsSplit[0].equals("newTablou")){
                    administrator.addProdus(new Tablou(Integer.parseInt(newCommandsSplit[1]), newCommandsSplit[2], Integer.parseInt(newCommandsSplit[3]), Integer.parseInt(newCommandsSplit[4]), Integer.parseInt(newCommandsSplit[5]),newCommandsSplit[6], ColorsType.valueOf(newCommandsSplit[7])));
                } else if(newCommandsSplit[0].equals("newLicitatie")){
                    Licitatie licitatie = new LicitatieBuilder()
                            .withId(Integer.parseInt(newCommandsSplit[1]))
                            .withNrParticipanti(Integer.parseInt(newCommandsSplit[2]))
                            .withNrPasiMaxim(Integer.parseInt(newCommandsSplit[3]))
                            .withIdProdus(Integer.parseInt(newCommandsSplit[4]))
                            .build();
                    CDL.addLicitatie(licitatie);
                }
                else if(newCommandsSplit[0].equals("newMobila")){
                    administrator.addProdus(new Mobila(Integer.parseInt(newCommandsSplit[1]), newCommandsSplit[2], Integer.parseInt(newCommandsSplit[3]), Integer.parseInt(newCommandsSplit[4]), Integer.parseInt(newCommandsSplit[5]),newCommandsSplit[6], newCommandsSplit[7]));
                }
                else if(newCommandsSplit[0].equals("newBijuterie")){
                    administrator.addProdus(new Bijuterie(Integer.parseInt(newCommandsSplit[1]), newCommandsSplit[2], Integer.parseInt(newCommandsSplit[3]), Integer.parseInt(newCommandsSplit[4]), Integer.parseInt(newCommandsSplit[5]),newCommandsSplit[6], Boolean.parseBoolean(newCommandsSplit[7])));
                }
            }
        }

    }
}
