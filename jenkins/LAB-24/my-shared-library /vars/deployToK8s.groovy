// deployToK8s.groovy
def call(String kubeConfig, String deploymentYaml, String kubeNamespace) {
    sh """
        kubectl --kubeconfig=$kubeConfig apply -f $deploymentYaml -n $kubeNamespace
    """
}
