##  **URL Shortening Service**

### 💻 BUILD PROJECT BY LINUX



---

### ✍️TECHNOLOGY 

##### 1. Java 8

##### 2. Spring Data JPA

##### 3. Junit5

##### 4. Gradle

##### 5. h2 database

---

### ✍️ Base62 vs Base64

`Base64` 알고리즘 62개의 숫자와 문자에 추가적으로 **+, /** 특수문자가 포함됩니다. URL에 특수문자가 포함된다면 추가적인 인코딩 작업이 필요하기 때문에 특수문자를 사용하지 않고 총 62개의 숫자와 문자로 이루어진 `Base62` 알고리즘을 사용합니다.

---

### ✍️ URL Shortening Key Max Length

[참고자료](https://domainnamestat.com/statistics/tld/others)에 따르면 2021년 기준 전 세계에 등록된 도메인의 개수는 약 5억 개 정도로, Base62 알고리즘 사용 시 **218340105584895개**까지 8자리로 Shortening Key를 유지할 수 있습니다. 8자리를 초과한 경우 `OutOfUrlMemoryException`이 발생합니다. 

---

### ✍️ URL INTEGRATION RULES

##### 1. 모든 프로토콜은 통일합니다. (https:// -> http://)

##### 2. URL 맨 마지막에 붙은 trailing slash는 제거합니다.

##### 3. 대/소문자를 구분하지 않고 모두 소문자로 처리합니다.

---

### ✍️URL INPUT RULES

##### 1. http:// 또는 https:// 를 포함한 형태의 URL을 입력합니다.

##### 2. 올바른 형태의 URL을 입력하지 않으면 `MethodArgumentNotValidException`이 발생합니다.

##### (ex : https://www.xxxxcom , www.xxx.com)

---

### ✍️TEST

##### 1. Service  : Unit Test / Integration Test

##### 2. Controller : Api Controller Unit Test / View Controller Integration Test

##### 3. Dto : Unit Test / Validation Test

##### 4. domain : Unit Test 

---

### ✍️  View

![view](https://user-images.githubusercontent.com/39195377/118530212-5c2b4000-b77f-11eb-96c8-b801c59185db.PNG)


---


