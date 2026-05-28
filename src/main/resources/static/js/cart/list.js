// 💡 재할당이 없는 변수들은 모두 const로 선언합니다.
const all = document.getElementById("all");
const selectdel = document.getElementById("selectdel");
const list = document.getElementById("list");

// 최초 목록 로드
getList();

function getList() {
    fetch("./cartlist")
        .then(r => r.text())
        .then(r => {
            list.innerHTML = r.trim();
            
            // 💡 리스트가 새로 렌더링될 때마다 전체 선택 체크박스 상태를 초기화해 주는 것이 안전합니다.
            all.checked = false;
        })
        .catch(err => console.error("목록 로딩 실패:", err));
}

// 1. 선택 삭제 버튼 클릭 이벤트
selectdel.addEventListener("click", () => {
    // 💡 이벤트 위임 구조에 맞춰 클릭하는 시점에 현재 화면에 존재하는 체크박스들을 새로 탐색해야 합니다.
    const ch = document.querySelectorAll(".ch");
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
        const resultCount = Number(r.trim()); // 💡 명확한 숫자 형변환
        if (resultCount > 0) {
            getList(); // 삭제 성공 시 목록 갱신
        } else {
            alert("삭제에 실패했습니다.");
        }
    });
});

// 2. 개별 삭제 버튼 클릭 이벤트 (부모인 list에 이벤트 위임 적용)
list.addEventListener("click", (e) => {
    // 클릭된 요소가 개별 삭제 버튼(.del)인지 확인합니다.
    if (e.target.classList.contains("del")) {
        if (!confirm("이 상품을 삭제하시겠습니까?")) return;

        // 버튼 이전 요소에서 data-pn 값을 가져옵니다.
        const pn = e.target.previousElementSibling.getAttribute("data-pn");
        const p = new URLSearchParams();
        p.append("productNum", pn);

        fetch("./delete", {
            method: "POST",
            body: p
        })
        .then(r => r.text())
        .then(r => {
            const resultCount = Number(r.trim());
            if (resultCount > 0) {
                getList(); // 삭제 성공 시 목록 갱신
            } else {
                alert('삭제 실패');
            }
        });
    }
});

// 3. 전체 선택(all) 체크박스 클릭 이벤트
all.addEventListener("click", () => {
    const ch = document.querySelectorAll(".ch"); // 💡 클릭 시점의 체크박스 탐색
    ch.forEach((c) => {
        c.checked = all.checked;
    });
});

// 4. 개별 체크박스 클릭 시 전체 선택(all) 체크박스 상태 연동 (list에 이벤트 위임 적용)
list.addEventListener("click", (e) => {
    // 클릭된 요소가 개별 체크박스(.ch)인지 확인합니다.
    if (e.target.classList.contains("ch")) {
        const ch = document.querySelectorAll(".ch");
        let isAllChecked = true;

        ch.forEach((element) => {
            if (!element.checked) {
                isAllChecked = false;
            }
        });

        all.checked = isAllChecked;
    }
});