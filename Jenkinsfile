pipeline {
  agent any
  stages {
    stage('Build') {
      agent {
        docker {
          image 'openjdk:13-jdk-alpine'
        }

      }
      steps {
        sh './gradlew build'
      }
    }
    stage('Test') {
      agent {
        docker {
          image 'openjdk:13-jdk-alpine'
        }

      }
      steps {
        sh './gradlew test'
      }
    }
  }
}