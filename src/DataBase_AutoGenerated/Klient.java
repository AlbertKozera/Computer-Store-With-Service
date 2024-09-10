package DataBase_AutoGenerated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@Entity
@IdClass(KlientPK.class)
public class Klient {
    private int idKlient;
    private int osobaIdOsoba;

    @Id
    @Column(name = "idKlient")
    public int getIdKlient() {
        return idKlient;
    }

    public void setIdKlient(int idKlient) {
        this.idKlient = idKlient;
    }

    @Id
    @Column(name = "Osoba_idOsoba")
    public int getOsobaIdOsoba() {
        return osobaIdOsoba;
    }

    public void setOsobaIdOsoba(int osobaIdOsoba) {
        this.osobaIdOsoba = osobaIdOsoba;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klient klient = (Klient) o;
        return idKlient == klient.idKlient &&
                osobaIdOsoba == klient.osobaIdOsoba;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKlient, osobaIdOsoba);
    }
}
