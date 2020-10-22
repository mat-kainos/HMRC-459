#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('branch name') {
            steps {
                sh "echo $BRANCH_NAME"
            }
        }
        stage('Check condition') {
            when {
                    beforeAgent true
                    expression { env.BRANCH_NAME ==~ /(?i:.*_HF)|develop/  } //matches: develop or *_HF or *_hf
            }
            steps {
                sh "echo $BRANCH_NAME"
            }
        }
    }
    post {
        always {
            deleteDir()
        }
    }
}