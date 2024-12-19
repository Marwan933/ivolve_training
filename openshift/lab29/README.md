# Storage Configuration

### create a openshift deployment named my-deployment with 1 replica using the nginx image exec into the nginx pod and create a file at /usr/share/nginx/html/hello.txt. delete the nginx pod and wait for the deployment to create anew pod  ... create presistant volume claim. modeify the deployment to attach the pvc to the pod at /usr/share/nginx/html/hello.txt. and repeat the previous steps and verify file persists across pod deletaions
---

## Step 1: Create the Deployment  

1.1 **Create a file named `my-deployment.yaml`**:

```bash
apiVersion: apps/v1
kind: Deployment
metadata:
  name: my-deployment
spec:
  replicas: 1
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
        image: nginx
        volumeMounts:
        - name: nginx-storage
          mountPath: /usr/share/nginx/html
      volumes:
      - name: nginx-storage
        persistentVolumeClaim:
          claimName: nginx-pvc

```
1.2 **Apply the deployment**

```bash
  kubectl apply -f my-deployment.yml
```


1.3 **Verify the pod is running**

```bash
kubectl get pods
```

### step 2: Exec into the Pod and Create a File

```bash
kubectl exec -it <my-deployment-b4b9fc88d-44mjj> -it bash
```

```bash
echo "hello,this is marwan hesham" >> /usr/share/nginx/html/hello.txt
```

- Verify the file is served by NGINX:

```bash
curl localhost/hello.txt
```

**Now, i will sohw the commands to explain the pervious steps**

<img width="782" alt="lab29 repeat" src="https://github.com/user-attachments/assets/7d7c8ad1-5620-4a35-8a30-cacb5449b084" />


### step 3: Create a PVC and Update the Deployment

**PVC Configuration**

- Create a file named `nginx-pvc.yml`

```bash
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: nginx-pvc
spec:
  accessModes:
  - ReadWriteOnce
  resources:
    requests:
      storage: 1Gi
```

- Modify the deployment to attach the PVC. Update `nginx-deployment.yml` as follows

```bash
#Create a Delpoyment
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: my-deployment
  
spec:
  replicas: 1
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - name: my-container
        image: nginx:latest
        ports:
        - containerPort: 80
        volumeMounts: 
        - name: nginx-volume
          mountPath: /usr/share/nginx/html
        volumes: 
        - name: nginx-volume
          PersistentVolumeClaim:
            claimName: nginx-pvc
```

<img width="682" alt="lab29 update" src="https://github.com/user-attachments/assets/0e2b0854-1d65-497e-a859-df9ecf52569d" />


- Apply the updated deployment

```bash
kubectl apply -f my-deployment.yaml
```

- **Delete the pod**

```bash
kubectl delete pod <nginx-pod-name>
```

- **Verify the File in the New Pod**

```bash
kubectl exec -it <new-nginx-pod-name> -- cat /usr/share/nginx/html/hello.txt
```

<img width="782" alt="lab29 repeat" src="https://github.com/user-attachments/assets/d39bab11-f3bc-48f0-ac39-f97c11536e0b" />

---

### step 4: Comparison Between PV, PVC, and StorageClass

<img width="421" alt="lab29 comp" src="https://github.com/user-attachments/assets/1b85dfa0-9938-4479-bc7a-f5012c55cfba" />

---

#### step 5: History of this lab 

<img width="666" alt="lab29 history" src="https://github.com/user-attachments/assets/b3d96368-1531-436e-bbbe-77a90a42a292" />

---

# Done..
