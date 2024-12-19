# Multi-container Applications

## Objective:
- Create a deployment for Jenkins with an init container that sleeps for 10 seconds before the Jenkins container starts.
- Use readiness and liveness probes.
- Create a NodePort service to expose Jenkins.
- Verify that the init container runs successfully and Jenkins is properly initialized.
- Explain the differences between readiness & liveness probes, and init & sidecar containers.

---

## Steps:

### 1. Create Deployment YAML
```yaml

apiVersion: apps/v1
kind: Deployment
metadata:
  name: jenkins
  labels:
    app: jenkins
spec:
  replicas: 1
  selector:
    matchLabels:
      app: jenkins
  template:
    metadata:
      labels:
        app: jenkins
    spec:
      initContainers:
      - name: init-container
        image: busybox
        command: ["sh", "-c", "sleep 10"]
      containers:
      - name: jenkins
        image: jenkins/jenkins:lts
        ports:
        - containerPort: 8080
        - containerPort: 50000
        readinessProbe:
          httpGet:
            path: /login
            port: 8080
          initialDelaySeconds: 30
          periodSeconds: 10
        livenessProbe:
          httpGet:
            path: /login
            port: 8080
          initialDelaySeconds: 90
          periodSeconds: 30
```

### 2. Apply Deployment

```bash
kubectl apply -f jenkins-deployment.yaml
```

### 3. Create NodePort Service YAML
```yaml

apiVersion: v1
kind: Service
metadata:
  name: jenkins
spec:
  selector:
    app: jenkins
  ports:
    - protocol: TCP
      port: 8080
      targetPort: 8080
      nodePort: 32000
  type: NodePort

```

### 4. Apply Service

```bash
kubectl apply -f jenkins-service.yaml
```

### 5. Verify Initialization and Access Jenkins

- Check the status of the init container:

```bash
kubectl get pods
```

<img width="737" alt="lab33 gets" src="https://github.com/user-attachments/assets/bb713033-7ae5-407c-ac27-56af3369a0da" />

 - Access Jenkins

   ![lab33 jenkins testjen](https://github.com/user-attachments/assets/a698509c-a937-4f91-bd28-91e3749fd899)


# Done..
