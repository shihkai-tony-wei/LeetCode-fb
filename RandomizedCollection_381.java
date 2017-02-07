/*380. Insert Delete GetRandom O(1) - duplicates allowed
*/

public class RandomizedCollection_381 {

    private Random rnd;

    // ArrayList supports amortized constant time add, but linear time remove -- need to record index
    private List<Integer> list;     
    private Map<Integer, Set<Integer>> map;

    /** Initialize your data structure here. */
    public RandomizedCollection_381() {
        rnd = new Random();
        list = new ArrayList<Integer>();
        map = new HashMap<Integer, Set<Integer>>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean flag = map.containsKey(val);
        list.add(val);
        if (!flag) {
            Set<Integer> set = new HashSet<>();
            map.put(val, set);
        }
        map.get(val).add(list.size() - 1);
        return !flag;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        // swap the value with the last element in list --> O(1) remove, otherwise O(n)
        int idx = map.get(val).iterator().next();   // the only way to get elements from hashset!!!
        map.get(val).remove(idx);

        if (idx < list.size() - 1) {    // otherwise skip
            int last = list.get(list.size() - 1);
            list.set(idx, last);
            map.get(last).remove(list.size() - 1);
            map.get(last).add(idx);
        }

        list.remove(list.size() - 1);
        if (map.get(val).isEmpty())
            map.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int idx = rnd.nextInt(list.size());
        return list.get(idx);
    }
}