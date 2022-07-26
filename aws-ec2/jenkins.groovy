#!/usr/bin/env groovy

pipeline {

    agent { label 'master' }

    parameters {
        string(name: 'account', description: 'Deploy into AWS Account', defaultValue: "aws-account" )
        string(name: 'ProjectName', description: 'Provide the unique stack name. MANDATORY field.', defaultValue: 'aws-terraform-project')
       
       
    }

    environment {
        AWS_CREDENTIALS = "${params.account}"
    }

    stages {

        stage('initialise-aws-terraform-project') {
            steps {
                script {
                    currentBuild.displayName = "#${env.BUILD_NUMBER} - ${params.action}"
                    currentBuild.description = "Build #${env.BUILD_NUMBER} - Deploying to ${params.account} - ProjectName ${params.ProjectName} "
                    sh 'printenv'
                    sh 'pwd'
                    sh 'cd /var/jenkins_home/workspace/aws-ec2-terraform/aws-ec2/terraform-manifest/'
                    sh 'ls'
                    sh 'terraform -chdir=/aws-ec2/terraform-manifest/ init'
                }
            }
        }
    }
}