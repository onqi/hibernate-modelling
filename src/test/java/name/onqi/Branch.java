package name.onqi;

import javax.persistence.Column;

/**
 * @author onqi
 */
public class Branch {
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "unit_id")
    private Long unitId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "device_id")
    private Long deviceId;

    public Branch() {/* for sql serialization */}

    public Branch(Long companyId, Long unitId, Long userId, Long deviceId) {
        this.companyId = companyId;
        this.unitId = unitId;
        this.userId = userId;
        this.deviceId = deviceId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Branch branch = (Branch) o;

        if (companyId != null ? !companyId.equals(branch.companyId) : branch.companyId != null) return false;
        if (deviceId != null ? !deviceId.equals(branch.deviceId) : branch.deviceId != null) return false;
        if (unitId != null ? !unitId.equals(branch.unitId) : branch.unitId != null) return false;
        if (userId != null ? !userId.equals(branch.userId) : branch.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = companyId != null ? companyId.hashCode() : 0;
        result = 31 * result + (unitId != null ? unitId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (deviceId != null ? deviceId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "companyId=" + String.format("%4s ", companyId) +
                ", unitId=" + String.format("%4s ", unitId) +
                ", userId=" + String.format("%4s ", userId) +
                ", deviceId=" + String.format("%4s ", deviceId) +
                '}';
    }
}
