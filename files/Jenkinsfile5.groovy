pipeline {
    agent none 

    options {
  buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '2', daysToKeepStr: '', numToKeepStr: '3')
}


    stages {
        stage ('Workflow') {
            parallel {

            stage('Build') { 
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
}
}