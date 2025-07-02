package {{package}}.controller;

import {{package}}.model.{{EntityName}};
import {{package}}.service.{{EntityName}}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class {{EntityName}}Controller {

    @Autowired
    private {{EntityName}}Service {{EntityName}}Service;

    
}
