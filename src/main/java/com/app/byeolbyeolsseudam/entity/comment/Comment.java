package com.app.byeolbyeolsseudam.entity.comment;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.Period;
import com.app.byeolbyeolsseudam.entity.board.Board;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COMMENT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Period {
    @Id @GeneratedValue @NotNull
    private Long commentId;
    @NotNull
    private String commentContent;
    private String commentFileName;
    private String commentFilePath;
    private String commentFileUuid;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public void changeMember(Member member){
        this.member = member;
    }

    public void changeBoard(Board board){
        this.board = board;
    }

    @Builder
    public Comment(String commentContent, String commentFileName, String commentFilePath, String commentFileUuid) {
        this.commentContent = commentContent;
        this.commentFileName = commentFileName;
        this.commentFilePath = commentFilePath;
        this.commentFileUuid = commentFileUuid;
    }

    public void update(CommentDTO commentDTO) {
        this.commentContent = commentDTO.getCommentContent();
        this.commentFileName = commentDTO.getCommentFileName();
        this.commentFilePath = commentDTO.getCommentFilePath();
        this.commentFileUuid = commentDTO.getCommentFileUuid();
    }
}
