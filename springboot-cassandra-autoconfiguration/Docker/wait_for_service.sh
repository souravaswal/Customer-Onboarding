#!/usr/bin/env bash
##########################################
# This script polls the service to check #
# it is available on provided port       #
##########################################
host=$1
port=$2
interval=$3
timeout=$4
start_ts=$(date +%s)
while :
    do
        echo $host is not up yet. Polling again...
        (echo > /dev/tcp/$host/$port) >/dev/null 2>&1
        result=$?
        current_ts=$(date +%s)
        if [[ $((current_ts - start_ts)) -ge $timeout ]]; then
            echo $host could not come up in $timeout seconds. Stopped Polling.
            break
        fi
        if [[ $result -eq 0 ]]; then
            end_ts=$(date +%s)
            echo $host is up in $((end_ts - start_ts)) seconds
            break
        fi
        sleep $interval
    done

