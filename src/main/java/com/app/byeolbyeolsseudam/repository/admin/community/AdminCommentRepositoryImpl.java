package com.app.byeolbyeolsseudam.repository.admin.community;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.comment.QCommentDTO;
import com.app.byeolbyeolsseudam.entity.comment.QComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.comment.QComment.comment;

@Repository
@RequiredArgsConstructor
public class AdminCommentRepositoryImpl implements AdminCommentCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommentDTO> showCommentList() {
        return jpaQueryFactory.select(new QCommentDTO(
                comment.commentId,
                comment.commentContent,
                comment.commentFileName,
                comment.commentFilePath,
                comment.commentFileUuid,
                comment.member.memberId,
                comment.member.memberName,
                comment.member.memberProfileName,
                comment.member.memberProfilePath,
                comment.member.memberProfileUuid,
                comment.board.boardId,
                comment.createdDate,
                comment.updatedDate
        )).from(comment).orderBy(comment.commentId.desc()).limit(10).fetch();
    }

    @Override
    public List<CommentDTO> searchComment(String keyword) {
        return jpaQueryFactory.select(new QCommentDTO(
                comment.commentId,
                comment.commentContent,
                comment.commentFileName,
                comment.commentFilePath,
                comment.commentFileUuid,
                comment.member.memberId,
                comment.member.memberName,
                comment.member.memberProfileName,
                comment.member.memberProfilePath,
                comment.member.memberProfileUuid,
                comment.board.boardId,
                comment.createdDate,
                comment.updatedDate
        )).from(comment)
                .where(comment.commentContent.contains(keyword))
                .orderBy(comment.commentId.desc())
                .fetch();
    }

    @Override
    public List<CommentDTO> searchCommentPaging(String keyword, Pageable pageable) {
        return jpaQueryFactory.select(new QCommentDTO(
                comment.commentId,
                comment.commentContent,
                comment.commentFileName,
                comment.commentFilePath,
                comment.commentFileUuid,
                comment.member.memberId,
                comment.member.memberName,
                comment.member.memberProfileName,
                comment.member.memberProfilePath,
                comment.member.memberProfileUuid,
                comment.board.boardId,
                comment.createdDate,
                comment.updatedDate
        )).from(comment)
                .where(comment.commentContent.contains(keyword))
                .orderBy(comment.commentId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
