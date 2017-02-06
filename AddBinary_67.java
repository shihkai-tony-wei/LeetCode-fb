/* 67. Add Binary
*/

public class AddBinary_67 {
    public String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (i >= 0 || j >= 0) {
        	int sum = carry;
        	if (i >= 0) sum += a.charAt(i--) - '0';	// a.charAt(i) is a char, need change to int
        	if (j >= 0) sum += b.charAt(j--) - '0';
        	sb.insert(0, sum % 2);
        	carry = sum / 2;
        }
        // if carry is still nonzero, insert another digit at front
        if (carry != 0) {
        	sb.insert(0, carry);
        }
        return sb.toString();
    }
}