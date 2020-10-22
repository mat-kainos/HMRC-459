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
                    expression { GIT_BRANCH ==~ /(?i)^(?!.*\\_HF\\b).*feature.*/  } //matches: feature* which doesn't contain *_HF
                    //\w*Id\b
                    //^(?!.*\_HF\b).*feature.*
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