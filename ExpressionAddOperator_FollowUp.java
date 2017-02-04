/*
Follow-up to LC 282. Expression Add Operators
Given a string that contains only digits 0-9 and a target value, 
return all possibilities to add binary operators (not unary) +, - 
so that the result is equal to the target value.

You may add operator in front of the first number.
*/
import java.util.*;
public class ExpressionAddOperator_FollowUp {

	public List<String> addOperators(String num, int target) {
		List<String> ans = new ArrayList<String>();
		helper(ans, new StringBuilder(), num, target, 0, 0);
		return ans;
	}

	private void helper(List<String> ans, StringBuilder path, String num, int target, int pos, long eval) {
		if (pos == num.length()) {
			if (eval == target) {
				if (path.charAt(0) == '+')
					ans.add(path.substring(1, path.length()));
				else
					ans.add(path.toString());
				return;
			}
		}
		for (int i = pos; i < num.length(); ++i) {
			if (i > pos && num.charAt(i) == '0') return;
			long curr = Long.valueOf(num.substring(pos, i + 1));
			int len = path.length();

			// try plus 
			helper(ans, path.append("+").append(curr), num, target, i + 1, eval + curr);
			path.setLength(len);

			// try minus
			helper(ans, path.append("-").append(curr), num, target, i + 1, eval - curr);
			path.setLength(len);
		}
	}

	public static void main(String[] args) {
		ExpressionAddOperator_FollowUp obj = new ExpressionAddOperator_FollowUp();
		List<String> list = obj.addOperators("123456", 24);
		System.out.println(Arrays.toString(list.toArray()));
	}
}

