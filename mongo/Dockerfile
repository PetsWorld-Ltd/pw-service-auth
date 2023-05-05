FROM mongo:4.4.19-focal

COPY ./init.json /

# --authenticationDatabase=admin - specification where to look for authorization
CMD mongoimport --db WebAdmin --collection Administrators --file init.json --jsonArray -u $MONGODB_USER -p $MONGODB_PASS --host=$MONGODB_ADDRESS --authenticationDatabase=admin --drop