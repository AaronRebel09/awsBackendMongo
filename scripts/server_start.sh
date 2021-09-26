#!/usr/bin/env bash
cd /home/ec2-user/server
sudo java -jar > /var/log/messages/test.log 2>&1 -Dserver.port=8582 \
    *.jar > /dev/null 2> /dev/null < /dev/null & 
