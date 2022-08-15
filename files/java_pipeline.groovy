pipeline {

    agent none
    tools {
  maven 'maven-3'
}

    stages {

        stage('Build') {

            agent {label 'build'}

            steps {

                git branch: 'main', url: 'https://github.com/avinash1995-git/java-code.git'
                sh 'mvn clean package'

            }
        }

        stage('Deploy') {

            agent{label 'build'}

            steps {

                sh '''

                cd /home/ec2-user/workspace/java_pipeline_new/target
                scp *.war ec2-user@172.31.12.54:/home/ec2-user/apache-tomcat-9.0.65/webapps

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
