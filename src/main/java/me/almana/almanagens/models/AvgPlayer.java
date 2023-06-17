package me.almana.almanagens.models;

import java.util.ArrayList;
import java.util.List;

public class AvgPlayer {

    private String uuid;
    private List<String> genIdList;

    public AvgPlayer(String uuid) {
        this.uuid = uuid;
        this.genIdList = new ArrayList<String>();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<String> getGenIdList() {
        return genIdList;
    }

    public void setGenIdList(List<String> genIdList) {
        this.genIdList = genIdList;
    }
}
