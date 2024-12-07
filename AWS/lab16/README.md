# Description
## build a serverless application using aws lambda, api gateway, and dynamoDB

### Step 1: Create dynamoDB Table

<img width="803" alt="lab16 createDynamoDB" src="https://github.com/user-attachments/assets/f539731e-bd1e-48df-a904-a64d80c73f21">

### Step 2: Create Lambda Function


<img width="701" alt="lab16 codeoflambda" src="https://github.com/user-attachments/assets/40cb1871-20a1-488a-8542-6575bb8c5fd6">


<img width="657" alt="lab16 function" src="https://github.com/user-attachments/assets/8814651e-743d-4af8-abc4-28ad6e7713e8">


<img width="778" alt="lab16 flow function lambda" src="https://github.com/user-attachments/assets/1fd6fb72-ba73-4737-89fd-76383f18103a">

### step 3: Set Up API Gateway
- API Name: `http-crud-tutorial-api`
- Create routes:
  - `GET /items/{id}`
  - `GET /items`
  - `PUT /items`
  - `DELETE /items/{id}`
- Attach Lambda integration for each route
---

## step 4: Testing with `CURL`

 1. **Create/Update Item**
```bash
curl -X "PUT" -H "Content-Type: application/json" -d '{
  "id": "123", 
  "price": 12345, 
  "name": "myitem"
}' https://<API-ID>.execute-api.<REGION>.amazonaws.com/items
```

 2. **Retrieve All Items**
```bash
curl https://<API-ID>.execute-api.<REGION>.amazonaws.com/items
```

 3. **Retrieve a Single Item**
```bash
curl https://<API-ID>.execute-api.<REGION>.amazonaws.com/items/123
```

 4. **Delete an Item**
```bash
curl -X "DELETE" https://<API-ID>.execute-api.<REGION>.amazonaws.com/items/123
```

<img width="811" alt="lab16 commands cli" src="https://github.com/user-attachments/assets/187d9519-fcd7-4ffe-84f7-21210cf3f63b">


## step 5: Clean-Up Instructions

1. **DynamoDB Table**: Delete from the DynamoDB console.
2. **API Gateway**: Delete the API.
3. **Lambda Function**: Remove the function.
4. **CloudWatch Logs**: Delete the associated log group.

---


<img width="499" alt="lab16 deletedb" src="https://github.com/user-attachments/assets/c9f70332-adac-4f7b-adce-8dca38619194">


<img width="451" alt="lab16 delete lambda" src="https://github.com/user-attachments/assets/0544f9df-2c59-458a-aeb4-396a5e03cade">

# Done ..
