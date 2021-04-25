package com.sml.sn.service.impl;

import com.sml.sn.entity.Type;
import com.sml.sn.mapper.TypeMapper;
import com.sml.sn.service.ITypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sn
 * @since 2021-03-29
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

}
