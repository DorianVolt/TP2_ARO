import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        Bag bag = new Bag(0);
        ArrayList<ObjectB> objects = bag.createFromFile("./src/sacs/sac0.txt");

        Solver.sortObjectsByRatio(objects);
        Solver solver = new Solver();

        System.out.println("Question 1 et 2\n------------------------------------------\n");
        Solver.solve(objects, bag);

        solver.Knapsack(1, objects.get(0).getWeight(), objects.get(0).getValue(), bag, objects);
        System.out.println("Question 3: \n------------------------------------------\n" + Bag.RED + "Optimum de l'algo recursif: " + solver.getOptimum() + Bag.RESET);
        for (ObjectB objectB : solver.chosen2) {
            objectB.display();
        }


    }

}
