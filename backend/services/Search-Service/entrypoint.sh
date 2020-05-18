#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar searchservice-1.0.0.jar