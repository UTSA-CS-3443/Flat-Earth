package utilities;

//obviously not optimized, just want to get something working
// maybe just send keycodes

public class KeyboardState {

	private boolean pressed[];
	
	public KeyboardState() {
		pressed = new boolean[4];
	}
	
	public void UpPress() {pressed[0] = true;}
	public void DownPress() {pressed[1] = true;}
	public void LeftPress() {pressed[2] = true;}
	public void RightPress() {pressed[3] = true;}

	public void UpRelease() {pressed[0] = false;}
	public void DownRelease() {pressed[1] = false;}
	public void LeftRelease() {pressed[2] = false;}
	public void RightRelease() {pressed[3] = false;}
	
	// this method must be called as the argument to a synchronized block
	// other wise it's not thread safe
	// most likely will only be used by the actual 'model' part. If client server is active,
	// probably send this whole object through kryonet, and access the copy over on the server side
	// optimization tip (for garbage collect) is to pass a variable into this and it sets its
	// values, instead of returning it. That way no variables have to be set a lot
	public synchronized boolean[] get() {
		boolean[] copy = new boolean[4]; 
		for (int i = 0; i < pressed.length; i++)
			copy[i] = pressed[i];
		return copy;
	}
	
	public synchronized void updateData(KeyboardState ks) {
		this.pressed = ks.get();
	}
	
}
