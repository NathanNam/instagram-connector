
package org.mule.modules.instagram.config;

import javax.annotation.Generated;
import org.mule.config.MuleManifest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * Registers bean definitions parsers for handling elements in <code>http://www.mulesoft.org/schema/mule/instagram</code>.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.0", date = "2015-08-28T10:31:22-07:00", comments = "Build mule-devkit-3.7.0.2589.361fd9f")
public class InstagramNamespaceHandler
    extends NamespaceHandlerSupport
{

    private static Logger logger = LoggerFactory.getLogger(InstagramNamespaceHandler.class);

    private void handleException(String beanName, String beanScope, NoClassDefFoundError noClassDefFoundError) {
        String muleVersion = "";
        try {
            muleVersion = MuleManifest.getProductVersion();
        } catch (Exception _x) {
            logger.error("Problem while reading mule version");
        }
        logger.error(((((("Cannot launch the mule app, the  "+ beanScope)+" [")+ beanName)+"] within the connector [instagram] is not supported in mule ")+ muleVersion));
        throw new FatalBeanException(((((("Cannot launch the mule app, the  "+ beanScope)+" [")+ beanName)+"] within the connector [instagram] is not supported in mule ")+ muleVersion), noClassDefFoundError);
    }

    /**
     * Invoked by the {@link DefaultBeanDefinitionDocumentReader} after construction but before any custom elements are parsed. 
     * @see NamespaceHandlerSupport#registerBeanDefinitionParser(String, BeanDefinitionParser)
     * 
     */
    public void init() {
        try {
            this.registerBeanDefinitionParser("config-with-oauth", new InstagramConnectorOAuth2ConfigurationStrategyConfigDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("config-with-oauth", "@Config", ex);
        }
        try {
            this.registerBeanDefinitionParser("authorize", new AuthorizeDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("authorize", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("unauthorize", new UnauthorizeDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("unauthorize", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-user", new GetUserDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-user", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-feed", new GetFeedDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-feed", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-recent-media", new GetRecentMediaDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-recent-media", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-media-liked", new GetMediaLikedDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-media-liked", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-user-search", new GetUserSearchDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-user-search", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-follows", new GetFollowsDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-follows", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-followed-by", new GetFollowedByDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-followed-by", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-requested-by", new GetRequestedByDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-requested-by", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-relationship", new GetRelationshipDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-relationship", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("post-modify-relationship", new PostModifyRelationshipDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("post-modify-relationship", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-media", new GetMediaDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-media", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-media-shortcode", new GetMediaShortcodeDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-media-shortcode", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-media-search", new GetMediaSearchDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-media-search", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-media-popular", new GetMediaPopularDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-media-popular", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-comments", new GetCommentsDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-comments", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("post-comments", new PostCommentsDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("post-comments", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("delete-comments", new DeleteCommentsDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("delete-comments", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-who-liked", new GetWhoLikedDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-who-liked", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("post-likes", new PostLikesDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("post-likes", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("delete-likes", new DeleteLikesDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("delete-likes", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-tag", new GetTagDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-tag", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-recently-tagged", new GetRecentlyTaggedDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-recently-tagged", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-tag-search", new GetTagSearchDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-tag-search", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-location", new GetLocationDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-location", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-recent-media-from-a-given-location", new GetRecentMediaFromAGivenLocationDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-recent-media-from-a-given-location", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-location-search", new GetLocationSearchDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-location-search", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-recent-media-from-a-geo-subscription", new GetRecentMediaFromAGeoSubscriptionDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-recent-media-from-a-geo-subscription", "@Processor", ex);
        }
    }

}
