package com.app.byeolbyeolsseudam.domain.comment;

import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class CommentDTO {
    private Long commentId;
    private String commentContent;
    private String commentFileName;
    private String commentFilePath;
    private String commentFileUuid;
    private Long memberId;
    private String memberName;
    private String memberProfileName;
    private String memberProfilePath;
    private String memberProfileUuid;
    private Long boardId;
    private String boardTitle;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @QueryProjection
    public CommentDTO(Long commentId, String commentContent, Long memberId, Long boardId, String boardTitle, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.memberId = memberId;
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @QueryProjection
    public CommentDTO(Long commentId, String commentContent, String commentFileName, String commentFilePath, String commentFileUuid, Long memberId, String memberName, String memberProfileName, String memberProfilePath, String memberProfileUuid, Long boardId, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentFileName = commentFileName;
        this.commentFilePath = commentFilePath;
        this.commentFileUuid = commentFileUuid;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberProfileName = memberProfileName;
        this.memberProfilePath = memberProfilePath;
        this.memberProfileUuid = memberProfileUuid;
        this.boardId = boardId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Comment toEntity(){
        return Comment.builder()
                .commentContent(commentContent)
                .commentFileName(commentFileName)
                .commentFilePath(commentFilePath)
                .commentFileUuid(commentFileUuid)
                .build();
    }
}
