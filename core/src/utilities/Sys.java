package utilities;

public class Sys {
	public static void exit(String msg) {
		System.out.println("Exiting: " + msg);
		System.exit(1);
	}
	
	public static void sleep(int millis, String errorMsg) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
			exit("SYS.sleep : " + errorMsg);
		}
	}
	
	public static void print(String msg) {
		System.out.println(msg);
	}
	
}
