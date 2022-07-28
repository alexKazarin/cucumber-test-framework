pipeline {
    agent any
    tools {
        maven 'maven-3.8.3'
        jdk 'jdk11'
    }
    stages {
        stage('build framework') {
            steps {
                echo 'Notify GitLab - build framework'
                updateGitlabCommitStatus name: 'build framework', state: 'pending'
                script {
                    try {
                        sh "ls -la"
                        sh "mvn -f framework/pom.xml clean install"
                    } catch (err) {
                        updateGitlabCommitStatus name: 'build framework', state: 'failed'
                    }
                }
                updateGitlabCommitStatus name: 'build framework', state: 'success'
            }
        }
        stage('build features') {
            steps {
                echo 'Notify GitLab - build features'
                updateGitlabCommitStatus name: 'build features', state: 'pending'
                script {
                    try {
                        sh "ls -la"
                        sh "mvn -f features/pom.xml clean validate"
                    } catch (err) {
                        updateGitlabCommitStatus name: 'build features', state: 'failed'
                    }
                }
                updateGitlabCommitStatus name: 'build features', state: 'success'
            }
        }
    }
}