pipeline {

    agent none

    parameters {
  string defaultValue: 'default', description: 'Please enter your string here', name: 'value', trim: true
}

    tools {
  maven 'maven-3'
}

    stages {

        stage('Build') {

            agent {label 'build'}

            steps {
                
                echo "this is my $value stage"
                git branch: 'main', url: 'https://github.com/avinash1995-git/java-code.git'
                sh 'mvn clean package'

            }
        }

        stage('Deploy') {

            agent{label 'build'}

            steps {

                sh '''
                echo "this is my $value stage"
                cd /home/ec2-user/workspace/java_pipeline_new/target
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