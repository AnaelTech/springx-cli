package {{package}}.model;

import jakarta.persistence.*;
{{#if lombok}}
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
{{/if}}

@Entity
@Table(name = "{{EntityName}}")
{{#if lombok}}
@Data
@NoArgsConstructor
@AllArgsConstructor
{{/if}}
public class {{EntityName}} {

    {{#each attributes}}
    {{#if isId}}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    {{/if}}
    private {{type}} {{name}};
    {{/each}}

}

