#  Helm Chart Deployment 

## **Objective**

### Create a new Helm chart for Nginx. Deploy the Helm chart and verify the deployment. Access the Nginx server. Delete nginx release.

#### step 1: Install Helm

1. Install Helm 
```
sudo snap install helm --classic
```
2. Verify Installation 
```
helm version
```

#### step 2: Create a New Helm Chart for Nginx
1. Create `quota.yaml` file
```
helm create nginx-chart
```
Navigate to chart directory
```
cd nginx-chart
```

<img width="801" alt="lab35 helm" src="https://github.com/user-attachments/assets/702fa065-4d4c-40d2-8dc2-4d7650e8d1ed" />


2. Deploy the Helm Chart
```
helm install nginx-release ./nginx-chart
```

<img width="797" alt="lab35 helminstall" src="https://github.com/user-attachments/assets/43ef5d51-5d35-4e19-9fa9-77c9f0b20fc6" />


3. Verify deployment
```bash
kubectl get pods
```

<img width="762" alt="lab35 getpods" src="https://github.com/user-attachments/assets/0d21de8f-12b2-49e0-84df-2718f5824007" />

```bash
kubectl get services
```

- if the service is of type `ClusterIP`, edit svc to change it to `NodePort`, How? By using This command

  <img width="795" alt="lab35 editnodecmd" src="https://github.com/user-attachments/assets/fb3cfd96-66b9-4310-9073-ec6e081caf31" />


  <img width="797" alt="lab35 finalgetsvcnodeport" src="https://github.com/user-attachments/assets/1ea6d9e9-455a-49f3-a07c-59edfff2186e" />

4. Get Minikube IP and Access the Service
```bash
minikube ip
# get port 
kubectl get svc nginx-release
```
5. Open the browser and navigate to
```
http://<minikube-ip>:<service-port>
```

<img width="960" alt="nginx" src="https://github.com/user-attachments/assets/e280494f-109f-4d89-8a85-0807dccd6f9d" />

#### step 3: Delete the Nginx Release
1. Uninstall the deployment
```
helm uninstall nginx-release
```
2. Verify the removal
```
kubectl get all
```

# Done..
