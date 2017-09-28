package hello;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TweetData;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
@RestController
public class TwitterController {

    private Twitter twitter;

    private ConnectionRepository connectionRepository;

    @Inject
    public TwitterController(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }
    
    @RequestMapping("/profile")
    public String profile(Model model) {
    	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json;
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }

        model.addAttribute("twitterProfile", twitter.userOperations().getUserProfile());
        CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
        model.addAttribute("friends", friends);
        try {

        	json = ow.writeValueAsString(friends);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return twitter.userOperations().getUserProfile().getName();
    }

    @RequestMapping("/friends")
    public String getFriends(Model model) {
    	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json= "Error!";
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }

        model.addAttribute(twitter.userOperations().getUserProfile());
        CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
        List<String> friendList = new ArrayList<>();
        for(int i = 0; i<friends.size(); i++ )
        {	
        	friendList.add(friends.get(i).getScreenName());
        	model.addAttribute("friends", friends);
        }
        try {
			json = ow.writeValueAsString(friendList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return json;
    }
     
    @RequestMapping("/tweets")
    public String tweets(Model model) {
    	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json= "Error!";
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }

         List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline(100);
         
        try {
			json = ow.writeValueAsString(tweets);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return json;
    }
    
    
    @RequestMapping("/post")
    public String postTweet(Model model) {
    	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json= "Error!";
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }  
        try {
        	TweetData data = new TweetData("hi from spring boot"
        			+ "");

            Tweet tweet = twitter.timelineOperations().updateStatus(data);
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        	json = ow.writeValueAsString(df.format(tweet.getCreatedAt()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return json;
    } 
    
    @RequestMapping("/updateStatus_withImage")
    public String updateStatus_withImage() {
    	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json= "Error!";
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }  
    	Resource photo = getUploadResource("photo.jpg", "PHOTO DATA");
    	Tweet tweet = twitter.timelineOperations().updateStatus(new TweetData("Test Message").withMedia(photo));
    	try {
			json = ow.writeValueAsString(tweet.getText());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
    
    @RequestMapping("/updateStatus_withLocation")
    public String updateStatus_withLocation() {
    	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json= "Error!";
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }  
    	Tweet tweet = twitter.timelineOperations().updateStatus(new TweetData("Test Message").atLocation(-111.2f, 123.1f));
    	try {
			json = ow.writeValueAsString(tweet.getText());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
    }
    
    @RequestMapping("/updateStatus_withInReplyToStatus")
    public String updateStatus_withInReplyToStatus() {
    	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json= "Error!";
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }  
    	Tweet tweet = twitter.timelineOperations().updateStatus(new TweetData("Test Message in reply to @someone").inReplyToStatus(123456));
    	try {
			json = ow.writeValueAsString(tweet.getText());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
    }
    
    @RequestMapping("/getUserTimeline_paged")
    public String getUserTimeline_paged() {
    	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json= "Error!";
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }  
		List<Tweet> timeline = twitter.timelineOperations().getUserTimeline("habuma" , 24);
		try {
			json = ow.writeValueAsString(timeline);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
    
    private Resource getUploadResource(final String filename, String content) {
		Resource resource = new ByteArrayResource(content.getBytes()) {
			@Override
			public String getFilename() throws IllegalStateException {
				return filename;
			};
		};
		return resource;
	}
}
