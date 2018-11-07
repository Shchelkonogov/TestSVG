package ru.tn.testSVG.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Objects;

@ManagedBean
@ViewScoped
public class MnemonicC {

    private String objectId, svgName;

    private String hello;

    public void doSomething() {
        //TODO реализовать выбор нужной мнемосхемы по введенным параметрам
        System.out.println("ok " + objectId);
        if (Objects.nonNull(objectId) && objectId.equals("123")) {
            svgName = "img/123.svg";
        } else {
            svgName = "img/mnemonic.svg?v5";
        }
    }

    public void jsCall() {
        System.out.println("hello from svg for object " + objectId);
        hello = "Hello from svg!";
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getSvgName() {
        return svgName;
    }

    public void setSvgName(String svgName) {
        this.svgName = svgName;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
