import java.util.*;

// Item class to store weight and value
class Item {
    int value, weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsack {

    // Function to calculate maximum value in Knapsack
    public static double getMaxValue(List<Item> items, int capacity) {
        // Sort items by value/weight ratio in descending order
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                double r1 = (double) a.value / a.weight;
                double r2 = (double) b.value / b.weight;
                if (r1 < r2) return 1;   // for descending order
                else if (r1 > r2) return -1;
                else return 0;
            }
        });

        double totalValue = 0.0;
        int currentWeight = 0;

        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                // take the whole item
                currentWeight += item.weight;
                totalValue += item.value;
            } else {
                // take fraction of the item
                int remaining = capacity - currentWeight;
                totalValue += item.value * ((double) remaining / item.weight);
                break; // Knapsack is full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item(60, 10));
        items.add(new Item(100, 20));
        items.add(new Item(120, 30));

        int capacity = 50;

        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum value in Knapsack = " + maxValue);
    }
}
