FROM mongo

COPY init.json /init.json
CMD mongoimport --host mongodb --db dev-teste --collection users --type json --file /init.json --jsonArray