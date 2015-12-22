package cn.com.leadfar.cms.backend.dao.impl;

import cn.com.leadfar.cms.backend.dao.MemberDao;
import cn.com.leadfar.cms.backend.model.Member;
import cn.com.leadfar.cms.backend.vo.PageVO;
import cn.com.leadfar.cms.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public Member findMemberById(int id) {
       return (Member) findById(Member.class,id);
    }

    @Override
    public void updateMember(Member member) {
        update(member);
    }

    @Override
    public void updateMemberPassword(int id, String oldPassword, String newPassword) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            Member member = (Member) session.selectOne(Member.class.getName() + ".findById", id);
            if (oldPassword.equals(member.getPassword())){
                member.setPassword(newPassword);
                session.update(Member.class.getName()+".updatePassword",member);
            }else {
                throw new RuntimeException("新密码与旧密码不匹配");
            }
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
            throw new RuntimeException(e.getMessage(),e); //ÖØÐÂÅ×³öÒì³££¬ÒÔ±ã¿Í»§¶Ë¿ÉÒÔ´¦Àí´ËÒì³££¬¸øÓÃ»§ÌáÊ¾ÐÅÏ¢
        } finally{
            session.close();
        }
    }
    @Override
    public PageVO findAllMembers() {
        Map params = new HashMap();
        return findPaginated(Member.class.getName()+".findAllMembers",params);
    }

    @Override
    public void delMembers(String[] ids) {
        del(Member.class,ids);
    }
}
