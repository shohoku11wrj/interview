
public class One_6 {
	
	// O(N^2)
	public static void rotate(int[][] matrix, int n) {
		for(int layer = 0; layer < n/2; ++layer) {
			int first = layer;
			int last = n -1 - layer;
			for(int i = first; i < last; ++i) {
				int offset = i - first;
				// save top
				int top = matrix[first][i];
				
				// left -> top
				matrix[first][i] = matrix[last-offset][first];
				
				// bottom -> left
				matrix[last-offset][first] = matrix[last][last-offset];
				
				// right -> bottom
				matrix[last][last-offset] = matrix[i][last];
				
				// top -> right
				matrix[i][last] = top;
			}
		}
	}
	
	public static int[][] createMatrix(int n) {
		int[][] matrix = new int[n][n];
		int i = 1;
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				matrix[row][col] = i;
				i++;
			}
		}
		return matrix;
	}
	
	public static void print(int[][] matrix, int n) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] matrix = createMatrix(4);
		print(matrix,matrix.length);
		rotate(matrix,matrix.length);
		System.out.println("--- Rotated Matrix ---");
		print(matrix,matrix.length);
	}

}
