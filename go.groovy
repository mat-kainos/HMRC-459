#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('branch name') {
            steps {
                sh "env"
            }
        }
        stage('Check condition develop or *_HF') {
            when {
                    beforeAgent true
                    expression { GIT_BRANCH ==~ /(?i:.*_HF)|develop/  } //matches: develop or *_HF or *_hf
            }
            steps {
                sh "echo $GIT_BRANCH"
            }
        }

        stage('Check condition feature but not *_HF') {
            when {
                    beforeAgent true
                    expression { GIT_BRANCH ==~ /feature*(?!)(?i:.*_HF)/  } //matches: feature* which doesn't contain *_HF
            }
            steps {
                sh "echo $GIT_BRANCH"
            }
        }
    }
    post {
        always {
            deleteDir()
        }
    }
}