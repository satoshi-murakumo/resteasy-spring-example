package example.application.model.dao.db;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import example.application.model.dao.db.dto.Employee;

/**
 */
@org.seasar.doma.AnnotateWith(annotations = {
    @org.seasar.doma.Annotation(target = org.seasar.doma.AnnotationTarget.CLASS, type = org.springframework.stereotype.Repository.class),
    @org.seasar.doma.Annotation(target = org.seasar.doma.AnnotationTarget.CONSTRUCTOR, type = javax.inject.Inject.class)
})
@Dao
public interface EmployeeDao {

    /**
     * @param employeeId
     * @return the Employee entity
     */
    @Select
    Employee selectById(BigDecimal employeeId);

    /**
     * @param employeeId
     * @param versionNo
     * @return the Employee entity
     */
    @Select(ensureResult = true)
    Employee selectByIdAndVersion(BigDecimal employeeId, BigDecimal versionNo);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(Employee entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(Employee entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(Employee entity);

    /**
     * @return all Employees
     */
    @Select
    List<Employee> selectAll();
}
