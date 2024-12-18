# Description
## deploy NGINX with 3 replicas .create a service to expose nginx deployment user port forwoding to access nginx service locally update nginx image to apache view deployment's rollout history . rollback nginx deployment to the previous image version and monitor pod status to confirm successful rolback
---
#### Step 1: Deploy NGINX with 3 replicas

<img width="588" alt="new lab27 deploy" src="https://github.com/user-attachments/assets/e3bf56db-66b2-4c8c-93b7-88aa01cf0c82" />

---

#### Step 2: Create a service to expose the NGINX deployment

<img width="387" alt="lab27 1-serviice vim" src="https://github.com/user-attachments/assets/e8a1cb67-117c-4a8d-b523-21261bde980a" />

---

#### Step 3: Apply them and get pods

<img width="726" alt="lab27 1 apply" src="https://github.com/user-attachments/assets/efe75d1c-9e69-4778-a656-678c3aa3e95e" />

---

#### Step 4: Expose the service to create a route and test by using port-forward
```bash
kubectl expose service/nginx-service
```

<img width="710" alt="lab27 1 portforward" src="https://github.com/user-attachments/assets/6df93254-bf4f-4fec-8c38-4352e47e3f57" />

<img width="532" alt="lab27 1 curl" src="https://github.com/user-attachments/assets/b12a911d-e269-48f0-ad41-2fa9665ab997" />

---

#### step 6: Roll Back ivolve Deployment to the Previous Image Version

**If you want to roll back the deployment to the previous version of the image, run:**

<img width="747" alt="lab27 1undoandstatus" src="https://github.com/user-attachments/assets/95635d3b-0fcf-495d-b5a1-2a311bbd735d" />

---

#### step 7: Update Deployment Image to Apache

**Update the deployment to use the Apache image:**
```bash
kubectl set image deployment/nginx-deployment nginx=httpd:latest

```
<img width="776" alt="lab27 1 setimage" src="https://github.com/user-attachments/assets/ac550051-8b5e-4ae3-bd70-4e636f8b6682" />

---

#### step 8: Monitor Pod Status to Confirm Successful Rollback

**To monitor the status of the pods and ensure that the rollback is successful, use:**
```bash
kubectl get pods -w
```
<img width="542" alt="lab27 1 monitor" src="https://github.com/user-attachments/assets/5bece570-af5a-4017-9bfd-3d3ea1ec9f8d" />

---

#### step 9: View Rollout History

**Check the rollout history:**
```bash
kubectl rollout history deployment/nginx-deployment
```
<img width="745" alt="lab27 1 history" src="https://github.com/user-attachments/assets/a5716964-00ea-4169-ac85-2ac6b159d2ba" />

---

#### step 10: History of this lab

<img width="438" alt="lab27 1histor2" src="https://github.com/user-attachments/assets/e581f7d2-008c-4451-8ae2-46a89fe2aace" />

--- 

<img width="466" alt="lab27 1history" src="https://github.com/user-attachments/assets/62bb8fbb-5002-4a5d-bc05-6e1cc634baab" />

### Done..!
