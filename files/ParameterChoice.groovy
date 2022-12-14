pipeline {

    agent none

    parameters {
  choice choices: ['build', 'deploy', 'test'], description: '''please enter your stage to be executed from below:

build , deploy, test''', name: 'choice'
}

    stages {

        stage('Build') {
            agent {label 'build'}

            steps {

                script {

                    if (params.choice == 'build') {

                echo "execute build stage"
                }

                else {
                    echo " skip build stage"
                }
                }
            }
        }
        
        

        stage('deploy') {
        

            agent any

            steps {

                echo "this is my deploy"
            }
        }

        stage('Test') {

            agent {label 'tomcat'}

            steps {

                echo "this is my test"
            }
        }
    }
}