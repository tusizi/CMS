package cn.com.leadfar.cms.backend.dao;

import cn.com.leadfar.cms.backend.model.Admin;
import cn.com.leadfar.cms.backend.model.Member;
import cn.com.leadfar.cms.backend.vo.PageVO;

/**
 * Created by twer on 11/28/15.
 */
public interface MemberDao {
    public void addMember(Member member);
    public Member findMemberByNickname(String nickname);
    public Object findMemberById(int id);
    public void updateMember(Member member);
    public void updateMemberPassword(int id,String oldPassword,String newPassword );
    public PageVO findAllMembers();
    public void delMembers(String[] ids);
}
