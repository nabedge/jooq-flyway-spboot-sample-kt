#!/bin/bash

# RDBMS start
docker-compose -f ./docker/docker-compose.yml down
docker-compose -f ./docker/docker-compose.yml up -d --build

count=0
alive_postgresql=0
while [ $alive_postgresql -lt 1 ] && [ $count -lt 20 ]
do
    psql -U sampledbuser -d sampledb -h localhost -p 5432 -c "select 1"  > /dev/null 2>&1
    if [ $? -eq 0 ]; then
        alive_postgresql=1
    fi
    echo "waiting for postgresql server start up..."
    count=$(( count + 1 ))
    sleep 1
done

# code clean up
sh ./gradlew clean

# create tables on PostgreSQL by flyway
sh ./gradlew flywayMigrate -p pj-db

# execute jOOQ code generator
sh ./gradlew test -p pj-db-gen

# compile code created by jOOQ
sh ./gradlew jar -x test -p pj-db

#  insert test data by flyway
sh ./gradlew testClasses flywayMigrate -Dflyway.locations=classpath:testdata -p pj-db

# test & build application
sh ./gradlew build -p pj-web
