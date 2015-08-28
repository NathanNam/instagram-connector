
package org.mule.modules.instagram.oauth;

import java.io.Serializable;
import javax.annotation.Generated;
import org.mule.api.store.ObjectStore;
import org.mule.common.security.oauth.OAuthState;
import org.mule.modules.instagram.OAuth2ConfigurationStrategy;
import org.mule.modules.instagram.adapters.InstagramConnectorOAuth2Adapter;
import org.mule.security.oauth.BaseOAuthClientFactory;
import org.mule.security.oauth.OAuth2Adapter;
import org.mule.security.oauth.OAuth2Manager;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.0", date = "2015-08-28T10:31:22-07:00", comments = "Build mule-devkit-3.7.0.2589.361fd9f")
public class InstagramConnectorOAuthClientFactory
    extends BaseOAuthClientFactory
{

    private InstagramConnectorOAuthManager oauthManager;

    public InstagramConnectorOAuthClientFactory(OAuth2Manager<OAuth2Adapter> oauthManager, ObjectStore<Serializable> objectStore) {
        super(oauthManager, objectStore);
        this.oauthManager = (InstagramConnectorOAuthManager) oauthManager;
    }

    @Override
    protected Class<? extends OAuth2Adapter> getAdapterClass() {
        return (org.mule.modules.instagram.adapters.InstagramConnectorRestClientAdapter.class);
    }

    @Override
    protected void setCustomAdapterProperties(OAuth2Adapter adapter, OAuthState state) {
        InstagramConnectorOAuth2Adapter connector = ((InstagramConnectorOAuth2Adapter) adapter);
        ((OAuth2ConfigurationStrategy) connector.getStrategy()).setScope(oauthManager.getScope());
    }

    @Override
    protected void setCustomStateProperties(OAuth2Adapter adapter, OAuthState state) {
        InstagramConnectorOAuth2Adapter connector = ((InstagramConnectorOAuth2Adapter) adapter);
    }

}
