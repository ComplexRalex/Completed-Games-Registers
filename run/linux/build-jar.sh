echo " >> Making jar file"
cd ../../build
if [ ! -d "../run/linux/jar" ]; then
    mkdir ../run/linux/jar
fi
jar -cmf ../ManifestFS.mf ../run/linux/jar/CGR.jar MainLauncher.class model/* view/* controller/* util/* system/Software.class gui/* icon/*
echo " >> Copying libraries"
if [ ! -d "../run/linux/jar/lib" ]; then
    mkdir ../run/linux/jar/lib
fi
cp ../lib/json-20200518.jar ../run/linux/jar/lib/ -u
cd ../run/linux
echo " >> Making jar file done."
