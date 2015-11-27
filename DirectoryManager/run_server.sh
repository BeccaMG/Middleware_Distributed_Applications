#!/bin/bash

if [ $# -eq 0 ]
  then
    echo "Input the address of the MailBox Manager as a parameter, please"
    exit
fi

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
echo "Moving to $DIR..."
cd $DIR

asadmin start-domain domain1

# Gets the MailBoxManager web services from the first argument passed to the script
ant -Dmbm=$1 deploy
# Executes the Publisher
java -cp .:class:jars/*:$GLASSFISH_HOME/lib/* directoryManager.ejb.Publisher
