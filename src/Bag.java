import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Bag {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";

    private int capacity;
    private int used;
    private int value;
    private ArrayList<ObjectB> objects;

    public Bag(int capacity) {
        this.objects = new ArrayList<>();
        this.capacity = capacity;
        this.used = 0;
        this.value = 0;
    }

    public void add(ObjectB object) {
        boolean canAdd = used + object.getWeight() <= capacity;
        if (canAdd) {
            used += object.getWeight();
            objects.add(object);
            value += object.getValue();
        } else {
            float multiplactor = (capacity - used) / (object.getWeight());
            if (multiplactor != 0) {
                ObjectB objectB = new ObjectB(multiplactor * object.getWeight(), object.getValue() * multiplactor);
                objects.add(objectB);
                used += objectB.getWeight();
                value += object.getValue();
            }
        }
    }

    public ArrayList<ObjectB> getObjects() {
        return objects;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getUsed() {
        return used;
    }

    public int getValue() {
        this.value = 0;
        for (ObjectB object : objects) {
            value += object.getValue();
        }
        return value;
    }

    public void display() {
        System.out.println("Dans le sac de capacité " + capacity + " il y a " + objects.size() + " objets");
        int i = 1;
        int totalValue = 0;
        int totalWeight = 0;
        for (ObjectB object : objects) {
            System.out.print("Object n°" + i + " :");
            object.display();
            totalValue += object.getValue();
            totalWeight += object.getWeight();
            i++;
        }
        System.out.println("Certains objets peuvent etre fractionnés");
        System.out.println("Pour une valeur totale de " + totalValue + " et un poids total de " + totalWeight);
        System.out.println(RED + "Optimum fractionnaire = " + totalValue + RESET + "\n");

    }

    public ArrayList<ObjectB> createFromFile(String fileName) throws IOException {
        ArrayList<ObjectB> objects = new ArrayList<>();
        File file = new File(fileName);
        int lineCount = 0;
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            String line = br.readLine();

            while (line != null) {
                if (lineCount == 0) {
                    this.capacity = Integer.parseInt(line);
                    lineCount++;
                } else {
                    float weight = Float.parseFloat(line.split(" ")[0]);
                    float value = Float.parseFloat(line.split(" ")[1]);
                    objects.add(new ObjectB(weight, value));
                }
                line = br.readLine();
            }
        } finally {
            br.close();
        }

        return objects;
    }
}
