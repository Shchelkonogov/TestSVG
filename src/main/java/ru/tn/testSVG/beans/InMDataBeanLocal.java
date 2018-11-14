package ru.tn.testSVG.beans;

import ru.tn.testSVG.model.MnemonicData;

import javax.ejb.Local;
import java.util.List;

@Local
public interface InMDataBeanLocal {

    List<MnemonicData> getData(String object);
}
