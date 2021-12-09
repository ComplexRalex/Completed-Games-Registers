echo " >> Running classes..."
if [ ! -d "run/" ]; then
    mkdir run
fi
cd run
java -cp "../../../build:../../../lib/json-20200518.jar:../../../res" MainLauncher
okay=$?
if [ $okay -ne 0 ]; then
    echo " >> An error ocurred when trying to run class files!"
    echo " >> Error code: $okay"
    exit 1
fi
cd ..
echo " >> Running stopped."
