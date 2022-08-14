pipeline {
    agent none 

    stages {
        stage ('Build') {

            agent { label 'build' }

            steps {
             echo 'This is my Build stage'
            }
        }

        stage ('Deploy') {

            agent { label 'build' }

            steps {
                echo 'This is my Deploy stage'
            }
        }

        stage ('Test') {

            agent { label 'tomcat' }

            steps {

                echo 'This is my Test stage'
                
            }
        }
    }
}