pipeline {
    agent none
    stages {
        stage('Node1') {
            agent { label 'build'}
            steps {
                sh '''
                 free >> memfile
                 '''
                 stash includes: 'memfile', name: 'memory'

            }
        }

        stage('Master') {
            agent {label 'master'}
            steps {
           unstash 'memory' 
        }
        }
    }
}