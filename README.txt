=======================================================================
=======================================================================
============                                            ===============
============                Microproject                ===============
============             By: Rebeca Machado             ===============
============               and Syed Kazmi               ===============
============                                            ===============
=======================================================================
=======================================================================

Directory hierarchy
===================

Microproject
??? DirectoryManager
?   ??? META-INF
?   ?   ??? persistence.xml
?   ??? src
?   ?   ??? client
?   ?   ?   ??? AdministrationClient.java
?   ?   ??? ejb
?   ?   ?   ??? IUserDirectory.java
?   ?   ?   ??? Publisher.java
?   ?   ?   ??? UserDirectory.java
?   ?   ?   ??? UserDirectoryRest.java
?   ?   ??? entity
?   ?       ??? User.java
?   ??? build.xml
?   ??? run_client.sh
?   ??? run_server.sh
??? MailBoxClient
?   ??? src
?   ?   ??? MailBoxClient.java
?   ??? build.xml
?   ??? run_client.sh
??? MailBoxManager
?   ??? META-INF
?   ?   ??? persistence.xml
?   ??? src
?   ?   ??? Box.java
?   ?   ??? IMailBoxManager.java
?   ?   ??? MailBox.java
?   ?   ??? MailBoxManager.java
?   ?   ??? MailBoxManagerServer.java
?   ?   ??? Message.java
?   ?   ??? NewsBox.java
?   ??? build.xml
?   ??? run_server.sh
??? demo.sh
??? hosts



Run the MailBoxManager:
----------------------
>$ sh ./MailBoxManager/run_server.sh <address of the directoryManager>


Run the DirectoryManager:
------------------------
>$ sh ./DirectoryManager/run_server.sh <address of the mailBoxManager>


Run the AdministrationClient:
----------------------------
>$ sh ./DirectoryManager/run_client.sh <address of the directoryManager>


Run the MailBoxClient:
---------------------
>$ sh ./MailBoxClient/run_client.sh <address of the mailBoxManager>

