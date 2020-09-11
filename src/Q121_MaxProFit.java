/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 */
public class Q121_MaxProFit {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int sell[] = new int[prices.length];
        int buy[] = new int[prices.length];

        // 初始化第一天收益
        sell[0] = 0;
        buy[0] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            // 更新卖出最佳收益
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);

            // 更新买入最佳收益
            buy[i] = Math.max(buy[i - 1], -prices[i]);
        }

        return Math.max(sell[prices.length - 1], buy[prices.length - 1]);

    }

    public static void main(String[] args) {
        Q121_MaxProFit obj = new Q121_MaxProFit();
        System.out.println(obj.maxProfit(new int[] {7,1,5,3,6,4}));
    }
}
