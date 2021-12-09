@echo off

echo " >> Making jar file"
cd ..\..\build
if not exist "..\run\windows\jar" (
    mkdir ..\run\windows\jar
)
jar -cmf ..\Manifest.mf ..\run\windows\jar\CGR.jar MainLauncher.class model\* view\* controller\* util\* system\Software.class gui\* icon\*
if %errorlevel% neq 0 (
    echo " >> An error ocurred when generating jar file!"
    echo " >> Error code: %errorlevel%"
    exit
)
echo " >> Copying libraries"
if not exist "..\run\windows\jar\lib" (
    mkdir ..\run\windows\jar\lib
)
copy "..\lib\json-20200518.jar" "..\run\windows\jar\lib\json-20200518.jar" /Y
cd ..\run\windows
echo " >> Making jar file done."
