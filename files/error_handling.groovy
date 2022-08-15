pipeline {

    agent any

    stages {

        stage ('Build') {

            steps {

                echo "this is my build stage"

                exit 1
            }
        }
        
    }
}