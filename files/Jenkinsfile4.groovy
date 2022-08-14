pipeline {
    agent none
    stages {
        stage('Build') {
            agent { label 'build'}
            steps {
                git branch: 'main', url: 'https://github.com/avinash1995-git/java-code.git'
                sh 'mvn clean package'
            }
        }
    }
}