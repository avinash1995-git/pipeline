pipeline {
    agent any
    stages {
        stage('BUILD') {
            steps {
                sh '''
                    #!/bin/bash 
                    pwd 
                    ls
                    echo "This is a BUILD stage"
                    sleep 5
                '''  
            }
        }
        stage('DEPLOY') {
            steps {
                echo "This is a DEPLOY stage"
                sh 'sleep 5'
            }
        }
        stage('TESTING1') {
            steps {
                sh 'echo "This is a TESTING1 stage"'
                sh 'sleep 5'
            }
        }
        stage('TESTING2') {
            steps {
                sh '''
                    echo "This is a TESTING2 stage"
                    sleep 5
                '''
            }
        }
    }
}