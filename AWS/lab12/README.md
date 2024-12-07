# Description
## create aws account , set billing alarm create 2 IAM groups (admin-developer) admin group has admin permissions, developer group only access to s3 , create admin-1 user console access only with MFA & admin-2 prog user with cli access only and list users and groups using commands , create dev-user with programmatic and console access


## **Step 1: Create an AWS Account**

1. Go to [AWS Sign Up](https://aws.amazon.com/).
2. Fill in the required details, verify your identity, and choose a support plan.
3. After setup, log in to the **AWS Management Console**.

---

## **Step 2: Set Up Billing Alarm**

1. Navigate to the **Billing Dashboard** in the AWS Console.
2. Go to **Budgets** > **Create Budget**.
3. **Choose Budget Type**: Cost Budget.
4. **Set Thresholds**: Specify the monthly budget amount (e.g., $10).
5. Configure **Email Alerts** for the budget threshold.
6. Review and create the budget.

---

## **Step 3: Create IAM Groups**

1. Navigate to the **IAM Console** > **Groups**.
2. Click **Create Group**:
   - **Group Name**: `Admin`.
   - Attach the **AdministratorAccess** policy.
   - Click **Create Group**.
3. Create another group:
   - **Group Name**: `Developer`.
   - Attach the **AmazonEC2FullAccess** policy.
   - Click **Create Group**.

---

<img width="808" alt="groups ,lab" src="https://github.com/user-attachments/assets/7a83d9ec-6eb6-4ee1-96dd-21dbfc62d01d">

## **Step 4: Create IAM Users**

### **1. Admin-1 User with Console Access Only**
1. Go to the **IAM Console** > **Users** > **Add Users**.
2. **User Name**: `admin-1&admin-2`.
3. **Access Type**: Select **AWS Management Console Access**.
4. **Password Options**: Set a custom password.
5. Assign the user to the **AdminGroup**.
6. Click **Create User**.
7. Enable MFA:
   - Go to the **Users** section and select `admin-1`.
   - In the **Security Credentials** tab, click **Manage MFA**.
   - Follow the instructions to set up MFA using an authenticator app.

<img width="691" alt="users of admingroup" src="https://github.com/user-attachments/assets/9cb4cfc2-e25c-4375-a89f-96d4b17d5291">

### **2. Admin-2-Prog User with CLI Access Only**
1. Go to **IAM Console** > **Users** > **Add Users**.
2. **User Name**: `admin-2`.
3. **Access Type**: Select **Programmatic Access**.
4. Assign the user to the **AdminGroup**.
5. Click **Create User** and download the **Access Key ID** and **Secret Access Key**.
---

<img width="756" alt="admin-2disbled" src="https://github.com/user-attachments/assets/96c9448e-0eea-437b-aa1d-0d905704945b">

### **3. Dev-User with Programmatic and Console Access**
1. Go to **IAM Console** > **Users** > **Add Users**.
2. **User Name**: `dev-user`.
3. **Access Type**: Select both **Programmatic Access** and **AWS Management Console Access**.
4. Assign the user to the **DeveloperGroup**.
5. Click **Create User** and download the **Access Key ID** and **Secret Access Key**.
---

<img width="742" alt="devvvv-user" src="https://github.com/user-attachments/assets/b40bb6b5-9c99-40e5-a5a0-1f9abac3adaf">


## **step 5: Attach Policies to Groups**:
   
1- Choose the Group from the list and go to `Permission` then `Add Permissions`.
  
- Attach the **AdministratorAccess** policy to the `admin` group:

  <img width="740" alt="Permissions of admingroup" src="https://github.com/user-attachments/assets/a4af3917-7bb0-4a0e-8fb8-81ab270b475c">

- Attach the **AmazonEC2FullAccess** policy to the `developer` group:

  <img width="721" alt="developer perm" src="https://github.com/user-attachments/assets/dc186331-e014-4395-81d6-84ebe1e483b8">

  ## **Step 6: List All Users and Groups Using AWS CLI**

1. Configure the CLI:
   ```bash
   aws configure
   ```

 Insert you `Access Key ID` , `Secret Access Key` , `Region` , `Output Format`

 ```bash
 aws iam list-users
 ```

<img width="797" alt="listusers" src="https://github.com/user-attachments/assets/1dd88bb2-b050-4781-897c-24ff5a4c6804">

```bash
aws iam list-groups
```

<img width="788" alt="groups" src="https://github.com/user-attachments/assets/70645082-c6c8-43fd-8873-f71ec1d54bff">

Test S3 Access:
   ```bash
   aws s3 ls
   ```

<img width="598" alt="testttt sss" src="https://github.com/user-attachments/assets/44564095-285b-40ce-b05f-c39a40910fab">


### Done..



