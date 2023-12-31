## 1. 프로젝트 이해

우테코 식당에서 열리는 12월 이벤트 진행을 위해서 요구사항을 만족하는 ‘12월 이벤트 플래너’를 제작해야 한다.

12월 이벤트 계획은 아래와 같다.

### **12월 이벤트 계획**

- 중복된 할인과 증정을 허용한다.

| 종류                            | 기간               | 내용                                                                                                                                                                        |
|-------------------------------|------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 크리스마스 디데이 할인                  | 12월 1일 - 12월 25일 | • 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가</br>◦ 12월 1일 → 1,000원 할인</br>◦ 12월 2일 → 1,100원 할인 (+100)</br>◦ …</br>◦ 12월 25일 → 3,400원 할인</br>• 총 주문 금액에서 해당 금액만큼 할인한다. |
| 평일 할인(월, 화, 수, 목, 일)          | 12월 1일 - 12월 31일 | 디저트 메뉴를 메뉴 1개당 2,023원 할인                                                                                                                                                  |
| 주말 할인(금, 토)                   | 12월 1일 - 12월 31일 | 메인 메뉴를 메뉴 1개당 2,023원 할인                                                                                                                                                   |
| 특별 할인(3, 10, 17, 24, 25, 31일) | 12월 1일 - 12월 31일 | 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인                                                                                                                                         |
| 증정 이벤트                        | 12월 1일 - 12월 31일 | 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정                                                                                                                                       |

### 12월 이벤트 배지 부여

<aside>
✔️ 총혜택 금액에 따른 다른 이벤트 배지를 부여한다.

- 5천 원 이상: 별
- 1만 원 이상: 트리
- 2만 원 이상: 산타

</aside>

### 고객에게 안내할 이벤트 주의 사항

<aside>
✔️ 총주문 금액이 10,000원 이상일 때만 이벤트가 적용된다.

- 음료만 주문하는 것은 불가능하다.
- 메뉴는 한 번에 최대 20개까지만 주문할 수 있다.

</aside>

### ‘12월 이벤트 플래너’ 개발 요청 사항

- 고객들이 식당에 방문할 날짜와 메뉴를 입력하면, 이벤트 플래너는 적절한 내용을 화면에 보여준다.
- 식당 예상 방문 날짜를 입력 받는다.
    - 방문할 날짜는 1 이상 31 이하의 숫자로만 입력 → 아닌 경우 에러 메시지 출력
    - 모든 에러 메시지는 “[ERROR]”로 시작한다.
- 주문할 메뉴의 이름과 개수를 입력 받는다.
    - 메뉴판에 없는 메뉴를 입력하는 경우 → 에러 메시지 출력
    - 메뉴의 개수는 1 이상의 숫자만 입력하도록 한다. → 이외의 값은 에러 메시지 출력
    - 입력 형식: 메뉴명-개수 (예시: 해산물파스타-1) → 예시와 다른 경우 에러 메시지 출력
    - 중복 메뉴를 입력한 경우 (예시: 해산물파스타-1,해산물파스타-1) → 에러 메시지 출력
- 주문 메뉴의 출력 순서는 자유롭게 출력한다.
- 총혜택 금액에 따라 이벤트 배지의 이름을 다르게 보여준다.
- 총혜택 금액 = 총 할인 금액 + 증명 메뉴의 가격
- 할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액
- 증정 메뉴
    - 증정 이벤트에 해당하지 않는 경우, 증정 메뉴 “없음”으로 보여준다.
- 혜택 내역
    - 고객에게 적용된 이벤트 내역만 보여준다.
    - 적용된 이벤트가 하나도 없다면 혜택 내역 “없음”으로 출력한다.
    - 혜택 내역에 여러 개의 이벤트가 적용된 경우, 출력 순서는 자유롭게 출력한다.
- 이벤트 배지
    - 이벤트 배지가 부여되지 않는 경우, “없음”으로 출력한다.
- 적용된 이벤트가 하나도 없는 경우는 모두 “없음”으로 출력한다.

### 메뉴

```
<애피타이저>
양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)

<메인>
티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)

<디저트>
초코케이크(15,000), 아이스크림(5,000)

<음료>
제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
```

---

### 2. MVC 패턴으로 구조 잡기

### Model
1. 할인 관련
    - **DiscountEventManager :** 할인 이벤트 목록을 관리하며, 직접 할인 적용한다.
    - **DiscountEvent :** 할인 이벤트 목록 인터페이스로, 할인 정보가 담겨 있다.
        - **ChristmasDiscountEvent :** 크리스마스 할인 이벤트 서비스 로직
        - **WeekdayDiscountEvent :** 평일 할인 이벤트 서비스 로직
        - **WeekendDiscountEvent :** 주말 할인 이벤트 서비스 로직
        - **SpecialDiscountEvent :** 특별 할인 이벤트 서비스 로직
        - **GiftEvent**: 증정 이벤트 서비스 로직
    - **Benefit :** 총혜택 금액을 통해 이벤트 배지를 부여한다.
        
        
2. 주문 관련
    - **OrderDate :** 방문 날짜를 관리하며, 유효성 검증을 시행한다.
    - **OrderInformation :** 주문 정보를 관리하며, 유효성 검증을 시행한다.
    - **OrderItem** : 주문 메뉴를 관리하며, 유효성 검증을 시행한다.
    - **Order :** 주문 정보를 생성하며, 각 주문 조건에 해당하는 할인을 적용한다.

### View

- **InputView :** 사용자 입력을 받는다.
- **OutputView:** 사용자에게 결과를 출력한다.

### Controller

- **MainController :** 주문과 할인 이벤트에 대한 전체 실행 흐름을 관리한다.

### Utils

- **InputValueValidator :** 사용자 입력값의 유효성 검증을 수행한다.

---

### 3. 기능 구현 목록

### 입력 기능

- [x]  식당 방문 날짜 입력 받기
    - [x] [서비스 예외] 1 ~ 31 범위의 숫자가 아닌 경우 예외를 발생시킨다.
    - [x]  [서비스 예외] 1개의 숫자가 아닌, 여러개의 숫자일 경우 예외를 발생시킨다.
    - [x]  "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." 라는 에러 메시지를 출력 한다.
    - [x]  적절한 에러 메시지 출력 후 다시 입력 받는다.
- [x]  주문할 메뉴와 개수 입력 받기 (형식: 해산물파스타-2)
    - [x]  [서비스 예외] 메뉴 형식이 예시와 다른 경우 예외를 발생시킨다.
    - [x]  [서비스 예외] 메뉴판에 없는 메뉴를 입력하는 경우 예외를 발생시킨다.
    - [x]  [서비스 예외] 음료 메뉴만 입력하는 경우 예외를 발생시킨다.
        - [x]  “[ERROR] 유효하지 않은 주문입니다. 음료 메뉴만 주문할 수 없습니다. 다시 입력해주세요.” 라는 에러 메시지를 출력한다.
- [x]  [서비스 예외] 메뉴 수량이 1 이상의 숫자가 아닌 경우 예외를 발생시킨다.
- [x]  [서비스 예외] 메뉴 수량이 20개를 초과한 경우 예외를 발생시킨다.
    - [x]  “[ERROR] 유효하지 않은 주문입니다. 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해주세요.” 라는 에러 메시지를 출력한다.
- [x]  "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 출력한다.
- [x]  적절한 에러 메시지 출력 후 다시 입력 받는다.

### 입력값 검증 기능 (공통)

- [x]  [단순 예외] 입력값이 없을 경우(NULL) 예외를 발생시킨다.
- [x]  [단순 예외] 숫자가 아닌 경우 예외를 발생시킨다.
- [x]  [단순 예외] 공백이 포함될 경우 예외를 발생시킨다.

### 주문 기능

- [x]  사용자로부터 입력 받은 방문 날짜, 메뉴 정보를 통해 주문을 생성한다.
    - [x]  총주문 금액이 10,000원 이상인지 검사한다.
- [x]  주문 객체에 할인 이벤트를 적용한다.

### 할인 이벤트 기능

- [x]  방문 날짜에 따라 적용 가능한 할인 이벤트를 확인한다.
    - [x]  크리스마스 디데이 할인 이벤트의 경우 1일 ~ 25일에 해당되는지 검사한다.
    - [x]  그 외 (평일, 주말, 특별, 증정 이벤트) 는 모두 1일 ~ 31일에 해당되는지 검사한다.
- [x]  총주문 금액 검사 결과에 따라 적용 가능한 할인 이벤트를 확인한다.
- [x]  각 조건에 맞는 할인 이벤트를 적용한다.
- [x]  각 이벤트에 따른 할인 금액을 계산한다.
    - [x] 크리스마스 디데이 할인 이벤트의 할인 금액을 계산한다.
        - [x] 1,000원으로 시작하여 크리스마스가 다가올수록 할인 금액이 100원씩 증가
    - [x] 평일 할인 이벤트의 할인 금액을 계산한다.
        - [x] 디저트 메뉴를 메뉴 1개당 2,023원 할인
    - [x] 주말 할인 이벤트의 할인 금액을 계산한다.
        - [x] 메인 메뉴를 메뉴 1개당 2,023원 할인
    - [x] 특별 할인 이벤트의 할인 금액을 계산한다.
        - [x] 이벤트 달력에 별이 있는지 확인한다.
        - [x] 총주문 금액에서 1,000원을 할인한다.
    - [x] 증정 이벤트
        - [x] 총주문 금액이 12만 원 이상인지 확인한다.
        - [x] 샴페인 1개를 증정한다.
- [x]  적용된 할인 이벤트 내역 정보를 저장한다.
- [x]  할인 전 총 주문 금액을 계산한다.
- [x]  총혜택 금액을 계산한다.
    - [x]  총혜택 금액 = 총 할인 금액 + 증정 메뉴 가격
- [x]  할인 후 예상 결제 금액을 계산한다.
    - [x]  할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액

### 이벤트 배지 기능

- [x]  총혜택 금액이 5천 원 이상인 경우 별 배지를 부여한다.
- [x]  총혜택 금액이 1만 원 이상인 경우 트리 배지를 부여한다.
- [x]  총혜택 금액이 2만 원 이상인 경우 산타 배지를 부여한다.

### 출력 기능

- [x]  프로그램 시작 문구를 출력한다.
- [x]  방문 날짜에 받는 이벤트 혜택 미리 보기 문구를 출력한다.
- [x]  주문 메뉴 목록을 출력한다. (순서는 자유)
- [x]  할인 전 총 주문 금액을 출력한다.
- [x]  증정 메뉴를 출력한다.
    - [x]  증정 이벤트에 해당하지 않는 경우, 증정메뉴 “없음”으로 출력한다.
- [x]  혜택 내역을 출력한다. (순서는 자유)
    - [x]  고객에게 적용된 이벤트만 출력한다.
    - [x]  적용된 이벤트가 하나도 없다면 혜택 내역 “없음”으로 출력한다.
- [x]  총혜택 내역을 출력한다.
- [x]  할인 후 예상 결제 금액을 출력한다.
- [x]  12월 이벤트 배지를 출력한다.
    - [x]  적용된 이벤트 배지가 없는 경우, “없음”으로 출력한다.

---

### 프로그래밍 요구사항 + 3주 차 공통 피드백

- [x]  indent depth는 2까지만 허용한다.
- [x]  3항 연산자를 쓰지 않는다.
- [x]  메서드의 길이가 15라인을 넘어가지 않도록 구현한다.
- [x]  메서드는 한 가지 일만 하도록 최대한 작게 만들어라.
- [x]  JUnit 5와 AssertJ를 이용하여 본인이 정리한 기능 목록이 정상 동작함을 테스트 코드로 확인한다.
- [x]  else 예약어를 쓰지 않는다. (switch/case도 허용하지 않음)
- [x]  도메인 로직에 단위 테스트를 구현해야 한다.
    - [x]  핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리해 구현한다.
- [x]  연관성이 있는 상수는 static final 대신 enum을 활용한다.
- [x]  객체는 객체스럽게 사용한다.
    - [x]  객체에서 데이터를 꺼내지(get) 말고 메시지를 던져 일하도록 구조를 짠다.
- [x]  필드의 수를 줄이기 위해 노력한다.
- [x]  성공하는 케이스 뿐만 아니라 예외에 대한 케이스로 테스트한다.
- [x]  테스트를 통과하기 위해 구현 코드를 변경하거나, 테스트에서만 사용되는 로직을 만들지 않는다.
- [x]  private 함수를 테스트 하고 싶다면 객체 분리를 고려해라.

### 추가된 요구사항

- 아래 있는`InputView`,`OutputView`클래스를 참고하여 입출력 클래스를 구현한다.
    - 입력과 출력을 담당하는 클래스를 별도로 구현한다.
    - 해당 클래스의 패키지, 클래스명, 메서드의 반환 타입과 시그니처는 자유롭게 구현할 수 있다.

```java
public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        // ...
    }
    // ...
}
```

```java
public class OutputView {
    public void printMenu() {
        System.out.println("<주문 메뉴>");
        // ...
    }
    // ...
}
```
