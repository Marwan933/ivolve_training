# Description

## use the AWS cli to create an s3 bucket , configure permissions and upload/download the files to/from the bucket , enable versioning and logging for the bucket

### Step 1: Install and Configure the aws cli

#### 1- install AWS cli:

[AWS Cli install](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)

#### 2- Configure aws cli:

<img width="442" alt="lab15,,config" src="https://github.com/user-attachments/assets/6bd263a5-bcb5-41e6-a9d1-1a618ac17fd2">

### Step 2: Create bucket

using this command
```bash
aws s3api create-bucket --bucket ivolve-marwanhesham-bucket --region us-east-1
```

<img width="796" alt="lab15 create bucket" src="https://github.com/user-attachments/assets/84a98944-ba7b-469a-85b6-dfef036b2a95">

### Step 3: Configure Bucket Permissions

#### 1- Create bucket-policy.json file

#### 2- add this poliicy on it:

```
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "PublicReadWriteAccess",
      "Effect": "Allow",
      "Principal": "*",
      "Action": [
        "s3:GetObject",
        "s3:PutObject"
      ],
      "Resource": "arn:aws:s3:::ivolve-marwanhesham-bucket/*"
    }
  ]
}

```

<img width="418" alt="policy" src="https://github.com/user-attachments/assets/71520271-24fe-4e2a-a133-1e557dcde834">

### step 4: Apply the policy using this command:
```
aws s3api put-bucket-policy --bucket ivolve-marwanhesham-bucket --policy file://bucket_policy.json

aws s3api get-bucket-policy --bucket ivolve-marwanhesham-bucket

```
### step 5: Disable "Block Public Access" Settings
To allow public policies, you need to disable the BlockPublicPolicy and other restrictive settings.
Run the following command:
```bash
aws s3api delete-public-access-block --bucket your-bucket-name
```

#### step 5: Enable Versioning
```bash
aws s3api put-bucket-versioning --bucket ivolve-marwanhesham-bucket --versioning-configuration Status=Enabled

```

#### step 6: Enable Logging

1. Enable logging for the original bucket:
   ```bash
   aws s3api put-bucket-logging --bucket ivolve-marwanhesham-bucket --bucket-logging-status '{
       "LoggingEnabled": {
           "TargetBucket": "ivolve-marwanhesham-bucket",
           "TargetPrefix": "logs/"
       }
   }'
   ```

**this photo Explain The last 2 steps**

   <img width="796" alt="lab15 versioning logging" src="https://github.com/user-attachments/assets/f4f47cad-cddc-4c87-8ffc-0c7cf1ce4787">

#### step 7: Upload and Download Files

**Upload a File:**

```bash
aws s3 cp bucket_policy.json s3://ivolve-marwanhesham-bucket/bucket_policy.json
```

<img width="824" alt="lab 15 uploadthefile" src="https://github.com/user-attachments/assets/c5eda212-2e90-4946-97f7-69622ab1c8b8">

**Download a File:**
```bash
aws s3 cp s3://ivolve-marzouk-bucket/policy.json policy1.json
```

#### step 8: List Files in the Bucket
```bash
aws s3 ls s3://ivolve-marwanhesham-bucket
```

### Done ...
