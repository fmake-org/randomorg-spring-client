pipeline {
  agent any
  environment {
    RANDOMORG_APIKEY = credentials('randomorg-api-key')
  }
  stages {
    stage('Build') {
      agent {
        docker {
          image 'openjdk:11-jdk-slim'
        }

      }
      steps {
        sh './gradlew assemble'
      }
    }
    stage('Test') {
      agent {
        docker {
          image 'openjdk:11-jdk-slim'
        }

      }
      steps {
        sh './gradlew check'
      }
    }
  }
  post {
      always {
          junit 'build/test-results/test/*.xml'
      }
  }
}