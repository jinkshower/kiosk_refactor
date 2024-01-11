Java로 구현한 Shake Shack Burger 키오스크   

## 기능 명세

 - [x] 메뉴 정보를 저장한다
 - [x] 메인 메뉴 화면을 출력한다
 - [x] 사용자는 메뉴를 선택할 수 있다
   - [x] 메뉴 선택시 상품 메뉴판을 출력한다
   - [x] 상품 선택시 옵션을 선택할 수 있다
   - [x] 옵션 선택시 장바구니에 상품을 추가할 수 있다
 - [x] 사용자는 장바구니 목록을 볼 수 있다
   - [x] 사용자는 장바구니에 있는 상품들을 주문할 수 있다
 - [x] 주문 완료 시 3초 이후에 메인 메뉴로 돌아간다
 - [x] 사용자는 장바구니를 초기화 할 수 있다
 - [x] 사용자는 총 판매금액을 확인할 수 있다
 - [x] 사용자는 판매된 모든 상품 목록을 볼 수 있다
 - [x] 숫자 이외의 입력은 허용 하지 않는다
 - [x] 화면에 출력 되지 않은 숫자 이외의 입력은 허용 하지 않는다


## refactoring list

 - [x] ScreenData 클래스의 메인화면 String 만들기 기능 객체로 옮기기   
 - [x] OrderDto Optional 적용   
 - [x] Menu class builder pattern 적용   
 - [x] static한 history 개선   
 - [x] 요구사항에 맞게 출력값 조정   

## feedback list

- [ ] Maincommand 분기문 if-else if, history 변수명 복수로
- [ ] OrderDto 변경 후 order 생성자에 주입
- [ ] ApplicationStatus, Commands 객체화
