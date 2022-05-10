package soap2;

public class soapapp {
	public String add(int a, int b) {
		return String.valueOf(a + b);
	}
	public String sub(int a, int b) {
		return String.valueOf(a - b);
	}
	public String mul(int a, int b) {
		return String.valueOf(a * b);
	}
	public String div(int a, int b) {
		return String.valueOf(a / b);
	}
	
	public String operation(int a, int b, char op) {
		if(Character.compare(op, '+') == 0)
			return add(a, b);
		else if(Character.compare(op, '-') == 0)
			return sub(a, b);
		else if(Character.compare(op, '*') == 0)
			return mul(a, b);
		else if(Character.compare(op, '/') == 0)
			return div(a, b);
		else
			return "Invalid Operation";
	}
}
