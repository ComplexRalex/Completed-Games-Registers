@echo off

echo " >> Running classes..."
if not exist "run/" (
    mkdir run
)
cd run
java -cp "../../../build;../../../lib/json-20200518.jar;../../../res" MainLauncher
if %errorlevel% neq 0 (
    echo " >> An error ocurred when trying to run class files!"
    echo " >> Error code: %errorlevel%"
    exit
)
cd ..
echo " >> Running stopped."
