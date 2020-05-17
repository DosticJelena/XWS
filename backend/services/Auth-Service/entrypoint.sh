#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar authservice-1.0.0.jar