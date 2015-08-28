package org.mule.modules.instagram;

import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.oauth.*;
import org.mule.api.annotations.param.Default;

@OAuth2(accessTokenUrl = "https://api.instagram.com/oauth/access_token",
authorizationUrl = "https://api.instagram.com/oauth/authorize", friendlyName = "OAuth2 Configuration")
public class OAuth2ConfigurationStrategy {

	/**
	 * Application's client identifier (consumer key)
	 */
	@Configurable
	@OAuthConsumerKey
	private String consumerKey;
	
	/**
	 * Application's client secret (consumer secret)
	 */
    @Configurable
    @OAuthConsumerSecret
    private String consumerSecret;

    /**
     * Note that in order to use these extended permissions (comments+relationships+likes), first you need to submit your app for Instagram's review.
     * Extended permissions can be specified in scope. (i.e. scope=basic+comments+relationships+likes)
     */
    @Configurable
    @Default(value = "basic")
    @OAuthScope
    private String scope;

  /*  
    @Default(value = "token")
    @OAuthResponseType
    private String response_type;
    */
	@OAuthAccessToken
	private String accessToken;

	/**
	 * Set consumerKey
	 * @param consumerKey
	 */
	public void setConsumerKey(String consumerKey){
		this.consumerKey = consumerKey;
	}
	
	/**
	 * Get consumerKey
	 * @return consumerKey
	 */
	public String getConsumerKey(){
		return this.consumerKey;
	}
	
	/**
	 * Set consumerSecret
	 * @param consumerSecret
	 */
	public void setConsumerSecret(String consumerSecret){
		this.consumerSecret = consumerSecret;
	}
	
	/**
	 * Get consumerSecret
	 * @return consumerSecret
	 */
	public String getConsumerSecret(){
		return this.consumerSecret;
	}
	/**
	 * Set scope of permissions
	 * @param scope
	 */
	public void setScope(String scope){
		this.scope = scope;
	}
	
	/**
	 * Get scope
	 * @return
	 */
	public String getScope(){
		return this.scope;
	}
	
	/**
	 * Set accessToken
	 * @param accessToken
	 */
	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
		//for debugging
		System.out.println(accessToken);
	}
	
	/**
	 * Get accessToken
	 * @return accessToken
	 */
	public String getAccessToken(){
		return this.accessToken;
	}
	
	/**
	 * Check whether accessToken is properly created.
	 * @return
	 */
	public Boolean isAuthorized(){
		if(accessToken.equalsIgnoreCase(null)){
			return true;
		}
		else{
			return false;
			
		}
	}

}
