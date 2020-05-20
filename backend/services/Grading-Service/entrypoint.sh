#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar gradingservice-1.0.0.jar