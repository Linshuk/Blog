package jmu.lsk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2024-04-28 22:22:32
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
