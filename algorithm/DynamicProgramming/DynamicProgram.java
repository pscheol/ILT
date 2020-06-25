package algorithm;

public class DynamicProgram {
	public static void main(String[] args) {
		String A = "GOOD MORNING.";
		String B = "GUTEN MORGEN.";
		
		String LCS = new String();
		int[][] table = new int[A.length()+1][B.length()+1];
		LCS(A, B, table);
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println("");
		}
		
		int i = A.length();
		int j=  B.length();
		LCS_TraceBack(A,B, i,j,table, LCS);
	
	System.out.println(fibonacci(10));
	System.out.println(fibonacci2(10));
	}

	public static long fibonacci(int n) {
		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;
		else {
			return fibonacci(n - 1) + fibonacci(n - 2);
		}
	}

	public static long fibonacci2(int n) {
		long result = 0;
		long[] table = new long[n + 1];
		if (n == 0 || n == 1) {
			return n;
		}
		table[0] = 0;
		table[1] = 1;
		for (int i = 2; i <= n; i++) {
			table[i] = table[i - 1] + table[i - 2];
			System.out.print(table[i] +" ");
		}
		result = table[n];
		return result;
	}

	public static int LCS(String X, String Y, int[][] table) {
		for (int i = 0; i < X.length(); i++) {
			table[i][0] = 0;
		}
		for (int j = 0; j < Y.length(); j++) {
			table[0][j] = 0;
		}
		
		for (int i = 1; i <= X.length(); i++) {
			for (int j = 1; j <= Y.length(); j++) {
				if (X.charAt(i-1) == Y.charAt(j-1)) {
						table[i][j] = table[i-1][j-1] + 1;
				} else {
						table[i][j] = table[i-1][j] >  table[i][j-1] ? table[i-1][j] : table[i][j-1];
				}
			}
		}
		return table[X.length()-1][Y.length()-1];	
	}
	public static void LCS_TraceBack(String x, String y,int i, int j,  int[][] table, String lcs) {
		if (i == 0  || j == 0) return;
		
		if (table[i][j] > table[i][j-1] && table[i][j] > table[i-1][j] && table[i][j] > table[i-1][j-1]) {
			String tmp = lcs;
			lcs = String.format("%c%s ", x.charAt(i-1), tmp);
			System.out.println(lcs);
			LCS_TraceBack(x,y, i-1 ,j-1, table, lcs);
		} else if (table[i][j] > table[i-1][j] && table[i][j] == table[i][j-1]) {
			LCS_TraceBack(x,y,i, j-1, table, lcs);
		} else {
			LCS_TraceBack(x,y, i-1, j, table, lcs);
		}
	}
}
