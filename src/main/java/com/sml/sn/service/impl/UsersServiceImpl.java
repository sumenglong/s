package com.sml.sn.service.impl;

import com.sml.sn.entity.Users;
import com.sml.sn.mapper.UsersMapper;
import com.sml.sn.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sn
 * @since 2021-03-30
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
