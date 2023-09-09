# EC2-exercise

## Connection
* SSH (Mac/Linux) or Git Bash (Windows)
  * Copy .PEM file to the machine from which you are going to connect.
  * Make sure permissions on .PEM file are appropriate (chmod 400 file.pem)
  * Connect with ssh command: ssh ec2-user@ec2-xxxxxx.eu-west-3.compute.amazonaws.com -i alledaags_pem.pem
  
* Putty (Windows)
  * See https://stackoverflow.com/questions/3190667/convert-pem-to-ppk-file-format
  
## Deploy  
* Compile the code locally
  * mvn clean install
* Upload the JAR-file to S3
  * you can use the interface https://s3.console.aws.amazon.com/s3/buckets/be.bitbox.alledaags/?region=eu-west-3&tab=overview
* Download the JAR on the server
  * Login (see previous chapter)
  * aws s3 cp s3://be.bitbox.alledaags/alledaags-0.0.1-SNAPSHOT.jar . --region=eu-west-X
  * (optional) instal java: 
    * sudo rpm --import https://yum.corretto.aws/corretto.key
    * sudo curl -L -o /etc/yum.repos.d/corretto.repo https://yum.corretto.aws/corretto.repo
      * sudo yum install -y java-20-amazon-corretto-devel 
  * chmod 777 alledaags-0.0.1-SNAPSHOT.jar  
  * java -jar alledaags-0.0.1-SNAPSHOT.jar or install it as a Linux service   
