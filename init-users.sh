#!/bin/bash
set -e

if [ -n "$ADDITIONAL_POSTGRES_USERS" ]; then
  IFS=',' read -ra USERS <<< "$ADDITIONAL_POSTGRES_USERS"
  for user_pair in "${USERS[@]}"; do
    IFS=':' read -ra CRED <<< "$user_pair"
    username=${CRED[0]}
    password=${CRED[1]}

    echo "Creating additional PostgreSQL user: $username"
    psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
      CREATE USER $username WITH PASSWORD '$password';
      GRANT ALL PRIVILEGES ON DATABASE $POSTGRES_DB TO $username;
EOSQL
  done
fi

