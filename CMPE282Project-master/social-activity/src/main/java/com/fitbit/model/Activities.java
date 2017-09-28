package com.fitbit.model;

public class Activities {
	private int activityId;
	private int activityParentId;
	private int calories;
	private String description;
	private float distance;
	private int duration;
	private boolean hasStartTime;
	private boolean isFavorite;
    private int logId;
    private String name;
    private String startTime; //":"00:25",
    private int steps;
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public int getActivityParentId() {
		return activityParentId;
	}
	public void setActivityParentId(int activityParentId) {
		this.activityParentId = activityParentId;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public boolean isHasStartTime() {
		return hasStartTime;
	}
	public void setHasStartTime(boolean hasStartTime) {
		this.hasStartTime = hasStartTime;
	}
	public boolean isFavorite() {
		return isFavorite;
	}
	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
    
    
}
