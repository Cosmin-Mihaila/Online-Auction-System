import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        List<Produs> produse = new LinkedList<>();
//        produse.add(new Produs(12));
//        produse.add(new Produs(123));
//        produse.add(new Produs(144));

        CasaDeLicitatii CDL = CasaDeLicitatii.getInstance();

        Administrator administrator = new Administrator(CDL);
//        CDL.setProduse(produse);

//        Licitatie licitatie = new LicitatieBuilder()
//                .withId(100)
//                .withIdProdus(123)
//                .withNrPasiMaxim(20)
//                .withNrParticipanti(3)
//                .build();
//
//        licitatie.setCDL(CDL);
//        CDL.addLicitatie(licitatie);
//
//        Licitatie licitatie2 = new LicitatieBuilder()
//                .withId(999)
//                .withIdProdus(144)
//                .withNrPasiMaxim(20)
//                .withNrParticipanti(3)
//                .build();
//
//        licitatie2.setCDL(CDL);
//        CDL.addLicitatie(licitatie2);

//        PersoanaFizica client1 = new PersoanaFizica(1, "Cosmin", "Targoviste", "2000");
//        PersoanaFizica client2 = new PersoanaFizica(2, "Cosmin2", "Targoviste", "2001");
//        PersoanaFizica client3 = new PersoanaFizica(3, "Cosmin3", "Targoviste", "2002");
//
//        CDL.addClient(client1);
//        CDL.addClient(client2);
//        CDL.addClient(client3);

//        CDL.listClients();

//        try {
//            CDL.joinClient(client1.getId(), 123, 1000);
//            CDL.joinClient(client2.getId(), 123, 1500);
//            CDL.joinClient(client3.getId(), 123, 2000);
//        }
//        catch (NotFoundAuction exception){
//            exception.printStackTrace();
//        }
//
//        try {
//            CDL.joinClient(client1.getId(), 144, 1000);
//            CDL.joinClient(client2.getId(), 144, 1000);
//            CDL.joinClient(client3.getId(), 144, 1000);
//        }
//        catch (NotFoundAuction exception){
//            exception.printStackTrace();
//        }
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
                    Broker broker = new Broker(CDL);
                } else if(newCommandsSplit[0].equals("newTablou")){
                    Tablou tablou = new Tablou(Integer.parseInt(newCommandsSplit[1]), newCommandsSplit[2], Integer.parseInt(newCommandsSplit[3]), Integer.parseInt(newCommandsSplit[4]), Integer.parseInt(newCommandsSplit[5]), ColorsType.valueOf(newCommandsSplit[6]));
                    CDL.addProdus(tablou);
                } else if(newCommandsSplit[0].equals("newLicitatie")){
                    Licitatie licitatie = new LicitatieBuilder()
                            .withId(Integer.parseInt(newCommandsSplit[1]))
                            .withNrParticipanti(Integer.parseInt(newCommandsSplit[2]))
                            .withNrPasiMaxim(Integer.parseInt(newCommandsSplit[3]))
                            .withIdProdus(Integer.parseInt(newCommandsSplit[4]))
                            .withCDL(CDL)
                            .build();
                    CDL.addLicitatie(licitatie);
                }

            }
        }

    }
}
