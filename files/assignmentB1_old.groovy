pipeline {

    agent none

    parameters {
  choice choices: ['master', 'build', 'tomcat'], description: 'Please enter the node to run your stage :', name: 'option'
}


    stages {

        stage('Build') {

            agent {label 'build'}

            steps {

                echo "This is my build stage"


            }
        }

         stage('Deploy') {

            agent {label 'build'}

            steps {

                echo "this is my Deploy stage"
            }
        }

         stage('Test1') {

            steps {

                script {

                   if(params.option == 'master') {
             
                     echo "this is my Test1 stage"
                
            }
                }
            }
        }

         stage('Test2') {

            steps {

                script {
                 
                 if (params.option == 'master')
                 {


                    echo "this is my Test2 stage"
                 }
                
            }
            }
        }
    }
}