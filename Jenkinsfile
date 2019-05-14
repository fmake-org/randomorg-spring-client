pipeline {
  agent any
  stages {
    stage('Build') {
      agent {
        docker {
          image 'openjdk:11-jdk-slim'
        }

      }
      steps {
        sh './gradlew build'
      }
    }
    stage('Test') {
      agent {
        docker {
          image 'openjdk:11-jdk-slim'
        }

      }
      steps {
        sh './gradlew test'
      }
    }
  }
}