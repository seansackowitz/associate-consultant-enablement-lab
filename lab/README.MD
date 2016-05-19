Installing Mongo:
Fedora
1. Add mongo repos to dnf
sudo dnf config-manager --add-repo http://repo.mongodb.org/yum/redhat/7/mongodb-org/3.2/x86_64/
2. Install with dnf
sudo dnf install mongodb-org --nogpgcheck
3. Check to make sure it is installed properly
Should read like this:
[cfrieden@localhost lab]$ mongod --version
db version v3.2.6
git version: 05552b562c7a0b3143a729aaa0838e558dc49b25
OpenSSL version: OpenSSL 1.0.1e-fips 11 Feb 2013
allocator: tcmalloc
modules: none
build environment:
    distmod: rhel70
    distarch: x86_64
    target_arch: x86_64

4. Start the mongo server
service mongod start
