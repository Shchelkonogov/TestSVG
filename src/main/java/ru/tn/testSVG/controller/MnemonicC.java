package ru.tn.testSVG.controller;

import ru.tn.testSVG.beans.LoadSvgBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.Objects;

//TODO этот класс надо дописывать тут много кода для тестирования новых фич
@ManagedBean
@ViewScoped
public class MnemonicC {

    private String objectId, svgName;

    @Inject
    private LoadSvgBean bean;

    private String hello;

    public void doSomething() {
        //TODO реализовать выбор нужной мнемосхемы по введенным параметрам
        System.out.println("load object: " + objectId);
        if (Objects.nonNull(objectId) && objectId.equals("123")) {
            svgName = "img/123.svg";
        } else {
            svgName = "img/" + bean.loadSvgName(objectId);
        }
        System.out.println("mnemonic file for object: " + objectId + " is " + svgName);
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
