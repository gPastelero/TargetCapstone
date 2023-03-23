pipeline {
  agent any
  stages {

    stage('Build') {
      steps {
        echo 'Building...'
        sh 'mvn clean package -DskipTests'
        archiveArtifacts artifacts: '**/target/*.jar'
      }
    }

    stage('Test') {
      steps {
        echo 'Testing....'
        sh 'mvn test -B'
        sh 'run_tests --format junit --output test-results.xml'
      }
    }
  }
}