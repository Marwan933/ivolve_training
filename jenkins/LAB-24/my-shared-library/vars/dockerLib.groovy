def buildImage(String imageName) {
    sh "docker build -t ${imageName} ."
}

def pushImage(String imageName, String dockerRegistryCredentialId) {
    withDockerRegistry([credentialsId: dockerRegistryCredentialId]) {
        sh "docker push ${imageName}"
    }
}

