package cn.originstar.yourjob.system.common.models;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class WithLogoModel extends StatableModel {

    private boolean hasLogo;

    // row logo'name:比如 test-row-logo.jpg
    private String rowLogoName;

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public boolean isHasLogo() {
        return hasLogo;
    }

    public void setHasLogo(boolean hasLogo) {
        this.hasLogo = hasLogo;
    }

    public String getRowLogoName() {
        return rowLogoName;
    }

    public void setRowLogoName(String rowLogoName) {
        this.rowLogoName = rowLogoName;
    }

}
