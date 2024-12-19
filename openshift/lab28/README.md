# Deployment vs. StatefulSet  

## Objective  
- **Compare** Deployment and StatefulSet.  
- Create a YAML file for **MySQL StatefulSet** configuration.  
- Write a YAML file to define a **Service** for the MySQL StatefulSet.

  ### Step 1: Comparison between Deployment and StatefulSet

   #### 1- Deployment:

   ##### *Purpose: Manages stateless applications.

   ##### *Pods Management: Ensures the specified number of replicas are running at any given time. Pods are interchangeable and can be replaced or rescheduled freely.

   ##### *Scaling: Scaling up or down is straightforward and pods are created or terminated as needed.

   ##### *Pod Identifiers: Pods have dynamic names, and no guarantees about pod identity.

   ##### *Storage: Uses persistent volume claims (PVCs) which are dynamically provisioned and not tied to any specific pod.

   #### 2- StatefulSet:

   ##### *Purpose: Manages stateful applications.

   ##### *Pods Management: Ensures the specified number of replicas are running with unique and persistent identities.

   ##### *Scaling: Scaling up or down requires careful consideration as the order of pod termination and creation is maintained.

   ##### *Pod Identifiers: Each pod has a stable, unique identifier maintained across rescheduling and scaling.

   ##### *Storage: Uses persistent volume claims (PVCs) that are statically provisioned and bound to each pod, ensuring data is preserved across rescheduling.


### Step 2: YAML Configuration for MySQL StatefulSet

```bash
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: mysql
  labels:
    app: mysql
spec:
  serviceName: "mysql"
  replicas: 3
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
        image: mysql:8.0
        env:
        - name: MYSQL_ROOT_PASSWORD
          value: "rootpassword"  # Replace with a secure password
        ports:
        - containerPort: 3306
          name: mysql
        volumeMounts:
        - name: mysql-data
          mountPath: /var/lib/mysql
  volumeClaimTemplates:
  - metadata:
      name: mysql-data
    spec:
      accessModes: [ "ReadWriteOnce" ]
      resources:
        requests:
          storage: 10Gi

```

### Step 3: Service Configuration for MySQL StatefulSet
```bash
apiVersion: v1
kind: Service
metadata:
  name: mysql
  labels:
    app: mysql
spec:
  ports:
  - port: 3306
    targetPort: 3306
    name: mysql
  clusterIP: 10.96.14.15  # Headless service for StatefulSet
  selector:
    app: mysql

```

#### step 4:apply them:

<img width="631" alt="lab28 apply" src="https://github.com/user-attachments/assets/0bba0eee-d05b-446e-beec-20b24189664e" />

#### step 5: Test
```bash
kubectl get pods

```

<img width="547" alt="lab28 getpods" src="https://github.com/user-attachments/assets/ad5d16b2-03f2-4fdf-9de9-7206a9e6125b" />

```bash

kubectl get pvc
kubectl get pv

```

<img width="796" alt="lab28 pvcandpv" src="https://github.com/user-attachments/assets/21eb5ba9-b812-49ed-bcec-98847c86d632" />


#### step 6: History of this lab 

<img width="345" alt="lab28,history" src="https://github.com/user-attachments/assets/0459ffe3-1970-4e4a-a893-7aebcace93d4" />

# Done..
