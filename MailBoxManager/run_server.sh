#!/bin/bash

if [ $# -eq 0 ]
  then
    echo "Input the address of the Directory Manager as a parameter, please"
    exit
fi

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
echo "Moving to $DIR..."
cd $DIR

ant build

# It gets the address of the Directory Manager as first parameter
java -cp .:class:jars/*:$GLASSFISH_HOME/lib/* mailBoxManager.MailBoxManagerServer $1
