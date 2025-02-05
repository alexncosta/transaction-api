pipeline {
    agent any
    stages {
        stage('Build Image Docker') {
            steps {
                echo 'Building..'
                script {
                    dockerapp = docker.build("educacaovirtual/produto-api:${env.BUILD_ID}", "-f ./src/Dockerfile ./src")
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                echo 'Pushing Docker Image..'
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                        dockerapp.push('latest')
                        dockerapp.push("${env.BUILD_ID}")
                    }
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy Kubernetes') {
            steps {
                echo 'Deploying on Kubernetes....'
                sh 'kubectl apply -f ./k8s/deployment.yml'
            }
        }
    }
}