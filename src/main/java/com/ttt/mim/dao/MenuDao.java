package com.ttt.mim.dao;

import com.ttt.mim.domain.Menu;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface MenuDao {
    /*
     *根据id查询菜单列表
     * @param id 菜单id
     * @return 菜单详情
     */
   Menu getById(Integer id);
    /*
     *根据查询条件查询菜单列表
     * @param map查询条件
     * @return 菜单列表
     */
     List<Menu> query(Map<String, Object> param);


    /*
     *根据菜单id删除菜单
     * @param id 菜单id
     * @return 删除个数
     */
     int delete(Integer id);

    /*批量删除
     *根据菜单id删除菜单
     * @param id 菜单id组
     * @return 删除个数
     */
     int batchDelete(Integer[] ids);


    /*
     *添加菜单信息
     *@param menu 菜单信息
     * @return 添加后的菜单信息
     */
     int add(Menu menu);

    /*
     *修改菜单信息
     *@param menu 菜单信息
     * @return 修改后的菜单信息
     */
     int update(Menu menu);
     /**
     * 查询总记录
      * @return 总记录条数
     */
     int count();
}

