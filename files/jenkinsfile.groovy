pipeline {
    agent any
    stages {
        stage ('Build') {
            steps {
                sh '''
                #!/bin/bash
                pwd
                ls
                echo "This is my first build job using pipeline

                '''
            }
        }

        stage ('deploy') {
            steps {
                echo "This is my first deploy job"
            }
        }

        stage ('Test1') {
            steps {
                sh '''
                   #!/bin/bash
                   echo "this is my test job"
                   pwd

                '''
            }
        }

        stage ('Test2') {
            steps {
            echo "my next level test here"
        }
        }
    }
}