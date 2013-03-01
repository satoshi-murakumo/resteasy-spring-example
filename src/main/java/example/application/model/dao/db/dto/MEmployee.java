package example.application.model.dao.db.dto;

import java.math.BigDecimal;
import org.joda.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

/**
 */
@Entity(listener = example.infrastructure.db.ConventinalEntityListener.class)
@Table(name = "M_EMPLOYEE")
public class MEmployee implements example.infrastructure.db.Conventional {

    /** */
    @Id
    @Column(name = "EMPLOYEE_ID")
    BigDecimal employeeId;

    /** */
    @Column(name = "EMPLOYEE_NAME")
    String employeeName;

    /** */
    @Column(name = "HIREDATE")
    LocalDateTime hiredate;

    /** */
    @Column(name = "SALARY")
    BigDecimal salary;

    /** */
    @Column(name = "CRTDATE")
    LocalDateTime crtdate;

    /** */
    @Column(name = "CRTEMPLCODE")
    String crtemplcode;

    /** */
    @Column(name = "CRTTERMID")
    String crttermid;

    /** */
    @Column(name = "UPDDATE")
    LocalDateTime upddate;

    /** */
    @Column(name = "UPDEMPLCODE")
    String updemplcode;

    /** */
    @Column(name = "UPDTERMID")
    String updtermid;

    /**
     * Returns the employeeId.
     *
     * @return the employeeId
     */
    public BigDecimal getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the employeeId.
     *
     * @param employeeId the employeeId
     */
    public void setEmployeeId(BigDecimal employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Returns the employeeName.
     *
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * Sets the employeeName.
     *
     * @param employeeName the employeeName
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * Returns the hiredate.
     *
     * @return the hiredate
     */
    public LocalDateTime getHiredate() {
        return hiredate;
    }

    /**
     * Sets the hiredate.
     *
     * @param hiredate the hiredate
     */
    public void setHiredate(LocalDateTime hiredate) {
        this.hiredate = hiredate;
    }

    /**
     * Returns the salary.
     *
     * @return the salary
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Sets the salary.
     *
     * @param salary the salary
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * Returns the crtdate.
     *
     * @return the crtdate
     */
    public LocalDateTime getCrtdate() {
        return crtdate;
    }

    /**
     * Sets the crtdate.
     *
     * @param crtdate the crtdate
     */
    public void setCrtdate(LocalDateTime crtdate) {
        this.crtdate = crtdate;
    }

    /**
     * Returns the crtemplcode.
     *
     * @return the crtemplcode
     */
    public String getCrtemplcode() {
        return crtemplcode;
    }

    /**
     * Sets the crtemplcode.
     *
     * @param crtemplcode the crtemplcode
     */
    public void setCrtemplcode(String crtemplcode) {
        this.crtemplcode = crtemplcode;
    }

    /**
     * Returns the crttermid.
     *
     * @return the crttermid
     */
    public String getCrttermid() {
        return crttermid;
    }

    /**
     * Sets the crttermid.
     *
     * @param crttermid the crttermid
     */
    public void setCrttermid(String crttermid) {
        this.crttermid = crttermid;
    }

    /**
     * Returns the upddate.
     *
     * @return the upddate
     */
    public LocalDateTime getUpddate() {
        return upddate;
    }

    /**
     * Sets the upddate.
     *
     * @param upddate the upddate
     */
    public void setUpddate(LocalDateTime upddate) {
        this.upddate = upddate;
    }

    /**
     * Returns the updemplcode.
     *
     * @return the updemplcode
     */
    public String getUpdemplcode() {
        return updemplcode;
    }

    /**
     * Sets the updemplcode.
     *
     * @param updemplcode the updemplcode
     */
    public void setUpdemplcode(String updemplcode) {
        this.updemplcode = updemplcode;
    }

    /**
     * Returns the updtermid.
     *
     * @return the updtermid
     */
    public String getUpdtermid() {
        return updtermid;
    }

    /**
     * Sets the updtermid.
     *
     * @param updtermid the updtermid
     */
    public void setUpdtermid(String updtermid) {
        this.updtermid = updtermid;
    }
}