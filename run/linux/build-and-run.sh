sh build-class.sh
if [ $? -ne 0 ]; then
    exit
fi
sh run.sh
