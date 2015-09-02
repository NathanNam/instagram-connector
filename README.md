# Instagram Anypoint Connector
Instagram Anypoint Connector allows you to easily connect to Instagram and to get meaningful data within a few clicks on Mule ESB or CloudHub.

# Mule supported versions
Mule 3.7

# [Destination service or application name] supported versions
[Instagram REST API v1] (https://instagram.com/developer/endpoints)

# Installation 
As a beta connector you can download the source code and build it with devkit to find it available on your local repository. Then you can add it to Studio

#Usage
1. After you import and install the Instagram Connector in Anypoint Studio, you need to create an [Instagram Client on Instagram] (https://instagram.com/developer/clients/manage/)
2. Get your Client ID and Client Secret of the Instgram Client.
3. After you drag and drop a HTTP connector in Anypoint Studio, please drag and drop the Instagram Connector.
4. Select the "Authorize" operation with your Client ID, Client Secret and Callback option.
5. Once you get authorized, please feel free to play with other operations
 * In order to utilize full functions of this connector, your Instagram Client needs to have [extended permissions](https://instagram.com/developer/authentication/). Since my Instagram Client is still in Instagram's review, I haven't been able to test POST/DEL operations of this connector.
