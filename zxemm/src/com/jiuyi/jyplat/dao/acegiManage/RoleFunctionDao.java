package com.jiuyi.jyplat.dao.acegiManage;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.jiuyi.jyplat.entity.system.RoleFunction;
import com.jiuyi.jyplat.entity.system.SysAction;
import com.jiuyi.jyplat.util.DBAction;
import com.jiuyi.jyplat.util.HibernateEntityDao;

@Repository
public class RoleFunctionDao extends HibernateEntityDao<RoleFunction> {

}
