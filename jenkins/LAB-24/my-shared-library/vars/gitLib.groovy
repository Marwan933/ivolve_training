def checkoutRepository(String repoUrl, String branch) {
    checkout scm: [$class: 'GitSCM', branches: [[name: branch]], userRemoteConfigs: [[url: repoUrl]]]
}

