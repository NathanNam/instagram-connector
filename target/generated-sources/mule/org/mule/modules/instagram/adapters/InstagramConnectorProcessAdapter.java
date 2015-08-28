
package org.mule.modules.instagram.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.instagram.InstagramConnector;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * A <code>InstagramConnectorProcessAdapter</code> is a wrapper around {@link InstagramConnector } that enables custom processing strategies.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.0", date = "2015-08-28T11:14:58-07:00", comments = "Build mule-devkit-3.7.0.2589.361fd9f")
public abstract class InstagramConnectorProcessAdapter
    extends InstagramConnectorLifecycleInjectionAdapter
    implements ProcessAdapter<InstagramConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, InstagramConnectorCapabilitiesAdapter> getProcessTemplate() {
        final InstagramConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,InstagramConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, InstagramConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, InstagramConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
