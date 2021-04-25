package com.sml.sn.service.impl;

import com.sml.sn.entity.Comments;
import com.sml.sn.mapper.CommentsMapper;
import com.sml.sn.service.ICommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sn
 * @since 2021-03-23
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements ICommentsService {

}
