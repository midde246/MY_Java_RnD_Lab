package exceptationhandling;

public class FinallyChecking {
	public static void main(String[] args) {
		int x = testfinnalyMehod(100, 0);
		System.out.println(x);
	}

	private static int testfinnalyMehod(int x, int y) {
		try{
		     x = x / y;
		     System.out.println("I am in try block !");
		} catch (ArithmeticException aex) {
			System.out.println("In catch block ! ");
			return x;
		}
		
		finally {
			System.out.println("I am in finlly block ! ");
		}
		return x;
	}
}
