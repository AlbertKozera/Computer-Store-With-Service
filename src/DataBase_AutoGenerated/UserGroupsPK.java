package DataBase_AutoGenerated;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UserGroupsPK implements Serializable {
    private int idOsoba;
    private int groupId;

    @Column(name = "idOsoba")
    @Id
    public int getIdOsoba() {
        return idOsoba;
    }

    public void setIdOsoba(int idOsoba) {
        this.idOsoba = idOsoba;
    }

    @Column(name = "group_id")
    @Id
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroupsPK that = (UserGroupsPK) o;
        return idOsoba == that.idOsoba &&
                groupId == that.groupId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOsoba, groupId);
    }
}
