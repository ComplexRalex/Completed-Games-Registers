echo " >> Building class files"
cd ../..
if [ ! -d "build/" ]; then
    mkdir build
fi
javac -d build/ -cp "lib/json-20200518.jar" -encoding "UTF-8" src/MainLauncher.java src/model/* src/view/* src/controller/* src/util/* src/system/Software.java
okay=$?
if [ $okay -ne 0 ]; then
    echo " >> An error ocurred when generating class files!"
    echo " >> Error code: $okay"
    exit 1
fi
cd build
echo " >> Copying resources"
if [ ! -d "icon/" ]; then
    mkdir icon
fi
cp ../res/icon/*.png icon/ -u
if [ ! -d "gui/" ]; then
    mkdir gui
fi
cp ../res/gui/*.png gui/ -u
cd ../run/linux
echo " >> Building class files done."
