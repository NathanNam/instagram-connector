
package org.mule.modules.instagram.processors;

import java.util.regex.Pattern;
import javax.annotation.Generated;
import org.mule.modules.instagram.oauth.InstagramConnectorOAuthManager;
import org.mule.security.oauth.processor.BaseOAuth2AuthorizeMessageProcessor;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.0", date = "2015-08-28T10:31:22-07:00", comments = "Build mule-devkit-3.7.0.2589.361fd9f")
public class AuthorizeMessageProcessor
    extends BaseOAuth2AuthorizeMessageProcessor<InstagramConnectorOAuthManager>
{

    private final static Pattern AUTH_CODE_PATTERN = Pattern.compile("code=([^&]+)");

    @Override
    protected String getAuthCodeRegex() {
        return AUTH_CODE_PATTERN.pattern();
    }

    @Override
    protected Class<InstagramConnectorOAuthManager> getOAuthManagerClass() {
        return InstagramConnectorOAuthManager.class;
    }

}
