# Configuring a MySQL Pod using ConfigMap and Secret

## Objective

- Create a namespace called `ivolve` and apply a resource quota to limit resource usage within the namespace.
- Create a Deployment in the `ivolve` namespace for MySQL with:
  - Resource requests: CPU: 0.5 vCPU, Memory: 1G.
  - Resource limits: CPU: 1 vCPU, Memory: 2G.
- Define MySQL database name and user in a ConfigMap.
- Store the MySQL root password and user password in a Secret.
- Exec into the MySQL pod and verify the database configurations.

---

### Step 1: Create the Namespace

```bash

apiVersion: v1
kind: ResourceQuota
metadata:
  name: ivolve-quota
  namespace: ivolve
spec:
  hard:
    requests.cpu: "2"
    requests.memory: 4Gi
    limits.cpu: "4"
    limits.memory: 8Gi
```

**Apply the namespace and quota configuration:**

```bash
kubectl apply -f quota.yaml
```


#### step 2: Create a MySQL ConfigMap

```bash
apiVersion: v1
kind: ConfigMap
metadata:
  name: mysql-config
  namespace: ivolve
data:
  database-name: ivolve_db
  user: ivolve_user

```

**Apply the ConfigMap:**

```bash
kubectl apply -f mysql-configmap.yaml

```
### Step 3: Create the Secret

```bash
apiVersion: v1
kind: Secret
metadata:
  name: mysql-secret
  namespace: ivolve
type: Opaque
data:
  root-password: bWFyd2Fu
  user-password: bWFyd2Fu
```

**Apply the Secret:**

```bash
kubectl apply -f mysql-secret.yaml
```

### Step 4: Create the Deployment
```bash

apiVersion: apps/v1
kind: Deployment
metadata:
  name: mysql
  namespace: ivolve
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mysql
  template:
    metadata:
      labels:
        app: mysql
    spec:
      containers:
      - name: mysql
        image: mysql:5.7
        env:
        - name: MYSQL_ROOT_PASSWORD
          valueFrom:
            secretKeyRef:
              name: mysql-secret
              key: root-password
        - name: MYSQL_DATABASE
          valueFrom:
            configMapKeyRef:
              name: mysql-config
              key: database-name
        - name: MYSQL_USER
          valueFrom:
            configMapKeyRef:
              name: mysql-config
              key: user
        - name: MYSQL_PASSWORD
          valueFrom:
            secretKeyRef:
              name: mysql-secret
              key: user-password
        ports:
        - containerPort: 3306
        resources:
          requests:
            memory: "1Gi"
            cpu: "500m"
          limits:
            memory: "2Gi"
            cpu: "1"

```

**Apply the Deployment:**

```bash
kubectl apply -f mysql-deployment.yaml
```

---

### Step 5: Verify the Configuration

1. **List all resources in the `ivolve` namespace:**
   ```bash
   kubectl get all -n ivolve
   ```
   
2. **Exec into the MySQL pod:**
   ```bash
   kubectl exec -it <mysql-pod-name> -n ivolve -- /bin/bash
   ```
 <img width="764" alt="lab32 exec" src="https://github.com/user-attachments/assets/ff1c1f15-67de-4f4c-a656-85fa3524cd67" />

3. **Verify database configurations inside the pod:**
   ```bash
   mysql -u ivolve_user -p
   # Enter the password stored in MYSQL_PASSWORD (decoded from the Secret).
   mysql> SHOW DATABASES;
   ```

   <img width="795" alt="lab32 showdb" src="https://github.com/user-attachments/assets/47283cb5-d90c-420c-893a-1e1f5a148290" />

# Done..
