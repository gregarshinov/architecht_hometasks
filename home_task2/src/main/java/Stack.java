import java.util.ArrayList;

public class Stack<T> {
    private ArrayList<T> sequence;

    public Stack() {
        sequence = new ArrayList<>();
    }

    public void append(T element) {
        sequence.add(element);
    }

    public T peep() {
        if (this.size() != 0) {
            return sequence.get(sequence.size() - 1);
        }
        return null;
    }

    public T pop() {
        if (this.size() != 0) {
            T element = this.peep();
            sequence.remove(sequence.size() - 1);
            return element;
        }
        return null;
    }

    public int size() {
        return sequence.size();
    }
}
