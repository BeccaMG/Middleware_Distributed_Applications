#!/bin/bash

if [ $# -eq 0 ]
  then
    echo "Input the address of the MailBox Manager as a parameter, please"
    exit
fi

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
echo "Moving to $DIR..."
cd $DIR

# It gets the address of the MailBox Manager from the first argument of the script
ant -Dmbm=$1 build > /dev/null
ant run