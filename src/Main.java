import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        List<Integer> expected = List.of(103, 2077672, 2095878, 2132531, 2166542);

        Bag bag = new Bag(0);
        for (int i = 0; i <= 4; i++) {
            System.out.println("\nResolution sac n°" + i + " : ");

            ArrayList<ObjectB> objects = bag.createFromFile("./src/sacs/sac" + i + ".txt");

            Solver.sortObjectsByRatio(objects);
            Solver solver = new Solver();
            solver.Knapsack(0, 0, 0, bag, objects);

            System.out.println(Bag.YELLOW + "Resultat attendu: " + expected.get(i) + Bag.RESET);
            System.out.println(Bag.RED + "Optimum de l'algo recursif: " + solver.getOptimum() + Bag.RESET);
            float poid = 0;
            for (ObjectB objectB : solver.bestItems) {
                poid += objectB.getWeight();
            }
            System.out.println("Capcité du sac : " + bag.getCapacity());
            System.out.println("Poid total: " + poid);
            System.out.println("Itérations : " + solver.counter + "\n");
        }


    }
/*ObjectB O1 = new ObjectB(15,3);
        ObjectB O2 = new ObjectB(20,6);
        ObjectB O3 = new ObjectB(8,1);
        ObjectB O4 = new ObjectB(10,4);
        ArrayList<ObjectB> objects = new ArrayList<>(List.of(O1,O2,O3,O4));*/
}
