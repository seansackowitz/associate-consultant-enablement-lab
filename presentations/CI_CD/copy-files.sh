echo "copying files to $1"

scp git-ssh.sh $1:~/app-root/data/.ssh
scp ~/.ssh/id_rsa $1:~/app-root/data/.ssh
