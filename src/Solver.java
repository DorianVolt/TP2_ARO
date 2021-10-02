import java.util.ArrayList;

public class Solver {

    public ArrayList<ObjectB> chosen2 = new ArrayList<>();
    float optimum = 0;
    private ArrayList<ObjectB> chosen = new ArrayList<>();

    public static void solve(ArrayList<ObjectB> objects, Bag bag) {
        sortObjectsByRatio(objects);
        for (ObjectB object : objects) {
            bag.add(object);
        }
        bag.display();
    }

    public static void sortObjectsByRatio(ArrayList<ObjectB> objects) {
        objects.sort((o1, o2) -> Float.compare(o2.getRatio(), o1.getRatio()));
    }


    //Q3
    public void Knapsack(int level, float weight, float profit, Bag bag, ArrayList<ObjectB> objects) {
        if (level == objects.size() - 1) {
            if (optimum < profit) {
                optimum = profit;
                chosen2 = new ArrayList<>(chosen);
            }
            return;
        }
        if (objects.get(level).getWeight() + weight <= bag.getCapacity()) {
            chosen.add(objects.get(level));
            Knapsack(level + 1, weight + objects.get(level).getWeight(), profit + objects.get(level).getValue(), bag, objects);
            chosen.remove(chosen.size() - 1);
        }
        Knapsack(level + 1, weight, profit, bag, objects);


    }

    public float getOptimum() {
        return optimum;
    }

    public ArrayList<ObjectB> getChosen() {
        return chosen;
    }
}
