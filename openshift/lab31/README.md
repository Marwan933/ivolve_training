#  Network Configuration 

## **Objective**

## Build a new image from Dockerfile in https://github.com/IbrahimmAdel/static-website.git
• Create a deployment using this image
• Create a service to expose the deployment.
• Define a network policy that allow traffic to the NGINX pods only from other pods within the same namespace.
• Enable the NGINX Ingress controller using Minikube addons.
• Create an Ingress resource.
• Update /etc/hosts to map the domain to the Minikube IP address.
• Access the custom NGINX service via the Ingress endpoint using the domain name

## **Steps:**

#### step :1 Build a new Docker image from the Dockerfile

1. Clone the repository
```
git clone https://github.com/IbrahimmAdel/static-website.git
```
2. Navigate to the project directory
```
cd static-website
```
3. Build the Docker image
```
docker build -t docker.io/marwanhesham463/static-website:latest .
```

<img width="784" alt="lab31 stat build" src="https://github.com/user-attachments/assets/1b0aff91-9273-4c49-9245-de44e7336079" />


#### step 2: create `deployment.yml`

```bash

apiVersion: apps/v1
kind: Deployment
metadata:
  name: static-website
spec:
  replicas: 1
  selector:
    matchLabels:
      app: static-website
  template:
    metadata:
      labels:
        app: static-website
    spec:
      containers:
      - name: nginx
        image: docker.io/marwanhesham463/static-website # Your custom image name
        ports:
        - containerPort: 80

```

- Apply the deployment:
  ```bash
  kubectl apply -f deployment.yml
  ```

#### **3. Expose the Deployment as a Service**
- Create a `service.yml` file:
  ```yaml
  ---
  apiVersion: v1
  kind: Service
  metadata:
    name: static-web-svc
  spec:
    selector:
      app: static-web
    ports:
    - port: 80
      targetPort: 80
  ```

- Apply the service:
  ```bash
  kubectl apply -f service.yml
  ```

#### **4. Create a Network Policy**
- Define a `network-policy.yml` file:
  ```yaml
  ---
  apiVersion: networking.k8s.io/v1
  kind: NetworkPolicy
  metadata:
      name: allow-same-namespace
      namespace: default
  spec  :
      podSelector:
        matchLabels:
          app: static-web
      ingress:
      - from:
        - podSelector: {}
  ```

- Apply the network policy:
  ```bash
  kubectl apply -f net-policy.yml
  ```

#### **step 5: Enable NGINX Ingress Controller**

- Enable the NGINX ingress addon:

  ```bash
  minikube addons enable ingress

  ```
<img width="703" alt="lab31 enable" src="https://github.com/user-attachments/assets/8eaedefb-3db9-4506-a35e-1c99c802b93c" />

#### **6. Create an Ingress Resource**
- Define an `ingress.yaml` file:
  ```yaml
  ---
  apiVersion: networking.k8s.io/v1
  kind: Ingress
  metadata:
      name: static-website-ingress
      namespace: default
      annotations:
        nginx.ingress.kubernetes.io/rewrite-target: /
  spec:
      rules:
      - host: static-website.local
        http:
          paths:
          - path: /
            pathType: Prefix
            backend:
              service:
                name: static-web-svc
                port:
                  number: 80
  ```

- Apply the ingress resource:
  ```bash
  kubectl apply -f ingress.yml
  ```
  
#### 7. Update /etc/hosts
1. Find the Minikube IP:
```
minikube ip
```
2. Edit /etc/hosts:
```
sudo vim/etc/hosts
#add this line 
<Minikube_IP> static-website.local
```

<img width="520" alt="lab31 miniip" src="https://github.com/user-attachments/assets/b0472c5d-cd3e-4a98-9c4d-f0ae1f026a05" />

---

#### 8. Access the Service via the Ingress
For the domain name (static-website.local) to work, it needs to map to the Minikube IP address. 
```
http://static-website.local
```

<img width="624" alt="lab31 website" src="https://github.com/user-attachments/assets/3aec15a3-39cc-45ab-b325-7bdadfb7b703" />

# Done..
