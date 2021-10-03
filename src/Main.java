import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        Bag bag = new Bag(0);
        ArrayList<ObjectB> objects = bag.createFromFile("./src/sacs/sac0.txt");
        /*ObjectB O1 = new ObjectB(15,3);
        ObjectB O2 = new ObjectB(20,6);
        ObjectB O3 = new ObjectB(8,1);
        ObjectB O4 = new ObjectB(10,4);
        ArrayList<ObjectB> objects = new ArrayList<>(List.of(O1,O2,O3,O4));*/

        Solver.sortObjectsByRatio(objects);


        Solver solver = new Solver();

        System.out.println("Question 1 et 2\n------------------------------------------\n");
        //System.out.println(Solver.solve(objects, bag));

        solver.Knapsack(0, 0, 0, bag, objects);
        System.out.println("Question 3: \n------------------------------------------\n" + Bag.RED + "Optimum de l'algo recursif: " + solver.getOptimum() + Bag.RESET);
        float wiegth = 0;
        for (ObjectB objectB : solver.bestItems) {
            wiegth += objectB.getWeight();
            objectB.display();
        }
        System.out.println(wiegth);
        System.out.println(solver.counter);


    }

}
