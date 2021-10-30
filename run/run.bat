@echo off

echo " >> Running classes..."
if not exist "run/" (
    mkdir run
)
cd run
java -cp "../../build;../../lib/json-20200518.jar;../../res" MainLauncher
cd ..
echo " >> Running stopped."