console.log("member join");

// 회원가입 입력 검증용 배열 (첫 번째 요소를 아이디 검증용으로 사용)
const checks = [false, false, false, false, false, false, false];

const username = document.getElementById("username");

username.addEventListener("blur", function() {
    console.log("비동기 통신 시작");
    
    // 사용자가 입력한 아이디 값 가져오기
    const inputValue = username.value.trim();
    
    // 빈 값일 경우 조건 처리
    if (inputValue === "") {
        console.log("아이디를 입력해 주세요.");
        checks[0] = false;
        return;
    }

    fetch(`./idCheck?username=${inputValue}`)
        .then(res => res.text())
        .then(res => {
            // 💡 서버 응답 결과를 명확하게 숫자로 형변환하여 비교합니다.
            const result = Number(res.trim()); [cite: 96, 107]
            
            // 💡 모든 사후 처리 및 콘솔 출력은 이 .then() 블록 안에서 이루어져야 합니다!
            if (result === 1) {
                console.log("중복 아님 - 사용 가능한 아이디입니다.");
                checks[0] = true;   // 아이디 검증 통과
            } else {
                console.log("중복입니다 - 다른 아이디를 입력해 주세요.");
                checks[0] = false;  // 아이디 검증 실패
            }
            
            // 데이터 수신 완료 시점에서 최종 상태 확인
            console.log("서버 응답 결과 숫자값:", result);
            console.log("현재 검증 상태 배열(checks):", checks);
            console.log("비동기 처리 완전 종료(finish)");
        })
        .catch(err => {
            console.error("통신 에러 발생:", err);
            checks[0] = false;
        });

    // ❌ 여기에 console.log(num)을 작성하면 서버 응답이 오기 전에 실행되므로 
    // 무조건 기존 빈 값만 출력됩니다. 따라서 삭제하거나 내부로 이동해야 합니다.
});