pipeline {
    agent any
    
    environment {
        DOCKER_TOKEN = credentials('docker-hub-token')
        BACKEND_IMAGE = 'thaboxanx/order-backend'
        FRONTEND_IMAGE = 'thaboxanx/order-frontend'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Backend: Build and Test') {
            steps {
                sh 'mvn clean package'
                sh 'mvn test'
            }
        }
        
        stage('Frontend: Build and Test') {
            steps {
                dir('frontend') {
                    sh 'npm install'
                    sh 'npm run test'
                    sh 'npm run build'
                }
            }
        }
        
        stage('Docker: Login') {
            steps {
                sh 'docker login -u thaboxanx -p $DOCKER_TOKEN'
            }
        }
        
        stage('Docker: Build Backend') {
            steps {
                sh 'docker build -t ${BACKEND_IMAGE}:${BUILD_NUMBER} -f Dockerfile .'
                sh 'docker tag ${BACKEND_IMAGE}:${BUILD_NUMBER} ${BACKEND_IMAGE}:latest'
            }
        }
        
        stage('Docker: Build Frontend') {
            steps {
                dir('frontend') {
                    sh 'docker build -t ${FRONTEND_IMAGE}:${BUILD_NUMBER} -f Dockerfile .'
                    sh 'docker tag ${FRONTEND_IMAGE}:${BUILD_NUMBER} ${FRONTEND_IMAGE}:latest'
                }
            }
        }
        
        stage('Docker: Push Images') {
            steps {
                sh 'docker push ${BACKEND_IMAGE}:${BUILD_NUMBER}'
                sh 'docker push ${BACKEND_IMAGE}:latest'
                sh 'docker push ${FRONTEND_IMAGE}:${BUILD_NUMBER}'
                sh 'docker push ${FRONTEND_IMAGE}:latest'
            }
        }
        
        stage('Clean Up') {
            steps {
                sh 'docker system prune -f'
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Sending notifications...'
        }
    }
}
