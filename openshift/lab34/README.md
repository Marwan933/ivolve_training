#  DaemonSets & Taint and Toleration

## what is daemonset and what is it used for ? Create a DaemonSet YAMLfile with a Pod template containing an Nginx container in openshift and verify the number of pods. Using minikube, Taint the Minikube node with a specific key-value pair ‘color=red’to simulate a tainted node. Create a pod with toleration ‘color=blue’ and notice what is the status of the pod, change the toleration to ‘color=red’and notice what will happen. Make a Comparison between Taint & Toleration & Node Affinity


### **Step 1: what is the daemonset**

#### A DaemonSet ensures that all (or some) Nodes run a copy of a Pod. As nodes are added to the cluster, Pods are added to them. As nodes are removed from the cluster, those Pods are garbage collected. Deleting a DaemonSet will clean up the Pods it created.

### **Step 2: Create a DaemonSet File for Nginx**

1. Create a YAML file (`Daemonset.yaml`) with the following content:

```bash
apiVersion: apps/v1
kind: DaemonSet
metadata:
  name: nginx-daemonset
  labels:
    app: nginx
spec:
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - name: nginx
        image: nginx:latest
        ports:
        - containerPort: 80
```

2. Apply the file:

    ```bash
    kubectl apply -f Daemonset.yaml
    ```
    
<img width="620" alt="lab34 applydeamon" src="https://github.com/user-attachments/assets/323ac057-59f8-492f-991f-55e5df5cdd5a" />


3. Verify the number of Pods running:

    ```bash
    kubectl get pods -o wide
    ```

    <img width="799" alt="lab34 gets" src="https://github.com/user-attachments/assets/b833d526-0704-4380-ba94-83f89b7b4588" />

### **Step 3: Taint the Minikube Node**

1. Add a taint to the Minikube node:

    ```bash
    kubectl taint nodes minikube color=red:NoSchedule
    ```

<img width="753" alt="lab34 taint" src="https://github.com/user-attachments/assets/ae1b91ba-8d50-48fa-9f77-8675cd3be577" />


2. Verify the taint is applied:

    ```bash
    kubectl describe node minikube | grep Taints
    ```

<img width="675" alt="lab34 verfiy taint" src="https://github.com/user-attachments/assets/56b95754-1992-458c-8a42-c098b7dd217f" />

### **Step 4: Create a Pod with a Toleration**

1. Create a YAML file (`toleration-pod.yaml`) for the pod:

    ```yaml
    apiVersion: v1
    kind: Pod
    metadata:
      name: nginx-toleration
    spec:
      containers:
      - name: nginx
        image: nginx:latest
      tolerations:
      - key: "color"
        operator: "Equal"
        value: "blue"
        effect: "NoSchedule"
    ```

2. Apply the file:

    ```bash
    kubectl apply -f toleration-pod.yaml
    ```

    <img width="739" alt="lab34 get pods1 1ready" src="https://github.com/user-attachments/assets/14dc114c-1712-4685-ac89-cbda1cfee915" />

3. Observe the status of the Pod:

    ```bash
    kubectl get pods
    ```

    <img width="684" alt="lab34 getpodsnew" src="https://github.com/user-attachments/assets/dd5a54f0-59f3-4d71-af94-53838ebc57ef" />

### **Step 5: Update the Toleration to Match the Taint**
1. Edit the YAML file to update the toleration value to `red`:

```yaml

  apiVersion: v1
kind: Pod
metadata:
  name: nginx-toleration
spec:
  tolerations:
  - key: "color"
    operator: "Equal"
    value: "red"
    effect: "NoSchedule"
  containers:
  - name: nginx
    image: nginx:latest

```

<img width="437" alt="lab34 red yamlllds" src="https://github.com/user-attachments/assets/bdc9dcc0-1dd9-45ba-b559-761c51b7eb4c" />


2. Reapply the updated file:

    ```bash
    kubectl apply -f toleration-pod.yaml
    ```

3. Verify that the Pod is now scheduled on the tainted node:

    ```bash
    kubectl get pods 
    ```
- Now It's on running state because it matches the taint of the Node

<img width="739" alt="lab34 get pods1 1ready" src="https://github.com/user-attachments/assets/02842e51-b664-43b7-9265-cdae4dd9a6ab" />

---

### **Step 6: Comparison of Taint, Toleration, and Node Affinity**

| **Aspect**          | **Taint**                                                                 | **Toleration**                                                       | **Node Affinity**                                                                 |
|---------------------|---------------------------------------------------------------------------|------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| **Definition**      | Marks a node with restrictions on which Pods can run there.              | Allows Pods to tolerate specific taints and get scheduled on tainted nodes. | Expresses Pod scheduling preferences to certain nodes based on their labels.     |
| **Purpose**         | Prevents Pods from being scheduled unless tolerated.                    | Enables Pods to run on tainted nodes.                                   | Places Pods on nodes matching specific labels.                                   |
| **Scope**           | Applied at the node level.                                               | Applied at the Pod level.                                              | Applied at the Pod level to match node labels.                                   |
| **Key Use Case**    | Reserve nodes for specific workloads or purposes.                       | Allows exceptions to taints for certain Pods.                          | Ensures Pods run on nodes with specific characteristics.                         |

---

# Done..
