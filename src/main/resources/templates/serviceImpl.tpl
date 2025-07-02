package {{package}}.service.impl;

import {{package}}.model.{{EntityName}};
import {{package}}.repository.{{EntityName}}Repository;
import {{package}}.service.{{EntityName}}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class {{EntityName}}ServiceImpl implements {{EntityName}}Service {

    @Autowired
    private {{EntityName}}Repository {{EntityName}}Repository;

}
