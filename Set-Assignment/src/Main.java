import java.util.*;
public class Main {
	public static void main(String args[]) {
		Set uni;
		ArrayList<Set> sets;
		Scanner input = new Scanner(System.in);
		System.out.print("\n--- Assignment 1 ---\n"
				+ "How many elements does the universe contain : ");
		int n = input.nextInt();
		uni = new Set(n);
		System.out.println("\nPlease Enter them each one on a separate line :");
		input.nextLine();// ignoring the empty line
		for(int i=0; i<n; i++)
			uni.add(input.nextLine());
		System.out.print("Enter the number of sets : ");
		n = input.nextInt();
		sets = new ArrayList<Set>(n);
		System.out.println();
		for(int i=1; i<=n; i++) {
			System.out.print("Enter the number of elements in set "+i+": ");
			int temp = input.nextInt();
			System.out.println("\nPlease Enter them each one on a separate line :");
			input.nextLine();// ignoring the empty line
			for(int j=0; j<temp; j++)
				sets.get(j).add(input.nextLine());
		}
		boolean working = true;
		while(working) {
			System.out.print("\n--- Main UI ---"
					+ "\n1 Union"
					+ "\n2 Intersection"
					+ "\n3 Complement"
					+ "\n4 Exit"
					+ "\nWhich operation do you want : ");
			int op = input.nextInt();
			ArrayList<String> result = new ArrayList<>();
			int one = 0, two = 0;
			switch(op) {
			case 1:
				System.out.println("which two sets to unite or -1 for back (ex: 1 2) : ");
				one = input.nextInt();
				if(one == -1)
					break;
				two = input.nextInt();
				result = sets.get(one).union(sets.get(two));
				break;
			case 2:
				System.out.println("which two sets to intersect or -1 for back (ex: 1 2) : ");
				one = input.nextInt();
				if(one == -1)
					break;
				two = input.nextInt();
				result = sets.get(one).intersection(sets.get(two));
				break;
			case 3:
				System.out.println("which two sets to unite or -1 for back (ex: 2) : ");
				one = input.nextInt();
				if(one == -1)
					break;
				result = sets.get(one).complement(uni);
				break;
			default:
				working = false;
			}
			if(working && !result.isEmpty()) {
				int size = result.size();
				for(int i=0; i<size; i++)
					System.out.println(result.get(i));
			}
		}
	}
	
}
