/** 
 *  GetAnswerCount class
 *  Author: Tomasz Turek
 *	Description: Class for keeping track incoming replies from users participating 
 *  in the quiz
 *
 */
package threads;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetAnswerCount implements Runnable {

	// number of Quiz participants
	private long numberOfParticipants;

	// number of registered question responses
	private long answerCount = 0;

	// thread running indicator
	private volatile boolean threadRunning = true;

	// logging function visible indicator
	private volatile boolean isVisible = false;

	// constructor
	public GetAnswerCount(Integer participants) {

		this.numberOfParticipants = participants;

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
	 * Thread function request from the server number of responses already
	 * registered.
	 */
	@Override
	public void run() {

		while (threadRunning) {

			// server url
			String url = "http://brod-server.herokuapp.com/answerCount";

			OkHttpClient client = new OkHttpClient();

			// server request
			Request request = new Request.Builder().url(url).build();

			// check if user wants log to be visible
			if (isVisible == true) {
				try (Response response = client.newCall(request).execute()) {

					// log response
					System.out.println("Current Response count is (" + response.body().string() + " / "
							+ numberOfParticipants + ")");

				} catch (Exception e) {

					System.out.println(e);
				}
			}

			try {
				// wait 5 seconds
				Thread.sleep(5000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

}
