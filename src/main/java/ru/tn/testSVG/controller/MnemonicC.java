package ru.tn.testSVG.controller;

import ru.tn.testSVG.beans.LoadSvgBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Objects;

/**
 * Контроллер jsf страницы
 */
@ManagedBean
@ViewScoped
public class MnemonicC {

    private String objectId, svgName, objectName;

    @EJB
    private LoadSvgBean bean;

    private String hello;

    /**
     * Инициализация загрузки мнемосхемы
     */
    public void initLoad() {
        System.out.println("load object: " + objectId);
        if (Objects.isNull(objectId)) {
            svgName = "/svg/error.svg";
        } else {
            if (objectId.equals("testNewFitch")) {
                svgName = "/img/testNewFitch.svg";
            } else {
                objectName = bean.getObjectName(objectId);
                if (Objects.nonNull(objectName)) {
                    objectName = " для " + objectName;
                }

                String fileName = bean.loadSvgName(objectId);
                if (Objects.isNull(fileName)) {
                    svgName = "/svg/error.svg";
                } else {
                    svgName = "/svg/" + fileName;
                }
            }
        }
        System.out.println("mnemonic file for object: " + objectId + " is " + svgName);
    }

    /**
     * Тестовый метод
     * Загружается из js кода по нажатию на элемент svg
     */
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

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
