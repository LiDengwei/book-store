package com.book.web.webUtil;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Happy on 2018/4/2.
 */
public class EmailUtil {

    public static boolean sendEmailWithSender(String email,String code,String sender,String senderPwd) throws  Exception{
        try {
            // 创建邮件配置
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", "smtp.fastmail.com"); // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
            props.setProperty("mail.smtp.ssl.enable", "true");// 开启ssl
            props.setProperty("mail.smtp.starttls.enable", "true");


            // 根据邮件配置创建会话，注意session别导错包
            Session session = Session.getDefaultInstance(props);
            // 开启debug模式，可以看到更多详细的输入日志
            session.setDebug(false);
            //创建邮件
            MimeMessage message = createEmail(session,email,code);
            //获取传输通道
            Transport transport = session.getTransport();
            transport.connect("smtp.fastmail.com", sender, senderPwd);//8p6kjgt7n2apad3q
            //连接，并发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }


//    public static boolean sendEmailNew(String email,String code) throws  Exception{
//        try {
//            // 创建邮件配置
//            Properties props = new Properties();
//            props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
//            props.setProperty("mail.smtp.host", "smtp.fastmail.com"); // 发件人的邮箱的 SMTP 服务器地址
//            props.setProperty("mail.smtp.port", "465");
//            props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
//            props.setProperty("mail.smtp.ssl.enable", "true");// 开启ssl
//            props.setProperty("mail.smtp.starttls.enable", "true");
//
//
//            // 根据邮件配置创建会话，注意session别导错包
//            Session session = Session.getDefaultInstance(props);
//            // 开启debug模式，可以看到更多详细的输入日志
//            session.setDebug(true);
//            //创建邮件
//            MimeMessage message = createEmail(session,email,code);
//            //获取传输通道
//            Transport transport = session.getTransport();
//            transport.connect("smtp.fastmail.com", "secretworth@fastmail.com", "xbgjd7ndpuagv98z");//8p6kjgt7n2apad3q
//            //连接，并发送邮件
//            transport.sendMessage(message, message.getAllRecipients());
//            transport.close();
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//
//    }


    private static MimeMessage createEmail(Session session,String email,String code) throws Exception {
        // 根据会话创建邮件
        MimeMessage msg = new MimeMessage(session);
        // address邮件地址, personal邮件昵称, charset编码方式
        InternetAddress fromAddress = new InternetAddress("secret@secretworth.com", "Secret", "utf-8");
        // 设置发送邮件方
        msg.setFrom(fromAddress);
        InternetAddress receiveAddress = new InternetAddress(email, "", "utf-8");
        // 设置邮件接收方
        msg.setRecipient(MimeMessage.RecipientType.TO, receiveAddress);
        // 设置邮件标题
        msg.setSubject("email verify code:"+ code, "utf-8");
        msg.setText("您好，您此次操作的邮箱验证码：" + code + "，有效期10分钟！");
        // 设置显示的发件时间
        msg.setSentDate(new Date());
        // 保存设置
        msg.saveChanges();
        return msg;
    }



    public static String createVerificationCode(int verificationCodeLength)
    {
        //    所有候选组成验证码的字符，可以用中文
        String[] verificationCodeArrary={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };
        String verificationCode = "";
        Random random = new Random();
        //此处是生成验证码的核心了，利用一定范围内的随机数做为验证码数组的下标，循环组成我们需要长度的验证码，做为页面输入验证、邮件、短信验证码验证都行
        for(int i=0;i<verificationCodeLength;i++){verificationCode += verificationCodeArrary[random.nextInt(verificationCodeArrary.length)];}
        return verificationCode;
    }

    private static Properties props = new Properties();
    static {
        props.put("mail.smtp.host", "smtp.fastmail.com");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.user", "secret@secretworth.com");
        props.put("mail.smtp.password", "4hk7xmkyafpkqsru");
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.port", 465);
    }
    //管理员向企业手动发发邮件
    public static boolean sentEmailxx(String to,String title,String content){
        try {
            Session session = Session.getInstance(props);//创建邮件会话
            MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象

            message.setFrom(new InternetAddress("secret@secretworth.com"));//设置发件人的地址
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));//设置收件人,并设置其接收类型为TO
            //设置信件内容
            //message.setText(mailContent); //发送 纯文本 邮件 TODO
            message.setSubject(title);//设置标题
            message.setContent(content, "text/html;charset=utf-8"); //发送HTML邮件，内容样式比较丰富
            message.setSentDate(new Date());//设置发信时间
            message.saveChanges();//存储邮件信息

            //发送邮件
            Transport transport = session.getTransport("smtp");
            transport.connect("secret@secretworth.com", "4hk7xmkyafpkqsru");
            transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
//        try {
//            boolean ret = sendEmailNew("431250498@qq.com", "654321");
//            if (ret){
//                System.out.println("send success");
//            }else{
//                System.out.println("send fail");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }
}
