

import java.util.ArrayList;
import java.util.List;

public class Solver {

    public ArrayList<ObjectB> bestItems = new ArrayList<>();
    float optimum = 0;
    int counter = 0;
    private ArrayList<ObjectB> currentIterationItems = new ArrayList<>();

    public static int solve(List<ObjectB> objects, Bag bag) {
        sortObjectsByRatio(objects);
        int value = 0;
        for (ObjectB object : objects) {
            value += object.getValue();

        }
        //bag.display();
        return value;
    }

    public static void sortObjectsByRatio(List<ObjectB> objects) {
        objects.sort((o1, o2) -> Float.compare(o2.getRatio(), o1.getRatio()));
    }


    //Q3
    public void Knapsack(int level, float weight, float profit, Bag bag, ArrayList<ObjectB> objects) {
        counter++;
        if (level == objects.size()) {
            if (optimum < profit) {
                optimum = profit;
                bestItems = new ArrayList<>(currentIterationItems);
                //System.out.println("J ai fini pd");
            }
            return;
        }
        //System.out.println(objects.get(level).getWeight() + weight);
        if (objects.get(level).getWeight() + weight <= bag.getCapacity()) {
            currentIterationItems.add(objects.get(level));
            //System.out.println("j'ai pris " + level);
            Knapsack(level + 1, weight + objects.get(level).getWeight(), profit + objects.get(level).getValue(), bag, objects);
            currentIterationItems.remove(currentIterationItems.size() - 1);
        }
        List<ObjectB> nextItems = new ArrayList<>(objects);
        nextItems = nextItems.subList(level + 1, objects.size());
        nextItems.addAll(currentIterationItems);
        //System.out.println("vavaavavavava " + solve(nextItems, bag));
        if (solve(nextItems, bag) >= optimum) {
            //System.out.println("je prend pas ta race "+level);
            Knapsack(level + 1, weight, profit, bag, objects);
        }
        else {
            //System.out.println("ANAL " +level);
        }

    }

    public float getOptimum() {
        return optimum;
    }

    public ArrayList<ObjectB> getChosen() {
        return currentIterationItems;
    }
}
