/*
Randomly place m bombm in H*W grid.

Idea: reservoir sampling.
time: O(H*W) -- iterate through the array once (regard 2d array as 1d)
space: O(m) to store the locations of m bombs is enough
*/
import java.util.*;
public class CreateBomb {

	private final int h;
	private final int w;
	private final int m;
	private int[] loc;
	private boolean[][] bombs;
	private Random rnd = new Random();

	public CreateBomb(int h, int w, int m) {
		this.h = h;
		this.w = w;
		this.m = m;
		loc = new int[m];
		bombs = new boolean[h][w];
	}

	public void placeBomb() {
		if (m >= h*w) {
			System.out.println("Invalid input.");
			return;
		}

		// 1. initialize the first m locations
		for (int i = 0; i < m; ++i) {
			loc[i] = i;
		}

		// 2. reservoir sampling and swapping indices
		for (int i = m; i < h*w; ++i) {
			int r = rnd.nextInt(i + 1);		// draw a number from [0, i]
			if (r < m) {
				loc[r] = i;
			}
		}

		// 3. return locations from 1d to 2d
		for (int pos : loc) {
			bombs[pos/w][pos%w] = true;
		}
	}

	public void display() {
		for (int i = 0; i < bombs.length; ++i) {
			for (int j = 0; j < bombs[0].length; ++j) {
				System.out.print(bombs[i][j] ? 'X' : '_');
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		CreateBomb cb = new CreateBomb(8, 8, 15);
		cb.placeBomb();
		cb.display();
	}
}