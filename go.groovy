#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('branch name') {
            steps {
                sh "env"
            }
        }
        stage('Check condition') {
            when {
                    beforeAgent true
                    expression { GIT_BRANCH ==~ /(?i:.*_HF)|develop/  } //matches: develop or *_HF or *_hf
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
