/*146. LRU Cache
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Idea: Hash table for O(1) lookups and doubly linked list for O(1) insert/delete
1. if get / put is called, and the key is in the map:
	- delete the node and insert it just before tail
2. if new element comes in and over capacity:
	- delete head.next (LRU element) and insert newcomer just before tail
*/

import java.util.*;

public class LRUCache_146 {

	private int capacity;
	private Map<Integer, Node> map;
	private Node head, tail;	// two dummy nodes

	private class Node {
		int key, val;
		Node prev, next;
		Node(int key, int val) {
			this.key = key;
			this.val = val;
		}
	}

    public LRUCache_146(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
        	System.out.println(-1);
        	return -1;
        }
        Node x = map.get(key);
        delete(x);
        insertAtTail(x);
        System.out.println(x.val);
        return x.val;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
        	Node x = map.get(key);
        	delete(x);
        } else if (map.size() == capacity) {
        	map.remove(head.next.key);
        	delete(head.next);
        }
        Node y = new Node(key, value);
        insertAtTail(y);
        map.put(key, y);
    }

    private void delete(Node x) {
    	// don't use on head or tail
    	x.prev.next = x.next;
    	x.next.prev = x.prev;
    }

    private void insertAtTail(Node x) {
    	Node temp = tail.prev;
    	tail.prev = x;
    	x.prev = temp;
    	temp.next = x;
    	x.next = tail;
    }

    public static void main(String[] args) {
    	LRUCache_146 lru = new LRUCache_146(4);
    	lru.put(1, 1);
    	lru.get(1);
    	lru.put(2, 2);
    	lru.put(3, 3);
    	lru.put(4, 4);
    	lru.get(1);
    	lru.put(5, 5);
    	lru.get(6);
    	lru.get(3);
    	lru.put(6, 6);
    	lru.get(2);
    }
}