package {{package}}.service;

import {{package}}.model.{{EntityName}};
import {{package}}.repository.{{EntityName}}Repository;
import org.springframework.beans.factory.annotation.Autowired;


public interface {{EntityName}}Service {

    @Autowired
    private {{EntityName}}Repository {{EntityName}}Repository;

}
