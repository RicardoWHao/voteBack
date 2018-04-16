package com.lingling.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailHelper {
    static ResourceBundle resource = ResourceBundle.getBundle("mail");
    // 发送邮件的账号
    public static String ownEmailAccount = resource.getString("username");
    // 发送邮件的密码------》授权码
    public static String ownEmailPassword = resource.getString("password");
    // 发送邮件的smtp 服务器 地址
    public static String myEmailSMTPHost = resource.getString("email.host");
    // 发送邮件对方的邮箱
    //public static String receiveMailAccount = "luoling_luo@163.com";

/*    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        // 设置邮件传输采用的协议smtp
        prop.setProperty("mail.transport.protocol", "smtp");
        // 设置发送人邮件服务器的smtp地址
        // 这里以网易的邮箱smtp服务器地址为例
        prop.setProperty("mail.smtp.host", myEmailSMTPHost);
        // 设置验证机制
        prop.setProperty("mail.smtp.auth", "true");

        // 创建对象回话跟服务器交互
        Session session = Session.getInstance(prop);
        // 会话采用debug模式
        session.setDebug(true);
        // 创建邮件对象
        Message message = createSimpleMail(session);

        Transport trans = session.getTransport();
        // 链接邮件服务器
        trans.connect(ownEmailAccount, ownEmailPassword);
        // 发送信息
        trans.sendMessage(message, message.getAllRecipients());
        // 关闭链接
        trans.close();
    }*/

    public static void sendEmail(String content,String address) throws Exception{
        Properties prop = new Properties();
        // 设置邮件传输采用的协议smtp
        prop.setProperty("mail.transport.protocol", "smtp");
        // 设置发送人邮件服务器的smtp地址
        // 这里以网易的邮箱smtp服务器地址为例
        prop.setProperty("mail.smtp.host", myEmailSMTPHost);
        // 设置验证机制
        prop.setProperty("mail.smtp.auth", "true");

        // 创建对象回话跟服务器交互
        Session session = Session.getInstance(prop);
        // 会话采用debug模式
        session.setDebug(true);
        // 创建邮件对象
        Message message = createSimpleMail(session,content,address);

        Transport trans = session.getTransport();
        // 链接邮件服务器
        trans.connect(ownEmailAccount, ownEmailPassword);
        // 发送信息
        trans.sendMessage(message, message.getAllRecipients());
        // 关闭链接
        trans.close();
    }
    /**
     * @Title: createSimpleMail
     * @Description: 创建邮件对象
     * @author: chengpeng
     * @param @param session
     * @param @return
     * @param @throws Exception    设定文件
     * @return Message    返回类型
     * @throws
     */
    public static Message createSimpleMail(Session session,String content,String address) throws Exception {
        MimeMessage message = new MimeMessage(session);
        // 设置发送邮件地址,param1 代表发送地址 param2 代表发送的名称(任意的) param3 代表名称编码方式
        message.setFrom(new InternetAddress(resource.getString("username"), resource.getString("email.from"), "utf-8"));
        // 代表收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(address, address, "utf-8"));
        // To: 增加收件人（可选）
        /*message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress("dd@receive.com", "USER_DD", "UTF-8"));
        // Cc: 抄送（可选）
        message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("ee@receive.com", "USER_EE", "UTF-8"));
        // Bcc: 密送（可选）
        message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));*/
        // 设置邮件主题
        message.setSubject("请查收验证码：");
        // 设置邮件内容
        message.setContent(content, "text/html;charset=utf-8");
        // 设置发送时间
        message.setSentDate(new Date());
        // 保存上面的编辑内容
        message.saveChanges();
        // 将上面创建的对象写入本地
        /*OutputStream out = new FileOutputStream("MyEmail.eml");
        message.writeTo(out);
        out.flush();
        out.close();*/
        return message;

    }

}