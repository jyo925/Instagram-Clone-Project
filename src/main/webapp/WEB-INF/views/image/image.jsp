<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<main class="main">
    <section class="container">
        <!--전체 리스트 시작-->
        <article class="story-list" id="image">

            <div class="story-list__item">
                <div class="sl__item__header">

                    <div>
                        <img class="profile-image" src="/upload/d80c3e72-de45-4ac9-842c-737bff5b9030_40.jpg"
                             onerror="this.src='/images/person.jpeg'">
                    </div>
                    <div>ssar</div>
                    <div>
                        <button style="margin-left: 500px;" type="button" onclick="addComment(24)">삭제
                        </button>
                    </div>
                </div>

                <div class="sl__item__img">
                    <img src="/upload/f9218410-df48-4bba-9f6e-5794939ea0b0_1.png">
                </div>

                <div class="sl__item__contents">
                    <div class="sl__item__contents__icon">

                        <button><i class="fas fa-heart active" id="storyLikeIcon-24" onclick="toggleLike(24)"></i>
                        </button>
                    </div>

                    <span class="like"><b id="storyLikeCount-24">1</b>likes</span>

                    <div class="sl__item__contents__content">
                        <p></p>
                    </div>

                    <div id="storyCommentList-24">
                        <div class="sl__item__contents__comment" id="storyCommentItem-19">
                            <p>
                                <b>jyo925 :</b> dd
                            </p>
                            <button onclick="deleteComment(19)">
                                <i class="fas fa-times"></i>
                            </button>
                        </div>
                    </div>

                    <div class="sl__item__input">
                        <input type="text" placeholder="댓글 달기..." id="storyCommentInput-24">
                        <button type="button" onclick="addComment(24)">게시</button>
                    </div>

                </div>
            </div>


        </article>
    </section>
</main>
<script src="/js/story.js"></script>
</body>
</html>
