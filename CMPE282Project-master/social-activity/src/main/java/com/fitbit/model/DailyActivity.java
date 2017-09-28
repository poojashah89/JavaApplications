package com.fitbit.model;

import java.util.ArrayList;

public class DailyActivity {
	private ArrayList<Activities> activities = new ArrayList<Activities>();
	
	private DailyGoals goals = new DailyGoals();
	
	private Summary summary = new Summary();

	public ArrayList<Activities> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<Activities> activities) {
		this.activities = activities;
	}

	public DailyGoals getGoals() {
		return goals;
	}

	public void setGoals(DailyGoals goals) {
		this.goals = goals;
	}

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}
	
	
	
}
