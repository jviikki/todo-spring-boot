#!/bin/bash
set -u

startMS=$(date +%s%3N)
ID=$(docker run --rm -d -p 8080:8080 ${1})
runningMS=`date +%s%3N`
while true
do
	curl http://localhost:8080/ > /dev/null 2>&1
	if [ $? -eq 0 ]; then
		break
	fi
done;
readyMS=$(date +%s%3N)
docker stop ${ID}
stoppedMS=$(date +%s%3N)

echo """
Running in	: $((${runningMS}-${startMS}))ms
Ready in	: $((${readyMS}-${startMS}))ms
Stopped in	: $((${stoppedMS}-${readyMS}))ms
"""
