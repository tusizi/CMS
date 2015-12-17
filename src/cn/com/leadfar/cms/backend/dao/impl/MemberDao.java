package cn.com.leadfar.cms.backend.dao.impl;

import cn.com.leadfar.cms.backend.model.Admin;
import cn.com.leadfar.cms.backend.model.Member;

/**
 * Created by twer on 11/28/15.
 */
public interface MemberDao {
    public void addMember(Member member);
    public Member findMemberByNickname(String nickname);
}
