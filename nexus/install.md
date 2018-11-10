## java

```shell
rpm -ivh jdk-8u151-linux-x64.rpm
java -version
```


## nexus

```shell
cd /home/wxh/nexus
tar -zxvf nexus-3.9.0-01-unix.tar.gz 
cd /home/wxh/nexus/nexus-3.9.0-01/bin
./nexus run &
```


## 开启远程访问端口

```shell
firewall-cmd --zone=public --add-port=8081/tcp --permanent
firewall-cmd --reload

```


nexus3默认端口是:8081  
nexus3默认账号是:admin  
nexus3默认密码是:admin123  

## 创建cuba代理库

proxy

Remote storage:  
https://repo.cuba-platform.com/content/groups/premium

Http Authentication:  
170704000693  
YQOk9IJO1IFG  

## pom.xml

```shell
mvn install
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>cuba-maven-puller</groupId>
    <artifactId>com.jimuyida</artifactId>
    <version>1.0-SNAPSHOT</version>

    <repositories>
        <repository>
            <id>normal</id>
            <url>http://192.168.10.11:8081/repository/maven-public/</url>
        </repository>
        <repository>
            <id>cuba</id>
            <url>http://192.168.10.11:8081/repository/cuba/</url>
        </repository>
        <repository>
            <id>cuba_premium</id>
            <url>http://192.168.10.11:8081/repository/cuba_premium/</url>
        </repository>

    </repositories>

    <dependencies>
        <dependency>
            <groupId>com.haulmont.cuba</groupId>
            <artifactId>cuba-gui</artifactId>
            <version>6.8.3</version>
        </dependency>

        <dependency>
            <groupId>com.haulmont.cuba</groupId>
            <artifactId>cuba-core</artifactId>
            <version>6.8.3</version>
        </dependency>

        <dependency>
            <groupId>com.haulmont.cuba</groupId>
            <artifactId>cuba-web</artifactId>
            <version>6.8.3</version>
        </dependency>

        <dependency>
            <groupId>com.haulmont.cuba</groupId>
            <artifactId>cuba-web-themes</artifactId>
            <version>6.8.3</version>
        </dependency>

        <dependency>
            <groupId>com.haulmont.cuba</groupId>
            <artifactId>cuba-web-toolkit</artifactId>
            <version>6.8.3</version>
        </dependency>


        <dependency>
            <groupId>com.haulmont.cuba</groupId>
            <artifactId>cuba-rest-api</artifactId>
            <version>6.8.3</version>
        </dependency>

        <dependency>
            <groupId>com.haulmont.cuba</groupId>
            <artifactId>cuba-portal</artifactId>
            <version>6.8.3</version>
        </dependency>

        <dependency>
            <groupId>com.haulmont.cuba</groupId>
            <artifactId>cuba-desktop</artifactId>
            <version>6.8.3</version>
        </dependency>

    	<dependency>
    		<groupId>com.haulmont.cuba</groupId>
    		<artifactId>cuba-client</artifactId>
    		<version>6.8.3</version>
    	</dependency>

        <dependency>
            <groupId>com.haulmont.cuba</groupId>
            <artifactId>cuba-uberjar</artifactId>
            <version>6.8.3</version>
        </dependency>

        <dependency>
            <groupId>com.haulmont.cuba</groupId>
            <artifactId>cuba-idp</artifactId>
            <version>6.8.3</version>
        </dependency>

    	<dependency>
    		<groupId>com.haulmont.reports</groupId>
    		<artifactId>reports-global</artifactId>
            <version>6.8.3</version>
    	</dependency>
        <dependency>
            <groupId>com.haulmont.reports</groupId>
            <artifactId>reports-gui</artifactId>
            <version>6.8.3</version>
        </dependency>
        <dependency>
            <groupId>com.haulmont.reports</groupId>
            <artifactId>reports-web</artifactId>
            <version>6.8.3</version>
        </dependency>
        <dependency>
            <groupId>com.haulmont.reports</groupId>
            <artifactId>reports-desktop</artifactId>
            <version>6.8.3</version>
        </dependency>
        <dependency>
            <groupId>com.haulmont.reports</groupId>
            <artifactId>reports-core</artifactId>
            <version>6.8.3</version>
        </dependency>

        <dependency>
            <groupId>com.haulmont.fts</groupId>
            <artifactId>fts-web</artifactId>
            <version>6.8.3</version>
        </dependency>
        <dependency>
            <groupId>com.haulmont.fts</groupId>
            <artifactId>fts-core</artifactId>
            <version>6.8.3</version>
        </dependency>
        <dependency>
            <groupId>com.haulmont.fts</groupId>
            <artifactId>fts-gui</artifactId>
            <version>6.8.3</version>
        </dependency>
        <dependency>
            <groupId>com.haulmont.fts</groupId>
            <artifactId>fts-desktop</artifactId>
            <version>6.8.3</version>
        </dependency>
        <dependency>
            <groupId>com.haulmont.fts</groupId>
            <artifactId>fts-global</artifactId>
            <version>6.8.3</version>
        </dependency>


        <dependency>
            <groupId>com.haulmont.charts</groupId>
            <artifactId>charts-global</artifactId>
            <version>6.8.3</version>
        </dependency>
        <dependency>
            <groupId>com.haulmont.charts</groupId>
            <artifactId>charts-gui</artifactId>
            <version>6.8.3</version>
        </dependency>
        <dependency>
            <groupId>com.haulmont.charts</groupId>
            <artifactId>charts-web</artifactId>
            <version>6.8.3</version>
        </dependency>
        <dependency>
            <groupId>com.haulmont.charts</groupId>
            <artifactId>charts-web-toolkit</artifactId>
            <version>6.8.3</version>
        </dependency>

        <dependency>
            <groupId>com.haulmont.bpm</groupId>
            <artifactId>bpm-gui</artifactId>
            <version>6.8.3</version>
        </dependency>
        <dependency>
            <groupId>com.haulmont.bpm</groupId>
            <artifactId>bpm-core</artifactId>
            <version>6.8.3</version>
        </dependency>
        <dependency>
            <groupId>com.haulmont.bpm</groupId>
            <artifactId>bpm-web</artifactId>
            <version>6.8.3</version>
        </dependency>
        <dependency>
            <groupId>com.haulmont.bpm</groupId>
            <artifactId>bpm-global</artifactId>
            <version>6.8.3</version>
        </dependency>




    </dependencies>
</project>
```

