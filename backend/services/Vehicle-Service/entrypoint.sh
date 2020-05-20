#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar vehicleservice-1.0.0.jar