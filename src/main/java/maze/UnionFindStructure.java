package maze;

public class UnionFindStructure {
    private final int[] elements;
    private final int[] sizes;

    public UnionFindStructure(int size) {
        elements = new int[size];
        sizes = new int[size];
        for (int i = 0; i < size; i++) {
            elements[i] = i;
            sizes[i] = 1;
        }
    }

    public void union(int i, int j) {
        i = root(i);
        j = root(j);
        if (sizes[i] <= sizes[j]) {
            elements[i] = j;
            sizes[j] += sizes[i];
        } else {
            elements[j] = i;
            sizes[i] += sizes[j];
        }
    }

    public boolean inOneSet(int i, int j) {
        return root(i) == root(j);
    }

    public int root(int i) {
        while (elements[i] != i) {
            i = elements[i];
        }
        return i;
    }

}
