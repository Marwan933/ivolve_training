def deployToKubernetes(String kubeConfigPath, String kubeToken, String deploymentYaml, String namespace) {
    sh """
    export KUBECONFIG=${kubeConfigPath}
    kubectl --token=${kubeToken} apply -f ${deploymentYaml} -n ${namespace}
    """
}
