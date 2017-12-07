package utilities;

/**
 * System package
 * 
 * @author mauricio
 *
 */
public class Sys {
	/**
	 * print and exit
	 * @param msg
	 */
	public static void exit(String msg) {
		System.out.println("Exiting: " + msg);
		System.exit(1);
	}
	
	/**
	 * sleep
	 * @param millis miliseconds
	 * @param errorMsg message if error
	 */
	public static void sleep(int millis, String errorMsg) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
			exit("SYS.sleep : " + errorMsg);
		}
	}
	
	/**
	 * prints
	 * 
	 * @param msg to print
	 */
	public static void print(String msg) {
		System.out.println(msg);
	}
	
}
