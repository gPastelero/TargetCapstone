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
      }
    }

    stage('Publish Report') {
       steps {
              // Use the Jenkins HTML Publisher plugin to generate an HTML report from the XML report
              publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'test-results', reportFiles: 'test-results.xml', reportName: 'Test Results'])
        }
      }
  }
}