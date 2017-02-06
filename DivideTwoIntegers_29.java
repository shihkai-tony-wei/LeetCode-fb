/*
LC 29. Divide Two Integers
Without using *, / , %

Idea: bit manipulation -- Long division on binary numbers

e.g. 17 / 3 = 5 ... 2
Steps:
	(1) left shift 3 (11) until just before it exceeds 17 (10001):
		3 << 2 = 1100 < 10001, but 3 << 3 = 11000 > 10001
		==> left shift 2 times, equivalent to multiplied by 1 << 2 = 4 in step 1.
	(2) Take difference: 10001 - (3 << 2) = 101, it is larger than 11
	(3) repeat the previous steps:
		-- 3 << 0 = 11 < 101, but 3 << 1 = 110 > 101
		==> left shift 0 times, equivalent to multiplied by 1 << 0 = 1 in step 2.
		-- Now take difference 101 - (3 << 0) = 10 < 11. STOP and return (4 + 1).

Time: O((logn) ^ 2)
outer loop reduced the size of problem to at most 1/2
inner loop has at most log(n) iterations to left shift the divisor
*/

public class DivideTwoIntegers_29 {
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || divisor == -1 && dividend == Integer.MIN_VALUE)
        	return Integer.MAX_VALUE;

        // only consider positive values
        int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;
        // must convert the dividend and divisor to long before using abs
        // counter example: abs(INT_MIN) = INT_MAX + 1
        long dvd = Math.abs((long) dividend), dvs = Math.abs((long) divisor);
        int res = 0;
        while (dvd >= dvs) {
        	long tmp = dvs, multiplier = 1;
        	while (dvd >= (tmp << 1)) {		// try this while dvs doesn't exceed dvd
        		tmp <<= 1;	// move tmp
        		multiplier <<= 1;  // record the multiplier in each step
        	}
        	dvd -= tmp;
        	res += multiplier;
        }
        return sign == 1 ? res : -res;
    }
}