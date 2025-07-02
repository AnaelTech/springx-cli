package {{package}}.repository;

import {{package}}.model.{{EntityName}};
import org.springframework.data.jpa.repository.JpaRepository;

public interface {{EntityName}}Repository extends JpaRepository<{{EntityName}}, Long> {

}
