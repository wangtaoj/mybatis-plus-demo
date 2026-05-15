package com.wangtao.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangtao.mybatisplus.dao.SysDictItemMapper;
import com.wangtao.mybatisplus.po.SysDictItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangtao
 * Created at 2026-05-16
 */
@Service
public class SysDictItemService {

    @Autowired
    private SysDictItemMapper sysDictItemMapper;

    public SysDictItem getById(Integer id) {
        return sysDictItemMapper.selectById(id);
    }

    public List<SysDictItem> listByDictCode(String dictCode) {
        return sysDictItemMapper.selectList(
            new LambdaQueryWrapper<SysDictItem>()
                .eq(SysDictItem::getDictCode, dictCode)
        );
    }

    public IPage<SysDictItem> page(Page<SysDictItem> page) {
        return sysDictItemMapper.selectPage(page, null);
    }

    public boolean save(SysDictItem sysDictItem) {
        return sysDictItemMapper.insert(sysDictItem) > 0;
    }

    public boolean updateById(SysDictItem sysDictItem) {
        return sysDictItemMapper.updateById(sysDictItem) > 0;
    }

    public boolean removeById(Integer id) {
        return sysDictItemMapper.deleteById(id) > 0;
    }

}