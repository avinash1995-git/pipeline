pipeline {

    agent any

    parameters {
  choice choices: ['Build', 'Deploy', 'Test'], description: 'Enter your stage to execute', name: 'option'
}


    stages{

        stage('Build') {

            when {
                expression { params.option == 'Build'}
            }

            steps {

                echo "This is my Build stage"
            }
        }

         stage('Deploy') {

            when {
                expression { params.option == 'Deploy'}
            }

            steps {

                echo "This is my Deploy stage"
            }
        }

         stage('Test') {

            when {
                expression { params.option == 'Test'}
            }

            steps {

                echo "This is my Test stage"
            }
        }
    }
}