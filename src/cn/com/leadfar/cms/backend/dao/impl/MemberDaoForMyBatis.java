package cn.com.leadfar.cms.backend.dao.impl;

import cn.com.leadfar.cms.backend.dao.AdminDao;
import cn.com.leadfar.cms.backend.model.Admin;
import cn.com.leadfar.cms.backend.model.Member;
import cn.com.leadfar.cms.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by tusizi on 2015/12/1.
 */
public class MemberDaoForMyBatis extends BaseDao implements MemberDao {
    @Override
    public void addMember(Member member) {
        add(member);
    }

    @Override
    public Member findMemberByNickname(String nickname) {
        Member member = null;

        //打开一个Session
        SqlSession session = MyBatisUtil.getSession();
        try {
            member = (Member)session.selectOne(Member.class.getName()+".findMemberByNickname",nickname);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            //关闭
            session.close();
        }
        return member;
    }
}
