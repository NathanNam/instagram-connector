
package org.mule.modules.instagram.oauth;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleMessage;
import org.mule.api.expression.ExpressionManager;
import org.mule.api.store.ObjectStore;
import org.mule.modules.instagram.InstagramConnector;
import org.mule.modules.instagram.OAuth2ConfigurationStrategy;
import org.mule.modules.instagram.adapters.InstagramConnectorOAuth2Adapter;
import org.mule.security.oauth.BaseOAuth2Manager;
import org.mule.security.oauth.OAuth2Adapter;
import org.mule.security.oauth.OAuth2Manager;
import org.mule.security.oauth.OnNoTokenPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A {@code InstagramConnectorOAuthManager} is a wrapper around {@link InstagramConnector } that adds access token management capabilities to the pojo.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.0", date = "2015-08-28T10:31:22-07:00", comments = "Build mule-devkit-3.7.0.2589.361fd9f")
public class InstagramConnectorOAuthManager
    extends BaseOAuth2Manager<OAuth2Adapter>
{

    private static Logger logger = LoggerFactory.getLogger(InstagramConnectorOAuthManager.class);

    @Override
    protected Logger getLogger() {
        return logger;
    }

    /**
     * Sets consumerKey
     * 
     * @param key to set
     */
    public void setConsumerKey(String value) {
        super.setConsumerKey(value);
    }

    /**
     * Sets consumerSecret
     * 
     * @param secret to set
     */
    public void setConsumerSecret(String value) {
        super.setConsumerSecret(value);
    }

    /**
     * Sets scope
     * 
     * @param scope to set
     */
    public void setScope(String value) {
        super.setScope(value);
    }

    @Override
    protected OAuth2Adapter instantiateAdapter() {
        return new org.mule.modules.instagram.adapters.InstagramConnectorRestClientAdapter(this);
    }

    @Override
    protected KeyedPoolableObjectFactory createPoolFactory(OAuth2Manager<OAuth2Adapter> oauthManager, ObjectStore<Serializable> objectStore) {
        return new InstagramConnectorOAuthClientFactory(oauthManager, objectStore);
    }

    @Override
    protected void setCustomProperties(OAuth2Adapter adapter) {
        InstagramConnectorOAuth2Adapter connector = ((InstagramConnectorOAuth2Adapter) adapter);
        ((OAuth2ConfigurationStrategy) connector.getStrategy()).setConsumerKey(getConsumerKey());
        ((OAuth2ConfigurationStrategy) connector.getStrategy()).setConsumerSecret(getConsumerSecret());
        ((OAuth2ConfigurationStrategy) connector.getStrategy()).setScope(getScope());
    }

    protected void fetchCallbackParameters(OAuth2Adapter adapter, String response) {
        InstagramConnectorOAuth2Adapter connector = ((InstagramConnectorOAuth2Adapter) adapter);
        ExpressionManager expressionManager = (muleContext.getExpressionManager());
        MuleMessage muleMessage = new DefaultMuleMessage(response, (muleContext));
    }

    public void setOnNoToken(OnNoTokenPolicy policy) {
        this.getDefaultUnauthorizedConnector().setOnNoTokenPolicy(policy);
    }

    @Override
    protected Set<Class<? extends Exception>> refreshAccessTokenOn() {
        Set<Class<? extends Exception>> types = new HashSet<Class<? extends Exception>>();
        types.add(Exception.class);
        types.add(Exception.class);
        types.add(Exception.class);
        types.add(Exception.class);
        return types;
    }

}
