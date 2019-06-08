#!/bin/bash

echo "--- GMA Levantando Docker FTP ---"

docker run -d -v /home/carguimaraes/workdock/ftp/dados:/home/vsftpd -p 20:20 -p 21:21 -p 47400-47470:47400-47470 -e FTP_USER=gma -e FTP_PASS=123 -e PASV_ADDRESS=192.168.15.2 --name ftp --restart=always bogem/ftp


