/**
 1. 유저 프로파일 페이지
 (1) 유저 프로파일 페이지 구독하기, 구독취소
 (2) 구독자 정보 모달 보기
 (3) 구독자 정보 모달에서 구독하기, 구독취소
 (4) 유저 프로필 사진 변경
 (5) 사용자 정보 메뉴 열기 닫기
 (6) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
 (7) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달
 (8) 구독자 정보 모달 닫기
 */

// (1) 유저 프로파일 페이지 구독하기, 구독취소
// (3) 구독자 정보 "모달"에서 구독하기, 구독취소
// this를 전달하는데 이벤트 정보임
// ajax
function toggleSubscribe(toUserid, obj) {
    if ($(obj).text() === "구독취소") {

        $.ajax({
            type: "delete",
            url: "/api/subscribe/" + toUserid,
            dataType: "json"
        }).done(res => {
            $(obj).text("구독하기");
            $(obj).toggleClass("blue");
        }).fail(error => {
            console.log("구독취소 실패", error);
        });

    } else {

        $.ajax({
            type: "post",
            url: "/api/subscribe/" + toUserid,
            dataType: "json"
        }).done(res => {
            $(obj).text("구독취소");
            $(obj).toggleClass("blue");
        }).fail(error => {
            console.log("구독하기 실패", error);
        });
    }
}

// (2) 구독정보 모달 보기
function subscribeInfoModalOpen(pageUserId) {

    $(".modal-subscribe").css("display", "flex");

    $.ajax({
        type: "get",
        url: `/api/user/${pageUserId}/subscribe`,
        dataType: "json"
    }).done(res => {
        // console.log(res.data);
        //u는 SubscribeDto
        res.data.forEach((u) => {
            let item = getSubscribeModalItem(u);
            console.log(item);
            $("#subscribeModalList").append(item);
        })
    }).fail(error => {
        console.log("구독정보 불러오기 오류", error);
    });
}

function getSubscribeModalItem(u) {
    let item = `<div class="subscribe__item" id="subscribeModalItem-${u.id}">
<div class="subscribe__img">
<img src="/upload/${u.profileImageUrl}" onerror="this.src='/images/person.jpeg'"/>
</div>
<div class="subscribe__text">
<h2>${u.username}</h2>
</div>
<div class="subscribe__btn">`;

    //동일 유저가 아닐때 구독취소/구독하기 버튼이 보여진다.
    if (!u.equalUserState) {
        if (u.subscribeState) {
            item += `<button class="cta blue" onclick="toggleSubscribe(${u.id}, this)">구독취소</button>`
        } else {
            item += `<button class="cta" onclick="toggleSubscribe(${u.id}, this)">구독하기</button>`
        }
    }

    item += `
    </div>
</div>`;

    return item;

}


// (3) 구독자 정보 "모달"에서 구독하기, 구독취소 ===> 사용X (1)로 처리
/*function toggleSubscribeModal(obj) {
    if ($(obj).text() === "구독취소") {
        $(obj).text("구독하기");
        $(obj).toggleClass("blue");
    } else {
        $(obj).text("구독취소");
        $(obj).toggleClass("blue");
    }
}*/

// (4) 유저 프로파일 사진 변경 (완)
function profileImageUpload(pageUserId, principalId) {

    if (pageUserId != principalId) {
        return;
    }

    $("#userProfileImageInput").click();

    $("#userProfileImageInput").on("change", (e) => {
        let f = e.target.files[0];

        if (!f.type.match("image.*")) {
            alert("이미지를 등록해야 합니다.");
            return;
        }

        // 서버에 프로필 이미지 전송
        let profileImageForm = $("#userProfileImageForm")[0];

        //FormData 객체를 이용하면 form 태그의 피르와 그 값을 나타내는 일련의 키/밸류 쌍을 담을 수 있다.
        //일반 form 태그는 serialize() 사용하나 지금처럼 사진을 전송할 때는 FormData를 사용해야 함
        let formData = new FormData(profileImageForm);

        $.ajax({
            type: "put",
            url: `/api/user/${principalId}/profileImageUrl`,
            data: formData,
            contentType: false, //필수: 기본 값이 x www form urlencoded인데 그러면 파일 전송을 못 하므로 false 처리
            processData: false, //필수: contentType을 false로 주면 QueryString 타입으로 자동 설정되기 때문에 false 처리
            encType: "multipart/form-data",
            dataType: "json"
        }).done(res => {
            // 사진 전송 성공시 이미지 변경
            let reader = new FileReader();
            reader.onload = (e) => {
                $("#userProfileImage").attr("src", e.target.result);
            }
            reader.readAsDataURL(f); // 이 코드 실행시 reader.onload 실행됨.

        }).fail(error => {
            console.log("오류", error);
        });


    });
}


// (5) 사용자 정보 메뉴 열기 닫기
function popup(obj) {
    $(obj).css("display", "flex");
}

function closePopup(obj) {
    $(obj).css("display", "none");
}


// (6) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
function modalInfo() {
    $(".modal-info").css("display", "none");
}

// (7) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달
function modalImage(pageUserId, principalId) {
    $(".modal-image").css("display", "none");
}

// (8) 구독자 정보 모달 닫기
function modalClose() {
    $(".modal-subscribe").css("display", "none");
    location.reload();
}






