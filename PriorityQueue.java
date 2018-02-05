import java.util.Arrays;


/**
 * 
 * Complexity:
 * O(nlogn)
 * 
 * BB:
 * 1
 * 
 */
public class PriorityQueue {

    final private int arr[];
    /**
     * The number of elements in the priority queue.
     */
    private int size;

    public PriorityQueue(int capacity) {
        arr = new int[capacity];
    }

    /*
     * https://www.cs.cmu.edu/~tcortina/15-121sp10/Unit06B.pdf
     * building min heap.
     */
    public void addAll(int[] arr) {
        for (int i : arr) {
            add(i);
        }
    }

    // O (logn)
    public void add(int value) {
        siftUp(value, size);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Integer peek( ) {
        if (size == 0) {
            return null;
        }
        return arr[0];
    }

    // O (logn)
    public Integer poll() {
        if (size == 0) {
            return null;
        }
        
        int returnValue = arr[0];
        int topValue = arr[size];
        siftDown(topValue, size);
        size--;
        
        return returnValue;
    }

    private void siftUp(int value, int index) {
        while (index > 0) { 
            int parent = (index - 1) / 2;
            if (value > arr[parent])  // comparator.compare(value, parentValue)
                break;
            arr[index] = arr[parent];
            index = parent;
        }
        arr[index] = value;
    }
    
    // pick smaller amongst my kids (where all kids are greater than me), and propagate it up.
    // keep doing it.
    private void siftDown(int topValue, int size) {
        int half = size / 2;    
        int parent = 0;
        
        while (parent < half) {
            int left = 2 * parent + 1;
            // size: 8.
            // arr: 0 1 2 3 4 5 6 7.
            // when parent = 3, then 3*2 + 2 = 8, ie right < size is not satisfied.
            int right = 2 * parent + 2;
 
            int nextParent = left;

            if (right < size && arr[right] <  arr[left]) {
                nextParent = right; // comparator.compare(arr[left], arr[right])
            };
         
            arr[parent] = arr[nextParent];
            parent = nextParent;
        }
        
        arr[parent] = topValue;
    }
   
}
