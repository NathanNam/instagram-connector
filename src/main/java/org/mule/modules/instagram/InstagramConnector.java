package org.mule.modules.instagram;

import org.mule.api.annotations.oauth.OAuthProtected;
import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;

import java.io.IOException;

import org.mule.api.annotations.ReconnectOn;
import org.mule.api.annotations.rest.HttpMethod;
import org.mule.api.annotations.rest.RestCall;
import org.mule.api.annotations.rest.RestPostParam;
import org.mule.api.annotations.rest.RestQueryParam;
import org.mule.api.annotations.rest.RestUriParam;



/**
 * Instagram Anypoint Connector (OAuth2)
 * @author Nathan (Dae Hyun) Nam
 * 	
 */

@Connector(name="instagram", friendlyName="Instagram")
public abstract class InstagramConnector {
    
	@Config
    private OAuth2ConfigurationStrategy strategy;

	/**
	 * Set connection strategy
	 * @param strategy
	 */
	public void setStrategy(OAuth2ConfigurationStrategy strategy){
		this.strategy = strategy;
	}
	
	/**
	 * Get connection strategy
	 * @return
	 */
	public OAuth2ConfigurationStrategy getStrategy(){
		return strategy;
	}


/**
 * Users	
 */
		
	/**
	 * Get basic information about a user. To get information about the owner of the access token, you can use self instead of the user-id.
	 * @param userId: put userId of users you'd like to know more about
	 * @return
	 * @throws IOException
	 */
	@Processor
    @OAuthProtected
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(uri="https://api.instagram.com/v1/users/{user-id}", method=HttpMethod.GET)
    public abstract String getUser(
    		@RestUriParam("user-id") String userId
    		) throws IOException;  
	
	/**
	 * Get user's feed
	 * @param userId
	 * @param countOfMedia: Count of media to return
	 * @return
	 * @throws IOException
	 */
	@Processor
    @OAuthProtected
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(uri="https://api.instagram.com/v1/users/{user-id}/media/recent/?count=100", method=HttpMethod.GET)
    public abstract String getFeed(
    		@RestUriParam("user-id") String userId,
    		@RestQueryParam("count") String countOfMedia
    		
    		) throws IOException;  
	
	/**
	 * Get the most recent media published by a user.
	 * @param userId
	 * @param countOfMedia: Count of media to return
	 * @return
	 * @throws IOException
	 */
	@Processor
    @OAuthProtected
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(uri="https://api.instagram.com/v1/users/{user-id}/media/recent?count=100", method=HttpMethod.GET)
    public abstract String getRecentMedia(
    		@RestUriParam("user-id") String userId,
    		@RestQueryParam("count") String countOfMedia
    		) throws IOException;  
	
	/**
	 * See a list of media liked by the authenticated user. 
	 * @param countOfMedia: Count of media to return
	 * @return
	 * @throws IOException
	 */
	@Processor
    @OAuthProtected
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(uri="https://api.instagram.com/v1/users/self/media/liked?count=100", method=HttpMethod.GET)
    public abstract String getMediaLiked(
    		@RestQueryParam("count") String countOfMedia
    		) throws IOException;  
	
	/**
	 * Search for a user by name.
	 * @param userName: Name you'd like to search for
	 * @param numberOfUsers: Number of users to return
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/users/search?q=nathan&count=100", method = HttpMethod.GET)
	public abstract String getUserSearch(
			@RestQueryParam("q") String userName,
			@RestQueryParam("count") int numberOfUsers)
	        throws IOException;

	
/**
 * Relationships	
 */
	/**
	 * Get the list of users this user follows.
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/users/{user-id}/follows", method = HttpMethod.GET)
	public abstract String getFollows(
			@RestUriParam("user-id") String userId)
	        throws IOException;
	
	/**
	 * Get the list of users this user is followed by.
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/users/{user-id}/followed-by", method = HttpMethod.GET)
	public abstract String getFollowedBy(
			@RestUriParam("user-id") String userId)
	        throws IOException;
	
	
	/**
	 * List the users who have requested this user's permission to follow.
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/users/self/requested-by", method = HttpMethod.GET)
	public abstract String getRequestedBy()
	        throws IOException;
	/**
	 * Get information about a relationship to another user.
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/users/{user-id}/relationship", method = HttpMethod.GET)
	public abstract String getRelationship(
			@RestUriParam("user-id") String userId)
	        throws IOException;
	
	
	/**
	 * Modify the relationship between the current user and the target user.
	 * Requirements
	 * You should have the extended permission for relationships to use this operation.
	 * @param userid
	 * @param action: action can be one of follow/unfollow/block/unblock/approve/ignore
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/users/{user-id}/relationship", method = HttpMethod.POST, contentType="application/json")
	public abstract String postModifyRelationship(
			@RestUriParam("user-id") String userId,
			@RestPostParam("action") String action
			)
	        throws IOException;
	
/**
 * Media
 */
	/**
	 * Get information about a media object.
	 * @param mediaId
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/media/{media-id}", method = HttpMethod.GET)
	public abstract String getMedia(
			@RestUriParam("media-id") String mediaId)
	        throws IOException;
	/**
	 * Get media object's shortcode
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/media/shortcode/D", method = HttpMethod.GET)
	public abstract String getMediaShortcode()
	        throws IOException;
	
	/**
	 * Search for media in a given area. 
	 * @param latitude
	 * @param longitude
	 * @param distance: For example, distance=1000 is equal to 1km.
	 * @return
	 * @throws IOException
	 */
	
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/media/search?lat=48.858844&lng=2.294351&distance=1000", method = HttpMethod.GET)
	public abstract String getMediaSearch(
			@RestQueryParam("lat") String latitude,
			@RestQueryParam("lng") String longitude,
			@RestQueryParam("distance") String distance
			)
	        throws IOException;

	/**
	 * Get a list of most popular media at the moment
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/media/popular", method = HttpMethod.GET)
	public abstract String getMediaPopular()
	        throws IOException;
	
/**
 * Comments
 */	
	/**
	 * Get a list of recent comments on a media object
	 * @param mediaId
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/media/{media-id}/comments", method = HttpMethod.GET)
	public abstract String getComments(
			@RestUriParam("media-id") String mediaId)
	        throws IOException;
	
	/**
	 * Create a comment on a media object with the following rules:
	 * 		The total length of the comment cannot exceed 300 characters.
	 * 		The comment cannot contain more than 4 hashtags.
	 * 		The comment cannot contain more than 1 URL.
	 * 		The comment cannot consist of all capital letters.
	 * Requirement:
	 * You should have the extended permission for comments to use this operation.
	 * @param mediaId
	 * @param comments
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/media/{media-id}/comments", method = HttpMethod.POST, contentType="application/json")
	public abstract String postComments(
			@RestUriParam("media-id") String mediaId,
			@RestPostParam("text") String comments
			)
	        throws IOException;
	
	/**
	 * Remove a comment either on the authenticated user's media object or authored by the authenticated user.
	 * Requirement:
	 * You should have the extended permission for comments to use this operation.
	 * @param mediaId
	 * @param commentId
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/media/{media-id}/comments/{comment-id}", method = HttpMethod.DELETE, contentType="application/json")
	public abstract String deleteComments(
			@RestUriParam("media-id") String mediaId,
			@RestUriParam("comment-id") String commentId
			)
	        throws IOException;
	
/**
* Likes
*/
	/**
	 * Get a list of users who liked this media
	 * @param mediaId
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/media/{media-id}/likes", method = HttpMethod.GET)
	public abstract String getWhoLiked(
			@RestUriParam("media-id") String mediaId)
	        throws IOException;
	
	/**
	 * Set a like on this media based on media-id.
	 * Requirement:
	 * You should have the extended permission for likes to use this operation.
	 * @param mediaId
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/media/{media-id}/likes", method = HttpMethod.POST, contentType="application/json")
	public abstract String postLikes(
			@RestUriParam("media-id") String mediaId
			)
	        throws IOException;
	
	/**
	 * Remove a like on this media based on media-id
	 * Requirement:
	 * You should have the extended permission for likes to use this operation.
	 * @param mediaId
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/media/{media-id}/likes", method = HttpMethod.DELETE, contentType="application/json")
	public abstract String deleteLikes(
			@RestUriParam("media-id") String mediaId
			)
	        throws IOException;
	
/**
* Tags
*/
	/**
	 * Get information about a tag object
	 * @param tageName (i.e. nofilter)
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/tags/{tag-name}", method = HttpMethod.GET)
	public abstract String getTag(
			@RestUriParam("tag-name") String tagName)
	        throws IOException;
	
	/**
	 * Get a list of recently tagged media
	 * @param tageName (i.e. filter)
	 * @param countOfTaggedMedia: the number of tagged media to return
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/tags/{tag-name}/media/recent?count=100", method = HttpMethod.GET)
	public abstract String getRecentlyTagged(
			@RestUriParam("tag-name") String tagName,
			@RestQueryParam("count") String countOfTaggedMedia)
	        throws IOException;
	/**
	 * Search for tags by name. If you try with "snowy," you will see snowy, snowyday, snowydays, and so on as return values.
	 * @param tagName: a valid tag name without a leading # (i.e. snowy)
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/tags/search?q=snowy", method = HttpMethod.GET)
	public abstract String getTagSearch(
			@RestQueryParam("q") String tagName)
	        throws IOException;
    
/**
* Locations
*/
	/**
	 * Get information about a location
	 * @param locationId: For example, 470267660 is location-id for Ramen Underground.
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/locations/{location-id}", method = HttpMethod.GET)
	public abstract String getLocation(
			@RestUriParam("location-id") String locationId)
	        throws IOException;
	
	/**
	 * Get a list of recent media objects from a given location
	 * @param locationId: For example, 470267660 is location-id for Ramen Underground.
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/locations/{location-id}/media/recent", method = HttpMethod.GET)
	public abstract String getRecentMediaFromAGivenLocation(
			@RestUriParam("location-id") String locationId)
	        throws IOException;
	/**
	 * Search for a location by geographic coordinate
	 * @param latitude
	 * @param longitude
	 * @param distance: For example, distance=1000 is equal to 1km.
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/locations/search?lat=48.858844&lng=2.294351&distance=1000", method = HttpMethod.GET)
	public abstract String getLocationSearch(
			@RestQueryParam("lat") String latitude,
			@RestQueryParam("lng") String longitude,
			@RestQueryParam("distance") String distance)
	        throws IOException;

/**
* Geographies
*/
	/**
	 * Get recent media from a geography subscription that you created.In order to get "geo-id," please read "https://instagram.com/developer/endpoints/geographies/" and "https://instagram.com/developer/realtime/" pages.
	 *  
	 * @param geographySubscriptionId
	 * @return
	 * @throws IOException
	 */
	/**
	 * Get recent media from a geography subscription that you created.In order to get "geo-id," please read "https://instagram.com/developer/endpoints/geographies/" and "https://instagram.com/developer/realtime/" pages.
	 *  
	 * @param geographySubscriptionId
	 * @param numberOfMedia: Max number of media to return.
	 * @return
	 * @throws IOException
	 */
	@Processor
	@OAuthProtected
	@RestCall(uri = "https://api.instagram.com/v1/geographies/{geo-id}/media/recent?count=100", method = HttpMethod.GET)
	public abstract String getRecentMediaFromAGeoSubscription(
			@RestUriParam("geo-id") String geographySubscriptionId,
			@RestQueryParam("count") String numberOfMedia
			)
	        throws IOException;
	
	
}