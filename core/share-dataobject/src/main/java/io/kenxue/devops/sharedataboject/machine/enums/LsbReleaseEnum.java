package io.kenxue.devops.sharedataboject.machine.enums;

public enum LsbReleaseEnum {

    Ubuntu("ubuntu","Distributor ID: Ubuntu"),
    Debian("debian","Distributor ID: Debian"),
    CentOS("centos","Distributor ID: CentOS");

    private String name;
    private String response;

    LsbReleaseEnum(String name,String response) {
        this.name = name;
        this.response = response;
    }

    public String getName() {
        return name;
    }

    public String getResponse() {
        return response;
    }

    public static LsbReleaseEnum get(String response){
        for (LsbReleaseEnum lsbReleaseEnum : values()) {
            if (lsbReleaseEnum.getResponse().equals(response)){
                return lsbReleaseEnum;
            }
        }
        throw new RuntimeException(String.format("无法匹配 response=%s 的该发行版",response));
    }
}
