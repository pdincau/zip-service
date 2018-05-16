node {

    def mvnHome = tool "Maven"
    def app

    stage("Checkout and Unit Tests") {
        checkout scm
        sh "${mvnHome}/bin/mvn clean test"
    }

    stage("Integration Test") {
        sh "${mvnHome}/bin/mvn clean test-compile failsafe:integration-test"
    }

    stage("Build") {
        sh "${mvnHome}/bin/mvn package"
        app = docker.build("pdincau/zip-service")
    }

    stage("UAT vs Container") {
        docker.image('pdincau/zip-service').withRun('-p 8081:8080') { c ->
            sh 'sleep 5'
            sh "${mvnHome}/bin/mvn -Dtest=\"*UAT\" -Dhost=localhost -Dport=8081 test"
        }
    }

    stage("Push Image") {
        app.push("${env.BUILD_NUMBER}")
    }

}
