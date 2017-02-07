/*380. Insert Delete GetRandom O(1)

Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
*/

public class RandomizedSet_380 {

    private Random rnd;

    // ArrayList supports amortized constant time add, but linear time remove -- need to record index
    private List<Integer> list;     
    private Map<Integer, Integer> map;

    /** Initialize your data structure here. */
    public RandomizedSet_380() {
        rnd = new Random();
        list = new ArrayList<Integer>();
        map = new HashMap<Integer, Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        // swap the value with the last element in list --> O(1) remove, otherwise O(n)
        exch(list, map.get(val), list.size() - 1);
        map.remove(val, list.size() - 1);
        list.remove(list.size() - 1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int idx = rnd.nextInt(list.size());
        return list.get(idx);
    }

    private void exch(List<Integer> a, int i, int j) {
        // swap and update the index_map
        int temp = a.get(i);

        a.set(i, a.get(j));
        map.put(a.get(j), i);

        a.set(j, temp);
        map.put(temp, j);
    }
}

// Follow up: what if duplicates are allowed?
// Use HashMap<Integer, Set<Integer>> to store the key's indices