/* see https://www.jenkins.io/doc/book/pipeline/ */

pipeline {
	agent any

	tools {
		jdk "Java 16"   /* may need to be adapted */
		maven "Maven 3.8.1"   /* may need to be adapted */
	}

    parameters {
        booleanParam(
			name: "Extended",
			description: "run extended tests",
			defaultValue: false
		)
    }

	stages {
		stage("Build") {
			steps {
				bat "mvn clean package"
			}
		}

		stage("Test") {
			when {
				expression {
					return params.Extended
				}
			}
			steps {
				bat "mvn verify -DskipUnitTests"
			}
		}

		stage("Analyze") {
			steps {
				script {
					def sonarQubeScannerHome = tool "Sonar Qube Runner 4.6.2"   /* may need to be adapted */
					withSonarQubeEnv("SonarQube") {   /* may need to be adapted */
						bat "${sonarQubeScannerHome}/bin/sonar-scanner"
					}
				}
			}
		}
	}
	post {
		always {
			junit allowEmptyResults: true, testResults: "target/surefire-reports/*.xml,target/failsafe-reports/*.xml"

			jacoco()
		}
	}
}