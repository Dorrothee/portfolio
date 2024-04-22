pipeline {
    agent any
    environment {
        DATE = new Date().format('yy.M')
        TAG = "${DATE}.${BUILD_NUMBER}"
	    DOCKER_IMAGE = 'final-img'
    }
    stages {
        stage ('Project Build and Test') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    docker.build("cauliflower413/${DOCKER_IMAGE}:${TAG}")
                }
            }
        }
	stage('Pushing Docker Image to Dockerhub') {
	    steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerHub') {
                        docker.image("cauliflower413/${DOCKER_IMAGE}:${TAG}").push("latest")
                    }
                }
            }
        }
    }
    post {
        always {
            archiveArtifacts('target/surefire-reports/TEST-*.xml')
        }
	    success {
                echo "Application testing successfully completed"
        }
        failure {
                echo "Application testing failed!"
        }
    }
}