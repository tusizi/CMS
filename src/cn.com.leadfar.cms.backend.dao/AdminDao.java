package cn.com.leadfar.cms.backend.dao;

import cn.com.leadfar.cms.backend.model.Admin;

/**
 * Created by twer on 11/28/15.
 */
public interface AdminDao {
    public Admin findAdminByUsername(String username);
}
