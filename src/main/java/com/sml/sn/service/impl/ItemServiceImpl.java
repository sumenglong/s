package com.sml.sn.service.impl;

import com.sml.sn.entity.Item;
import com.sml.sn.mapper.ItemMapper;
import com.sml.sn.service.IItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sn
 * @since 2021-03-22
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

}
