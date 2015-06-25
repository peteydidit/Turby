package competeydidit.facebook.www.turby69.Utility;

import java.util.Date;

public class Deal {
    private String id;
    private Date datePosted;
    private int upvoteCount;
    private int downvoteCount;
    private String googlePlacesId;
    private Date startDay;
    private Date endDay;
    private int dayStartTime;
    private int dayEndTime;
    private Day[] days;
    private boolean weeklyRecurrence;
    private boolean monthlyRecurrence;
    private String requirements;
    private String description;

    public Deal(String id, Date datePosted, int upvoteCount, int downvoteCount, String googlePlacesId, Date startDay, Date endDay, int dayStartTime, int dayEndTime, Day[] days, boolean weeklyRecurrence, boolean monthlyRecurrence, String requirements, String description) {
        this.id = id;
        this.datePosted = datePosted;
        this.upvoteCount = upvoteCount;
        this.downvoteCount = downvoteCount;
        this.googlePlacesId = googlePlacesId;
        this.startDay = startDay;
        this.endDay = endDay;
        this.dayStartTime = dayStartTime;
        this.dayEndTime = dayEndTime;
        this.days = days;
        this.weeklyRecurrence = weeklyRecurrence;
        this.monthlyRecurrence = monthlyRecurrence;
        this.requirements = requirements;
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "Requirements: " + requirements + "\nDescription: " + description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public int getUpvoteCount() {
        return upvoteCount;
    }

    public void setUpvoteCount(int upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public int getDownvoteCount() {
        return downvoteCount;
    }

    public void setDownvoteCount(int downvoteCount) {
        this.downvoteCount = downvoteCount;
    }

    public String getGooglePlacesId() {
        return googlePlacesId;
    }

    public void setGooglePlacesId(String googlePlacesId) {
        this.googlePlacesId = googlePlacesId;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public int getDayStartTime() {
        return dayStartTime;
    }

    public void setDayStartTime(int dayStartTime) {
        this.dayStartTime = dayStartTime;
    }

    public int getDayEndTime() {
        return dayEndTime;
    }

    public void setDayEndTime(int dayEndTime) {
        this.dayEndTime = dayEndTime;
    }

    public Day[] getDays() {
        return days;
    }

    public void setDays(Day[] days) {
        this.days = days;
    }

    public boolean getWeeklyRecurrence() {
        return weeklyRecurrence;
    }

    public void setWeeklyRecurrence(boolean weeklyRecurrence) {
        this.weeklyRecurrence = weeklyRecurrence;
    }

    public boolean getMonthlyRecurrence() {
        return monthlyRecurrence;
    }

    public void setMonthlyRecurrence(boolean monthlyRecurrence) {
        this.monthlyRecurrence = monthlyRecurrence;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
