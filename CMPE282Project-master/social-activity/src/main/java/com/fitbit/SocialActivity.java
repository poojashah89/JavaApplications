
package com.fitbit;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fitbit.model.Activities;
import com.fitbit.model.Activity;
import com.fitbit.model.DailyActivity;
import com.fitbit.model.DailyGoals;
import com.fitbit.model.FGoal;
import com.fitbit.model.FatGoal;
import com.fitbit.model.Goal;
import com.fitbit.model.LifetimeActivity;
import com.fitbit.model.Summary;
import com.fitbit.model.WeightGoal;
import com.intranet.model.yelp.BusinessDAO;
import com.intranet.model.yelp.BusinessRegion;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
@EnableWebSecurity
public class SocialActivity extends WebSecurityConfigurerAdapter {

	@Autowired
	OAuth2RestTemplate fitbitOAuthRestTemplate;

	@Value("${fitbit.api.resource.activitiesUri}")
	String fitbitActivitiesUri;
	
	@Value("${fitbit.api.resource.weightGoalUri}")
	String fitbitWeightGoalUri;
	
	@Value("${fitbit.api.resource.fatGoalUri}")
	String fitbitFatGoalUri;
    
	@Value("${fitbit.api.resource.goalUri}")
	String fitbitGoalUri;
   
	String fitbitDailyActivityUri;
	
    @RequestMapping(value="/search",method= RequestMethod.GET)
    public List business(@RequestParam String location){
        BusinessDAO business = new BusinessDAO();
        List<BusinessRegion> businessList=business.list(location);
        return businessList;
    }
    
    @RequestMapping("/activity-goal")
	public LifetimeActivity activityGoalUri() {
    	LifetimeActivity lifetimeActivity;

		try {
			DailyGoals a = fitbitOAuthRestTemplate.getForObject(fitbitGoalUri, DailyGoals.class);
			lifetimeActivity = a.getGoals();
		}
		catch(Exception e) {
			lifetimeActivity = new LifetimeActivity();
		}
		return lifetimeActivity;
	}
    
	 @RequestMapping("/daily-activity")
	public Summary  dailyActivityUri() {
		Summary  activity;
    	Date now = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	String today = sdf.format(now);
    	
    	fitbitDailyActivityUri = "https://api.fitbit.com/1/user/-/activities/date/"+ today + ".json";
		try {
			DailyActivity a = fitbitOAuthRestTemplate.getForObject(fitbitDailyActivityUri, DailyActivity.class);
			activity = a.getSummary();	 
		}
		catch(Exception e) {
			activity = new Summary ();
		}
		return activity;
	}
	
	@RequestMapping("/lifetime-activity")
	public LifetimeActivity lifetimeActivity() {
		LifetimeActivity lifetimeActivity;

		try {
			Activity a = fitbitOAuthRestTemplate.getForObject(fitbitActivitiesUri, Activity.class);
			lifetimeActivity = a.getLifetime().getTotal();
			 
		}
		catch(Exception e) {
			lifetimeActivity = new LifetimeActivity();
		}

		return lifetimeActivity;
	}

	
	@RequestMapping("/weight-goal")
	public WeightGoal getWeightGoal() {
		WeightGoal wtgoal;

		try {
			Goal goal = fitbitOAuthRestTemplate.getForObject(fitbitWeightGoalUri, Goal.class);
			wtgoal = goal.getGoal();
		}
		catch(Exception e) {
			wtgoal = new WeightGoal();
		}

		return wtgoal;
	}
	
	@RequestMapping("/fat-goal")
	public FatGoal getFatGoal() {
		FatGoal fatGoal;

		try {
			FGoal goal = fitbitOAuthRestTemplate.getForObject(fitbitFatGoalUri, FGoal.class);
			fatGoal = goal.getGoal();
		}
		catch(Exception e) {
			fatGoal = new FatGoal();
		}

		return fatGoal;
	}
	
	
	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**").permitAll().anyRequest()
				.authenticated();
	}

	public static void main(String[] args) {
		// System.getProperties().put( "server.port", 8181 );  //8181 port is set here
		SpringApplication.run(SocialActivity.class, args);
	}
}
 
