package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.comment.CommentRepository;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment write(String content, int imageId, int userId) {
        //방법 1
        //Comment 객체를 만들어서 insert하는 것보다 쿼리 작성하는 방법으로 진행
        //네이티브 쿼리는 Comment객체를 리턴 받을 수 없음
        //IllegalArgumentException: Modifying queries can only use void or int/Integer as return type! Offending method
//        commentRepository.mSave(content, imageId, userId); ---> X


        //방법2
        //객체 생성 시 ID 값만 담아서 INSERT 할 수 있다. findbyid 사용 X
        //id값만 빼서 comment 테이블에 넣어준다. 단, id값만 있는 Comment 객체를 리턴받게 된다.
        Image image = new Image();
        image.setId(imageId);
//        User user = new User();
//        user.setId(userId);

        //방법3
        //user는 id뿐만 아니라 이름도 필요하면 아래 방식으로
        User userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException("해당 유저 아이디를 찾을 수 없습니다."));

        Comment comment = new Comment();
        comment.setComment(content);
        comment.setImage(image);
        comment.setUser(userEntity);

        return commentRepository.save(comment);
    }

    @Transactional
    public void remove() {

    }
}
