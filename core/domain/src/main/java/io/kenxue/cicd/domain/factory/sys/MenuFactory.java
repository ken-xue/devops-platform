package io.kenxue.cicd.domain.factory.sys;

import io.kenxue.cicd.domain.domain.sys.Menu;
/**
 * 菜单表
 * @author mikey
 * @date 2021-12-03 17:22:00
 */
public class MenuFactory {
    public static Menu getMenu(){
        return new Menu();
    }
}
