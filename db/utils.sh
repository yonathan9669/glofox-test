function split() {
  local IFS="${2:-$' :|'}"
  eval $1\=\(${!1}\)
}

function set_var() {
  local var=$1
  test -z ${!var} && test -f .env && eval $var=$(grep -e "^$var=.*" .env | cut -d '=' -f2)
}

function required_vars() {
  local REQUIRED_VARS=$1
  local error=false
  split REQUIRED_VARS

  for var in "${REQUIRED_VARS[@]}"; do
    set_var $var
    if test -z ${!var}; then
      error=true
      echo "\"$var\" it's required"
    elif [ $2 ]; then
      echo "$var=${!var}"
    fi
  done

  if $error; then
    echo "Some required variables are missing, please SET them into the .env file or export them"
    exit 1;
  fi
}
