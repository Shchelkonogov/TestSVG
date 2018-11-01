package ru.tn.testSVG.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MnemonicC {

    private String objectId, svgName;

    public void doSomething() {
        //TODO реализовать выбор нужной мнемосхемы по введенным параметрам
        System.out.println("ok " + objectId);
        svgName = "img/mnemonic.svg?v2";
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
}
