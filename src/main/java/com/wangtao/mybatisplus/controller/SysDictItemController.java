package com.wangtao.mybatisplus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangtao.mybatisplus.po.SysDictItem;
import com.wangtao.mybatisplus.service.SysDictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wangtao
 * Created at 2026-05-16
 */
@RestController
@RequestMapping("/sysDictItem")
public class SysDictItemController {

    @Autowired
    private SysDictItemService sysDictItemService;

    @GetMapping("/{id}")
    public SysDictItem getById(@PathVariable Integer id) {
        return sysDictItemService.getById(id);
    }

    @GetMapping("/list")
    public List<SysDictItem> listByDictCode(@RequestParam String dictCode) {
        return sysDictItemService.listByDictCode(dictCode);
    }

    @GetMapping("/page")
    public IPage<SysDictItem> page(@RequestParam(defaultValue = "1") Integer current,
                                   @RequestParam(defaultValue = "10") Integer size) {
        return sysDictItemService.page(Page.of(current, size));
    }

    @PostMapping
    public boolean save(@RequestBody SysDictItem sysDictItem) {
        return sysDictItemService.save(sysDictItem);
    }

    @PutMapping
    public boolean update(@RequestBody SysDictItem sysDictItem) {
        return sysDictItemService.updateById(sysDictItem);
    }

    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable Integer id) {
        return sysDictItemService.removeById(id);
    }

}