#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar messageservice-1.0.0.jar