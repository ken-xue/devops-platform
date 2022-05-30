package io.kenxue.devops.domain.factory.sys;

import io.kenxue.devops.domain.domain.sys.Menu;
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
