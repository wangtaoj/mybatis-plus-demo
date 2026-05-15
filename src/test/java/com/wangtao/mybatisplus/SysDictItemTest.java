package com.wangtao.mybatisplus;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangtao.mybatisplus.dao.SysDictItemMapper;
import com.wangtao.mybatisplus.po.SysDictItem;
import com.wangtao.mybatisplus.service.SysDictItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author wangtao
 * Created at 2026-05-16
 */
@SpringBootTest
public class SysDictItemTest {

    @Autowired
    private SysDictItemMapper sysDictItemMapper;

    @Autowired
    private SysDictItemService sysDictItemService;

    @Test
    public void testInsertAndSelect() {
        SysDictItem item = new SysDictItem();
        item.setDictCode("test_code");
        item.setDictItemValue("test_value");
        item.setDictItemName("测试项");
        int rows = sysDictItemMapper.insert(item);
        Assertions.assertEquals(1, rows);
        Assertions.assertNotNull(item.getId());

        SysDictItem dbItem = sysDictItemMapper.selectById(item.getId());
        Assertions.assertNotNull(dbItem);
        Assertions.assertEquals("test_code", dbItem.getDictCode());
        Assertions.assertEquals("test_value", dbItem.getDictItemValue());
        Assertions.assertEquals("测试项", dbItem.getDictItemName());
        System.out.println(dbItem);

        sysDictItemMapper.deleteById(item.getId());
    }

    @Test
    public void testUpdate() {
        SysDictItem item = new SysDictItem();
        item.setDictCode("test_code2");
        item.setDictItemValue("test_value2");
        item.setDictItemName("测试项2");
        sysDictItemMapper.insert(item);

        item.setDictItemName("更新后的名称");
        int rows = sysDictItemMapper.updateById(item);
        Assertions.assertEquals(1, rows);

        SysDictItem dbItem = sysDictItemMapper.selectById(item.getId());
        Assertions.assertEquals("更新后的名称", dbItem.getDictItemName());

        sysDictItemMapper.deleteById(item.getId());
    }

    @Test
    public void testDelete() {
        SysDictItem item = new SysDictItem();
        item.setDictCode("test_code3");
        item.setDictItemValue("test_value3");
        item.setDictItemName("测试项3");
        sysDictItemMapper.insert(item);

        int rows = sysDictItemMapper.deleteById(item.getId());
        Assertions.assertEquals(1, rows);

        SysDictItem dbItem = sysDictItemMapper.selectById(item.getId());
        Assertions.assertNull(dbItem);
    }

    @Test
    public void testPage() {
        IPage<SysDictItem> page = sysDictItemMapper.selectPage(Page.of(1, 10), null);
        System.out.println("总记录数: " + page.getTotal());
        System.out.println("当前页数据: " + page.getRecords());
    }

    @Test
    public void testServiceListByDictCode() {
        SysDictItem item = new SysDictItem();
        item.setDictCode("gender_test");
        item.setDictItemValue("male");
        item.setDictItemName("男性");
        sysDictItemMapper.insert(item);

        List<SysDictItem> list = sysDictItemService.listByDictCode("gender_test");
        Assertions.assertFalse(list.isEmpty());
        Assertions.assertEquals("male", list.get(0).getDictItemValue());
        System.out.println(list);

        sysDictItemMapper.deleteById(item.getId());
    }

}