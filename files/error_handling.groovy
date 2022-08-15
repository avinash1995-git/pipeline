pipeline {

    agent any

    stages {

        stage ('Build') {

            steps {
                sh '''
                echo "this is my build stage"

                exit 1
                '''
            }
        }
        
    }
}