const create = document.getElementById("create");

create.addEventListener("click", () => {
    // 💡 재할당이 없는 변수들은 let 대신 const를 사용하는 것이 안전합니다.
    const pn = create.getAttribute("data-pn");
    
    const p = new URLSearchParams();
    p.append("productNum", pn);

    fetch("../cart/create", {
        method: "POST",
        body: p
    })
    .then(r => r.text())
    .then(r => {
        // 공백 제거 후 숫자로 명확하게 변환합니다.
        const resultCount = Number(r.trim());
        
        // 💡 0보다 크다면 성공 (보통 성공 시 1 저장되므로)
        if (resultCount > 0) {
            const flag = confirm("장바구니로 이동하시겠습니까?");
            if (flag) {
                // 실제 이동 로직이 들어갈 자리입니다. (예: location.href = "../cart/list";)
                alert("장바구니 페이지로 이동합니다.");
            }
        } else {
            alert('장바구니 등록에 실패했습니다.');
        }
    })
    .catch(err => {
        // 네트워크 에러 등을 대비한 예외 처리 추가
        console.error("통신 에러 발생:", err);
        alert("오류가 발생했습니다. 다시 시도해 주세요.");
    });
});