# EC2-exercise

## Connection
* SSH (Mac/Linux) or Git Bash (Windows)
  * Copy .PEM file to the machine from which you are going to connect.
  * Make sure permissions on .PEM file are appropriate (chmod 600 file.pem)
  * Connect with ssh command: ssh ec2-user@ec2-35-181-154-70.eu-west-3.compute.amazonaws.com -i alledaags_pem.pem
  
* Putty (Windows)
  * See https://stackoverflow.com/questions/3190667/convert-pem-to-ppk-file-format
  
## Deploy  
* Compile the code locally
  * mvn clean install
* Upload the JAR-file to S3
  * you can use the interface https://s3.console.aws.amazon.com/s3/buckets/alledaags-folder/?region=eu-west-3&tab=overview
* Download the JAR on the server
  * Login (see previous chapter)
  * aws s3 cp s3://alledaags-folder/alledaags-0.0.1-SNAPSHOT.jar /opt/alledaags --region=eu-west-3
  * (optional) instal java: sudo yum install java-11
  * java -jar alledaags-0.0.1-SNAPSHOT.jar   
