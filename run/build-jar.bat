@echo off

echo " >> Making jar file"
cd ..\build
if not exist "..\run\jar" (
    mkdir ..\run\jar
)
jar -cmf ..\Manifest.mf ..\run\jar\CGR.jar MainLauncher.class model\* view\* controller\* util\* system\Software.class gui\* icon\*
echo " >> Copying libraries"
if not exist "..\run\jar\lib" (
    mkdir ..\run\jar\lib
)
copy "..\lib\json-20200518.jar" "..\run\jar\lib\json-20200518.jar" /Y
cd ..\run
echo " >> Building jar file done."
