package edu.ncsu.csc216.wolf_scheduler.course;

/** Creating the Course Class. Defines a course inside a schedule with different fields
 * such as name, title, section, credits, instructorId, meetingDays, startTime, and endTime.
 * It has many methods such as getName, setName, getTitle, setTitle, getSection, setSection,
 * getCredits, setCredits, getInstructorId, setInstructorId, getMeetingDays, getStartTime,
 * getEndTime, getMeetingString, and setMeetingDaysAndTime. It also has two constructors,
 * one for some fields, a course with no times or days, and one for all fields, a full Course.
 * @author David Mond
 */
public class Course {
	/** Course's name. */
	private String name;
	/** Course's title. */
	private String title;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Returns the Course's name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more
	 * than 8, does not contain a space between letter characters and number
	 * characters, has less than 1 or more than 4 letter characters, and not exactly
	 * three trailing digit characters, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {
		final int minNameLength = 5;
		final int maxNameLength = 8;
		final int minLetterCount = 1;
		final int maxLetterCount = 4;
		final int digitMaxCount = 3;
		// Throw exception if the name is null
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Throw exception if the name is an empty string
		// Throw exception if the name contains less than 5 character or greater than 8
		// characters
		if (name.length() < minNameLength || name.length() > maxNameLength) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Check for pattern of L[LLL] NNN
		int letterCount = 0;
		int digitCount = 0;
		boolean flag = false;
		for (int i = 0; i < name.length(); i++) {
			if (!flag) {
				if (Character.isLetter(name.charAt(i))) {
					letterCount++;
				} else if (name.substring(i, i + 1).equals(" ")) {
					flag = true;
				}

				else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			} else if (flag) {
				if (Character.isDigit(name.charAt(i))) {
					digitCount++;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}
		}

		// Check that the number of letters is correct
		if (letterCount < minLetterCount || letterCount > maxLetterCount) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Check that the number of digits is correct
		if (digitCount != digitMaxCount) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		this.name = name;
	}

	/**
	 * Returns the Course's title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title.
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		// Throw exception if the title is null or empty
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Returns the Course's section.
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section.
	 * 
	 * @param section the section to set
	 */
	public void setSection(String section) {
		// Throw exception if the section is null or doesn't fit correct length
		if (section == null || section.length() != 3) {
			throw new IllegalArgumentException("Invalid section.");
		}
		for (int i = 0; i < section.length(); i++) {
			// Throw exception if a character of the section is not a number
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}

		this.section = section;
	}

	/**
	 * Returns the Course's credits.
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits.
	 * 
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		final int minCredits = 1;
		final int maxCredits = 5;
		// Throw exception if credits is not in the correct range
		if (credits < minCredits || credits > maxCredits) { // credits out of range
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}

	/**
	 * Returns the Course's instructor ID.
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructor ID.
	 * 
	 * @param instructorId the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		// Throw exception if the instructorID is null or empty
		if (instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns the Course's meeting days.
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course's start time.
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the course's end time.
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the meeting days and times with a start and end time.	 * 
	 * @param meetingDays the meeting days for the courses.
	 * @param startTime   the starting time for the courses.
	 * @param endTime     the ending time for the courses.
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// Throw exception if the meetingDays is null or empty
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if ("A".equals(meetingDays)) { // Arranged
			// Throw exception if the start or end time is not 0 after being arranged
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			this.meetingDays = meetingDays;
			this.startTime = 0;
			this.endTime = 0;
		}

		else { // not arranged
			int mCount = 0;
			int tCount = 0;
			int wCount = 0;
			int hCount = 0;
			int fCount = 0;
			for (int i = 0; i < meetingDays.length(); i++) {
				// Count each day
				if (meetingDays.charAt(i) == 'M') {
					mCount++;
				} else if (meetingDays.charAt(i) == 'T') {
					tCount++;
				} else if (meetingDays.charAt(i) == 'W') {
					wCount++;
				} else if (meetingDays.charAt(i) == 'H') {
					hCount++;
				} else if (meetingDays.charAt(i) == 'F') {
					fCount++;
				}
				// If not m, t, w, h, f, throw an exception
				else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			// If a day shows up more than once, throw an exception
			if (mCount > 1 || tCount > 1 || wCount > 1 || hCount > 1 || fCount > 1) { // checks for duplicates
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			// Convert military time to standard time
			int startHour = startTime / 100;
			int endHour = endTime / 100;
			int startMin = startTime % 100;
			int endMin = endTime % 100;
			final int upperHour = 23;
			final int upperMinute = 59;

			// Throw an exception if time out of range
			if (startHour < 0 || startHour > upperHour) { // not between 0 and 23, inclusive
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			if (startMin < 0 || startMin > upperMinute) { // not between 0 and 59, inclusive
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			if (endHour < 0 || endHour > upperHour) { // not between 0 and 23, inclusive
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			if (endMin < 0 || endMin > upperMinute) { // not between 0 and 59, inclusive
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			if (endTime < startTime) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			// everything is valid and works together!
			this.meetingDays = meetingDays;
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}

	/**
	 * Converts military time to regular time and gets the meeting string of meeting
	 * days, start time, and end time.
	 * 
	 * @return the meeting string which consists of meeting days, start time, and
	 *         end time.
	 */
	public String getMeetingString() {
		// Use getter methods to get days and times
		String meet = getMeetingDays();
		// Convert military to standard
		int startHour = getStartTime() / 100;
		int startMin = getStartTime() % 100;
		int endHour = getEndTime() / 100;
		int endMin = getEndTime() % 100;
		boolean extraZeroStart = false;
		boolean extraZeroEnd = false;
		String res = "";
		String startTimer = "AM";
		String endTimer = "AM";
		// If military hour past or equal to 12, it is PM
		if (startHour >= 12) {
			if (startHour != 12) {
				startHour -= 12;
			}
			startTimer = "PM";
		}
		if (endHour >= 12) {
			if (endHour != 12) {
				endHour -= 12;
			}
			endTimer = "PM";
		}
		// If minute is single digit, add extra 0
		if (startMin < 10) {
			extraZeroStart = true;
		}

		if (endMin < 10) {
			extraZeroEnd = true;
		}
		// If arranged, return arranged
		if ("A".equals(meet)) {
			return "Arranged";
		}
		// Check if it is midnight
		if (startHour == 0) {
			startHour = 12;
			startTimer = "AM";
		}
		if (endHour == 0) {
			endHour = 12;
			endTimer = "AM";
		}
		// Check to see if the starting or ending times have a leading 0, adjust res
		// accordingly
		if (extraZeroStart || extraZeroEnd) {
			if (extraZeroEnd && extraZeroStart) {
				res += meet + " " + startHour + ":" + "0" + startMin + startTimer + "-" + endHour + ":" + "0" + endMin
						+ endTimer;
			} else if (extraZeroStart) {
				res += meet + " " + startHour + ":" + "0" + startMin + startTimer + "-" + endHour + ":" + endMin
						+ endTimer;
			} else {
				res += meet + " " + startHour + ":" + startMin + startTimer + "-" + endHour + ":" + "0" + endMin
						+ endTimer;
			}
		} else {
			res += meet + " " + startHour + ":" + startMin + startTimer + "-" + endHour + ":" + endMin + endTimer;
		}
		// Return the string result
		return res;
	}

	/**
	 * Constructs a Course object with values for all fields and uses encapsulation.
	 * 
	 * @param name         name of Course 
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		setName(name);
		setTitle(title);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Generates a hashCode for Course using all fields.
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + credits;
		result = prime * result + endTime;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (endTime != other.endTime)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(meetingDays)) {
			return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays;
		}
		return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays + ","
				+ startTime + "," + endTime;
	}
}
