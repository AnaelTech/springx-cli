#!/bin/bash
git pull
mvn clean package
./install.sh
