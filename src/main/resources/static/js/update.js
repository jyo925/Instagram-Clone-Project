// (1) 회원정보 수정
function update(userId) {

    let data = $("#profileUpdate").serialize();
    // console.log(data); serialize()를 사용하면 form 태그 안의 모든 필드 정보를 볼 수 있다.

    $.ajax({
        type: "put",
        url: `/api/user/${userId}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res => {
        console.log("update 성공");

    }).fail(error => {
        console.log("update 실패");

    });

}