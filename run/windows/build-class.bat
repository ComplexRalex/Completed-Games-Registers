@echo off

echo " >> Building class files"
cd ../..
if not exist "build/" (
    mkdir build
)
javac -d build\ -cp "lib\json-20200518.jar" -encoding "UTF-8" src\MainLauncher.java src\model\* src\view\* src\controller\* src\util\* src\system\Software.java
if %errorlevel% neq 0 (
    echo " >> An error ocurred when generating class files!"
    echo " >> Error code: %errorlevel%"
    exit
)
cd build
echo " >> Copying resources"
if not exist "icon/" (
    mkdir icon
)
copy "..\res\icon\*.png" "icon\*.png" /Y
if not exist "gui/" (
    mkdir gui
)
copy "..\res\gui\*.png" "gui\*.png" /Y
cd ..\run\windows
echo " >> Building class files done."
