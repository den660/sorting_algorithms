package algorithms;

public interface SortingAlgorithm {

    public int[] sort(int[] numberArray);
    public int getComparisons();
    public int getArrayAccesses();
    public String getName();
    public void init();
}
