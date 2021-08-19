/**
 2. 스토리 페이지
 (1) 스토리 로드하기
 (2) 스토리 스크롤 페이징하기
 (3) 좋아요, 안좋아요
 (4) 댓글쓰기
 (5) 댓글삭제
 */

// (1) 스토리 로드하기

let page = 0;

function storyLoad() {

    $.ajax({
        type: "get",
        url: `/api/image?page=${page}`,
        dataType: "json"
    }).done(res => {
        // console.log(res);
        res.data.forEach((image) => {
            let storyItem = getStoryItem(image);
            $("#storyList").append(storyItem);
        })
    }).fail(error => {
        console.log(error);
    });
}

storyLoad();


function getStoryItem(image) {
    let item = `<div class="story-list__item">
<div class="sl__item__header">
<div>
<img class="profile-image" src="/upload/${image.user.profileImageUrl}"
onerror="this.src='/images/person.jpeg'"/>
</div>
<div>${image.user.username}</div>
</div>

<div class="sl__item__img">
<img src="/upload/${image.postImageUrl}"/>
</div>

<div class="sl__item__contents">
<div class="sl__item__contents__icon">

<button>`;
    if (image.likeState) {
        item += `<i class="fas fa-heart active" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;
    } else {
        item += `<i class="fa-heart far" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;
    }

    item +=
        `</button>
</div>

<span class="like"><b id="storyLikeCount-${image.id}">${image.likeCount}</b>likes</span>

<div class="sl__item__contents__content">
<p>${image.caption}</p>
</div>

<div id="storyCommentList-${image.id}">
    <div class="sl__item__contents__comment" id="storyCommentItem-1">
        <p>
            <b>Lovely :</b> 부럽습니다.
        </p>

        <button>
            <i class="fas fa-times"></i>
        </button>
    </div>
</div>

<div class="sl__item__input">
<input type="text" placeholder="댓글 달기..." id="storyCommentInput-${image.id}"/>
<button type="button" onClick="addComment(${image.id})">게시</button>
</div>

</div>
</div>`;

    return item;
}

// (2) 스토리 스크롤 페이징하기
$(window).scroll(() => {
    /*
        console.log("윈도 스크롤 탑: ", $(window).scrollTop());
        console.log("문서의 높이: ", $(document).height());
        console.log("윈도의 높이: ", $(window).height());
    */

    //문서의 높이 - 윈도의 높이 = 윈도 스크롤 탑
    let checkNum = $(window).scrollTop() - ($(document).height() - $(window).height());
    // console.log(checkNum);
    if (checkNum < 1 && checkNum > -1) {
        page++;
        storyLoad();
    }


});


// (3) 좋아요, 좋아요 취소
function toggleLike(imageId) {
    let likeIcon = $(`#storyLikeIcon-${imageId}`);
    let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
    let likeCount = Number(likeCountStr);

    if (likeIcon.hasClass("far")) { //좋아요

        $.ajax({
            type: "post",
            url: `/api/image/${imageId}/likes`,
            dataType: "json"
        }).done(res => {
            likeCount += 1;
            $(`#storyLikeCount-${imageId}`).text(likeCount);

            likeIcon.addClass("fas");
            likeIcon.addClass("active");
            likeIcon.removeClass("far");
        }).fail(error => {
            console.log("오류", error);
        });


    } else { //좋아요 취소

        $.ajax({
            type: "delete",
            url: `/api/image/${imageId}/likes`,
            dataType: "json"
        }).done(res => {
            likeCount -= 1;
            $(`#storyLikeCount-${imageId}`).text(likeCount);

            likeIcon.removeClass("fas");
            likeIcon.removeClass("active");
            likeIcon.addClass("far");
        }).fail(error => {
            console.log("오류", error);
        });
    }
}

// (4) 댓글쓰기
function addComment(imageId) {

    let commentInput = $(`#storyCommentInput-${imageId}`);
    let commentList = $(`#storyCommentList-${imageId}`);

    let data = {
        imageId: imageId,
        content: commentInput.val()
    }

    if (data.content === "") {
        alert("댓글을 작성해주세요!");
        return;
    }

    $.ajax({
        type: "post",
        url: `/api/comment`,
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    }).done(res => {
        console.log("성공", res);
    }).fail(error => {
        console.log("오류", error);
    });

    let content = `
			  <div class="sl__item__contents__comment" id="storyCommentItem-2""> 
			    <p>
			      <b>GilDong :</b>
			      댓글 샘플입니다.
			    </p>
			    <button><i class="fas fa-times"></i></button>
			  </div>
	`;
    // commentList.prepend(content);
    commentList.append(content);
    commentInput.val("");
}

// (5) 댓글 삭제
function deleteComment() {

}







