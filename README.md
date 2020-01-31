sample
=====================================

pre requirements:

    # "psql" client command required. (PostgreSQL as the server is NOT required)
    brew install postgresql
    sh setup.sh

Run with:

    ./gradlew bootRun -p pj-web
    or
    java -jar ./pj-web/build/libs/pj-web-1.0-SNAPSHOT.jar
