package com.hmdp.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    private static final String key = "CACHE_TYPE_LIST";
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Result getTypeList() {
        String typeJson = stringRedisTemplate.opsForValue().get(key);

        if(StrUtil.isNotBlank(typeJson)){
            List<ShopType> shopTypes = JSONUtil.toList(typeJson, ShopType.class);
            return Result.ok(shopTypes);
        }

        List<ShopType> shopTypeList = query().orderByAsc("sort").list();

        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shopTypeList));
        return Result.ok(shopTypeList);
    }
}
