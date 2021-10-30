echo " >> Running classes..."
if [ ! -d "run/" ]; then
    mkdir run
fi
cd run
java -cp "../../../build:../../../lib/json-20200518.jar:../../../res" MainLauncher
cd ..
echo " >> Running stopped."
