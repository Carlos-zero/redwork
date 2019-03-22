package com.example.demo;

import com.example.demo.Service.Impl.UserServiceImpl;
import com.example.demo.Service.UserService;

import java.sql.SQLException;

public class test {

    public static void main(String[] args) throws SQLException {
/*UserSqlDao userSqlDao = new UserSqlDaoImpl();
        User user = null;
        user=userSqlDao.login("angel","qwer");
        System.out.println(user.toString());*/

//        String log=userSqlDao.updateData("qwer","123456@qq.com","99999","angel");
//        String log=userSqlDao.updateRole("angel","god");
//        String log=userSqlDao.insertUser("test","123","12350@qq.com","911","admin");
//        String log=userSqlDao.deleteUser("test1");
//        System.out.println(log);
        UserService userService=new UserServiceImpl();
//        userService.getUserLogin("test","123");
//        User user=((UserServiceImpl) userService).getUser();
//        System.out.println(user.toString());

//        String log=userService.getAdminResUpData("123","9999@qq.com","0000","test");
//        String log=userService.getGodResUpRole("test","god");
        String log=userService.getGodResAddUser("ilike","1234","8888@qq.com","110","user");
//        String log=userService.getGodResDelUser("test");
        System.out.println(log);






    }
}

/**
 * 权限检查切面 * 根据用户原有的权限，与目标方法的权限进行匹配 * @author yangguang *
 */
//public class AccessTargetObject {
//    //用户本身的权限
//    private List<Privilege> userPrivilege = new ArrayList<Privilege>();
//
//    public List<Privilege> getUserPrivilege() {
//        return userPrivilege;
//    }
//
//    // 环绕通知
//    public Object accessMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//// /** * 得到目标类的class形式 * 得到目标方法 */
//        Class targetClass = joinPoint.getTarget().getClass();
//        String targetMethodName = joinPoint.getSignature().getName();
//        String methodAccess = AnnotationParse.parse(targetClass, targetMethodName);
//        boolean flage = false;
//        for (Privilege privilege : userPrivilege) {
//            if (methodAccess.equals(privilege.getName())) {
//                flage = true;
//                break;
//            }
//        }
//        if (flage) {
//            return joinPoint.proceed();
//        } else {
//            System.out.println("权限不足");
//            return null;
//        }
//    }
//}