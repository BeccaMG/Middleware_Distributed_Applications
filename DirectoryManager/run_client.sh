#!/bin/bash
# echo $1
# remotecmd="java -Dorg.omg.CORBA.ORBInitialHost=$HOSTNAME -classpath \$CLASSPATH:\$HOME/appServ/EJBLabs/Microproject/DirectoryManager/class directoryManager.client.AdministrationClient"
# ssh "$1" $remotecmd

if [ $# -eq 0 ]
  then
    echo "Input the address of the Directory Manager as a parameter, please"
    exit
fi

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
echo "Moving to $DIR..."
cd $DIR

# This runs the client connected to the server passed as first parameter IT NEEDS TO BE COMPILED
java -Dorg.omg.CORBA.ORBInitialHost=$1 -classpath $CLASSPATH:class/ directoryManager.client.AdministrationClient