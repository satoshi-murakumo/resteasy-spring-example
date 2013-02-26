package example.application.model.dao.db;

import example.application.model.dao.db.dto.MEmployee;

import java.math.BigDecimal;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

/**
 */
@org.seasar.doma.AnnotateWith(annotations = {
    @org.seasar.doma.Annotation(target = org.seasar.doma.AnnotationTarget.CLASS, type = org.springframework.stereotype.Repository.class),
    @org.seasar.doma.Annotation(target = org.seasar.doma.AnnotationTarget.CONSTRUCTOR, type = javax.inject.Inject.class)
})
@Dao
public interface MEmployeeDao {

    /**
     * @param employeeId
     * @return the MEmployee entity
     */
    @Select
    MEmployee selectById(BigDecimal employeeId);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(MEmployee entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(MEmployee entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(MEmployee entity);
}
