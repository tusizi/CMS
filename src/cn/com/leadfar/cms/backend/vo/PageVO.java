package cn.com.leadfar.cms.backend.vo;

import java.util.List;

/**
 * Created by tusizi on 2015/11/13.
 */
public class PageVO {
    private int total;
    private List datas;

    public List getDatas() {
        return datas;
    }

    public void setDatas(List datas) {
        this.datas = datas;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


}
