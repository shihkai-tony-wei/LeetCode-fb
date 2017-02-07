/*
Task schedules with cooldown.
- each task takes time 1 to finish
- each task has a cooldown

e.g. task = [1,2,3,2,3], cd = 3
then 1 2 3 _ _ 2 3 ==> return 7


Idea: use a map to store (key, value) = (task #, earliest next starting time(inclusive))
There are two types
1. task in cooldown; 2. not in cooldown
**************************************************
Do a if condition, BUT NO ELSE block!!!! 
Update the map and do the task in EVERY LOOP!!!
**************************************************
*/
import java.util.*;

public class TaskWithCooldown {
	// 1. return the total time
	public static int schedule1(int[] tasks, int cooldown) {
		if (tasks == null || tasks.length == 0) return 0;
		Map<Integer, Integer> map = new HashMap<>();
		// curr represents the time slot for the current task
		int curr = 0;
		for (int task : tasks) {
			if (map.containsKey(task) && map.get(task) > curr) {
				// if this job is still in cooldown, update curr to just before the next start time
				curr = map.get(task);
			}
			// update the map
			map.put(task, curr + cooldown + 1);

			// and do the current task for 1 unit time
			++curr;
		}
		return curr;
	}

	// 2. return the path of the schedule
	public static String schedule2(int[] tasks, int cooldown) {
		int curr = 0;
		Map<Integer, Integer> map = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		for (int task : tasks) {
			if (map.containsKey(task) && curr < map.get(task)) {
				for (int i = curr + 1; i < map.get(task); ++i)
					sb.append("_,");
				curr = map.get(task);
			}
			map.put(task, curr + cooldown + 1);
			++curr;
			// add current task..
			sb.append(task).append(',');
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	/* 3. if tasks can be reordered, find the minimum totoal time needed
	Idea: Fill in the task(s) with Highest frequency(s), 
	and fill in the empty slots for less frequent tasks.
	Final length = (# of blocks) * (size of each block) + extra 1 set of max tasks
	Final length should also be at least as long as tasks input.

	time O(n), space O(n)
	corner case: multiple tasks have the same frequency

	e.g. cooldown = 3; tasks = [1,1,1,2,2,2,3,4]
	1 2 _ _ || 1 2 _ _ || 1 2
	 					 extra 
	*/

	public static int schedule3(int[] tasks, int cooldown) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int task : tasks) {
			map.put(task, map.getOrDefault(task, 0) + 1);
		}

		int maxFreq = 0;
		int numOfMax = 0;	// number of tasks with same maxFreq
		for (int task : map.keySet()) {
			if (map.get(task) > maxFreq) {
				maxFreq = map.get(task);
				numOfMax = 1;
			} else if (map.get(task) == maxFreq) {
				++numOfMax;
			}
		}
		// if the rest fill in empty slots
		int canFill = (maxFreq - 1) * (cooldown + 1) + numOfMax;
		return Math.max(canFill, tasks.length);
	}

	public static void main(String[] args) {
		int[] task = {1,2,2,3,2,3};
		int cd = 3;
		int totalTime = TaskWithCooldown.schedule1(task, cd);
		String path = TaskWithCooldown.schedule2(task, cd);
		int minTime = TaskWithCooldown.schedule3(task, cd);
		System.out.println("totalTime = " + totalTime);
		System.out.println(path);
		System.out.println("minimum time = " + minTime);
	}
}