
package org.mule.modules.instagram.adapters;

import javax.annotation.Generated;
import org.mule.api.MetadataAware;
import org.mule.modules.instagram.InstagramConnector;


/**
 * A <code>InstagramConnectorMetadataAdapter</code> is a wrapper around {@link InstagramConnector } that adds support for querying metadata about the extension.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.0", date = "2015-08-28T10:31:22-07:00", comments = "Build mule-devkit-3.7.0.2589.361fd9f")
public abstract class InstagramConnectorMetadataAdapter
    extends InstagramConnectorCapabilitiesAdapter
    implements MetadataAware
{

    private final static String MODULE_NAME = "Instagram";
    private final static String MODULE_VERSION = "1.0.0-SNAPSHOT";
    private final static String DEVKIT_VERSION = "3.7.0";
    private final static String DEVKIT_BUILD = "mule-devkit-3.7.0.2589.361fd9f";
    private final static String MIN_MULE_VERSION = "3.5.0";

    public String getModuleName() {
        return MODULE_NAME;
    }

    public String getModuleVersion() {
        return MODULE_VERSION;
    }

    public String getDevkitVersion() {
        return DEVKIT_VERSION;
    }

    public String getDevkitBuild() {
        return DEVKIT_BUILD;
    }

    public String getMinMuleVersion() {
        return MIN_MULE_VERSION;
    }

}
