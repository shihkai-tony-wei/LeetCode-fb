/*121. Best Time to Buy and Sell Stock

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Idea: DP
- keep the maxprof so far
- keep the lowest price so far

*/
public class BuySellStock_121 {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int p : prices) {
        	if (p > minPrice)
        		maxProfit = Math.max(maxProfit, p - minPrice);
        	else
        		minPrice = p;
        }
        return maxProfit;
    }

    // follow up LC 122: II if multiple transaction allowed
    public int maxProfit(int[] prices) {
        int prof = 0;
        for (int i = 0; i < prices.length - 1; ++i) {
        	if (prices[i] < prices[i + 1])
        		prof += prices[i + 1] - prices[i];
        }
        return prof;
    }


    // LC 123. III at most two transactions:
    // maximize the profit sequentially - buy 1st -> sell 1st -> buy 2nd -> sell 2nd
    public int maxProfit(int[] prices) {
        int sell1 = 0, sell2 = 0, buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        for (int i : prices) {
            buy1 = Math.max(buy1, -i);
            sell1 = Math.max(sell1, i + buy1);
            buy2 = Math.max(buy2, sell1 - i);
            sell2 = Math.max(sell2, i + buy2);
        }
        return sell2;
    }
}