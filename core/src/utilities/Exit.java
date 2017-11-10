package utilities;

public class Exit {
	public static void exit(String msg) {
		System.out.println("Exiting: " + msg);
		System.exit(1);
	}
}
