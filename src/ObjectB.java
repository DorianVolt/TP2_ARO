public class ObjectB {


    private float ratio;
    private float weight;
    private float value;

    public ObjectB(float weight, float value) {
        this.weight = weight;
        this.value = value;
        this.ratio = value / weight;
    }

    public float getWeight() {
        return weight;
    }

    public float getValue() {
        return value;
    }

    public float getRatio() {
        return ratio;
    }


    public void display() {
        System.out.println("Value: " + getValue() + " | Weight: " + getWeight());
    }
}
