pipeline {

    agent none

    parameters {
  string defaultValue: 'default', description: 'Please enter your git hub url', name: 'github_var', trim: true
  string defaultValue: 'default', description: 'Please enter your deploy path', name: 'path_var', trim: true
}

    tools {
  maven 'maven-3'
}

    stages {

        stage('Build') {

            agent {label 'build'}

            steps {
                
                echo "this is my build stage"
                git branch: 'main', url: params.github_var
                sh 'mvn clean package'

            }
        }

        stage('Deploy') {

            agent{label 'build'}

            steps {

                sh '''
                echo "this is my deploy stage"
                cd $path_var
                scp *.war ec2-user@184.72.5.85:/home/ec2-user/apache-tomcat-9.0.65/webapps

                '''
            }
        }

        stage('Central Test') {

            parallel {

                stage('Unit Test'){

                    agent {label 'tomcat'}
                  steps {
                    sh '''
                    cd /home/ec2-user/apache-tomcat-9.0.65/webapps
                    ./test.sh

                    '''
                }
                }

                stage('Final Test'){

                    agent {label 'tomcat'}
                   steps {
                    sh '''
                    cd /home/ec2-user/apache-tomcat-9.0.65/webapps
                    ./finaltest.sh

                    '''
                }
                }

                
            }
        }
        
        
    }
}