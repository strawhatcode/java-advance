package com.hat.javaadvance.design_pattern.facade;

/**
 * 外观角色，封装了软件或服务的启动和关闭
 */
public class Facade {
    PhoneService phoneService;
    SMSService smsService;
    NETService netService;
    Wechat wechat;
    InputMethod inputMethod;

    public Facade() {
        phoneService = new PhoneService();
        smsService = new SMSService();
        netService = new NETService();
        wechat = new Wechat();
        inputMethod = new InputMethod();
    }

    public void open(){
        phoneService.open();
        smsService.open();
        netService.open();
        inputMethod.open();
    }
    public void stop(){
        phoneService.close();
        smsService.close();
        netService.close();
        inputMethod.close();
        if (wechat != null){
            wechat.close();
        }
    }
}
