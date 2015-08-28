
package org.mule.modules.instagram.adapters;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.registry.RegistrationException;
import org.mule.api.registry.ResolverException;
import org.mule.api.registry.TransformerResolver;
import org.mule.api.transformer.DataType;
import org.mule.api.transformer.Transformer;
import org.mule.api.transformer.TransformerException;
import org.mule.modules.instagram.InstagramConnector;
import org.mule.registry.TypeBasedTransformerResolver;
import org.mule.security.oauth.OAuth2Adapter;
import org.mule.security.oauth.OAuth2Manager;
import org.mule.transformer.simple.ObjectToString;
import org.mule.transformer.types.DataTypeFactory;
import org.mule.transport.http.HttpMuleMessageFactory;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.0", date = "2015-08-28T10:31:22-07:00", comments = "Build mule-devkit-3.7.0.2589.361fd9f")
public class InstagramConnectorRestClientAdapter
    extends InstagramConnectorOAuth2Adapter
    implements MuleContextAware, Disposable, Initialisable
{

    private int responseTimeout;
    private MuleContext muleContext;
    private volatile HttpClient httpClient;
    private HttpMuleMessageFactory httpMuleMessageFactory;
    private volatile MultiThreadedHttpConnectionManager connectionManager;

    public InstagramConnectorRestClientAdapter(OAuth2Manager<OAuth2Adapter> oauthManager) {
        super(oauthManager);
    }

    private MuleMessage getMuleMessage(HttpMethod method, String encoding) {
        try {
            MuleMessage muleMessage = httpMuleMessageFactory.create(method, encoding);
            muleMessage.getPayloadAsString();
            return muleMessage;
        } catch (Exception e) {
            throw new RuntimeException("Couldn't transform http response to MuleMessage", e);
        }
    }

    public void setMuleContext(MuleContext context) {
        muleContext = context;
        httpMuleMessageFactory = new HttpMuleMessageFactory(muleContext);
    }

    private Transformer getPayloadTransformer(DataType inputDataType, DataType outputDataType) {
        try {
            TransformerResolver typeBasedResolver = muleContext.getRegistry().lookupObject(TypeBasedTransformerResolver.class);
            Transformer typeResolverTransformer = typeBasedResolver.resolve(inputDataType, outputDataType);
            if ((typeResolverTransformer == null)||(typeResolverTransformer instanceof ObjectToString)) {
                Transformer transformer = muleContext.getRegistry().lookupTransformer(inputDataType, outputDataType);
                if (transformer!= null) {
                    return transformer;
                }
            }
            return typeResolverTransformer;
        } catch (ResolverException rese) {
            throw new RuntimeException(rese.getMessage(), rese);
        } catch (RegistrationException re) {
            throw new RuntimeException(re.getMessage(), re);
        } catch (TransformerException te) {
            throw new RuntimeException(te.getMessage(), te);
        }
    }

    @Override
    public void initialise()
        throws InitialisationException
    {
        connectionManager = new MultiThreadedHttpConnectionManager();
        httpClient = new HttpClient(connectionManager);
        httpClient.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
        httpClient.getParams().setParameter("http.socket.timeout", responseTimeout);
        httpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
        httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
    }

    /**
     * Sets responseTimeout
     * 
     * @param value Value to set
     */
    public void setResponseTimeout(int value) {
        this.responseTimeout = value;
    }

    public String getUser(String userId)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/users/{user-id}";
        uri = uri.replace("{user-id}", String.valueOf(userId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getUser", String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getUser", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getFeed(String userId, String countOfMedia)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/users/{user-id}/media/recent/?count=100";
        uri = uri.replace("{user-id}", String.valueOf(userId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("count").append("=").append(String.valueOf(countOfMedia));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getFeed", String.class, String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getFeed", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getRecentMedia(String userId, String countOfMedia)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/users/{user-id}/media/recent?count=100";
        uri = uri.replace("{user-id}", String.valueOf(userId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("count").append("=").append(String.valueOf(countOfMedia));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getRecentMedia", String.class, String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getRecentMedia", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getMediaLiked(String countOfMedia)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/users/self/media/liked?count=100";
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("count").append("=").append(String.valueOf(countOfMedia));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getMediaLiked", String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getMediaLiked", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getUserSearch(String userName, int numberOfUsers)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/users/search?q=nathan&count=100";
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("q").append("=").append(String.valueOf(userName));
        queryString.append("&").append("count").append("=").append(String.valueOf(numberOfUsers));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getUserSearch", String.class, int.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getUserSearch", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getFollows(String userId)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/users/{user-id}/follows";
        uri = uri.replace("{user-id}", String.valueOf(userId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getFollows", String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getFollows", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getFollowedBy(String userId)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/users/{user-id}/followed-by";
        uri = uri.replace("{user-id}", String.valueOf(userId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getFollowedBy", String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getFollowedBy", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getRequestedBy()
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/users/self/requested-by";
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getRequestedBy");
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getRequestedBy", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getRelationship(String userId)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/users/{user-id}/relationship";
        uri = uri.replace("{user-id}", String.valueOf(userId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getRelationship", String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getRelationship", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String postModifyRelationship(String userId, String action)
        throws IOException
    {
        HttpMethod method = null;
        method = new PostMethod();
        String uri = "https://api.instagram.com/v1/users/{user-id}/relationship";
        uri = uri.replace("{user-id}", String.valueOf(userId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new NameValuePair("action", String.valueOf(action)));
        ((PostMethod) method).addParameters(postParameters.toArray(new NameValuePair[] {}));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("postModifyRelationship", String.class, String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named postModifyRelationship", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getMedia(String mediaId)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/media/{media-id}";
        uri = uri.replace("{media-id}", String.valueOf(mediaId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getMedia", String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getMedia", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getMediaShortcode()
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/media/shortcode/D";
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getMediaShortcode");
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getMediaShortcode", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getMediaSearch(String latitude, String longitude, String distance)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/media/search?lat=48.858844&lng=2.294351&distance=1000";
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("lat").append("=").append(String.valueOf(latitude));
        queryString.append("&").append("lng").append("=").append(String.valueOf(longitude));
        queryString.append("&").append("distance").append("=").append(String.valueOf(distance));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getMediaSearch", String.class, String.class, String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getMediaSearch", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getMediaPopular()
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/media/popular";
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getMediaPopular");
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getMediaPopular", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getComments(String mediaId)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/media/{media-id}/comments";
        uri = uri.replace("{media-id}", String.valueOf(mediaId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getComments", String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getComments", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String postComments(String mediaId, String comments)
        throws IOException
    {
        HttpMethod method = null;
        method = new PostMethod();
        String uri = "https://api.instagram.com/v1/media/{media-id}/comments";
        uri = uri.replace("{media-id}", String.valueOf(mediaId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new NameValuePair("text", String.valueOf(comments)));
        ((PostMethod) method).addParameters(postParameters.toArray(new NameValuePair[] {}));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("postComments", String.class, String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named postComments", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String deleteComments(String mediaId, String commentId)
        throws IOException
    {
        HttpMethod method = null;
        method = new DeleteMethod();
        String uri = "https://api.instagram.com/v1/media/{media-id}/comments/{comment-id}";
        uri = uri.replace("{media-id}", String.valueOf(mediaId));
        uri = uri.replace("{comment-id}", String.valueOf(commentId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("deleteComments", String.class, String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named deleteComments", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getWhoLiked(String mediaId)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/media/{media-id}/likes";
        uri = uri.replace("{media-id}", String.valueOf(mediaId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getWhoLiked", String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getWhoLiked", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String postLikes(String mediaId)
        throws IOException
    {
        HttpMethod method = null;
        method = new PostMethod();
        String uri = "https://api.instagram.com/v1/media/{media-id}/likes";
        uri = uri.replace("{media-id}", String.valueOf(mediaId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        ((PostMethod) method).addParameters(postParameters.toArray(new NameValuePair[] {}));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("postLikes", String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named postLikes", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String deleteLikes(String mediaId)
        throws IOException
    {
        HttpMethod method = null;
        method = new DeleteMethod();
        String uri = "https://api.instagram.com/v1/media/{media-id}/likes";
        uri = uri.replace("{media-id}", String.valueOf(mediaId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("deleteLikes", String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named deleteLikes", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getTag(String tagName)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/tags/{tag-name}";
        uri = uri.replace("{tag-name}", String.valueOf(tagName));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getTag", String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getTag", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getRecentlyTagged(String tagName, String countOfTaggedMedia)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/tags/{tag-name}/media/recent?count=100";
        uri = uri.replace("{tag-name}", String.valueOf(tagName));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("count").append("=").append(String.valueOf(countOfTaggedMedia));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getRecentlyTagged", String.class, String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getRecentlyTagged", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getTagSearch(String tagName)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/tags/search?q=snowy";
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("q").append("=").append(String.valueOf(tagName));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getTagSearch", String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getTagSearch", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getLocation(String locationId)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/locations/{location-id}";
        uri = uri.replace("{location-id}", String.valueOf(locationId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getLocation", String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getLocation", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getRecentMediaFromAGivenLocation(String locationId)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/locations/{location-id}/media/recent";
        uri = uri.replace("{location-id}", String.valueOf(locationId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getRecentMediaFromAGivenLocation", String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getRecentMediaFromAGivenLocation", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getLocationSearch(String latitude, String longitude, String distance)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/locations/search?lat=48.858844&lng=2.294351&distance=1000";
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("lat").append("=").append(String.valueOf(latitude));
        queryString.append("&").append("lng").append("=").append(String.valueOf(longitude));
        queryString.append("&").append("distance").append("=").append(String.valueOf(distance));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getLocationSearch", String.class, String.class, String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getLocationSearch", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getRecentMediaFromAGeoSubscription(String geographySubscriptionId, String numberOfMedia)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://api.instagram.com/v1/geographies/{geo-id}/media/recent?count=100";
        uri = uri.replace("{geo-id}", String.valueOf(geographySubscriptionId));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("count").append("=").append(String.valueOf(numberOfMedia));
        queryString.append("&").append("access_token").append("=").append(getAccessToken());
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = InstagramConnector.class.getMethod("getRecentMediaFromAGeoSubscription", String.class, String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getRecentMediaFromAGeoSubscription", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

}
