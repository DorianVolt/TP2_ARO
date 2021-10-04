

import java.util.ArrayList;
import java.util.List;

public class Solver {

    public ArrayList<ObjectB> bestItems = new ArrayList<>();
    float optimum = 0;
    int iterationCounter = 0;
    private ArrayList<ObjectB> currentIterationItems = new ArrayList<>();

    public int glouton(List<ObjectB> objects, Bag bag) {
        sortObjectsByRatio(objects);
        for (ObjectB object : objects) {
            bag.add(object);
        }
        return bag.getValue();
    }

    public static void sortObjectsByRatio(List<ObjectB> objects) {
        objects.sort((o1, o2) -> Float.compare(o2.getRatio(), o1.getRatio()));
    }


    public void Knapsack(int level, float weight, float profit, Bag bag, ArrayList<ObjectB> objects) {
        iterationCounter++;
        //Condition d'arret
        if (level == objects.size()) {
            if (optimum < profit) {
                optimum = profit;
                bestItems = new ArrayList<>(currentIterationItems);
            }
            return;
        }
        //On prend l'objet
        //On verifie qu'il ne depasse pas la capacité
        if (objects.get(level).getWeight() + weight <= bag.getCapacity()) {
            currentIterationItems.add(objects.get(level));
            Knapsack(level + 1, weight + objects.get(level).getWeight(), profit + objects.get(level).getValue(), bag, objects);
            currentIterationItems.remove(currentIterationItems.size() - 1);
        }
        //On verifie si explorer la branche vaut le coup
        List<ObjectB> nextItems = new ArrayList<>(objects).subList(level + 1, objects.size());
        nextItems.addAll(currentIterationItems);
        if (glouton(nextItems, new Bag(bag.getCapacity())) >= optimum) {
            Knapsack(level + 1, weight, profit, bag, objects);
        }
    }

    public float getOptimum() {
        return optimum;
    }

    public ArrayList<ObjectB> getChosen() {
        return currentIterationItems;
    }
}
