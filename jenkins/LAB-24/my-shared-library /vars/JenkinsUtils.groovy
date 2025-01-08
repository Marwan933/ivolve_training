// src/com/mycompany/JenkinsUtils.groovy
package com.mycompany

class JenkinsUtils implements Serializable {
    def script
    
    JenkinsUtils(script) {
        this.script = script
    }
    
    def updateDeploymentImage(String deploymentYaml, String dockerImage) {
        script.sh "sed -i 's|image:.*|image: $dockerImage|g' $deploymentYaml"
    }
}
