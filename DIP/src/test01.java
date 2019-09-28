
public class test01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int z = 1;
		int[][] test = new int[4][4];
		for (int i = 0; i < test.length; i++)
			for (int j = 0; j < test.length; j++) {

				test[i][j] = z++;
			}

		
		
		
		for (int i = 0; i < test.length; i++) {
			for (int j = 0; j < test.length; j++) {

				System.out.print(test[i][j]);
			}
			System.out.print("\n");	
		}
		
		System.out.println("------------------------------");	
		// ascending index = true
		// decending index = false
		
		boolean direction = true;
		for (int i = 0; i < 4; i++)
		{
			if (direction)
				for (int j = 0; j < 4; j++) {

					System.out.print(test[i][j]);
					direction = false;
				}
			else if (!direction)
				for (int j = 4 - 1; j >= 0; j--) {

					System.out.print(test[i][j]);
					direction = true;
				}
			System.out.print("\n");
		}
	}
}
