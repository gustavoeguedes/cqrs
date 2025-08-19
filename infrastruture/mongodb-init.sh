#!/bin/bash
set -e

echo "Updating MongoDB users."

mongo admin -u mongoadmin -p mongopassword <<EOF
db = db.getSiblingDB("ms-beautique-query");

if (db.getUser("ms-sync")) {
  db.dropUser("ms-sync");
}

if (db.getUser("ms-beautique-query")) {
  db.dropUser("ms-beautique-query");
}

db.createUser({
  user: "ms-sync",
  pwd: "ms-sync",
  roles: [{ role: "dbOwner", db: "ms-beautique-query" }]
});

db.createUser({
  user: "ms-beautique-query",
  pwd: "ms-beautique-query",
  roles: [{ role: "read", db: "ms-beautique-query" }]
});
EOF

echo "MongoDB users updated successfully."
