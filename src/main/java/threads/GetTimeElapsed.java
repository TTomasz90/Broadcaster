
/** 
 *  GetTimeElapsed class
 *  Author: Tomasz Turek
 *	Description: Class for keeping track of passage of time of each quiz question asked.
 *
 */

package threads;

public class GetTimeElapsed implements Runnable {

	// initial time when question is asked
	private long startTime;

	// elapsed minute counter.
	private long minutes;

	// thread running indicator
	private volatile boolean threadRunning = true;

	// logging function visible indicator
	private volatile boolean isVisible = false;

	// constructor
	public GetTimeElapsed(Long start) {

		this.startTime = start;

	}

	/**
	 * End the thread
	 */
	public void stopRunning() {
		threadRunning = false;
	}

	/**
	 * Set Thread OK for a restart
	 */
	public void startRunning() {
		threadRunning = true;
	}

	/**
	 * Set log to be visible by the user
	 */
	public void setVisible() {
		System.out.println("Displaying Running Question Log");
		isVisible = true;
	}

	/**
	 * Set log to be invisible by the user
	 */
	public void setInvisible() {
		isVisible = false;

	}

	/**
	 * Get visibility status
	 */
	public boolean getVsibility() {

		return this.isVisible;

	}

	/**
	 * Thread function logs amount of time passed since the start of a Quiz question
	 */
	@Override
	public void run() {

		while (threadRunning) {

			long currentTime = System.currentTimeMillis();

			long seconds = currentTime - startTime;

			// if minute passes increment minute counter
			if (seconds > 60000) {

				startTime = System.currentTimeMillis();

				seconds = 0;

				minutes += 1;
			}

			// check if user wants log to be visible
			if (isVisible == true) {
				System.out.println("Elapsed Time || minutes: " + minutes + " seconds: " + seconds / 1000 + " ");
			}

			// wait 5 seconds
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

}
