git pull
mvn clean install
app_id=$(ps -ef | grep 'adstock-be-0.0.1-SNAPSHOT.jar' | grep -v 'grep' | awk '{ printf $2 }')
sudo kill $app_id
echo Killed AD_STOCK_BE ..........
echo Restarting..................
echo --------------------------------------------------------------------------------------------------------------
nohup java -jar "target/adstock-be-0.0.1-SNAPSHOT.jar" &