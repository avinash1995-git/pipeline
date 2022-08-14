pipeline {
    agent none 

    options {
  buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '2', daysToKeepStr: '', numToKeepStr: '3')
}

 parameters {
  string defaultValue: 'My string', description: 'Please enter your string here', name: 'value'
}


    stages {
        stage ('Workflow') {
            parallel {

            stage('Build') { 
            agent { label 'build' }

            steps {
             echo "the string entered is $value"
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