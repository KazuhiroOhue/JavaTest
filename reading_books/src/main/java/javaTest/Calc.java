package javaTest;


public class Calc {

	public static void main(String[] args) {
		int percentage = 0;
		int a = 526;
		int b = 490;
		double ans = (double)a/(double)b *100;
		
		double answer = ((double)Math.round(ans * 10))/10;
		System.out.println(ans);
		System.out.println(answer);
		
		if (answer > 100.0) {
			answer = 100.0;
			
		} 
		
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(a / b);
		System.out.println(answer);
		
//		int num = 100;
		System.out.println("aaaaa");

	}

}
