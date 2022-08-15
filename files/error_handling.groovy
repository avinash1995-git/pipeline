pipeline {

    agent any

    stages {

        stage ('Build') {

            steps {

                catchError(buildResult: 'SUCCESS', message: 'This is known error', stageResult: 'FAILURE') {
                sh '''
                echo "this is my build stage"

                exit 1
                '''
                }
            }
        }

        stage('Commit') {

            steps {
                echo "this is mt commit stage"
            }
        }
        
    }
}