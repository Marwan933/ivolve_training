// dockerActions.groovy
def buildAndPushDockerImage(String dockerImage) {
    sh "docker build -t $dockerImage ."
    
    withCredentials([usernamePassword(credentialsId: 'Docker_hub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
        sh "docker login -u \$DOCKER_USERNAME -p \$DOCKER_PASSWORD"
        sh "docker push $dockerImage"
    }
}
