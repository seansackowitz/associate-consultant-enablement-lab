#!/bin/bash

ID_RSA="$OPENSHIFT_DATA_DIR/.ssh/id_rsa"

ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no -i $ID_RSA $1 $2
