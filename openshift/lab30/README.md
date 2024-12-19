#   Security and RBAC

## **Objective**

### Create a Service Account. Define a Role named pod-reader allowing read-only access to pods in the namespace. Bind the pod-reader Role to the Service Account and get ServiceAccount token. Make a Comparison between service account - role & role binding - and cluster role & cluster role binding
### Comparison Between Concepts

#### step 1: Define a Role `pod-reader`
This role will allow read-only access to Pods in the default namespace.
```bash

apiVersion: rbac.authorization.k8s.io/v1
kind: Role
metadata:
  namespace: default     
  name: ivolve-marwan
rules:
  - apiGroups: [""]     
    resources: ["pods"] 
    verbs: ["get", "list", "watch"]  
```

#### step 2: Bind the `pod-reader` Role to the Service Account
Create a RoleBinding to bind the Role to the ServiceAccount.
```bash

apiVersion: rbac.authorization.k8s.io/v1
kind: RoleBinding
metadata:
  name: ivolve-binding
  namespace: default  # Replace with your namespace
subjects:
  - kind: ServiceAccount
    name: ivolve-marwan
    namespace: default
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: Role
  name: ivolve-role

```

<img width="570" alt="lab30 bind" src="https://github.com/user-attachments/assets/ae6ed0c0-d55d-49ff-91d5-f33f43d753d2" />

#### step 3: Apply the YAML files
if the files were created 

```bash
kubectl get serviceaccount
kubectl get rolebinding -n deafult
```

<img width="633" alt="lab30 serviceacc" src="https://github.com/user-attachments/assets/9eaa8fc1-82a9-4ae1-b67d-8ef1b1eeed46" />

<img width="581" alt="lab30 ivolvebinding" src="https://github.com/user-attachments/assets/d4a5abe6-ce4a-40ed-980d-b49f540e66d5" />

---

#### step 4: Get the ServiceAccount Token
```
kubectl create token ivolve-marwan -n deafult
```
<img width="795" alt="lab30 token" src="https://github.com/user-attachments/assets/c5eec5bb-30b2-4871-a5bc-73558c58cbfd" />

---

#### step 5: Comparison

| **Concept**           | **ServiceAccount**                       | **Role**                                    | **RoleBinding**                             | **ClusterRole**                             | **ClusterRoleBinding**                       |
|------------------------|------------------------------------------|---------------------------------------------|---------------------------------------------|---------------------------------------------|----------------------------------------------|
| **Scope**             | Associated with a namespace              | Restricted to a namespace                   | Restricted to a namespace                   | Cluster-wide                                | Cluster-wide                                 |
| **Purpose**           | Provides identity for Pods to access the API server | Defines permissions within a namespace      | Binds a Role to a user, group, or SA        | Defines cluster-wide permissions            | Binds a ClusterRole to a user, group, or SA  |
| **Namespace-Specific** | Yes                                      | Yes                                         | Yes                                         | No                                          | No                                           |
| **Typical Usage**     | For Pods to authenticate to the API server | Grant specific permissions to a namespace   | Assign Roles to identities                  | Grant broad permissions, e.g., managing nodes | Assign ClusterRoles to identities            |

---

# Done..
