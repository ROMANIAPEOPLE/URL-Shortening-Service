### π» BUILD PROJECT BY LINUX ( λΉλ νμ€νΈ νκ²½ : centos-7.3-64)

#### 1. JDK 1.8 μ€μΉ μ¬λΆ νμΈ

```bash
# java -version
```

**jdk 1.8**μ΄ μ€μΉλμ΄ μλ€λ©΄ μλμ κ°μ΄ μΆλ ₯λ©λλ€.

![jdkμ μμ€γ](https://user-images.githubusercontent.com/39195377/118615740-fd57dc00-b7fb-11eb-819e-e8e8340d24b3.PNG)

λ§μ½ **jdk1.8**μ΄ μ€μΉλμ΄ μμ§ μλ€λ©΄ λ€μ λͺλ Ήμ΄λ‘ μ€μΉν©λλ€.

```bash
# sudo yum install java-1.8.0-openjdk-devel
```
-----

μλ λͺλ Ήμ΄λ₯Ό ν΅ν΄ νκ²½λ³μ μ€μ μ νμΈν©λλ€.

```bash
# echo $JAVA_HOME
```

![νκ²½λ³μ](https://user-images.githubusercontent.com/39195377/118670553-aff66180-b831-11eb-934a-7dc5dd9ffb55.PNG)

λ§μ½ νκ²½λ³μ μ€μ μ΄ λμ΄μμ§ μλ€λ©΄ μλ λͺλ Ήμ΄λ€λ‘ νκ²½λ³μ μ€μ μ μλ£ν©λλ€.

```bash
# readlink -f /usr/bin/javac
```

![νκ²½λ³μ μ€μ ](https://user-images.githubusercontent.com/39195377/118670552-aec53480-b831-11eb-88cb-149ba2d89bf2.PNG)

```bash
# vim /etc/profile
```

vim νΈμ§κΈ°λ₯Ό μ΄μ΄ μλμ κ°μ΄ νκ²½λ³μλ₯Ό μλ ₯&μμ ν©λλ€.

```bash
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.292.b10-1.el7_9.x86_64
```

![νκ²½λ³μ export](https://user-images.githubusercontent.com/39195377/118670557-aff66180-b831-11eb-9f99-055c3902d52b.PNG)

λ§μ§λ§μΌλ‘ μλ λͺλ Ήμ΄λ₯Ό μλ ₯ν΄ λ³κ²½ν νκ²½λ³μλ₯Ό μ μ©ν©λλ€.

```bash
# source /etc/profile
```




#### 2. git μ€μΉ μ¬λΆ νμΈ

```bash
# git --version
```

**git**μ΄ μ μμ μΌλ‘ μ€μΉλμ΄ μλ€λ©΄ μλμ κ°μ΄ μΆλ ₯λ©λλ€.

![κΉμ€μΉμ¬λΆ](https://user-images.githubusercontent.com/39195377/118615744-fdf07280-b7fb-11eb-90b1-f8689069d60f.PNG)

λ§μ½ **git**μ΄ μ€μΉλμ΄ μμ§ μλ€λ©΄ λ€μ λͺλ Ήμ΄λ‘ μ€μΉν©λλ€.

```bash
# sudo yum install git
```



#### 3. git clone

μλ λͺλ Ήμ΄λ₯Ό μ°¨λ‘λλ‘ μλ ₯ν΄ ν΄λλ₯Ό μμ±νκ³ , μμ±ν ν΄λλ‘ μ΄λν©λλ€.

```bash
# mkdir ~/exam_mss && cd ~/exam_mss
```

ν΄λΉ νλ‘μ νΈμ λν **git clone**μ μ§νν©λλ€.

```bash
# git clone https://github.com/ROMANIAPEOPLE/URL-Shortening-Service.git
```

μλ λͺλ Ήμ΄λ₯Ό μ°¨λ‘λλ‘ μλ ₯ν΄ `URL-Shortening-Service` ν΄λκ° μμ±λλμ§ νμΈνκ³  ν΄λΉ ν΄λλ‘ μ΄λν©λλ€.

```bash
ls -al
```

![ls-al](https://user-images.githubusercontent.com/39195377/118615742-fdf07280-b7fb-11eb-8532-7125041c0d5e.PNG)

```bash
cd URL-Shortening-Service
```

#### 4. gradle build & project start

**Permission denied**λ°©μ§λ₯Ό μν΄ μλ λͺλ Ήμ΄λ₯Ό μλ ₯ν©λλ€. (gradlew μ€ν κΆν λΆμ¬)

```bash
chmod +x ./gradlew
```

μλ λͺλ Ήμ΄λ₯Ό μλ ₯ν΄μ buildλ₯Ό μ§νν©λλ€.

```bash
./gradlew build
```

buildκ° μλ£λλ©΄ μλμ κ°μ΄ μΆλ ₯λ©λλ€.

![λΉλμμΉμ€](https://user-images.githubusercontent.com/39195377/118615738-fd57dc00-b7fb-11eb-82e9-b34af9ed42c6.PNG)

**build/libs** ν΄λλ‘ μ΄λν΄ μμ±λ **jar νμΌμ νμΈ**ν©λλ€.

```bash
cd build/libs
```

![λΌμ€νΈ ls-al](https://user-images.githubusercontent.com/39195377/118615733-fc26af00-b7fb-11eb-8d71-bdc34c8201f4.PNG)

λ°±κ·ΈλΌμ΄λλ‘ jar νμΌμ μ€νν©λλ€.

```bash
nohup java -jar task-0.0.1-SNAPSHOT.jar &
```

##### λ€μ μ£Όμλ‘ μ μν΄ URL Shortening Servcieλ₯Ό μ΄μ©ν©λλ€.

```http
http://localhost:8080/
```

---

### μ€λ₯ & λ‘κ·Έ νμΈ

Web server failed to start. Port 8080 was already in use μλ¬ λ°μμ, μ¬μ©μ€μΈ 8080 portμ PIDλ₯Ό μ°Ύμμ μ’λ£ ν λ€μ μ€νν©λλ€.


**μ€νμ€μΈ 8080PORT PID μ°ΎκΈ°**

```bash
lsof -i tcp:8080
```

##### κ°μ  μ’λ£

```bash
kill -15 μμμ μ°Ύμ PID
```

**μ€νμ€μΈ νλ‘μ νΈμ log μ λ³΄λ μλ λͺλ Ήμ΄λ₯Ό ν΅ν΄ νμΈμ΄ κ°λ₯ν©λλ€.**
```bash
# cat nohup.out
```


---

<details>
  
<summary> PROJECT INFO (Click!) </summary>
  
<div markdown="1">

### βοΈTECHNOLOGY 

##### 1. Java 8

##### 2. Spring Data JPA

##### 3. Junit5

##### 4. Gradle

##### 5. h2 database

---

### βοΈ Base62 vs Base64

`Base64` μκ³ λ¦¬μ¦μ 62κ°μ μ«μμ λ¬Έμμ μΆκ°μ μΌλ‘ **+, /** νΉμλ¬Έμκ° ν¬ν¨λ©λλ€. URLμ νΉμλ¬Έμκ° ν¬ν¨λλ€λ©΄ μΆκ°μ μΈ μΈμ½λ© μμμ΄ νμνκΈ° λλ¬Έμ νΉμλ¬Έμλ₯Ό μ¬μ©νμ§ μκ³  μ΄ 62κ°μ μ«μμ λ¬Έμλ‘ μ΄λ£¨μ΄μ§ `Base62` μκ³ λ¦¬μ¦μ μ¬μ©ν©λλ€.

---

### βοΈ URL Shortening Key Max Length

[μ°Έκ³ μλ£](https://domainnamestat.com/statistics/tld/others)μ λ°λ₯΄λ©΄ 2021λ κΈ°μ€ μ  μΈκ³μ λ±λ‘λ λλ©μΈμ κ°μλ μ½ 5μ΅ κ° μ λλ‘, Base62 μκ³ λ¦¬μ¦ μ¬μ© μ μ νν **218340105584895κ°**κΉμ§ 8μλ¦¬λ‘ Shortening Keyλ₯Ό μ μ§ν  μ μμ΅λλ€.

---

### βοΈ EXCPETION HANDLING

##### 1. OutOfUrlMemoryException : μ μ₯λ URLμ κ°μκ° 218340105584895κ°λ₯Ό μ΄κ³Όνλ©΄ λ°μν©λλ€. (status code : 500)

##### 2. UrlNotFoundException : μλ² λ΄ μ€λ₯λ‘ μΈν΄ μ μ₯λ URL κ²μ μ€ν¨μ λ°μν©λλ€. (status code : 500)

##### 3. MethodArgumentNotValidException : μλͺ»λ ννμ URL μλ ₯μ λ°μν©λλ€. (status code : 400)

##### 4. κ·Έ μΈ λͺ¨λ  μμΈλ status code 500κ³Ό "μμμΉ λͺ»ν μλ¬" λ©μμ§λ₯Ό λ°νν©λλ€.

---

### βοΈ URL INTEGRATION RULES

##### 1. λͺ¨λ  νλ‘ν μ½μ ν΅μΌν©λλ€. (https:// -> http://)

##### 2. URL λ§¨ λ§μ§λ§μ λΆμ trailing slashλ μ κ±°ν©λλ€.

##### 3. λ/μλ¬Έμλ₯Ό κ΅¬λΆνμ§ μκ³  λͺ¨λ μλ¬Έμλ‘ μ²λ¦¬ν©λλ€.

---

### βοΈURL INPUT RULES

##### 1. νλ‘ν μ½(http:// λλ https://)μ ν¬ν¨ν ννμ URLμ μλ ₯ν΄μΌ ν©λλ€.

##### 2. μ¬λ°λ₯Έ ννμ URLμ μλ ₯νμ§ μμΌλ©΄ URL λ³νμ΄ μ§νλμ§ μμ΅λλ€.

##### (ex : https://www.xxxxcom , www.xxx.com, wwwxxxcom)

---

### βοΈTEST

##### 1. Service  : Unit Test

##### 2. Controller : Api Controller Unit Test / View Controller Integration Test

##### 3. Dto : Unit Test / Validation Test

##### 4. domain : Unit Test 

---

### βοΈ  View

![view](https://user-images.githubusercontent.com/39195377/118530212-5c2b4000-b77f-11eb-96c8-b801c59185db.PNG)


---

</div>
</details>

