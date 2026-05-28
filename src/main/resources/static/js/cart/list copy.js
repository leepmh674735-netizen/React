// 💡 한 번 선언 후 변하지 않는 상수는 const로 관리합니다.
const all = document.getElementById("all");
const selectdel = document.getElementById("selectdel");
const list = document.getElementById("list");

// 최초 장바구니 목록 조회
getList();

function getList() {
    fetch("./cartlist")
        .then(r => r.text())
        .then(r => {
            list.innerHTML = r.trim();
            
            // 리스트가 새롭게 비동기 갱신될 때 전체 선택 체크박스도 연동하여 해제해 줍니다.
            all.checked = false;
        })
        .catch(err => console.error("목록 조회 실패:", err));
}

// [1] 선택 삭제 이벤트 처리
selectdel.addEventListener("click", () => {
    const ch = document.querySelectorAll(".ch"); // 클릭 시점에 렌더링된 체크박스 탐색
    const pn = new URLSearchParams();
    let hasChecked = false;

    ch.forEach((c) => {
        if (c.checked) {
            pn.append("productNum", c.getAttribute("data-pn"));
            hasChecked = true;
        }
    });

    if (!hasChecked) {
        alert("삭제할 상품을 선택해 주세요.");
        return;
    }

    if (!confirm("선택한 상품을 장바구니에서 삭제하시겠습니까?")) return;

    fetch("./delete", {
        method: "POST",
        body: pn
    })
    .then(r => r.text())
    .then(r => {
        // 💡 문자열을 명확하게 숫자로 바꾸어 크기를 비교합니다.
        const resultCount = Number(r.trim());
        if (resultCount > 0) {
            getList(); // 삭제 성공 시 리스트 동적 갱신
        } else {
            alert("선택 삭제 처리에 실패했습니다.");
        }
    });
});

// [2] 이벤트 위임을 활용한 하위 요소(개별삭제, 개별체크박스) 통합 관리
list.addEventListener("click", (e) => {
    
    // ① 개별 삭제 버튼(.del)을 클릭한 경우
    if (e.target.classList.contains("del")) {
        if (!confirm("이 상품을 삭제하시겠습니까?")) return;

        const pn = e.target.previousElementSibling.getAttribute("data-pn");
        const p = new URLSearchParams();
        p.append("productNum", pn);

        fetch("./delete", {
            method: "POST",
            body: p
        })
        .then(r => r.text())
        .then(r => {
            // 💡 문자열을 숫자로 형변환하여 처리합니다.
            const resultCount = Number(r.trim());
            if (resultCount > 0) {
                getList(); // 삭제 성공 시 리스트 동적 갱신
            } else {
                alert('삭제 실패');
            }
        }); 
    }

    // ② 개별 체크박스(.ch)를 클릭한 경우
    if (e.target.classList.contains("ch")) {
        const chs = document.getElementsByClassName("ch");
        let isAllChecked = true;
        
        for (let c of chs) {
            if (!c.checked) {
                isAllChecked = false;
                break; // 하나라도 체크가 풀려있다면 더 돌지 않고 중단
            }
        }
        all.checked = isAllChecked; // 전체 선택 상태 연동
    }
});
    
// [3] 전체 선택(all) 체크박스 제어
all.addEventListener("click", () => {
    const ch = document.querySelectorAll(".ch");
    ch.forEach((c) => {
        c.checked = all.checked;
    });
});