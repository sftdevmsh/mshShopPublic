##!/bin/bash
#
#NEW_ADDRESS="shop.online.com"
#
#current_version=$(awk -F'[<>]' '/<artifactId>moduleController<\/artifactId>/ {getline; print $3}' ..\..\moduleController\pom.xml)
#major=$(echo "$current_version" | cut -d '.' -f 1)
#minor=$(echo "$current_version" | cut -d '.' -f 2)
#patch=$(echo "$current_version" | cut -d '.' -f 3 | cut -d '-' -f 1)
#patch=$((patch + 1))
#new_version="$major.$minor.$patch-RELEASE"
#sed -i '' "s/<version>$current_version<\/version>/<version>$new_version<\/version>/" ..\..\moduleController\pom.xml
#echo "Version incremented to: $new_version"
#
## List of modules
#modules=("moduleCommon" "moduleRepository" "moduleService" "moduleController")
#
## Loop through each module and execute Maven commands
#for module in "${modules[@]}"
#do
#    cd "$module" || exit
##    /Users/holosen/IdeaProjects/maven/apache-maven-3.9.9/bin/mvn clean compile package install
#    mvn clean compile package install
#    cd ..
#done
#
