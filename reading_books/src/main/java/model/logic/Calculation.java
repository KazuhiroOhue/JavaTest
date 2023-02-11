package model.logic;

public class Calculation {
	
	public double calculation(int totalPages, int readPages) {
		//百分率を計算
		double num = (double)readPages / (double)totalPages * 100;
		// 小数第二位で四捨五入
		double ans =  ((double)Math.round(num * 10))/10;
		// 上限値は100.0
		if (ans > 100.0) {
			ans = 100.0;
		} 
		return ans;
		
	}
}
