### 💻 BUILD PROJECT BY LINUX (서버 환경 : centos-7.3-64)

#### 1. JDK 1.8 설치 여부 확인

```bash
# java -version
```

**jdk 1.8**이 설치되어 있다면 아래와 같이 출력됩니다.

![jdk정상설ㅊ](https://user-images.githubusercontent.com/39195377/118615740-fd57dc00-b7fb-11eb-819e-e8e8340d24b3.PNG)

만약 **jdk1.8**이 설치되어 있지 않다면 다음 명령어로 설치합니다.

```bash
# sudo yum install java-1.8.0-openjdk-devel
```



#### 2. git 설치 여부 확인

```bash
# git --version
```

**git**이 정상적으로 설치되어 있다면 아래와 같이 출력됩니다.

![깃설치여부](https://user-images.githubusercontent.com/39195377/118615744-fdf07280-b7fb-11eb-90b1-f8689069d60f.PNG)

만약 **git**이 설치되어 있지 않다면 다음 명령어로 설치합니다.

```bash
# sudo yum install git
```



#### 3. git clone

아래 명령어를 차례대로 입력해 폴더를 생성하고, 생성한 폴더로 이동합니다.

```bash
# mkdir exam_mss && cd ~/exam_mss
```

해당 프로젝트에 대한 **git clone**을 진행합니다.

```bash
# git clone https://github.com/ROMANIAPEOPLE/URL-Shortening-Service.git
```

아래 명령어를 차례대로 입력해 `URL-Shortening-Service` 폴더가 생성됐는지 확인하고 해당 폴더로 이동합니다.

```bash
ls -al
```

![ls-al](https://user-images.githubusercontent.com/39195377/118615742-fdf07280-b7fb-11eb-8532-7125041c0d5e.PNG)

```bash
cd URL-Shortening-Service
```

#### 4. gradle build & project start

**Permission denied**방지를 위해 아래 명령어를 입력합니다. (gradlew 실행 권한 부여)

```bash
chmod +x ./gradlew
```

아래 명령어를 입력해서 build를 진행합니다.

```bash
./gradlew build
```

build가 완료되면 아래와 같이 출력됩니다.

![빌드석섹스](https://user-images.githubusercontent.com/39195377/118615738-fd57dc00-b7fb-11eb-82e9-b34af9ed42c6.PNG)

**build/libs** 폴더로 이동해 생성된 **jar 파일을 실행**합니다.

```bash
cd build/libs
```

![라스트 ls-al](https://user-images.githubusercontent.com/39195377/118615733-fc26af00-b7fb-11eb-8d71-bdc34c8201f4.PNG)

```bash
java -jar task-0.0.1-SNAPSHOT.jar
```

##### 다음 주소로 접속해 URL Shortening Servcie를 이용합니다.

```http
http://localhost:8080/
```

---

### etc.

만약 정상적으로 Springboot project가 실행되었는데 http://localhost:8080/ 접속이 안된다면 다음 명령어를 통해 실행중인 tomcat의 PID를 찾아 강제 종료 후 다시 연결해야 합니다.

**실행중인 tomcat 찾기**

```bash
ps -ef | grep tomcat
```

##### tomcat 강제 종료

```bash
kill -15 위에서 찾은 tomcat PID
```

---
# PROJECT INFO

### ✍️TECHNOLOGY 

##### 1. Java 8

##### 2. Spring Data JPA

##### 3. Junit5

##### 4. Gradle

##### 5. h2 database

---

### ✍️ Base62 vs Base64

`Base64` 알고리즘은 62개의 숫자와 문자에 추가적으로 **+, /** 특수문자가 포함됩니다. URL에 특수문자가 포함된다면 추가적인 인코딩 작업이 필요하기 때문에 특수문자를 사용하지 않고 총 62개의 숫자와 문자로 이루어진 `Base62` 알고리즘을 사용합니다.

---

### ✍️ URL Shortening Key Max Length

[참고자료](https://domainnamestat.com/statistics/tld/others)에 따르면 2021년 기준 전 세계에 등록된 도메인의 개수는 약 5억 개 정도로, Base62 알고리즘 사용 시 정확히 **218340105584895개**까지 8자리로 Shortening Key를 유지할 수 있습니다.

---

### ✍️ EXCPETION HANDLING

##### 1. OutOfUrlMeMemoryException : 저장된 URL의 개수가 218340105584895개를 초과하면 발생합니다. (status code : 500)

##### 2. UrlNotFoundException : 서버 내 오류로 인해 저장된 URL 검색 실패시 발생합니다. (status code : 500)

##### 3. MethodArgumentNotValidException : 잘못된 형태의 URL 입력시 발생합니다. (status code : 400)

---

### ✍️ URL INTEGRATION RULES

##### 1. 모든 프로토콜은 통일합니다. (https:// -> http://)

##### 2. URL 맨 마지막에 붙은 trailing slash는 제거합니다.

##### 3. 대/소문자를 구분하지 않고 모두 소문자로 처리합니다.

---

### ✍️URL INPUT RULES

##### 1. 프로토콜(http:// 또는 https://)을 포함한 형태의 URL을 입력해야 합니다.

##### 2. 올바른 형태의 URL을 입력하지 않으면 URL 변환이 진행되지 않습니다.

##### (ex : https://www.xxxxcom , www.xxx.com, wwwxxxcom)

---

### ✍️TEST

##### 1. Service  : Unit Test

##### 2. Controller : Api Controller Unit Test / View Controller Integration Test

##### 3. Dto : Unit Test / Validation Test

##### 4. domain : Unit Test 

---

### ✍️  View

![view](https://user-images.githubusercontent.com/39195377/118530212-5c2b4000-b77f-11eb-96c8-b801c59185db.PNG)


---


