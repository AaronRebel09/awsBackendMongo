#!/usr/bin/env bash
cd /home/ec2-user/server
sudo nohup java -jar -Dserver.port=8582 \
    *.jar > /dev/null 2> /dev/null < /dev/null &
