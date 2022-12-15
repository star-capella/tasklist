pipeline {
    agent any

    // tools {
    //     // Install the Maven version configured as "M3" and add it to the path.
    //     maven "M3"
    // }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', changelog: false, poll: false, url: 'ssh://roz/srv/git/tasklist'

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"

                sh "podman build --tag springlist -f ./Dockerfile"
                sh "podman run -dt --net=host --rm tasklist"
            }
            
            stage('Deploy') {
            steps {
                sh '''
                    scp -P 1950 -i ~/.ssh/roz_rsa target/tasklist*.war tcurtis@roz:~/
                    //ssh -p 1950 -i ~/.ssh/roz_rsa -t -t tcurtis@roz << EOF
// rm -r ~/Archive/public_html
// cp -r ~/public_html ~/Archive
// rm -r ~/public_html
// unzip tcurtis.war -d public_html/
// sudo systemctl restart tomcat9
// exit
// EOF
//                 '''
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
