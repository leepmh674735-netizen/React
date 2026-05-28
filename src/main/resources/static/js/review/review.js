const review_list = document.getElementById("review_list");
const review_contents = document.getElementById("review_contents");
const review_add = document.getElementById("review_add");
const update_btn = document.getElementById("update_btn");
const contents_update = document.getElementById("contents_update");
const review_update_num = document.getElementById("review_update_num");

const product_num = review_list.getAttribute("data-product-num");

// 최초 리뷰 리스트 출력
getList(product_num);

// [1] 리뷰 수정 처리 (모달 내부 수정 완료 버튼 클릭)
update_btn.addEventListener("click", () => {
    // 💡 재할당이 없는 객체이므로 let 대신 const 사용
    const p = new URLSearchParams();
    p.append("reviewContents", contents_update.value);
    p.append("reviewNum", review_update_num.value);

    fetch("/review/update", {
        method: "POST",
        body: p
    })
    .then(r => r.text())
    .then(r => {
        // 💡 문자열을 명확하게 숫자로 바꾸어 결과 값을 검증합니다.
        const resultCount = Number(r.trim());
        if (resultCount > 0) {
            alert("수정이 완료되었습니다.");
            getList(product_num); // 리스트 동적 갱신
            contents_update.value = ""; // 💡 innerText를 value로 수정하여 텍스트 창 비우기
            
            // 만약 부트스트랩 모달을 쓰신다면 아래 주석을 풀고 닫기 처리를 할 수 있습니다.
            // document.getElementById("close").click();
        } else {
            alert("수정에 실패했습니다.");
        }
    })
    .catch(err => console.error("수정 통신 에러:", err));
});

// [2] 이벤트 위임을 활용한 하위 요소(리뷰 삭제, 리뷰 수정 준비) 제어
review_list.addEventListener("click", (e) => {
    
    // ① 삭제 버튼(.review_delete)을 클릭한 경우
    if (e.target.classList.contains("review_delete")) {
        if (!confirm("이 리뷰를 정말로 삭제하시겠습니까?")) return;

        const n = e.target.parentElement.getAttribute("data-review-num");
        const p = new URLSearchParams();
        p.append("reviewNum", n);

        fetch("/review/delete", {
            method: "POST",
            body: p
        })
        .then(r => r.text())
        .then(r => {
            const resultCount = Number(r.trim());
            if (resultCount > 0) {
                alert('리뷰가 삭제되었습니다.');
                getList(product_num); // 리스트 동적 갱신
            } else {
                alert('리뷰 삭제에 실패했습니다.');
            }
        })
        .catch(err => console.error("삭제 통신 에러:", err));
    }

    // ② 수정 버튼(.review_update)을 클릭한 경우 (모달창에 기존 데이터 채우기)
    if (e.target.classList.contains("review_update")) {
        // 기존 작성되었던 내용 텍스트 추출
        const originContents = e.target.parentElement.parentElement.firstElementChild.innerText;
        
        // 💡 innerText 대신 value 속성에 값을 주입해야 입력창 폼에 정상 노출됩니다.
        contents_update.value = originContents;
        
        // 히든 폼이나 변수에 매핑할 리뷰 고유 번호 추출 및 바인딩
        const reviewNum = e.target.parentElement.getAttribute("data-review-num");
        review_update_num.value = reviewNum;
    }
});


// [3] 리뷰 신규 등록 처리
review_add.addEventListener("click", () => {
    if (review_contents.value.trim() === "") {
        alert("리뷰 내용을 입력해 주세요.");
        return;
    }

    const p = new FormData();
    p.append("reviewContents", review_contents.value);
    p.append("productNum", product_num);
    p.append("reviewStar", 4); // 평점 기본값 세팅

    fetch("../review/create", {
        method: "POST",
        body: p
    })
    .then(r => r.text())
    .then(r => {
        const resultCount = Number(r.trim());
        if (resultCount > 0) {
            alert("리뷰가 정상적으로 등록되었습니다.");
            getList(product_num); // 리스트 동적 갱신
        } else {
            alert("리뷰 등록에 실패했습니다.");
        }

        // 💡 입력 폼 엘리먼트 초기화는 항상 value로 수행합니다.
        review_contents.value = "";
    })
    .catch(err => console.error("등록 통신 에러:", err));
});

// [4] 리뷰 목록 조회 및 비동기 렌더링
function getList(num) {
    fetch(`/review/list?productNum=${num}`)
        .then(r => r.text())
        .then(r => {
            review_list.innerHTML = r.trim();
        })
        .catch(err => console.error("리뷰 목록 로딩 실패:", err));
}