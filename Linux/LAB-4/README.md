# Description 
# DNS and Network Configuration with Hosts File and BIND9

## **Objective**
This project demonstrates the differences between using the `hosts` file and BIND9 as a DNS solution for URL resolution. It includes instructions for:

- Modifying the `hosts` file to resolve a URL to a specific IP address.
- Configuring BIND9 to resolve wildcard subdomains.
- Verifying URL resolution using `dig` or `nslookup`.

---

## **Key Features**
- **Hosts File Configuration:** Resolve `name-ivolve.com` to `192.168.1.10` statically.
- **DNS Configuration (BIND9):** Set up wildcard subdomains (`*.name-ivolve.com`) to resolve to `192.168.1.10`.
- **Testing:** Verify DNS resolution with `dig` and `nslookup`.

---

## **Prerequisites**
- Ubuntu or a Linux-based OS with administrative privileges.
- BIND9 installed for DNS server configuration.
- Basic knowledge of DNS and network configuration.

---

## **Setup Instructions**

### **1. Using the Hosts File**
The `hosts` file allows you to statically map domain names to IP addresses locally.

#### **Steps**
1. **Open the `hosts` file**:
   ```bash
   sudo nano /etc/hosts
   ```

2. **Add the following entry to map `name-ivolve.com` to `192.168.1.10`:**
```bash
   192.168.1.10    name-ivolve.com
```

**Save the file and test the resolution:**
```bash
ping name-ivolve.com
```

## **2. Configuring BIND9 for DNS**

BIND9 is used to configure a local DNS server for resolving domain names, including wildcard subdomains.

## **Steps to Configure BIND9**
**1.Install BIND9: If BIND9 is not installed, use the following command:**
```bash
sudo apt update
sudo apt install bind9
```
**2.Define the Zone in BIND9: Edit the BIND9 configuration file (named.conf.local):**
```bash
sudo nano /etc/bind/named.conf.local
```
Add the following zone definition for `name-ivolve.com`
```bash
zone "name-ivolve.com" {
    type master;
    file "/etc/bind/db.name-ivolve.com";
};

```

**3.Create the Zone File: Copy the default zone file:**
```bash
sudo cp /etc/bind/db.local /etc/bind/db.name-ivolve.com
```
**4.Edit the Zone File: Open the zone file:**
```bash
sudo nano /etc/bind/db.name-ivolve.com
```
Update the contents with the following:
```bash
$TTL    604800
@       IN      SOA     ns.name-ivolve.com. admin.name-ivolve.com. (
                          2         ; Serial
                          604800    ; Refresh
                          86400     ; Retry
                          2419200   ; Expire
                          604800 )  ; Negative Cache TTL

; Name servers
@       IN      NS      ns.name-ivolve.com.

; A records
ns      IN      A       192.168.1.10
@       IN      A       192.168.1.10
*.name-ivolve.com. IN A 192.168.1.10

```
**5.Validate the Configuration: Run the following commands to ensure the configuration is correct:**
```bash
sudo named-checkconf
sudo named-checkzone name-ivolve.com /etc/bind/db.name-ivolve.com
```

**6.Restart BIND9: Apply the configuration changes:**
```bash
sudo systemctl restart bind9
```

### Testing the Configuration
**1.Using dig**

Run the following commands to test DNS resolution:

-Test `name-ivolve.com` resolution:
```bash
dig @192.168.1.10 name-ivolve.com
```

-Test wildcard subdomain resolution:
```bash
dig @192.168.1.10 subdomain.name-ivolve.com
```
**2.Using nslookup**

Test with nslookup to verify DNS resolution:

-Test `name-ivolve.com` resolution:
```bash
nslookup name-ivolve.com 192.168.1.10
```
-Test wildcard subdomain resolution:
```bash
nslookup subdomain.name-ivolve.com 192.168.1.10
```

### Done...!
