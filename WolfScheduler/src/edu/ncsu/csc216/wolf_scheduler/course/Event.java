/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Event class has many methods, and one field for the event details. It extends the class
 * Activity as well and has one super constructor which initializes the title, startTime,
 * endTime, and the eventDetails.
 */
public class Event extends Activity {
	/** Event details for activity. */
	private String eventDetails;
	

	/** Constructor to create the event with a title, start time, end time, meeting days, and
	 * event details.
	 * @param title title for the event.
	 * @param meetingDays days that the event will meet.
	 * @param startTime time that the event will start.
	 * @param endTime time that the event will end.
	 * @param eventDetails details of the event.
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}

	/** Gets the event details.
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/** Sets the event details to the parameter.
	 * @param eventDetails the eventDetails to set
	 */
	public void setEventDetails(String eventDetails) {
		if(eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}
	/**
	 * Gets the short display array of size 4.
	 * @return 2D String array that has the short display.
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] arr = new String[4];
		arr[0] = "";
		arr[1] = "";
		arr[2] = getTitle();
		arr[3] = getMeetingString();
		return arr;
	}
	/**
	 * Gets the long display array of size 7.
	 * @return 2D String array that has the long display.
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] arr = new String[7];
		arr[0] = "";
		arr[1] = "";
		arr[2] = getTitle();
		arr[3] = "";
		arr[4] = "";
		arr[5] = getMeetingString();
		arr[6] = getEventDetails();
		return arr;
	}
	
	/**
	 * Converts the required fields into a string with commas between.
	 * @return String with fields and commas.
	 */
	@Override
	public String toString() {
		
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime()
		+ "," + getEventDetails();
	}
	

}
