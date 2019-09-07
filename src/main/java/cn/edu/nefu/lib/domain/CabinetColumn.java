package cn.edu.nefu.lib.domain;

/**
 * @Classname CabinetColumn
 * @Description 书包柜的实体类
 * @auther daijiankun laptop
 * @create 2019-08-06 2:52 PM
 */

import java.util.concurrent.ConcurrentHashMap;

/**
 * 2019-08-09 4:10 PM
 * 柜子分类的依据是列，预约的是时候只能选择在所有柜子中随机一个或者在某一列中随机一个。
 * 类比商品的秒杀，我们可以认为柜子的每列都是一类商品，每个柜子是其中一件商品。
 * 这样书包柜的问题就转化成了普通的商品秒杀问题，随之数据库的设计也可以实现。
 */
public class CabinetColumn {

    private Long id;
    private int floor; //所在楼层
    private int col; //所在列数
    private int count; //每列的柜子数

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "CabinetColumn{" +
                "id=" + id +
                ", floor=" + floor +
                ", col=" + col +
                ", count=" + count +
                '}';
    }
}
