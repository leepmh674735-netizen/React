const add = document.getElementById("add");
const result = document.getElementById("result");
const create = document.getElementById("create");

// 💡 1. 속성값이 없을 때(null)를 대비하고, 가져온 문자열을 명확히 '숫자'로 변환합니다.
let count = result.getAttribute("data-file-size");
if (count === null || count === undefined) {
    count = 0;
} else {
    count = Number(count); // "3" -> 3 변환
}

// 파일 추가 버튼 클릭 이벤트
add.addEventListener("click", function(){
    // 최대 5개까지만 업로드 가능하도록 제한
    if (count > 4) {
        alert('최대 5개만 가능합니다.');
        return;
    }
    
    // 💡 let 대신 재할당이 필요 없는 const를 사용하여 안전하게 엘리먼트 생성
    const d = document.createElement("div");
    const i = document.createElement("input");
    i.type = "file";
    i.name = "attach";
    i.classList.add("form-control");

    const b = document.createElement("button");
    b.type = "button";
    b.innerText = "X";
    b.classList.add("del");

    // div 상자 안에 input과 버튼을 넣고 result 영역 최상단에 추가
    d.append(i);
    d.append(b);
    result.prepend(d);

    count++; // 추가되었으므로 카운트 증가
});

// 파일 삭제 버튼 클릭 이벤트 (이벤트 위임 활용)
result.addEventListener("click", function(e){
    if (e.target.classList.contains("del")) {
        e.target.parentElement.remove(); // X 버튼이 속한 div 줄 전체를 삭제
        count--; // 삭제되었으므로 카운트 감소
    }
});