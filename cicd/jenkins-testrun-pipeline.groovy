pipeline {
    agent any
    tools {
        maven 'maven-3.8.3'
        jdk 'jdk11'
    }
    stages {
        stage('build framework') {
            steps {
                sh "ls -la"
                sh "mvn -f framework/pom.xml clean install"
            }
        }
        stage('execute tests') {
            steps {
                sh "ls -la"
                sh("mvn -f features/pom.xml clean post-integration-test")
            }
        }
        stage('generate report') {
            steps {
                allure jdk: '', reportBuildPolicy: 'ALWAYS', results: [[path: 'features/target/allure-results']]
            }
        }
    }
}